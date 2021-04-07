using HotelAutomation.Application.Common.Models.MailModel;
using HotelAutomation.Application.Common.Models.RervationModels;
using HotelAutomation.Application.Common.Models.RoomModels;
using HotelAutomation.Application.Services;
using HotelAutomation.Domain.Entitities;
using Microsoft.AspNetCore.Authorization;
using Microsoft.AspNetCore.Mvc;
using System.Threading.Tasks;

namespace HotelAutomation.API.Controllers
{
    [Route("api/[controller]")]
    [ApiController]
    [Authorize]
    public class EmailController : ControllerBase
    {
        private readonly MailService mailService;
        public EmailController (MailService mailService)
        {
            this.mailService = mailService;
        }
        /*[AllowAnonymous]
        [HttpPost("send")]
        public IActionResult SendMail(MailRequest mail)
        {
            mailService.SendEmail(mail);
            return Ok(); 
        }*/
    }
}
