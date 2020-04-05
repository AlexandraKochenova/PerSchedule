using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Runtime.Serialization;

namespace FeedMeServer.Models
{
    [DataContract]
    public class Pet
    {
        [DataMember(Name = "id")]
        public int Id { get; set; }

        [DataMember(Name = "name")]
        public string Name { get; set; }

        [DataMember(Name = "age")]
        public int Age { get; set; }

        [DataMember(Name = "weight")]
        public double Weight { get; set; }

        [DataMember(Name = "responsibilityList")]
        public string ResponsibilityList { get; set; }

        [DataMember(Name = "ownerFamilyId")]
        public int OwnerFamilyId { get; set; }
    }
}