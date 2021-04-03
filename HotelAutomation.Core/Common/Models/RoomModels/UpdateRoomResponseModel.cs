using HotelAutomation.Application.Common.Models.Users;
using System;
using System.Collections.Generic;
using System.Text;

namespace HotelAutomation.Application.Common.Models.RoomModels
{
    public class UpdateRoomResponseModel
    {
        public string Id { get; set; }
        public string Number { get; set; }

        public string Beds { get; set; }

        public FacilityResponseModel Facilities { get; set; }

        public bool Reserved { get; set; }

    }
    public class FacilityResponseModel
    {
        public bool Wifi { get; set; }

        public bool AC { get; set; }

        public bool TV { get; set; }

        public bool NSR { get; set; }
    }

}

