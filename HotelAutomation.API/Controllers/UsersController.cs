using HotelAutomation.Application.Common.Models.Users;
using HotelAutomation.Application.Services;
using HotelAutomation.Domain.Entitities;
using HotelAutomation.Domain.Exceptions;
using Microsoft.AspNetCore.Authorization;
using Microsoft.AspNetCore.Mvc;

namespace HotelAutomation.API.Controllers
{
    [Route("api/[controller]")]
    [ApiController]
    [Authorize]
    public class UsersController : ControllerBase
    {
        private readonly UserService userService;

        public UsersController(UserService userService)
        {
            this.userService = userService;
        }
        [AllowAnonymous]
        [HttpPost]
        public IActionResult Register(User user)
        {
            
            return Ok(userService.Add(user));
        }

        [HttpGet("{id}")]
        public IActionResult Get(string id)
        {

            return Ok(userService.GetById(id));

        }

    

        [AllowAnonymous]
        [HttpPost("authenticate")]
        public IActionResult Authenticate( LoginModel model)
        {
            return Ok(userService.Authenticate(model));  
        }
    }
}
