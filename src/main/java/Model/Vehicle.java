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
public class Vehicle {
    String plate;
    int owner_id;
    String model;
    String type;
    int number_seats;
    
    public Vehicle(String plate, int owner_id, String model, String type, int number_seats) {
        this.plate = plate;
        this.owner_id = owner_id;
        this.model = model;
        this.type = type;
        this.number_seats = number_seats;
    }

    public Vehicle() {}

    public String getPlate() {
        return plate;
    }

    public void setPlate(String plate) {
        this.plate = plate;
    }

    public int getOwner_id() {
        return owner_id;
    }

    public void setOwner_id(int owner_id) {
        this.owner_id = owner_id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
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
