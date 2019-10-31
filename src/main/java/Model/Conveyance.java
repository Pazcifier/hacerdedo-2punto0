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
public class Conveyance {
    String matricula;
    int ci_owner;
    String model;
    String color;
    String type;
    int number_seats;
    
    public Conveyance(String plate, int owner_id, String model, String color, String type, int number_seats) {
        this.matricula = plate;
        this.ci_owner = owner_id;
        this.model = model;
        this.color = color;
        this.type = type;
        this.number_seats = number_seats;
    }

    public Conveyance() {}

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String plate) {
        this.matricula = plate;
    }

    public int getCi_owner() {
        return ci_owner;
    }

    public void setCi_owner(int owner_id) {
        this.ci_owner = owner_id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getNumber_seats() {
        return number_seats;
    }

    public void setNumber_seats(int number_seats) {
        this.number_seats = number_seats;
    }
}
