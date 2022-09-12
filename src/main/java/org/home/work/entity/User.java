package org.home.work.entity;

import java.util.List;

/**
 * user information class.
 */
public class User {
    /**
     * user unique id
     */
    private Integer id;
    /**
     * user name
     */
    private String name;
    /**
     * user password
     */
    private String password;

//    public User(Integer id, String name, String password, List<Role> roles) {
//        this.id = id;
//        this.name = name;
//        this.password = password;
//        this.roles = roles;
//    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
