using System;
using System.Collections.Generic;
using System.Text;
using System.ComponentModel.DataAnnotations;

namespace HotelAutomation.Application.Common.Models.Users
{
    public class LoginModel 
    {

        public string Email { get; set; }

        public string Password { get; set; }
    }
}
