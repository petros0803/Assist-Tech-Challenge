using MongoDB.Bson;
using MongoDB.Bson.Serialization.Attributes;

namespace HotelAutomation.Domain.Common
{
    public class BaseEntity
    {
        [BsonId]
        [BsonRepresentation(BsonType.ObjectId)]
        public string Id { get; set; }
    }
}
