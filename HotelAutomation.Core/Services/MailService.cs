using AutoMapper;
using AutoMapper.Configuration;
using HotelAutomation.Application.Common.Interfaces.Repositories;
using HotelAutomation.Application.Common.Models.MailModel;
using System;
using System.Collections.Generic;
using System.Text;
using System.Threading.Tasks;
using System.Net;
using System.Net.Mail;
using System.Net.Mime;

namespace HotelAutomation.Application.Services
{
    public class MailService
    {
        private readonly IMailRepository mailRepository;
        private readonly IReservationRepository reservationRepository;
        private readonly IRoomRepository roomRepository;
        private readonly IUserRepository userRepository;
        private readonly IConfiguration configuration;
        private readonly IMapper mapper;

        public MailService(IReservationRepository reservationRepository, IRoomRepository roomRepository, IUserRepository userRepository)
        {
            this.reservationRepository = reservationRepository;
            this.roomRepository = roomRepository;
            this.userRepository = userRepository;
            //this.configuration = configuration;
        }
        public void SendEmail(MailRequest mail)
        {
            MailAddress to = new MailAddress(mail.ToEmail);
            MailAddress from = new MailAddress("");
            MailMessage message = new MailMessage(from, to);
            message.Subject = mail.Subject;
            message.Body = mail.Body;
            SmtpClient client = new SmtpClient("smtp.server.address", 2525)
            {
              
                Credentials = new NetworkCredential("smtp_username", "smtp_password"),
                EnableSsl = true
            };
            
             client.Send(message);
            
        }
    }
}   

