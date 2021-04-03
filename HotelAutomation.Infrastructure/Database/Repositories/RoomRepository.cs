using HotelAutomation.Domain.Entitities;
using HotelAutomation.Domain.Exceptions;
using MongoDB.Driver;
using System;
using System.Collections.Generic;
using System.Text;
using HotelAutomation.Application.Common.Interfaces.Repositories;
using HotelAutomation.Application.Common.Models.RoomModels;

namespace HotelAutomation.Infrastructure.Database.Repositories
{
    public class RoomRepository : IRoomRepository
    {
        private readonly IMongoCollection<Room> _room;

        public RoomRepository(IDatabaseSettings databaseSettings)
        {
            var client = new MongoClient(databaseSettings.ConnectionString);
            var database = client.GetDatabase(databaseSettings.DatabaseName);

            _room = database.GetCollection<Room>(databaseSettings.RoomsCollectionNumber);

        }

        public Room Insert(Room room)
        {
            _room.InsertOne(room);
            return room;
        }

        public Room Delete(string id)
        {
            _room.DeleteOne(u => u.Id == id);
            return null;
        }

        public Room GetById(string id)
        {
            var room = _room.Find<Room>(r => r.Id == id).FirstOrDefault();
            if (room == null)
            {
                throw new EntityNotFoundException();
            }
            return room;
        }


        public Room Update(Room room, string id)
        {
            _room.ReplaceOne(r => r.Id == id, room);
            return room;
        }

        public Room GetByStatus(bool status)
        {
            var room = _room.Find<Room>(r => r.Reserved == status).FirstOrDefault();
            if (room == null)
            {
                throw new EntityNotFoundException();
            }
            return room;
        }

        
    }
}
