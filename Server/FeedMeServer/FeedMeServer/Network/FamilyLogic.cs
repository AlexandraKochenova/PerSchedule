using System;
using System.Collections.Generic;
using System.Linq;
using System.Runtime.Remoting.Contexts;
using System.Web;
using FeedMeServer.Models;

namespace FeedMeServer.Network
{
    public partial class NetworkLogic : IService1
    {

        public FamilyUserRelations AddNewFriendship(FamilyUserRelations familyUserRelations)
        {
            using(FeedMeContext context = new FeedMeContext())
            {
                familyUserRelations.UserFirst = context.Users.Find(familyUserRelations.UserFirst.Id);
                familyUserRelations.UserSecond = context.Users.Find(familyUserRelations.UserSecond.Id);

                context.FamilyUserRelations.Add(familyUserRelations);
                context.SaveChanges();
                return familyUserRelations;
            }
        }

        public string DeleteNewFriendship(string id)
        {
            using (FeedMeContext context = new FeedMeContext())
            {
                int friendshipID;
                if (int.TryParse(id, out friendshipID))
                {
                    FamilyUserRelations familyUserRelations = context.FamilyUserRelations.Find(id);
                    if (familyUserRelations != null)
                    {
                        context.FamilyUserRelations.Remove(familyUserRelations);
                        context.SaveChanges();
                        return Constants.SUCCESS;
                    }
                    else
                    {
                        return Constants.FRIENDSHIP_NOT_FOUND;
                    }
                }
                else
                {
                    return Constants.ENTER_INT;
                }
                
            }
        }

        public string AddNewFamilyMember(string id)
        {
            int friendshipId;
            if (int.TryParse(id, out friendshipId))
            {
                using (FeedMeContext context = new FeedMeContext())
                {
                    FamilyUserRelations current = context.FamilyUserRelations.Find(friendshipId);
                    if (current.UserFirst.FamilyId != -1) {
                        if (current.UserSecond.FamilyId != -1)
                        {
                            //У обоих пользователей есть свое семейство. Необходимо выбрать.
                            //TODO 
                            return Constants.FRIENDSHIP_CHOICE_ONE_FAMILY;
                        }
                        else
                        {
                            //Семейство есть только у первого. Второй вступает к первому.
                            current.UserSecond.FamilyId = current.UserFirst.FamilyId;
                            context.SaveChanges();
                            return current.UserSecond.FamilyId.ToString();
                        }
                    } 
                    else
                    {
                        if (current.UserSecond.FamilyId != -1)
                        {
                            //Семейство есть только у второго. Первый вступает ко второму.
                            current.UserFirst.FamilyId = current.UserSecond.FamilyId;
                            context.SaveChanges();
                            return current.UserFirst.FamilyId.ToString();
                        }
                        else
                        {
                            //Семейств нет ни у одного. Создание нового семейства.
                            Family family = new Family();
                            family.HeadID = current.UserFirst.Id;
                            context.Families.Add(family);
                            context.SaveChanges();
                            var fam = from fa in context.Families where fa.HeadID.Equals(current.UserFirst.Id) select fa;
                            if (fam != null &&  fam.Count() > 0)
                            {
                                family = fam.First<Family>();
                                fam.ToList<Family>().ForEach(delegate (Family tempFamily)
                                {
                                    current.UserFirst.FamilyId = tempFamily.Id;
                                    current.UserSecond.FamilyId = tempFamily.Id;
                                });
                            }
                            context.SaveChanges();
                            return current.UserSecond.FamilyId.ToString();
                        }

                    }
                }
            }
            else
            {
                return Constants.ENTER_INT;
            }
        }

        public string DeleteFamilyMember(FamilyUserRelations familyUserRelations)
        {
            using (FeedMeContext context = new FeedMeContext())
            {
                User isHead = context.Users.Find(familyUserRelations.UserFirst.Id);
                if (familyUserRelations.UserFirst.Id == familyUserRelations.UserSecond.Id)
                {
                    isHead.FamilyId = -1;
                    context.SaveChanges();
                    return Constants.USER_SELF_CRUSHED;
                }
                Family family = context.Families.Find(isHead.FamilyId);
                if (family.HeadID != familyUserRelations.UserFirst.Id)
                {
                    return Constants.NOT_ACCESS_FOR_DELETE_USER;
                }
                User user = context.Users.Find(familyUserRelations.UserSecond.Id);
                user.FamilyId = -1;
                context.SaveChanges();
                return Constants.SUCCESS;
            }
        }

        public AllFriendshipResponse GetNewFriendships(string id)
        {
            int userID;
            AllFriendshipResponse response = new AllFriendshipResponse();
            if (int.TryParse(id, out userID))
            {
                response.NewFriendshipList = new List<FamilyUserRelations>();
                using (FeedMeContext context = new FeedMeContext())
                {
                    var relationships = from f in context.FamilyUserRelations where f.UserFirst.Id.Equals(userID) select f;
                    if (relationships != null && relationships.Count() > 0)
                    {
                        relationships.ToList<FamilyUserRelations>().ForEach(delegate (FamilyUserRelations tempFam)
                        {
                            FamilyUserRelations currentFam = new FamilyUserRelations
                            {
                                ID = tempFam.ID,
                                UserFirst = tempFam.UserFirst,
                                UserSecond = tempFam.UserSecond
                            };
                            response.NewFriendshipList.Add(currentFam);
                        });
                        response.Status = (int)Constants.STATUSES.OK;
                        response.Message = Constants.SUCCESS;
                    }

                    relationships = from f in context.FamilyUserRelations where f.UserSecond.Id.Equals(userID) select f;
                    if (relationships != null && relationships.Count() > 0)
                    {
                        relationships.ToList<FamilyUserRelations>().ForEach(delegate (FamilyUserRelations tempFam)
                        {
                            FamilyUserRelations currentFam = new FamilyUserRelations
                            {
                                ID = tempFam.ID,
                                UserFirst = tempFam.UserFirst,
                                UserSecond = tempFam.UserSecond
                            };
                            response.NewFriendshipList.Add(currentFam);
                        });
                        response.Status = (int)Constants.STATUSES.OK;
                        response.Message = Constants.SUCCESS;
                    }

                    if (response.NewFriendshipList.Count() <= 0)
                    {
                        response.Status = (int)Constants.STATUSES.ERROR;
                        response.Message = Constants.FRIENDSHIP_NOT_FOUND;
                    }
                }
                
            }
            else
            {
                response.Status = (int)Constants.STATUSES.ERROR;
                response.Message = Constants.ENTER_INT;
            }
            return response;
        }

        public FamilyResponse GetFamilyUsers(string id)
        {
            FamilyResponse response = new FamilyResponse();
            response.FamilyList = new List<User>();
            using (FeedMeContext context = new FeedMeContext())
            {
                int famId;
                if (int.TryParse(id, out famId))
                {
                    var users = from u in context.Users where u.FamilyId.Equals(famId) select u;
                    if (users != null && users.Count() > 0)
                    {
                        users.ToList<User>().ForEach(delegate (User user)
                        {
                            User currentUser = new User
                            {
                                Id = user.Id,
                                Name = user.Name,
                                LastName = user.LastName,
                                Login = "",
                                Password = "",
                                FamilyId = user.FamilyId
                            };
                            response.FamilyList.Add(currentUser);
                        });
                        response.Status = (int)Constants.STATUSES.OK;
                        response.Message = Constants.SUCCESS;
                    }
                    else
                    {
                        response.Status = (int)Constants.STATUSES.ERROR;
                        response.Message = Constants.USER_NOT_FOUND;
                    }
                }
            }
            return response;
        }



    }
}