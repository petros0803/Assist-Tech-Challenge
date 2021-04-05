using System;
using System.Collections.Generic;
using System.Text;

namespace HotelAutomation.Application.Common.Models.RervationModels
{
    public class ReservationRequestModel
    {
        public string UserId { get; set; }

        public string RoomId { get; set; }

        public DateTime StartDate { get; set; }

        public DateTime ExpirationDate { get; set; }
    }
}
