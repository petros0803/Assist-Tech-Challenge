﻿
using HotelAutomation.Application.Common.Models.Users;
using System;
using System.Collections.Generic;
using System.Text;

namespace HotelAutomation.Application.Common.Models.UserModels
{
    class CreateRoomRespomseModel : BaseIdResponseModel
    {
        public string Number { get; set; }

        public string Beds { get; set; }

        public Facility Facilities { get; set; }


        
    }
    public class Facility
    {
        public bool Wifi { get; set; }

        public bool AC { get; set; }

        public bool TV { get; set; }

        public bool NSR { get; set; }

    }
}
