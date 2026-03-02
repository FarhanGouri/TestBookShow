package org.example.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum Roles {
    User("user"),Admin("admin"),Partner("partner"),Manager("manager");

    @Getter
    private String databaseColumnValue;

    private Roles fromValue(String value){
        switch (value){
            case "user":
                return Roles.User;
            case "admin":
                return Roles.Admin;
            case "partner":
                return Roles.Partner;
            case "manager":
                return Roles.Manager;
            default:
                throw new IllegalArgumentException("Role for ["+value+"] not supported.");
        }
    }
}