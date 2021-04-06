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
    public class ReservationsController : ControllerBase
    {
        private readonly ReservationService reservationService;

        public ReservationsController(ReservationService reservationService)
        {
            this.reservationService = reservationService;
        }
        [AllowAnonymous]
        [HttpPost]
        public IActionResult RegisterReservation(ReservationRequestModel reservation)
        {
            
            return Ok(reservationService.Add(reservation));
        }

        [AllowAnonymous]
        [HttpDelete("{id}")]
        public IActionResult DeleteRoom(string id)
        {
            reservationService.Delete(id);
            return Ok();
        }

        [AllowAnonymous]
        [HttpGet("{id}")]
        public IActionResult GetReservationVyId(string id)
        {

            return Ok(reservationService.GetById(id));
        }

        [AllowAnonymous]
        [HttpGet]
        public  IActionResult GetAllRooms()
        {
            return Ok(reservationService.GetAllReservations());
        }
        /*

        [AllowAnonymous]
        [HttpGet]
        public async Task<IActionResult> GetRoomByStatusAsync([FromQuery]  RoomFilterModel filter )
        {
            return Ok(await roomService.GetByStatusAsync(filter));
        }

        
        [AllowAnonymous]
        [HttpPut("{id}")]
        public IActionResult UpdateRoom(UpdateRoomRequestModel room, string id)
        {

            return Ok(roomService.Update(room, id));
        }*/
    }
}
