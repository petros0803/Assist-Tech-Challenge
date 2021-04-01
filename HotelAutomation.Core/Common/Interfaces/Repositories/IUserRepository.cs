using HotelAutomation.Domain.Entitities;

namespace HotelAutomation.Application.Common.Interfaces.Repositories
{
    public interface IUserRepository
    {
        User Insert(User user);

        User Delete(User user);

        User Update(User user);

        User GetById(string id);
        User GetByEmail(string email);
    }
}
