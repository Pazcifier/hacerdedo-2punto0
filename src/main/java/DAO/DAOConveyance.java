/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this temmatricula file, choose Tools | Temmatriculas
 * and open the temmatricula in the editor.
 */
package DAO;

import Model.Conveyance;
import DB.ConnectionFactory;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author estudiante.fit
 */
public class DAOConveyance implements DAOInterface<Conveyance> {

    private Conveyance extract(ResultSet rs) throws SQLException {
        Conveyance v = new Conveyance();
                
        v.setMatricula(rs.getString("matricula"));
        v.setCi_owner(Integer.parseInt(rs.getString("ci_owner")));
        v.setModel(rs.getString("model"));
        v.setColor(rs.getString("color"));
        v.setType(rs.getString("type"));
        v.setNumber_seats(rs.getInt("number_seats"));
                
        return v;
    }
    
    @Override
    public Conveyance get(int matricula) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public Conveyance getConveyance(String matricula) {
        Connection con = ConnectionFactory.getConnection();
        String query = String.format("SELECT * FROM conveyances WHERE matricula = %s", matricula);
        try {
            Statement selectOne = con.createStatement();
            ResultSet rs = selectOne.executeQuery(query);
            
            while(rs.next()) {
                return extract(rs);
            }
        } catch(SQLException sqle) {
            sqle.printStackTrace();
        }
        finally {
            ConnectionFactory.closeConnection(con);
        }
        return null;
    }
    
    public boolean validateOwnerVehicle(String matricula, int cedula) {
        Connection con = ConnectionFactory.getConnection();
        String query = String.format("SELECT * FROM conveyances WHERE matricula = '%s' AND ci_owner = '%s'", matricula, cedula);
        try {
            Statement select = con.createStatement();
            ResultSet rs = select.executeQuery(query);
            
            int i = 0;
            while(rs.next()) {
                i++;
            }
            
            if (i == 1) {
                return true;
            }
            else {
                return false;
            }
        } catch(SQLException sqle) {
            sqle.printStackTrace();
        }
        finally {
            ConnectionFactory.closeConnection(con);
        }
        return false;
    }

    @Override
    public List<Conveyance> getAll() {
        Connection con = ConnectionFactory.getConnection();
        String query = "SELECT * FROM conveyances";
        List<Conveyance> vehicles = new ArrayList<Conveyance>();
        try {
            Statement selectAll = con.createStatement();
            ResultSet rs = selectAll.executeQuery(query);
            
            while (rs.next()) {
                Conveyance v = extract(rs);
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
    
    public List<Conveyance> getAllFromUser(int ci) {
        Connection con = ConnectionFactory.getConnection();
        String query = String.format("SELECT * FROM conveyances WHERE ci_owner = '%s'", ci);
        List<Conveyance> vehicles = new ArrayList<Conveyance>();
        try {
            Statement selectAll = con.createStatement();
            ResultSet rs = selectAll.executeQuery(query);
            
            while (rs.next()) {
                Conveyance v = extract(rs);
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
    public boolean insert(Conveyance v) {
        
        String matricula = v.getMatricula();
        String[] matriculaSeparada = matricula.split("-");
        
        if (!Validator.onlyLetters(matriculaSeparada[0]) || !Validator.onlyNumbers(matriculaSeparada[1])
                || !Validator.onlyLetters(v.getColor())) {
            return false;
        }
        
        Connection con = ConnectionFactory.getConnection();
        String query = "INSERT INTO conveyances(matricula, ci_owner, model, color, type, number_seats) VALUES (?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement ps = con.prepareStatement(query);
            if (ps != null) {
                ps.setString(1, v.getMatricula());
                ps.setInt(2, v.getCi_owner());
                ps.setString(3, v.getModel());
                ps.setString(4, v.getColor());
                ps.setString(5, v.getType());
                ps.setInt(6, v.getNumber_seats());
                
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
    public boolean update(Conveyance v) {
        
        if (!Validator.onlyLetters(v.getColor())) {
            return false;
        }        
        
        Connection con = ConnectionFactory.getConnection();
        String query = String.format("UPDATE conveyances SET model = ?, color = ?, type = ?, number_seats = ? WHERE matricula = ?");
        try {
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, v.getModel());
            ps.setString(2, v.getColor());
            ps.setString(3, v.getType());
            ps.setInt(4, v.getNumber_seats());
            
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
    public boolean delete(Conveyance v) {
        Connection con = ConnectionFactory.getConnection();
        String query = String.format("DELETE FROM conveyances WHERE matricula = '%s'", v.getMatricula());
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
