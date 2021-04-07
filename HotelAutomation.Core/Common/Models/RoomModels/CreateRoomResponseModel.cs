
using HotelAutomation.Application.Common.Models.Users;
using System;
using System.Collections.Generic;
using System.Text;

namespace HotelAutomation.Application.Common.Models.UserModels
{
    public class CreateRoomResponseModel : BaseIdResponseModel
    {
        //public string Id { get; set; }

        public string Number { get; set; }

        public int? Beds { get; set; }

        public Facility Facilities { get; set; }

        public double? Price { get; set; }



    }
    public class Facility
    {
        public bool Wifi { get; set; }

        public bool AC { get; set; }

        public bool TV { get; set; }

        public bool NSR { get; set; }

    }
}
