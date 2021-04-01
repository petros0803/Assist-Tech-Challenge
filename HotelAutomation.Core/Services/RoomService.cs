using AutoMapper;
using HotelAutomation.Application.Common.Interfaces.Repositories;
using HotelAutomation.Domain.Entitities;
using Microsoft.Extensions.Configuration;
using System;
using System.Collections.Generic;
using System.Text;

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
    }
}
