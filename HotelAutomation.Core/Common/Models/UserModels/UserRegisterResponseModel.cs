using System;
using System.Collections.Generic;
using System.Text;

namespace HotelAutomation.Application.Common.Models.UserModels
{
    public class UserRegisterResponseModel
    {
        public string Name { get; set; }

        public string Surname { get; set; }

        public string Email { get; set; }

        public string PhoneNumber { get; set; }
    }
}
