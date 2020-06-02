using System;
using System.Collections.Generic;
using System.Linq;
using System.Runtime.Serialization;
using System.Web;

namespace FeedMeServer.Models
{
    [DataContract]
    public class FamilyUserRelations
    {

        [DataMember(Name = "id")]
        public int ID {get; set; } 

        [DataMember(Name = "userFirst")]
        public User UserFirst { get; set; }

        [DataMember(Name = "userSecond")]
        public User UserSecond { get; set; }

    }
}