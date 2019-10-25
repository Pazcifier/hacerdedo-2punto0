/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.hacerdedo.punto0;

import Clases.*;
import Model.Usuario;
import DAO.DAOUser;

import com.mapbox.api.directions.v5.MapboxDirections;
import com.mapbox.geojson.Point;

import java.net.URI;
import java.net.URISyntaxException;
import java.sql.*;
import java.sql.DriverManager;

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
    
    private static boolean checkCompatibilidad(Ruta chofer, Ruta pasajero) {
        double x1Chofer = chofer.getOrigen().getLongitud();
        double x2Chofer = chofer.getDestino().getLongitud();
        double y1Chofer = chofer.getOrigen().getLatitud();
        double y2Chofer = chofer.getDestino().getLatitud();
        
        double x1Pasajero = pasajero.getOrigen().getLongitud();
        double y1Pasajero = pasajero.getOrigen().getLatitud();
        double x2Pasajero = pasajero.getDestino().getLongitud();
        double y2Pasajero = pasajero.getDestino().getLatitud();
        
        if (x2Pasajero == x2Chofer && y2Pasajero == y2Chofer) {
            System.out.println("Compatibilidad 50%: Compatibles en destino");
            
            double mChofer;
            double bChofer;
            
            try {
            mChofer = (y2Chofer - y1Chofer) / (x2Chofer - x1Chofer);

            //double mPasajero = (y2Pasajero - y1Pasajero) / (x2Chofer - x1Pasajero);
            
            bChofer = y1Chofer - mChofer * x1Chofer;
            
            //double bPasajero = y1Pasajero - mPasajero * x1Pasajero;
            
            //double x0 = -(bChofer - bPasajero)/(mChofer - mPasajero);
                
            } catch(ArithmeticException e) {
                System.out.println("Error en cálculo de compatibilidad: " + e);
                return false;
            }
            
            if (y1Pasajero == mChofer * x1Pasajero + bChofer) {
                System.out.println("Compatibles 100%: Pasajero está en la ruta del chofer");
                return true;
            } else if (true) {
                //Implementar en la condición proyección de punto a un segmento
                System.out.println("Compatibles 100%: Pasajero está cerca de la ruta del chofer");
                return true;
            }
            System.out.println("Falla de compatibilidad: Pasajero está muy lejos de la ruta del chofer");
        }
        System.out.println("Compatibles 0%: Ambas personas no se dirigen al mismo destino");
        return false; 
    }
    
    public static void main(String[] args) {
        Connection con = ConnectionFactory.getConnection();
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
        
        System.out.println(checkCompatibilidad(rC, rP));
        
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
            DAOUser dU = new DAOUser();
            Usuario u = new Usuario(2, "Marcos", "Pérez Jiménez", "414123456", "12345");

            dU.getAll();
            
            
            
        }catch(Exception e){
            System.out.println("Exception: " + e.getMessage());
        }
        
        finally {
            ConnectionFactory.closeConnection(con);
        }
    }
    
}
