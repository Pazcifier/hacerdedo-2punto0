/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import java.util.ArrayList;

/**
 *
 * @author estudiante.fit
 */
public class Ruta {
    private Punto origen;
    private Punto destino;
    
    public Ruta(Punto origen, Punto destino) {
        this.origen = origen;
        this.destino = destino;
    }
    
    public ArrayList<Punto> getRuta() {
        ArrayList<Punto> ruta = new ArrayList<Punto>();
        ruta.add(origen);
        ruta.add(destino);
        return ruta;
    }

    public Punto getOrigen() {
        return origen;
    }

    public Punto getDestino() {
        return destino;
    }

    @Override
    public String toString() {
        return "Ruta{" + "origen=" + origen.toString() + ", destino=" + destino.toString() + '}';
    }
    
}
