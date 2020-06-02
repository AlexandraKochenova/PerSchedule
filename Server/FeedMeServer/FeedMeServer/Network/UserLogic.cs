using System;
using System.Collections.Generic;
using System.Data.Entity.Core.Metadata.Edm;
using System.Linq;
using System.Web;
using FeedMeServer.Models;

namespace FeedMeServer.Network
{
    public partial class NetworkLogic : IService1
    {
        public string Registration(User user)
        {
            using (FeedMeContext context = new FeedMeContext())
            {
                var users = from u in context.Users where u.Login.Equals(user.Login) select u;
                if (users == null || users.Count() == 0)
                {
                    context.Users.Add(user);
                    context.SaveChanges();
                    return Constants.USER_CREATED;
                }
                else
                {
                    return Constants.USER_ALREADY_EXISTS;
                }
            }
        }

        public UserResponse Auth(Auth data)
        {
            UserResponse response = new UserResponse();
            using (FeedMeContext context = new FeedMeContext())
            {
                var users = from u in context.Users where u.Login.Equals(data.Login) select u;
                if (users != null && users.Count() > 0)
                {
                    User currentUser = users.First<User>();
                    if (currentUser.Password.Equals(data.Password))
                    {
                        response.Status = (int)Constants.STATUSES.OK;
                        response.Message = Constants.SUCCESS;
                        response.User = currentUser;
                    }
                    else
                    {
                        response.Status = (int)Constants.STATUSES.ERROR;
                        response.Message = Constants.WRONG_USER_DATA;
                    }
                }
                else
                {
                    response.Status = (int)Constants.STATUSES.ERROR;
                    response.Message = Constants.WRONG_USER_DATA;
                }

            }
            return response;
        }

        public string EditUser(User user)
        {
            using (FeedMeContext context = new FeedMeContext())
            {
                User currentUser = context.Users.Find(user.Id);
                if (currentUser != null)
                {
                    currentUser.Login = user.Login;
                    currentUser.Password = user.Password;
                    currentUser.Name = user.Name;
                    currentUser.LastName = user.LastName;
                    currentUser.FamilyId = user.FamilyId;
                    context.SaveChanges();
                    return Constants.USER_UPDATED;
                }
                else
                {
                    return Constants.USER_NOT_FOUND;
                }
            }

        }

        public string DeleteUser(User user)
        {
            return Constants.USER_DELETED;
        }

    }
}