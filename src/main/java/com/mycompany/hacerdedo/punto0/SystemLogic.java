/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.hacerdedo.punto0;

import Clases.PostedDriver;
import Clases.PostedPassenger;
import Clases.Punto;
import Clases.Ruta;
import DAO.DAOFriend;
import DAO.DAOTravel;
import DAO.DAOUser;
import java.sql.*;
import DB.ConnectionFactory;
import Model.Friend;
import Model.Travel;
import Model.User;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author estudiante.fit
 */
public class SystemLogic {
    
    private ArrayList<PostedDriver> driverTravels = new ArrayList<PostedDriver>();
    private ArrayList<PostedPassenger> passengerTravels = new ArrayList<PostedPassenger>();
    
    private ArrayList<PostedDriver> driverInProgress = new ArrayList<PostedDriver>();
    private ArrayList<PostedPassenger> passengerInProgress = new ArrayList<PostedPassenger>();

    public static SystemLogic sl;
    public static SystemLogic initSystemLogic()
    {
        if (sl == null) {
            sl = new SystemLogic();
        }
        return sl;
    }
    public PostedDriver postDriverTravel(String matricula, Ruta route, int ci, int seats_available) {
        PostedDriver pD = new PostedDriver(matricula, route, ci, seats_available);
        driverTravels.add(pD);
        return pD;
    }
    
    public PostedPassenger postPassengerTravel(Ruta route, int ci, int companions) {
        PostedPassenger pP = new PostedPassenger(route, ci, companions);
        passengerTravels.add(pP);
        return pP;
    }
    
    public void checkPostCompatibillity() {
        ArrayList<PostedDriver> toDeleteDriver = new ArrayList<PostedDriver>();
        ArrayList<PostedPassenger> toDeletePassenger = new ArrayList<PostedPassenger>();
        
        DAOTravel dT = new DAOTravel();
        for (PostedPassenger pP : passengerTravels) {
            for (PostedDriver pD : driverTravels) {
                if (checkCompatibilidad(pD.getRoute(), pP.getRoute())) {
                    Travel t = new Travel(pD.getCedula(), pP.getCedula(), new Timestamp(System.currentTimeMillis()), pD.getMatricula(), pD.getSeats_available(), pP.getCompanions());
                    if (dT.insert(t)) {
                        toDeleteDriver.add(pD);
                        toDeletePassenger.add(pP);
                    }
                    else {
                        System.out.println("Error de inserción");
                    }
                }
            }
        }
        passengerTravels.removeAll(toDeletePassenger);
        driverTravels.removeAll(toDeleteDriver);
    }
    
        public boolean checkPostCompatibillityPassenger(PostedPassenger pP) {
        ArrayList<PostedDriver> toDeleteDriver = new ArrayList<PostedDriver>();
        ArrayList<PostedPassenger> toDeletePassenger = new ArrayList<PostedPassenger>();
        
        DAOTravel dT = new DAOTravel();
            for (PostedDriver pD : driverTravels) {
                if (checkCompatibilidad(pD.getRoute(), pP.getRoute())) {
                    Travel t = new Travel(pD.getCedula(), pP.getCedula(), new Timestamp(System.currentTimeMillis()), pD.getMatricula(), pD.getSeats_available(), pP.getCompanions());
                    if (dT.insert(t)) {
                        toDeleteDriver.add(pD);
                        toDeletePassenger.add(pP);
                        driverInProgress.add(pD);
                        passengerInProgress.add(pP);
                    }
                    passengerTravels.removeAll(toDeletePassenger);
                    driverTravels.removeAll(toDeleteDriver);
                    return true;
                }
            }
            return false;
    }

        public boolean checkPostCompatibillityDriver(PostedDriver pD) {
        ArrayList<PostedDriver> toDeleteDriver = new ArrayList<PostedDriver>();
        ArrayList<PostedPassenger> toDeletePassenger = new ArrayList<PostedPassenger>();
        
        DAOTravel dT = new DAOTravel();
            for (PostedPassenger pP : passengerTravels) {
                if (checkCompatibilidad(pD.getRoute(), pP.getRoute())) {
                    Travel t = new Travel(pD.getCedula(), pP.getCedula(), new Timestamp(System.currentTimeMillis()), pD.getMatricula(), pD.getSeats_available(), pP.getCompanions());
                    if (dT.insert(t)) {
                        toDeleteDriver.add(pD);
                        toDeletePassenger.add(pP);
                        driverInProgress.add(pD);
                        passengerInProgress.add(pP);
                    }
                    passengerTravels.removeAll(toDeletePassenger);
                    driverTravels.removeAll(toDeleteDriver);
                    return true;
                }
            }
            return false;
    }
    
