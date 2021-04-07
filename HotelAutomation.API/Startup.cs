using HotelAutomation.API.Middleware;
using HotelAutomation.Application.Common.Interfaces.Repositories;
using HotelAutomation.Application.Common.Models.Mappings;
using HotelAutomation.Application.Services;
using HotelAutomation.Infrastructure.Database;
using HotelAutomation.Infrastructure.Database.Repositories;
using Microsoft.AspNetCore.Authentication.JwtBearer;
using Microsoft.AspNetCore.Builder;
using Microsoft.AspNetCore.Hosting;
using Microsoft.Extensions.Configuration;
using Microsoft.Extensions.DependencyInjection;
using Microsoft.Extensions.Hosting;
using Microsoft.Extensions.Options;
using Microsoft.IdentityModel.Tokens;
using Microsoft.OpenApi.Models;
using System;
using System.Text;

namespace HotelAutomation.API
{
    public class Startup
    {
        public Startup(IConfiguration configuration)
        {
            Configuration = configuration;
        }

        public IConfiguration Configuration { get; }

        // This method gets called by the runtime. Use this method to add services to the container.
        public void ConfigureServices(IServiceCollection services)
        {
            services.AddControllers();
            services.Configure<DatabaseSettings>(
            Configuration.GetSection(nameof(DatabaseSettings)));

            services.AddSingleton<IDatabaseSettings>(sp =>
                sp.GetRequiredService<IOptions<DatabaseSettings>>().Value);

            services.AddScoped<UserService>();

            services.AddScoped<RoomService>();

            services.AddScoped<ReservationService>();

            services.AddScoped<IUserRepository, UserRepository>();

            services.AddScoped<IRoomRepository, RoomRepository>();

            services.AddScoped<IReservationRepository, ReservationRepository>();

            services.AddAutoMapper(config =>
            {
                config.AddProfile(new UserMappingProfiles());
            }) ;
            AddJwt(services, Configuration);
            services.AddSwaggerGen(s =>
            {
                s.AddSecurityDefinition("Bearer", new OpenApiSecurityScheme
                {
                    Description = "JWT Authorization header using the Bearer scheme (Example: 'Bearer YOUR_TOKEN')",
                    Name = "Authorization",
                    In = ParameterLocation.Header,
                    Type = SecuritySchemeType.ApiKey,
                    Scheme = "Bearer"
                });

                s.AddSecurityRequirement(new OpenApiSecurityRequirement
                {
                    {
                        new OpenApiSecurityScheme
                        {
                            Reference = new OpenApiReference
                            {
                                Type = ReferenceType.SecurityScheme,
                                Id = "Bearer"
                            }
                        },
                        Array.Empty<string>()
                    }
                });
            });
        }

        // This method gets called by the runtime. Use this method to configure the HTTP request pipeline.
        public void Configure(IApplicationBuilder app, IWebHostEnvironment env)
        {
            if (env.IsDevelopment())
            {
                app.UseDeveloperExceptionPage();
               
            }
            app.UseCors(corsPolicyBuilder =>
                   corsPolicyBuilder.AllowAnyOrigin()
                                    .AllowAnyMethod()
                                    .AllowAnyHeader()
            );

            app.UseMiddleware<ExceptionHandlingMiddleware>();

            app.UseSwagger();
            app.UseSwaggerUI(c => c.SwaggerEndpoint("/swagger/v1/swagger.json", "HotelAutomation.API v1"));

            app.UseHttpsRedirection();

            app.UseRouting();

            app.UseAuthentication();

            app.UseAuthorization();

            app.UseEndpoints(endpoints =>
            {
                endpoints.MapControllers();
            });

        }
        public static void AddJwt(IServiceCollection services, IConfiguration configuration)
        {
            var secretKey = configuration.GetValue<string>("JwtConfiguration:SecretKey");

            var key = Encoding.ASCII.GetBytes(secretKey);

            services.AddAuthentication(x =>
            {
                x.DefaultAuthenticateScheme = JwtBearerDefaults.AuthenticationScheme;
                x.DefaultChallengeScheme = JwtBearerDefaults.AuthenticationScheme;
            })
            .AddJwtBearer(x =>
            {
                x.RequireHttpsMetadata = false;
                x.SaveToken = true;
                x.TokenValidationParameters = new TokenValidationParameters
                {
                    ValidateIssuerSigningKey = true,
                    IssuerSigningKey = new SymmetricSecurityKey(key),
                    ValidateIssuer = false,
                    ValidateAudience = false
                };
            });
        }
    }
}
