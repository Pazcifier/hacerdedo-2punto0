/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author estudiante.fit
 */
public class User {
    private int ci;
    private String name;
    private String surname;
    private String telephone;
    private String password;
    private double rating = 0;
    private String type = "Passenger";
    
    public User(int id, String name, String last_name, String phone, String password) {
        this.ci = id;
        this.name = name;
        this.surname = last_name;
        this.telephone = phone;
        this.password = password;
    }

    public User() {}

    public int getCi() {
        return ci;
    }

    public void setCi(int id) {
        this.ci = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String last_name) {
        this.surname = last_name;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String phone) {
        this.telephone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
    
}