    public boolean simulateTravel() {
        if (passengerInProgress.size() > 0 && driverInProgress.size() > 0) {
            System.out.println("Viajes en curso...");
            DAOTravel dT = new DAOTravel();
            try {
                for (PostedPassenger pP : passengerInProgress) {
                    for (PostedDriver pD : driverInProgress) {
                        Thread.sleep(10000);
                        Travel t = dT.getIncompleteTravel(pD.getCedula(), pP.getCedula());
                        t.setDate_end(new Timestamp(System.currentTimeMillis()));
                        if (dT.update(t)) {
                            return true;
                        } else {
                            return false;
                        }
                    }
                }
            } catch(Exception e) {
                System.out.println("Exception: " + e);
            }
        }
        return false;
    }
    
    public boolean login(String cedula, String password) throws SQLException {
        Connection con = ConnectionFactory.getConnection();
        boolean estado =false;
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
                estado = true;
            }
            
            
        } catch(SQLException sqle) {
            System.out.println("Error de conexión a la base de datos: " + sqle);
        }
        finally {
            ConnectionFactory.closeConnection(con);
        }
        return estado;
    }
    
    private double distanciaPuntoRecta(double mChofer, double bChofer, Punto origenPasajero) {        
        double xPasajero = origenPasajero.getLongitud();
        double yPasajero = origenPasajero.getLatitud();
        
        double numerador = (-mChofer * xPasajero) + yPasajero - bChofer;
        numerador = Math.abs(numerador);
        double denominador = mChofer*mChofer + 1;
        denominador = Math.sqrt(denominador);
        
        return numerador/denominador;    
    }
    
    public boolean checkCompatibilidad(Ruta chofer, Ruta pasajero) {
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
                return false;
            }
            
            if (distanciaPuntoRecta(mChofer, bChofer, pasajero.getOrigen()) <= 5) {
                System.out.println("Compatibles 100%: Pasajero está en la ruta o cerca de la ruta del chofer");
                return true;
            }
        }
        System.out.println("Compatibles 0%: Ambas personas no se dirigen al mismo destino");
        return false;
    }
    
    public ArrayList<User> friendsOfFriends(User u) {
        DAOFriend dF = new DAOFriend();
        DAOUser dU = new DAOUser();
        
        List<Friend> friends = dF.getAllFromUser(u.getCi());
        ArrayList<User> recommended = new ArrayList<User>();
        
        for (Friend f : friends) {
            int cedulaAmigo = f.getCi_friend();
            List<Friend> amigosAmigo = dF.getAllFromUser(cedulaAmigo);
            for (Friend f2 : amigosAmigo) {
                int cedulaAmigoAmigo = f2.getCi_friend();
                User possibleRecommend = dU.get(cedulaAmigoAmigo);
                if ((cedulaAmigoAmigo != u.getCi()) && !(u.getRecommended().contains(possibleRecommend))) {
                    recommended.add(possibleRecommend);
                }
            }
        }
        u.setRecommended(recommended);
        return u.getRecommended();
    }

    public ArrayList<PostedDriver> getDriverTravels() {
        return driverTravels;
    }

    public void setDriverTravels(ArrayList<PostedDriver> driverTravels) {
        this.driverTravels = driverTravels;
    }

    public ArrayList<PostedPassenger> getPassengerTravels() {
        return passengerTravels;
    }

    public void setPassengerTravels(ArrayList<PostedPassenger> passengerTravels) {
        this.passengerTravels = passengerTravels;
    }

    @Override
    public String toString() {
        return "SystemLogic{" + "driverTravels=" + driverTravels + ", passengerTravels=" + passengerTravels + '}';
    }
    
    
}
