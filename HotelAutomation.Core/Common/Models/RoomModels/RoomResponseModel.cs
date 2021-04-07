using System;
using System.Collections.Generic;
using System.Text;

namespace HotelAutomation.Application.Common.Models.RoomModels
{
    public class RoomResponseModel
    {
        public string Id { get; set; }
        public string Number { get; set; }

        public int? Beds { get; set; }

        public GetFacility Facilities { get; set; }

        public bool Reserved { get; set; }

        public double? Price { get; set; }

        public DateTime? StartDate { get; set; }

        public DateTime? ExpirationDate { get; set; }

    }
    public class GetFacility
    {
        public bool Wifi { get; set; }

        public bool AC { get; set; }

        public bool TV { get; set; }

        public bool NSR { get; set; }

    }
}

