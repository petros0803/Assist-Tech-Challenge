using HotelAutomation.Domain.Entitities;
using System.Collections.Generic;
using System.Threading.Tasks;

namespace HotelAutomation.Application.Common.Interfaces.Repositories
{
    public interface IRoomRepository
    {
        Room Insert(Room room);

        Room Delete(string id);

        Room Update(Room room, string id);

        Room GetById(string id);

        Task<List<Room>> GetByStatusAsync(bool status);

    }
}
