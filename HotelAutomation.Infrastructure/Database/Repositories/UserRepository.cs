using HotelAutomation.Application.Common.Interfaces.Repositories;
using HotelAutomation.Domain.Entitities;
using HotelAutomation.Domain.Exceptions;
using MongoDB.Driver;

namespace HotelAutomation.Infrastructure.Database.Repositories
{
    //public class UserRepository : BaseRepository<User> , IUserRepository
    public class UserRepository : IUserRepository
    {

        private readonly IMongoCollection<User> _user;

        public UserRepository(IDatabaseSettings databaseSettings)
        {
            var client = new MongoClient(databaseSettings.ConnectionString);
            var database = client.GetDatabase(databaseSettings.DatabaseName);

            _user = database.GetCollection<User>(databaseSettings.UsersCollectionName);

        }

        public User Insert(User user)
        {
            _user.InsertOne(user);
            return user;
        }

        public User Delete(User user)
        {
            _user.DeleteOne(u => u.Id == user.Id);
            return user;
        }

        public User GetById(string id)
        {
            var user = _user.Find<User>(u => u.Id == id).FirstOrDefault();
            if( user == null)
            {
                throw new EntityNotFoundException();
            }
            return user;
        }

       
        public User Update(User user)
        {
            _user.ReplaceOne(u => u.Id == user.Id, user);
            return user;

        }

        public User GetByEmail(string email)
        {
            var user = _user.Find<User>(u => u.Email == email).FirstOrDefault();
            if (user == null)
            {
                throw new EntityNotFoundException();
            }
            return user;
        }
    }
}
