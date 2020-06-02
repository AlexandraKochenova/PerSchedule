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

        [DataMember(Name = "dateCreating")]
        public long DateCreating { get; set; }

        [DataMember(Name = "timeDoing")] 
        public long TimeDoing { get; set; }

        [DataMember(Name = "readyDates")]
        public string ReadyDate { get; set; }

        [DataMember(Name = "period")]
        public string Period { get; set; }

        [DataMember(Name = "information")]
        public string Information { get; set; }

        [DataMember(Name = "responsibilityCode")]
        public int ResponsibilityCode { get; set; }

        [DataMember(Name = "petId")]
        public int PetId { get; set; }

        [DataMember(Name = "userID")]
        public int UserID { get; set; }
    }
}