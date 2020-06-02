using System;
using System.Collections.Generic;
using System.Linq;
using System.Runtime.Serialization;
using System.Web;

namespace FeedMeServer.Models
{
    [DataContract]
    public class FamilyResponse : BaseResponse
    {
        [DataMember(Name = "familyList")]
        public List<User> FamilyList;
    }
}