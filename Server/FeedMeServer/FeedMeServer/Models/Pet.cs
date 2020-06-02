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
        public long Age { get; set; }

        [DataMember(Name="sex")]
        public bool Sex { get; set; }

        [DataMember(Name= "type")]
        public string Type { get; set; }

        [DataMember(Name = "weight")]
        public double Weight { get; set; }

        [DataMember(Name = "familyID")]
        public int FamilyID { get; set; }
    }
}