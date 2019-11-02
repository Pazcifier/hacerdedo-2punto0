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
public class PostedPassenger {
    private Ruta route;
    private int cedula;
    private int companions;
    
    public PostedPassenger(Ruta route, int cedula, int companions) {
        this.route = route;
        this.cedula = cedula;
        this.companions = companions;
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

    public int getCompanions() {
        return companions;
    }

    public void setCompanions(int companions) {
        this.companions = companions;
    }

    @Override
    public String toString() {
        return "PostedPassenger{" + "route=" + route + ", cedula=" + cedula + ", companions=" + companions + '}';
    }

    
}
