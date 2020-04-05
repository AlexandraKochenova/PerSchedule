using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Runtime.Serialization;

namespace FeedMeServer.Models
{
    [DataContract]
    public class Responsibility
    {
        [DataMember(Name = "id")]
        public int Id { get; set; }

        [DataMember(Name = "dateTimeResponsibility")]
        public DateTime DateTimeResponsibility { get; set; }

        [DataMember(Name = "information")]
        public string Information { get; set; }

        [DataMember(Name = "responsibilityCode")]
        public int ResponsibilityCode { get; set; }

        [DataMember(Name = "petId")]
        public int PetId { get; set; }

        [DataMember(Name = "ownerId")]
        public int OwnerId { get; set; }
    }
}