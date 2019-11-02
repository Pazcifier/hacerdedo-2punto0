/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.sql.Date;
import java.sql.Timestamp;

/**
 *
 * @author estudiante.fit
 */
public class Travel {
    int ci_driver;
    int ci_passenger;
    Timestamp date_ini;
    Timestamp date_end = null;
    String matricula_vehicle;
    int seats_available;
    int companions;
    String progress = "INCOMPLETE";

    public Travel(int ci_d, int ci_p, Timestamp date_i, String matricula, int seats, int companions) {
        this.ci_driver = ci_d;
        this.ci_passenger = ci_p;
        this.date_ini = date_i;
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

    public Timestamp getDate_ini() {
        return date_ini;
    }

    public void setDate_ini(Timestamp date_ini) {
        this.date_ini = date_ini;
    }

    public Timestamp getDate_end() {
        return date_end;
    }

    public void setDate_end(Timestamp date_end) {
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

    public String getProgress() {
        return progress;
    }

    public void setProgress(String progress) {
        this.progress = progress;
    }

    @Override
    public String toString() {
        return "Travel{" + "ci_driver=" + ci_driver + ", ci_passenger=" + ci_passenger + ", date_ini=" + date_ini + ", date_end=" + date_end + ", matricula_vehicle=" + matricula_vehicle + ", seats_available=" + seats_available + ", companions=" + companions + ", progress=" + progress + '}';
    }
    
}
