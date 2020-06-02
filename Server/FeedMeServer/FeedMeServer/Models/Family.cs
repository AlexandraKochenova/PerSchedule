using System;
using System.Collections.Generic;
using System.Linq;
using System.Runtime.Serialization;
using System.Web;

namespace FeedMeServer.Models
{
    [DataContract]
    public class Family
    {
        [DataMember(Name = "id")]
        public int Id { get; set; }

        [DataMember(Name = "headID")]
        public int HeadID { get; set; }
    }
}