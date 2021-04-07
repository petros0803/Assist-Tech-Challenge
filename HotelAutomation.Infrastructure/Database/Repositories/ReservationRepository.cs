using HotelAutomation.Application.Common.Interfaces.Repositories;
using HotelAutomation.Application.Common.Models.RervationModels;
using HotelAutomation.Domain.Entitities;
using HotelAutomation.Domain.Exceptions;
using MongoDB.Driver;
using System;
using System.Collections.Generic;
using System.Text;

namespace HotelAutomation.Infrastructure.Database.Repositories
{
    public class ReservationRepository : IReservationRepository
    {
            private readonly IMongoCollection<Reservation> _reservation;

            public ReservationRepository(IDatabaseSettings databaseSettings)
            {
                var client = new MongoClient(databaseSettings.ConnectionString);
                var database = client.GetDatabase(databaseSettings.DatabaseName);

                _reservation = database.GetCollection<Reservation>(databaseSettings.ReservationsColection);

            }

            public Reservation Insert(Reservation reservation)
            {
                _reservation.InsertOne(reservation);
                return reservation;
            }

            public Reservation Delete(string id)
            {
                _reservation.DeleteOne(u => u.Id == id);
                return null;
            }

            public Reservation GetById(string id)
            {
                var reservation = _reservation.Find<Reservation>(r => r.Id == id).FirstOrDefault();
                if (reservation == null)
                {
                    throw new EntityNotFoundException();
                }
                return reservation;
            }

        

        public Reservation Update(Reservation reservation, string id)
            {
                _reservation.ReplaceOne(r => r.Id == id, reservation);
                return reservation;
            }

            public List<Reservation> GetAllReservations()
            {
            var reservations = _reservation.AsQueryable();
            return reservations.ToList();
            }
           
        }
    }
