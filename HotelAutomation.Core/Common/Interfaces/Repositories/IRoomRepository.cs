using HotelAutomation.Application.Common.Models.RoomModels;
using HotelAutomation.Domain.Entitities;
using System;
using System.Collections.Generic;
using System.Text;

namespace HotelAutomation.Application.Common.Interfaces.Repositories
{
    public interface IRoomRepository
    {
        Room Insert(Room room);

        Room Delete(string id);

        Room Update(Room room, string id);

        Room GetById(string id);

        Room GetByStatus(bool status);

    }
}
