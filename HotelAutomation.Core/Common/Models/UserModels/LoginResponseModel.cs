using System;
using System.Collections.Generic;
using System.Text;

namespace HotelAutomation.Application.Common.Models.UserModels
{
    public class LoginResponseModel
    {
        public string Name { get; set; }

        public string Surname { get; set; }

        public string PhoneNumber { get; set; }

        public string Token { get; set; }
    }
}
