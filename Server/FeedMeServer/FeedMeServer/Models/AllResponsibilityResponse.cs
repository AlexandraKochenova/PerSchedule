using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Runtime.Serialization;

namespace FeedMeServer.Models
{
    [DataContract]
    public class AllResponsibilityResponse : BaseResponse
    {
        [DataMember(Name = "responsibilitieList")]
        public List<Responsibility> ResponsibilitiesList;
    }
}