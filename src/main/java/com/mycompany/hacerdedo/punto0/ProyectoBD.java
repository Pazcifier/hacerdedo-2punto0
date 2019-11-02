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
import java.util.List;

/**
 *
 * @author estudiante.fit
 */

public class ProyectoBD {
    private static void login(Connection con, String cedula, String password) throws SQLException {
        try {
            Statement select = con.createStatement();
            String login = String.format("SELECT * FROM users WHERE ci = '%s' AND password = '%s'", 
                    cedula, password);
            
            ResultSet rs = select.executeQuery(login);
            
            int i = 0;
            while(rs.next()) {
                i++;
            }
            
            if (i == 1) {
                System.out.println("Login exitoso.");
            }
            else {
                System.out.println("Usuario o contraseña incorrectos");
            }
            
            
        } catch(SQLException sqle) {
            System.out.println("Error de conexión a la base de datos: " + sqle);
        }
    }
    
    private static double distanciaPuntoRecta(double mChofer, double bChofer, Punto origenPasajero) {        
        double xPasajero = origenPasajero.getLongitud();
        double yPasajero = origenPasajero.getLatitud();
        
        double numerador = (-mChofer * xPasajero) + yPasajero - bChofer;
        numerador = Math.abs(numerador);
        double denominador = mChofer*mChofer + 1;
        denominador = Math.sqrt(denominador);
        
        System.out.println(numerador/denominador);
        
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
        System.out.println("Estableciendo conexión con base de datos");
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
            /*
            test test = new test();
            Point origen = Point.fromLngLat(-34.8411056,-55.9945361);
            Point destination = Point.fromLngLat(-34.8811342,-56.083531);
            test.obtenerRuta(origen, destination);
            */
            
            DAOUser u = new DAOUser();
            User user = new User(12345672, "Jose", "Paz", "094974932", "123");
            //u.insert(user);
            
            DAOFriend f = new DAOFriend();
            Friend friend = new Friend(63043005, 63341075);
            //f.insert(friend);
            
            System.out.println(u.get(63043005).toString());
            
            User usuario = u.get(63341075);
            
            List<Friend> amigos = f.getAllFromUser(usuario.getCi());
            
            //Más o menos bien, garantizar que no te devuelva a ti mismo y repetidos
            for (Friend a:amigos) {
                int cedula = a.getCi_friend();
                List<Friend> amigosSegundo = f.getAllFromUser(cedula);
                for (Friend b:amigosSegundo) {
                    int cedulaB = b.getCi_friend();
                    User possibleRecommend = u.get(cedulaB);
                    if ((cedulaB != usuario.getCi()) && !(usuario.getRecommended().contains(possibleRecommend))) {
                        usuario.getRecommended().add(possibleRecommend);
                    }
                }
            }
            
            System.out.println(usuario.getRecommended().toString());
        
        } catch(Exception e){
            System.out.println("Exception: " + e.getMessage());
        }
        
        finally {
            ConnectionFactory.closeConnection(con);
        }
    }   
}
