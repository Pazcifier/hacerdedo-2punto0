/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Model.Vehicle;
import DB.ConnectionFactory;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author estudiante.fit
 */
public class DAOVehicle implements DAOInterface<Vehicle> {

    private Vehicle extract(ResultSet rs) throws SQLException {
        Vehicle v = new Vehicle();
                
        v.setPlate(rs.getString("plate"));
        v.setOwner_id(rs.getInt("owner_id"));
        v.setModel(rs.getString("model"));
        v.setType(rs.getString("type"));
        v.setNumber_seats(rs.getInt("number_seats"));
                
        return v;
    }
    
    @Override
    public Vehicle get(int plate) {
        Connection con = ConnectionFactory.getConnection();
        String query = String.format("SELECT * FROM vehicles WHERE plate = %s", plate);
        try {
            Statement selectOne = con.createStatement();
            ResultSet rs = selectOne.executeQuery(query);
            
            while(rs.next()) {
                extract(rs);
            }
        } catch(SQLException sqle) {
            sqle.printStackTrace();
        }
        finally {
            ConnectionFactory.closeConnection(con);
        }
        return null;
    }

    @Override
    public List<Vehicle> getAll() {
        Connection con = ConnectionFactory.getConnection();
        String query = "SELECT * FROM vehicles";
        List<Vehicle> vehicles = new ArrayList<Vehicle>();
        try {
            Statement selectAll = con.createStatement();
            ResultSet rs = selectAll.executeQuery(query);
            
            while (rs.next()) {
                Vehicle v = extract(rs);
                vehicles.add(v);
            }
            return vehicles;
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }
        finally {
            ConnectionFactory.closeConnection(con);
        }
        return null;
    }

    @Override
    public boolean insert(Vehicle v) {
        Connection con = ConnectionFactory.getConnection();
        String query = "INSERT INTO vehicles(plate, owner_id, modle, type, number_seats) VALUES (?, ?, ?, ?, ?)";
        try {
            PreparedStatement ps = con.prepareStatement(query);
            if (ps != null) {
                ps.setString(1, v.getPlate());
                ps.setInt(2, v.getOwner_id());
                ps.setString(3, v.getModel());
                ps.setString(4, v.getType());
                ps.setInt(5, v.getNumber_seats());
                
                int i = ps.executeUpdate();
                
                if (i == 1) {
                    return true;
                }
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }
        finally {
            ConnectionFactory.closeConnection(con);
        }
        return false;
    }

    @Override
    public boolean update(Vehicle v) {
        Connection con = ConnectionFactory.getConnection();
        String query = String.format("UPDATE vehicles SET model = ?, type = ?, number_seats = ? WHERE plate = ?");
        try {
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, v.getModel());
            ps.setString(2, v.getType());
            ps.setInt(3, v.getNumber_seats());
            
            int i = ps.executeUpdate();
            
            if (i == 1) {
                return true;
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }
        finally {
            ConnectionFactory.closeConnection(con);
        }
        return false;
    }

    @Override
    public boolean delete(Vehicle v) {
        Connection con = ConnectionFactory.getConnection();
        String query = String.format("DELETE FROM vehicles WHERE plate = '%s'", v.getPlate());
        try {
            Statement delete = con.createStatement();
            int i = delete.executeUpdate(query);
            
            if (i == 1) {
                return true;
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }
        finally {
            ConnectionFactory.closeConnection(con);
        }
        return false;
    }

}
