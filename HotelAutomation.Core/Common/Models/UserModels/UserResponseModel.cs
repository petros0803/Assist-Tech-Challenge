using HotelAutomation.Application.Common.Models.Users;

namespace HotelAutomation.Application.Common.Models
{
    public class UserResponseModel  : BaseIdResponseModel
    {
        public string Name  { get; set; }

        public string Surname { get; set; }

        public string Email { get; set; }

        public string PhoneNumber { get; set; }
    }
}
