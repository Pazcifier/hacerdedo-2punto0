/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author estudiante.fit
 */
public class Punto {
    private double longitud = 0;
    private double latitud = 0;
    
    public Punto(double lon, double lat) {
        longitud = lon;
        latitud = lat;
    }

    //Punto() {}
    
    public ArrayList<Double> getPunto() {
        ArrayList<Double> punto = new ArrayList<Double>();
        punto.add(longitud);
        punto.add(latitud);
        return punto;
    }

    public double getLatitud() {
        return latitud;
    }

    public double getLongitud() {
        return longitud;
    }

    @Override
    public String toString() {
        return "Punto{" + "longitud=" + longitud + ", latitud=" + latitud + '}';
    }
    
}
