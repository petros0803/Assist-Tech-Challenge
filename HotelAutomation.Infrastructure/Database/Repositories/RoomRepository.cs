using HotelAutomation.Domain.Entitities;
using HotelAutomation.Domain.Exceptions;
using MongoDB.Driver;
using System;
using System.Collections.Generic;
using System.Text;
using HotelAutomation.Application.Common.Interfaces.Repositories;

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

        public Room Delete(Room room)
        {
            _room.DeleteOne(u => u.Id == room.Id);
            return room;
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


        public Room Update(Room room)
        {
            _room.ReplaceOne(r => r.Id == room.Id, room);
            return room;

        }

        
    }
}
