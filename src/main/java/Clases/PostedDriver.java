/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

/**
 *
 * @author estudiante.fit
 */
public class PostedDriver {
    private String matricula;
    private Ruta route;
    private int cedula;
    private int seats_available;
    
    public PostedDriver(String matricula, Ruta route, int cedula, int seats) {
        this.matricula = matricula;
        this.route = route;
        this.cedula = cedula;
        this.seats_available = seats;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public Ruta getRoute() {
        return route;
    }

    public void setRoute(Ruta route) {
        this.route = route;
    }

    public int getCedula() {
        return cedula;
    }

    public void setCedula(int cedula) {
        this.cedula = cedula;
    }

    public int getSeats_available() {
        return seats_available;
    }

    public void setSeats_available(int seats_available) {
        this.seats_available = seats_available;
    }

    @Override
    public String toString() {
        return "PostedDriver{" + "matricula=" + matricula + ", route=" + route + ", cedula=" + cedula + ", seats_available=" + seats_available + '}';
    }
    
    
    
}
