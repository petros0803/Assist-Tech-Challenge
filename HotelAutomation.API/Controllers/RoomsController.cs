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

        [AllowAnonymous]
        [HttpGet("{id}")]
        public IActionResult GetRoomById(string id)
        {
            
            return Ok(roomService.GetById(id));
        }

        [AllowAnonymous]
        [HttpGet]
        public IActionResult GetRoomByStatusAsync([FromQuery]  RoomFilterModel filter )
        {
            return Ok(roomService.GetByStatus(filter));
        }

        [AllowAnonymous]
        [HttpDelete("{id}")]
        public IActionResult DeleteRoom(string id)
        {
            roomService.Delete(id);
            return Ok();
        }
        [AllowAnonymous]
        [HttpPut("{id}")]
        public IActionResult UpdateRoom(UpdateRoomRequestModel room, string id)
        {

            return Ok(roomService.Update(room, id));
        }
    }
}
