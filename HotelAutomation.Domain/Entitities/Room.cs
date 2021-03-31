﻿using HotelAutomation.Domain.Common;
using System;
using System.Collections.Generic;
using System.Text;


namespace HotelAutomation.Domain.Entitities
{
    public class Room : BaseEntity
    {
        public string Number { get; set; }

        public string Beds { get; set; }

        public Facility Facilities { get; set; }


        public  class Facility 
        {
            public bool Wifi { get; set; }

            public bool AC { get; set; }

            public bool TV { get; set; }

            public bool NSR { get; set; }

        }
    }
}
