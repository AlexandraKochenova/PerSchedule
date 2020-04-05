using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using FeedMeServer.Models;

namespace FeedMeServer.Network
{
    public partial class NetworkLogic : IService1
    {
        public string NewResponsibility(Responsibility responsibility)
        {
            using (FeedMeContext context = new FeedMeContext())
            {
                context.Responsibilities.Add(responsibility);
                context.SaveChanges();
                return Constants.RESPONSIBILITY_CREATED;
            }
        }

        public string EditResponsibility(Responsibility responsibility)
        {
            using (FeedMeContext context = new FeedMeContext())
            {
                Responsibility currentResponsibility = context.Responsibilities.Find(responsibility.Id);
                if (currentResponsibility != null)
                {
                    currentResponsibility.DateTimeResponsibility = responsibility.DateTimeResponsibility;
                    currentResponsibility.Information = responsibility.Information;
                    currentResponsibility.ResponsibilityCode = responsibility.ResponsibilityCode;
                    currentResponsibility.PetId = responsibility.PetId;
                    currentResponsibility.OwnerId = responsibility.OwnerId;
                    context.SaveChanges();
                    return Constants.RESPONSIBILITY_UPDATED;
                }
                else
                {
                    return Constants.RESPONSIBILITY_NOT_FOUND;
                }
            }
        }

        public string DeleteResponsibility(Responsibility responsibility)
        {
            using (FeedMeContext context = new FeedMeContext())
            {
                Responsibility currentResponsibility = context.Responsibilities.Find(responsibility.Id);
                if (currentResponsibility != null)
                {
                    context.Responsibilities.Remove(currentResponsibility);
                    context.SaveChanges();
                    return Constants.RESPONSIBILITY_DELETED;
                }
                else
                {
                    return Constants.RESPONSIBILITY_NOT_FOUND;
                }
                
               
            }
        }

        public AllResponsibilityResponse GetAllResponsibility(string id)
        {
            AllResponsibilityResponse response = new AllResponsibilityResponse();
            using (FeedMeContext context = new FeedMeContext())
            {
                int userId;
                if (int.TryParse(id, out userId))
                {
                    var users = from u in context.Users where u.Id.Equals(userId) select u;
                    User user = users.First();
                    int familyId = user.FamilyId;
                    response.ResponsibilitiesList = new List<Responsibility>();
                    var pets = from p in context.Pets where p.OwnerFamilyId.Equals(familyId) select p;
                    if (pets != null && pets.Count() > 0)
                    {
                        pets.ToList<Pet>().ForEach(delegate (Pet pet)
                        {
                            var responsibilities = from r in context.Responsibilities where r.PetId.Equals(pet.Id) select r;
                            if (responsibilities != null && responsibilities.Count() > 0)
                            {
                                responsibilities.ToList<Responsibility>().ForEach(delegate (Responsibility responsibility)
                                {
                                    Responsibility currentResponsibility = new Responsibility()
                                    {
                                        Id = responsibility.Id,
                                        DateTimeResponsibility = responsibility.DateTimeResponsibility,
                                        Information = responsibility.Information,
                                        ResponsibilityCode = responsibility.ResponsibilityCode,
                                        PetId = responsibility.PetId,
                                        OwnerId = responsibility.OwnerId
                                    };
                                    response.ResponsibilitiesList.Add(currentResponsibility);
                                    response.Status = (int)Constants.STATUSES.OK;
                                    response.Message = Constants.SUCCESS;
                                });
                            }
                            else
                            {
                                response.Status = (int)Constants.STATUSES.ERROR;
                                response.Message = Constants.RESPONSIBILITY_NOT_FOUND;
                            }
                        });
                    }
                    else
                    {
                        response.Status = (int)Constants.STATUSES.ERROR;
                        response.Message = Constants.PET_NOT_FOUND;
                    }
                }
                
            }
            return response;
        }
    }
}