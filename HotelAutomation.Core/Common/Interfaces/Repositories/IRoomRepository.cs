using HotelAutomation.Domain.Entitities;
using System;
using System.Collections.Generic;
using System.Text;

namespace HotelAutomation.Application.Common.Interfaces.Repositories
{
    public interface IRoomRepository
    {
        Room Insert(Room room);

        Room Delete(Room room);

        Room Update(Room room);

        Room GetById(string id);

    }
}
