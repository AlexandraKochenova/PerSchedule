using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Runtime.Serialization;

namespace FeedMeServer.Models
{
    [DataContract]
    public class AllPetsResponse : BaseResponse
    {
        [DataMember(Name = "petsList")]
        public List<Pet> PetsList;
    }
}