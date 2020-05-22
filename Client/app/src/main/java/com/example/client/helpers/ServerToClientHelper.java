package com.example.client.helpers;

import com.example.client.classes.Pet;
import com.example.client.classes.User;
import com.example.client.server_models.ServerPet;
import com.example.client.server_models.ServerUser;

import java.util.Date;

public class ServerToClientHelper {

    public static Pet toClientPet(ServerPet serverPet) {
        Pet newPet = new Pet(serverPet.getId());
        newPet.setName(serverPet.getName());
        newPet.setSex(serverPet.getSex());
        newPet.setType(serverPet.getType());
        Date dateBirth = new Date(serverPet.getAge());
        newPet.setDateOfBirth(dateBirth);
        return newPet;
    }

    public static ServerPet toServerPet(Pet pet, int ownerFamId) {
        ServerPet newPet = new ServerPet();
        newPet.setId(pet.getId());
        newPet.setOwnerFamilyId(ownerFamId);
        newPet.setName(pet.getName());
        newPet.setSex(pet.getSex());
        newPet.setType(pet.getType());
        newPet.setAge(pet.getDateOfBirth().getTime());
        return newPet;
    }


    public static User toClientUser(ServerUser serverUser) {
        User newUser = new User();
        newUser.setId(serverUser.id);
        newUser.setFamilyId(serverUser.familyId);
        newUser.setLogin(serverUser.login);
        newUser.setName(serverUser.name);
        newUser.setPassword(serverUser.password);
        newUser.setHeadOfFamily(serverUser.isHeadOfFamily);
        return newUser;
    }




}
