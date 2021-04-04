using AutoMapper;
using HotelAutomation.Application.Common.Exceptions;
using HotelAutomation.Application.Common.Interfaces.Repositories;
using HotelAutomation.Application.Common.Models.RoomModels;
using HotelAutomation.Domain.Entitities;
using HotelAutomation.Domain.Exceptions;
using Microsoft.Extensions.Configuration;
using System;
using System.Collections.Generic;
using System.Text;
using System.Threading.Tasks;

namespace HotelAutomation.Application.Services
{
    public class RoomService
    {
        private readonly IRoomRepository roomRepository;
        private readonly IConfiguration configuration;
        private readonly IMapper mapper;

        public RoomService(IRoomRepository roomRepository)
        {
            this.roomRepository = roomRepository;
            //this.configuration = configuration;
        }

        public void Add(Room room)
        {
            roomRepository.Insert(room);

        }

        public RoomResponseModel GetById(string id)
        {
            var room = roomRepository.GetById(id);

            return new RoomResponseModel
            {
                Number = room.Number,
                Beds = room.Beds,
                Facilities = new GetFacility
                {
                    Wifi = room.Facilities.Wifi,
                    AC = room.Facilities.AC,
                    TV = room.Facilities.TV,
                    NSR = room.Facilities.NSR
                }

            };


        }

        public async Task<List<RoomResponseModel>> GetByStatusAsync(bool status)
        {

            var rooms = await roomRepository.GetByStatusAsync(status);
            List<RoomResponseModel> listRoomsResponse = new List<RoomResponseModel>();
            foreach (Room room in rooms)
            {

                var roomResponse = new RoomResponseModel
                {
                    Number = room.Number,
                    Beds = room.Beds,
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
            room.Beds = model.Beds;
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
