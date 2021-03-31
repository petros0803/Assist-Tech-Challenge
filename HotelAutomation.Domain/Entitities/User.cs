using HotelAutomation.Domain.Common;

namespace HotelAutomation.Domain.Entitities
{
    public class User : BaseEntity
    {
        public string Name { get; set; }

        public string Surname { get; set; }

        public string Email { get; set; }

        public string Password { get; set; }

        public string PhoneNumber { get; set; }

        public  string Role { get; set; }

        


    }

    public static class UserRole 
    {
        public const string Admin = "Admin";

        public const string User = "User";

        public const string Staff = "Staff";

    }
}
