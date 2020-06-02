using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI.WebControls;
using FeedMeServer.Models;

namespace FeedMeServer.Network
{
    public partial class NetworkLogic : IService1
    {
        public string NewThing(Thing thing)
        {
            using (FeedMeContext context = new FeedMeContext())
            {
                context.Things.Add(thing);
                context.SaveChanges();
                string response = Constants.THING_CREATED;
                var things = from t in context.Things where t.FamilyID.Equals(thing.FamilyID) select t;
                if (things != null && things.Count() > 0)
                {
                    things.ToList<Thing>().ForEach(delegate (Thing temp)
                    {
                        if (temp.Name.Equals(thing.Name))
                        {
                            response = temp.Id.ToString();
                        }
                    });
                }
                return response;
            }
        }

        public string DeleteThing(string id)
        {
            int thingID;
            if (int.TryParse(id, out thingID))
            {
                using (FeedMeContext context = new FeedMeContext())
                {
                    Thing thing = context.Things.Find(thingID);
                    if (thing != null)
                    {
                        context.Things.Remove(thing);
                        context.SaveChanges();
                        return Constants.THING_DELETED;
                    }
                    else
                    {
                        return Constants.THING_NOT_FOUND;
                    }
                }
            }
            else
            {
                return Constants.ENTER_INT;
            }
        }

        public string EditThing(Thing thing)
        {
            using (FeedMeContext context = new FeedMeContext())
            {
                Thing editableThing= context.Things.Find(thing.Id);
                if (editableThing != null)
                {
                    editableThing.Name = thing.Name;
                    editableThing.Information = thing.Information;
                    editableThing.FamilyID = thing.FamilyID;
                    editableThing.Code = thing.Code;
                    context.SaveChanges();
                    return Constants.THING_UPDATED;
                }
                else
                {
                    return Constants.THING_NOT_FOUND;
                }
            }
        }

        public AllThingsResponse GetAllThings(string id)
        {
            AllThingsResponse response = new AllThingsResponse();
            response.ThingsList = new List<Thing>();
            using (FeedMeContext context = new FeedMeContext())
            {
                int userId;
                if (int.TryParse(id, out userId))
                {
                    User user = context.Users.Find(userId);
                    var things = from p in context.Things where p.FamilyID.Equals(user.FamilyId) select p;
                    if (things != null && things.Count() > 0)
                    {
                        things.ToList<Thing>().ForEach(delegate (Thing tempThing)
                        {
                            Thing currentThing = new Thing
                            {
                                Id = tempThing.Id,
                                Name = tempThing.Name,
                                Code = tempThing.Code,
                                Information = tempThing.Information,
                                FamilyID = tempThing.FamilyID
                            };
                            response.ThingsList.Add(currentThing);
                        });
                        response.Status = (int)Constants.STATUSES.OK;
                        response.Message = Constants.SUCCESS;
                    }
                    else
                    {
                        response.Status = (int)Constants.STATUSES.ERROR;
                        response.Message = Constants.THING_NOT_FOUND;
                    }
                }

            }
            return response;
        }


    }
}