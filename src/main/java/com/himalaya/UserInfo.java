package com.himalaya;

import lombok.AllArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Setter
@AllArgsConstructor
public class UserInfo implements Serializable {

    private static final long serialVersionUID = -967621291280452430L;

    private String username;
    private int age;

    public String getUsername() {
        return username;
    }

    public int getAge() {
        return age;
    }
}