using AutoMapper;
using HotelAutomation.Application.Common.Models.UserModels;
using HotelAutomation.Application.Common.Models.Users;

using HotelAutomation.Domain.Entitities;
using System;
using System.Collections.Generic;
using System.Text;

namespace HotelAutomation.Application.Common.Models.Mappings
{
    public class UserMappingProfiles : Profile
    {
        
            public UserMappingProfiles()
            {
                CreateMap<LoginModel, User>();
                //CreateMap<>
            }
        
    }
}
