using System;
using System.Collections.Generic;
using System.Linq;
using System.Runtime.Serialization;
using System.Web;

namespace FeedMeServer.Models
{
    [DataContract]
    public class AllFriendshipResponse : BaseResponse
    {
        [DataMember(Name = "newFriendshipList")]
        public List<FamilyUserRelations> NewFriendshipList { get; set; }

    }
}