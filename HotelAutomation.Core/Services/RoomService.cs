using AutoMapper;
using HotelAutomation.Application.Common.Exceptions;
using HotelAutomation.Application.Common.Interfaces.Repositories;
using HotelAutomation.Application.Common.Models.RoomModels;
using HotelAutomation.Application.Common.Models.UserModels;
using HotelAutomation.Domain.Entitities;
using HotelAutomation.Domain.Exceptions;
using Microsoft.Extensions.Configuration;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace HotelAutomation.Application.Services
{
    public class RoomService
    {
        private readonly IRoomRepository roomRepository;
        private readonly IReservationRepository reservationRepository;
        private readonly IConfiguration configuration;
        private readonly IMapper mapper;

        public RoomService(IRoomRepository roomRepository, IReservationRepository reservationRepository)
        {
            this.roomRepository = roomRepository;
            this.reservationRepository = reservationRepository;
            //this.configuration = configuration;
        }

        public string  Add(Room room)
        {
            roomRepository.Insert(room);
            return room.Id;

        }

        public RoomResponseModel GetById(string id)
        {
            var room = roomRepository.GetById(id);

            return new RoomResponseModel
            {
                Number = room.Number,
                Beds = room.Beds,
                Price = room.Price,
                StartDate = room.StartDate,
                ExpirationDate = room.ExpirationDate,
                Facilities = new GetFacility
                {
                    Wifi = room.Facilities.Wifi,
                    AC = room.Facilities.AC,
                    TV = room.Facilities.TV,
                    NSR = room.Facilities.NSR
                }

            };


        }

        public List<RoomResponseModel> GetByStatus(RoomFilterModel filter)
        {
            
            var rooms = roomRepository.GetByStatus(filter);
            //var reservations = reservationRepository.GetAllReservations();
           
            List<RoomResponseModel> listRoomsResponse = new List<RoomResponseModel>();
            if (filter != null)
            {
                if (filter.BedsNumber != null)
                    rooms = rooms.Where(x => x.Beds == filter.BedsNumber).ToList();
                if (filter.status != null)
                    rooms = rooms.Where(x => x.Reserved == filter.status).ToList();
                if (filter.AC != null)
                    rooms = rooms.Where(x => x.Facilities.AC == filter.AC).ToList();
                if (filter.Wifi != null)
                    rooms = rooms.Where(x => x.Facilities.Wifi == filter.Wifi).ToList();
                if (filter.TV != null)
                    rooms = rooms.Where(x => x.Facilities.TV == filter.TV).ToList();
                if (filter.NSR != null)
                    rooms = rooms.Where(x => x.Facilities.NSR == filter.NSR).ToList();
                if (filter.StartDate != null)
                    rooms = rooms.Where(x => x.StartDate == filter.StartDate).ToList();
                if (filter.ExpirationDate != null)
                    rooms = rooms.Where(x => x.ExpirationDate == filter.ExpirationDate).ToList();


            }
            foreach (Room room in rooms)
                {


                    var roomResponse = new RoomResponseModel
                    {
                        Id = room.Id,
                        Number = room.Number,
                        Beds = room.Beds,
                        Price = room.Price,
                        StartDate = room.StartDate,
                        ExpirationDate = room.ExpirationDate,

                        Facilities = new GetFacility
                        {
                            Wifi = room.Facilities.Wifi,
                            AC = room.Facilities.AC,
                            TV = room.Facilities.TV,
                            NSR = room.Facilities.NSR
                        },

                        Reserved = room.Reserved
                    };
                    listRoomsResponse.Add(roomResponse);
                }
                return listRoomsResponse;

            
          
        }

  

        public void Delete(string id)
        {
            roomRepository.Delete(id);

        }

        public UpdateRoomResponseModel Update(UpdateRoomRequestModel model, string id)
        {
            var room = roomRepository.GetById(id);

            room.Number = model.Number;
            room.Beds = (int)model.Beds;
            room.Facilities.TV = model.Facilities.TV;
            room.Facilities.AC = model.Facilities.AC;
            room.Facilities.NSR = model.Facilities.NSR;
            // automapper

            var updatedroom = this.roomRepository.Update(room, id);

            return new UpdateRoomResponseModel
            {
                Id = updatedroom.Id,
                Number = updatedroom.Number,
                Beds = updatedroom.Beds,
                Price = updatedroom.Price,
                StartDate = updatedroom.StartDate,
                ExpirationDate = updatedroom.ExpirationDate,
                Facilities = new FacilityResponseModel
                {
                    Wifi = updatedroom.Facilities.Wifi,
                    AC = updatedroom.Facilities.AC,
                    TV = updatedroom.Facilities.TV,
                    NSR = updatedroom.Facilities.NSR
                }
            };
            //automapper




        }
    }
}
