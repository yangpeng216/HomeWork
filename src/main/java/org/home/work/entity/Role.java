package org.home.work.entity;

/**
 * role class, the user can have different roles.
 */
public class Role {
    /**
     * role Id
     */
    private Integer id;
    /**
     * role name
     */
    private String name;


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
}
