using System;
using System.Collections.Generic;
using System.Text;
using AutoMapper;
using HotelAutomation.Application.Common.Models.UserModels;
using HotelAutomation.Domain.Entitities;

namespace HotelAutomation.Application.Common.Models.Users
{
    public class AutoMapper : Profile
    {
        public AutoMapper()
        {
            CreateMap<LoginModel, User>();
            CreateMap<RoomResponseModel, Room>();
        }
    }
}
