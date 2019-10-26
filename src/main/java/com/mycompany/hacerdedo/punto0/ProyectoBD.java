/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.hacerdedo.punto0;

import DB.ConnectionFactory;
import Clases.*;
import DAO.DAOFriend;
import Model.User;
import DAO.DAOUser;
import Model.Friend;

import com.mapbox.api.directions.v5.MapboxDirections;
import com.mapbox.geojson.Point;

import java.sql.*;
import java.util.ArrayList;

/**
 *
 * @author estudiante.fit
 */

public class ProyectoBD {
    private static void login(Connection con, int cedula, String password) throws SQLException {
        try {
            Statement select = con.createStatement();
            String login = String.format("SELECT * FROM usuario WHERE cedula = %s AND password = '%s'", 
                    cedula, password);
            
            select.executeQuery(login);
            System.out.println("Login exitoso");
            
        } catch(SQLException sqle) {
            System.out.println("Usuario o contraseña incorrectos");
        }
    }
    
    private static double distanciaPuntoRecta(double mChofer, double bChofer, Punto origenPasajero) {        
        double xPasajero = origenPasajero.getLongitud();
        double yPasajero = origenPasajero.getLatitud();
        
        double numerador = (-mChofer * xPasajero) + yPasajero - bChofer;
        numerador = Math.abs(numerador);
        double denominador = mChofer*mChofer + 1;
        denominador = Math.sqrt(denominador);
        
        return numerador/denominador;    
    }
    
    private static ArrayList<Punto> checkCompatibilidad(Ruta chofer, Ruta pasajero) {
        ArrayList<Punto> rutaFinal = new ArrayList<Punto>();
        
        double x1Chofer = chofer.getOrigen().getLongitud();
        double x2Chofer = chofer.getDestino().getLongitud();
        double y1Chofer = chofer.getOrigen().getLatitud();
        double y2Chofer = chofer.getDestino().getLatitud();
        
        double x2Pasajero = pasajero.getDestino().getLongitud();
        double y2Pasajero = pasajero.getDestino().getLatitud();
        
        if (x2Pasajero == x2Chofer && y2Pasajero == y2Chofer) {
            System.out.println("Compatibilidad 50%: Compatibles en destino");
            
            double mChofer;
            double bChofer;
            
            try {
            
            mChofer = (y2Chofer - y1Chofer) / (x2Chofer - x1Chofer);
            bChofer = y1Chofer - mChofer * x1Chofer;
                
            } catch(ArithmeticException e) {
                System.out.println("Error en cálculo de compatibilidad: " + e);
                return rutaFinal;
            }
            
            if (distanciaPuntoRecta(mChofer, bChofer, pasajero.getOrigen()) <= 5) {
                System.out.println("Compatibles 100%: Pasajero está en la ruta o cerca de la ruta del chofer");
                rutaFinal.add(chofer.getOrigen());
                rutaFinal.add(pasajero.getOrigen());
                rutaFinal.add(chofer.getDestino());
                
                return rutaFinal;
            }
        }
        System.out.println("Compatibles 0%: Ambas personas no se dirigen al mismo destino");
        return rutaFinal; 
    }
    
    public static void main(String[] args) {
        Connection con = ConnectionFactory.getConnection();
        System.out.println("Estableciendo conexión con " + ConnectionFactory.getDBHOST());
        /*
        MapboxDirections.Builder client;
        
        test test = new test();
        Point destination = Point.fromLngLat(25.9383791, 61.1698556);
        test.getRoute(destination);
        */
        Punto p1 = new Punto(40,20);
        Punto p2 = new Punto(10,40);
        
        Punto p3 = new Punto(28,30);
        Punto p4 = new Punto(10,40);
        
        Ruta rC = new Ruta(p1, p2);
        Ruta rP = new Ruta(p3, p4);
        
        //System.out.println(checkCompatibilidad(rC, rP));
        
        try {
        //Conexión con Base de Datos de VM
        //Conexión = jbdc:postgresql://IP/NombreBD,usuario,contraseña
        /*
        con =
            DriverManager.getConnection("jdbc:postgresql://192.168.56.101/basededatos1","postgres","12345");
        */
        //con = getConnection();
        
        
        if (!con.isClosed()) {
            System.out.println("Conexión exitosa");
        }
            test test = new test();
            Point origen = Point.fromLngLat(-34.8411056,-55.9945361);
            Point destination = Point.fromLngLat(-34.8811342,-56.083531);
            test.obtenerRuta(origen, destination);
            
            
 
        }catch(Exception e){
            System.out.println("Exception: " + e.getMessage());
        }
        
        finally {
            ConnectionFactory.closeConnection(con);
        }
    }
    
}
