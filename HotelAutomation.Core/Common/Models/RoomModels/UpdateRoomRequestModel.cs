using HotelAutomation.Application.Common.Models.UserModels;
using System;
using System.Collections.Generic;
using System.Text;

namespace HotelAutomation.Application.Common.Models.RoomModels
{
    public class UpdateRoomRequestModel 
    {
        public string Number { get; set; }

        public string Beds { get; set; }

       public UpdateFacilityRequestModel Facilities { get; set; }


    }
    public class UpdateFacilityRequestModel
    {
        public bool AC { get; set; }

        public bool TV { get; set; }

        public bool NSR { get; set; }
    }
    
}

