using HotelAutomation.Application.Common.Models.RervationModels;
using HotelAutomation.Domain.Entitities;
using System;
using System.Collections.Generic;
using System.Text;

namespace HotelAutomation.Application.Common.Interfaces.Repositories
{
    public interface IReservationRepository
    {
        Reservation Insert(Reservation reservation);

        Reservation Delete(string id);

        Reservation Update(Reservation reservation, string id);

        Reservation GetById(string id);

        List<Reservation> GetAllReservations();
    }
}
