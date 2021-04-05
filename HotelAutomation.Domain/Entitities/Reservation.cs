using HotelAutomation.Domain.Common;
using System;
using System.Collections.Generic;
using System.Text;

namespace HotelAutomation.Domain.Entitities
{
    public class Reservation : BaseEntity
    {
        public string UserId { get; set; }

        public string RoomId { get; set; }

        public DateTime StartDate { get; set; }

        public DateTime ExpirationDate { get; set; }
    }
}
