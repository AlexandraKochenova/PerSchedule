using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace FeedMeServer
{
    public class Constants
    {
        public enum STATUSES { ERROR = 1, OK = 0 };

        public static string ENTER_INT = "Please, enter int value.";
        public static string SUCCESS = "Success.";

        public static string WRONG_USER_DATA = "The username or password you entered is incorrect";

        public static string USER_NOT_DELETED = "The user with this email has not been deleted.";
        public static string USER_DELETED = "User successfully deleted.";
        public static string USER_CANT_DELETED = "User can not been deleted, becouse already have one or more orders.";
        public static string USER_NOT_FOUND = "User not found...";
        public static string USER_ALREADY_EXISTS = "User with this email already exists.";
        public static string USER_CREATED = "User created.";
        public static string USER_ALREADY_DELETED = "User has already been deleted.";
        public static string USER_RESTORED = "User successfully restored";
        public static string USER_UPDATED = "User successfully updated";

        public static string PET_CREATED = "Pet created.";
        public static string PET_DELETED = "Pet successfully deleted.";
        public static string PET_UPDATED = "Pet successfully updated.";
        public static string PET_NOT_FOUND = "Pet not found.";
        public static string PET_NOT_CREATED = "Pet not created.";

        public static string RESPONSIBILITY_NOT_FOUND = "Responsibility not found";
        public static string RESPONSIBILITY_CREATED = "Responsibility created.";
        public static string RESPONSIBILITY_UPDATED = "Responsibility successfully updated.";
        public static string RESPONSIBILITY_DELETED = "Responsibility successfully deleted.";

        public static string FRIENDSHIP_NOT_FOUND = "Friendship not found.";
        public static string FRIENDSHIP_CHOICE_ONE_FAMILY = "Choice one family.";
        public static string NOT_ACCESS_FOR_DELETE_USER = "This user has not access for delete another user from family.";
        public static string USER_SELF_CRUSHED = "User self-crushed.";

        public static string THING_CREATED = "THING created.";
        public static string THING_DELETED = "THING successfully deleted.";
        public static string THING_UPDATED = "THING successfully updated.";
        public static string THING_NOT_FOUND = "THING not found.";
        public static string THING_NOT_CREATED = "THING not created.";

    }
}