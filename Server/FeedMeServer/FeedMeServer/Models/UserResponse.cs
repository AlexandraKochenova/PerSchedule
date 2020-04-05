using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Runtime.Serialization;

namespace FeedMeServer.Models
{
    [DataContract]
    public class UserResponse : BaseResponse
    {
        [DataMember(Name = "user")]
        public User User { get; set; }
    }
}