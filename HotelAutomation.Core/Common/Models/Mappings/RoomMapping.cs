using AutoMapper;
using HotelAutomation.Application.Common.Models.RoomModels;
using HotelAutomation.Application.Common.Models.UserModels;
using HotelAutomation.Domain.Entitities;
using System;
using System.Collections.Generic;
using System.Text;

namespace HotelAutomation.Application.Common.Models.Mappings
{
    public class RoomMapping : Profile
    {
        public RoomMapping()
        {
            CreateMap<CreateRoomResponseModel, Room>();
            CreateMap<UpdateRoomRequestModel, Room>();
            
        }
    }
}
