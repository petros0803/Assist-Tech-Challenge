using AutoMapper;
using AutoMapper.Configuration;
using HotelAutomation.Application.Common.Interfaces.Repositories;
using HotelAutomation.Application.Common.Models.RervationModels;
using HotelAutomation.Domain.Entitities;
using System;
using System.Collections.Generic;
using System.Text;

namespace HotelAutomation.Application.Services
{
    public class ReservationService
    {
        private readonly IReservationRepository reservationRepository;
        private readonly IRoomRepository roomRepository;
        private readonly IUserRepository userRepository;
        private readonly IConfiguration configuration;
        private readonly IMapper mapper;

        public ReservationService(IReservationRepository reservationRepository, IRoomRepository roomRepository, IUserRepository userRepository)
        {
            this.reservationRepository = reservationRepository;
            this.roomRepository = roomRepository;
            this.userRepository = userRepository;
            //this.configuration = configuration;
        }

        public ReservationResponseModel Add(ReservationRequestModel reservation)
        {

            var reservationn = new Reservation
            {
                UserId = reservation.UserId,
                RoomId = reservation.RoomId,
                StartDate = reservation.StartDate,
                ExpirationDate = reservation.ExpirationDate

            };
            reservationRepository.Insert(reservationn);
            var room = roomRepository.GetById(reservation.RoomId);
            room.Reserved = true;
            room.StartDate = reservation.StartDate;
            room.ExpirationDate = reservation.ExpirationDate;
            roomRepository.Update(room , reservation.RoomId);
            return new ReservationResponseModel
            {
                UserId = reservation.UserId,
                RoomId = reservation.RoomId,
                StartDate = reservation.StartDate,
                ExpirationDate = reservation.ExpirationDate
            };

        }

        public void Delete(string id)
        {
            var reservation = reservationRepository.GetById(id);
            var room = roomRepository.GetById(reservation.RoomId);
            room.Reserved = false;
            roomRepository.Update(room, reservation.RoomId);
            reservationRepository.Delete(id);

        }

        public ReservationResponseModel GetById(string id)
        {
            var reservation = reservationRepository.GetById(id);

            return new ReservationResponseModel
            {
                UserId = reservation.UserId,
                RoomId = reservation.RoomId,
                StartDate = reservation.StartDate,
                ExpirationDate = reservation.ExpirationDate
            };



        }


        public List<ReservationResponseModel> GetAllReservations()
        {

            var reservations = reservationRepository.GetAllReservations();
            List<ReservationResponseModel> listReservationsResponse = new List<ReservationResponseModel>();
            
            foreach (Reservation reservation in reservations)
            {


                var reservationResponse = new ReservationResponseModel
                {
                    UserId = reservation.UserId,
                    RoomId = reservation.RoomId,
                    StartDate = reservation.StartDate,
                    ExpirationDate = reservation.ExpirationDate
                };
                listReservationsResponse.Add(reservationResponse);
            }
            return listReservationsResponse;



        }


        public List<Reservation> GetUserReservations(string id)
        {
            var user = userRepository.GetById(id);
            var reservations = reservationRepository.GetAllReservations();
            foreach (Reservation reservation in reservations)
            {

            }
            return null;
            
        }
        /* 

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
             //automapper*/

    }
    }
