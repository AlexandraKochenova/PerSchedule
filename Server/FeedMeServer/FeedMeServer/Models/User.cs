using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Runtime.Serialization;

namespace FeedMeServer.Models
{
    [DataContract]
    public class User
    {
        [DataMember(Name = "id")]
        public int Id { get; set; }

        [DataMember(Name = "name")]
        public string Name { get; set; }

        [DataMember(Name = "login")]
        public string Login { get; set; }

        [DataMember(Name = "password")]
        public string Password { get; set; }

        [DataMember(Name = "familyId")]
        public int FamilyId { get; set; }

        [DataMember(Name = "familyName")]
        public string FamilyName { get; set; }

        [DataMember(Name = "isHeadOfFamily")]
        public bool IsHeadOfFamily { get; set; }
    }
}