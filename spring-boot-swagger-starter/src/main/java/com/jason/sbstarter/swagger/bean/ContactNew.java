package com.jason.sbstarter.swagger.bean;

import java.io.Serializable;

/**
 * @author JasonLu
 */
public class ContactNew implements Serializable {


    private static final long serialVersionUID = -372421614509053212L;

    private String name;
    private String url;
    private String email;


    public ContactNew() {
    }

    public ContactNew(String name, String url, String email) {
        this.name = name;
        this.url = url;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public String getUrl() {
        return url;
    }

    public String getEmail() {
        return email;
    }
}
