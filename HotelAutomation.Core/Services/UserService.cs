using AutoMapper;
using HotelAutomation.Application.Common.Exceptions;
using HotelAutomation.Application.Common.Interfaces.Repositories;
using HotelAutomation.Application.Common.Models;
using HotelAutomation.Application.Common.Models.UserModels;
using HotelAutomation.Application.Common.Models.Users;
using HotelAutomation.Domain.Entitities;
using Microsoft.Extensions.Configuration;
using Microsoft.IdentityModel.Tokens;
using System;
using System.IdentityModel.Tokens.Jwt;
using System.Security.Claims;
using System.Text;

namespace HotelAutomation.Application.Services
{
    public class UserService
    {
        private readonly IUserRepository userRepository;
        private readonly IConfiguration configuration;
        private readonly IMapper mapper;

        public UserService(IUserRepository userRepository, IMapper mapper, IConfiguration configuration)
        {
            this.userRepository = userRepository;
            this.configuration = configuration;
        }

        public void Add(User user)
        {
            userRepository.Insert(user);

        }

        public UserResponseModel GetById(string id)
        {

            var user = userRepository.GetById(id);
            
            return new UserResponseModel
            {
                Name = user.Name,
                Surname = user.Surname,
                Email = user.Email,
                PhoneNumber = user.PhoneNumber
            };
            
        }

        public void Register(LoginModel model)
        {
            var user = this.mapper.Map<User>(model);
        }

        public LoginResponseModel Authenticate(LoginModel model)
        {
            var user = userRepository.GetByEmail(model.Email);

            if(user.Password == model.Password)
            {

                return new LoginResponseModel
                {
                    Name = user.Name,
                    Surname = user.Surname,
                    PhoneNumber = user.PhoneNumber,
                    Token = GenerateToken(user)
                };

            }
          
            else
            {
                throw new BadRequestException();
            }
        }
        private string GenerateToken(User user)
        {
            var secretKey = configuration.GetValue<string>("JwtConfiguration:SecretKey");

            var key = Encoding.ASCII.GetBytes(secretKey);

            var tokenHandler = new JwtSecurityTokenHandler();

            var tokenDescriptor = new SecurityTokenDescriptor
            {
                Subject = new ClaimsIdentity(new[] {
                    new Claim(ClaimTypes.NameIdentifier, user.Id.ToString()),                  
                    new Claim(ClaimTypes.Email, user.Email)

                }),
                Expires = DateTime.UtcNow.AddDays(7),
                SigningCredentials = new SigningCredentials(new SymmetricSecurityKey(key), SecurityAlgorithms.HmacSha256Signature)
            };

            var token = tokenHandler.CreateToken(tokenDescriptor);

            return tokenHandler.WriteToken(token);
        }
    }
}
