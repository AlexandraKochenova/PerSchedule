using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using FeedMeServer.Models;

namespace FeedMeServer.Network
{
    public partial class NetworkLogic : IService1
    {
        public string NewPet(Pet pet)
        {
            using (FeedMeContext context = new FeedMeContext())
            {
                context.Pets.Add(pet);
                context.SaveChanges();
                return Constants.PET_CREATED;
            }
        }

        public string EditPet(Pet pet)
        {
            using (FeedMeContext context = new FeedMeContext())
            {
                Pet currentPet = context.Pets.Find(pet.Id);
                if (currentPet != null)
                {
                    currentPet.Name = pet.Name;
                    currentPet.Age = pet.Age;
                    currentPet.Weight = pet.Weight;
                    currentPet.ResponsibilityList = pet.ResponsibilityList;
                    currentPet.OwnerFamilyId = pet.OwnerFamilyId;
                    context.SaveChanges();
                    return Constants.PET_UPDATED;
                }
                else
                {
                    return Constants.PET_NOT_FOUND;
                }
            }
        }

        public string DeletePet(string id)
        {
            int petId;
            if (int.TryParse(id, out petId))
            {
                using (FeedMeContext context = new FeedMeContext())
                {
                    Pet pet = context.Pets.Find(petId);
                    if (pet != null)
                    {
                        context.Pets.Remove(pet);
                        context.SaveChanges();
                        return Constants.PET_DELETED;
                    }
                    else
                    {
                        return Constants.PET_NOT_FOUND;
                    }
                }
            }
            else
            {
                return Constants.ENTER_INT;
            }
        }

        public AllPetsResponse GetAllPets(string id)
        {
            AllPetsResponse response = new AllPetsResponse();
            response.PetsList = new List<Pet>();
            using(FeedMeContext context = new FeedMeContext())
            {
                int userId;
                if (int.TryParse(id, out userId)){
                    var users = from u in context.Users where u.Id.Equals(userId) select u;
                    User user = users.First();
                    var pets = from p in context.Pets where p.OwnerFamilyId.Equals(user.FamilyId) select p;
                    if (pets != null && pets.Count() > 0)
                    {
                        pets.ToList<Pet>().ForEach(delegate (Pet pet)
                        {
                            Pet currentPet = new Pet
                            {
                                Id = pet.Id,
                                Name = pet.Name,
                                Age = pet.Age,
                                Weight = pet.Weight,
                                ResponsibilityList = pet.ResponsibilityList,
                                OwnerFamilyId = pet.OwnerFamilyId
                            };
                            response.PetsList.Add(currentPet);
                        });
                        response.Status = (int)Constants.STATUSES.OK;
                        response.Message = Constants.SUCCESS;
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