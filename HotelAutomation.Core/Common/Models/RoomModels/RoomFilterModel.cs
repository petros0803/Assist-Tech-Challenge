using System;
using System.Collections.Generic;
using System.Text;

namespace HotelAutomation.Application.Common.Models.RoomModels
{
    public class RoomFilterModel
    {
        public int? BedsNumber { get; set; }

        public bool? Wifi { get; set; }

        public bool? AC { get; set; }

        public bool? TV { get; set; }

        public bool? NSR { get; set; }

        public bool? status { get; set; }

        public double Price { get; set; }

    }
}

