package com.brayadiaz.tiendaplaneta;

/**
 * Created by braya on 1/11/2017.
 */

public class User {
    private String name,autor,presta, phone;
    private String uid;

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getPresta() {
        return presta;
    }

    public void setPresta(String presta) {
        this.presta = presta;
    }

    public User(String name, String autor, String presta, String uid, String phone) {
        this.name = name;
        this.autor = autor;
        this.presta = presta;
        this.uid = uid;
        this.phone = phone;

    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public User() {//este lo pide firebase
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }
}
