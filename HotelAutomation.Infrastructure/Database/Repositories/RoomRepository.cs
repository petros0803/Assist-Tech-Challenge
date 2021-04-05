using HotelAutomation.Domain.Entitities;
using HotelAutomation.Domain.Exceptions;
using MongoDB.Driver;
using System;
using System.Collections.Generic;
using System.Text;
using HotelAutomation.Application.Common.Interfaces.Repositories;
using HotelAutomation.Application.Common.Models.RoomModels;
using MongoDB.Bson;
using System.Threading.Tasks;
using System.Linq;
using MongoDB.Driver.Linq;

namespace HotelAutomation.Infrastructure.Database.Repositories
{
    public class RoomRepository : IRoomRepository
    {
        private readonly IMongoCollection<Room> _roomsCollection;

        public RoomRepository(IDatabaseSettings databaseSettings)
        {
            var client = new MongoClient(databaseSettings.ConnectionString);
            var database = client.GetDatabase(databaseSettings.DatabaseName);

            _roomsCollection = database.GetCollection<Room>(databaseSettings.RoomsCollectionNumber);

        }

        public Room Insert(Room room)
        {
            _roomsCollection.InsertOne(room);
            return room;
        }

        public Room Delete(string id)
        {
            _roomsCollection.DeleteOne(u => u.Id == id);
            return null;
        }

        public Room GetById(string id)
        {
            var room = _roomsCollection.Find<Room>(r => r.Id == id).FirstOrDefault();
            if (room == null)
            {
                throw new EntityNotFoundException();
            }
            return room;
        }


        public Room Update(Room room, string id)
        {
            _roomsCollection.ReplaceOne(r => r.Id == id, room);
            return room;
        }

        

        public List<Room> GetByStatus(RoomFilterModel filter)
        {
            var rooms = _roomsCollection.AsQueryable();
           

            //IQueryable<Room>
            /*if(filter != null)
            {
                if (filter.BedsNumber != null)
                    rooms = rooms.Where(x => x.Beds == filter.BedsNumber);
                if (filter.status != null)
                    rooms = rooms.Where(x => x.Reserved == filter.status);
                if (filter.AC !=null)
                    rooms = rooms.Where(x => x.Facilities.AC == filter.AC);
                if (filter.Wifi != null)
                    rooms = rooms.Where(x => x.Facilities.Wifi == filter.Wifi);
                if (filter.TV != null)
                    rooms = rooms.Where(x => x.Facilities.TV == filter.TV);
                if (filter.NSR != null)
                    rooms = rooms.Where(x => x.Facilities.NSR == filter.NSR);

            }*/
            
               
            

            return rooms.ToList();




        }

        
    }
}
