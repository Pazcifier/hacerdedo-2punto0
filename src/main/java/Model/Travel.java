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
public class Travel {
    int ci_driver;
    int ci_passenger;
    //POR CAMBIAR: Dates
    String date_ini;
    String date_end = null;
    String matricula_vehicle;
    int seats_available;
    int companions;

    public Travel(int ci_d, int ci_p, String date_i, String date_e, String matricula, int seats, int companions) {
        this.ci_driver = ci_d;
        this.ci_passenger = ci_p;
        this.date_ini = date_i;
        this.date_end = date_e;
        this.matricula_vehicle = matricula;
        this.seats_available = seats;
        this.companions = companions;
    }
    
    public Travel() {}

    public int getCi_driver() {
        return ci_driver;
    }

    public void setCi_driver(int ci_driver) {
        this.ci_driver = ci_driver;
    }

    public int getCi_passenger() {
        return ci_passenger;
    }

    public void setCi_passenger(int ci_passenger) {
        this.ci_passenger = ci_passenger;
    }

    public String getDate_ini() {
        return date_ini;
    }

    public void setDate_ini(String date_ini) {
        this.date_ini = date_ini;
    }

    public String getDate_end() {
        return date_end;
    }

    public void setDate_end(String date_end) {
        this.date_end = date_end;
    }

    public String getMatricula_vehicle() {
        return matricula_vehicle;
    }

    public void setMatricula_vehicle(String matricula_vehicle) {
        this.matricula_vehicle = matricula_vehicle;
    }

    public int getSeats_available() {
        return seats_available;
    }

    public void setSeats_available(int seats_available) {
        this.seats_available = seats_available;
    }

    public int getCompanions() {
        return companions;
    }

    public void setCompanions(int companions) {
        this.companions = companions;
    }
    
    
}
