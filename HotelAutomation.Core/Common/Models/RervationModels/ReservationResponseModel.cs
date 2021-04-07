using HotelAutomation.Domain.Entitities;
using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations.Schema;
using System.Text;

namespace HotelAutomation.Application.Common.Models.RervationModels
{
    public class ReservationResponseModel
    {
        public string Id { get; set; }

        public string UserId { get; set; }

        public string RoomId { get; set; }

        public DateTime StartDate { get; set; }

        public DateTime ExpirationDate { get; set; }
    }
}
