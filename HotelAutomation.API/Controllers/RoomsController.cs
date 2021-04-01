using HotelAutomation.Application.Services;
using HotelAutomation.Domain.Entitities;
using Microsoft.AspNetCore.Authorization;
using Microsoft.AspNetCore.Mvc;

namespace HotelAutomation.API.Controllers
{
    [Route("api/[controller]")]
    [ApiController]
    [Authorize]
    public class RoomsController : ControllerBase
    {
        private readonly RoomService roomService;

        public RoomsController(RoomService roomService)
        {
            this.roomService = roomService;
        }
        [AllowAnonymous]
        [HttpPost]
        public IActionResult RegisterRoom(Room room)
        {
            roomService.Add(room);
            return Ok();
        }
    }
}
