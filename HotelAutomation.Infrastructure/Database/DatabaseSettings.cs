namespace HotelAutomation.Infrastructure.Database
{
    public class DatabaseSettings : IDatabaseSettings
    {
        public  string UsersCollectionName { get; set; }

        public string RoomsCollectionNumber { get; set; }

        public string ReservationsColection { get; set; }

        public  string ConnectionString { get; set; }

        public  string DatabaseName { get; set; }
    }
}
