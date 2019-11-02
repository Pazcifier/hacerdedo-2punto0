/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;
import Model.Travel;
import java.util.List;
import java.sql.*;
import DB.ConnectionFactory;
import java.util.ArrayList;


/**
 *
 * @author estudiante.fit
 */
public class DAOTravel implements DAOInterface<Travel> {
    
    private Travel extract(ResultSet rs) throws SQLException {
        Travel t = new Travel();
                
        t.setCi_driver(Integer.parseInt(rs.getString("ci_driver")));
        t.setCi_passenger(Integer.parseInt(rs.getString("ci_passenger")));
        t.setDate_ini(rs.getTimestamp("date_ini"));
        t.setDate_end(rs.getTimestamp("date_end"));
        t.setMatricula_vehicle(rs.getString("matricula_vehicle"));
        t.setSeats_available(rs.getInt("seats_available"));
        t.setCompanions(rs.getInt("companions"));
        
        return t;
    }
    
    @Override
    public Travel get(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public Travel getIncompleteTravel(int c_chofer, int c_pasajero) {
        Connection con = ConnectionFactory.getConnection();
        String query = String.format("SELECT * FROM travels WHERE ci_driver = '%s' AND ci_passenger = '%s' AND progress = 'INCOMPLETE'", c_chofer, c_pasajero);
        try {
            Statement select = con.createStatement();
            ResultSet rs = select.executeQuery(query);
            
            while (rs.next()) {
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

    @Override
    public List<Travel> getAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public List<Travel> getAllFromUser(int ci) {
        Connection con = ConnectionFactory.getConnection();
        String query = String.format("SELECT * FROM travels WHERE ci_driver = '%s' OR ci_passenger = '%s'", ci, ci);
        List<Travel> travels = new ArrayList<Travel>();
        try {
            Statement selectAll = con.createStatement();
            ResultSet rs = selectAll.executeQuery(query);
            
            while (rs.next()) {
                Travel t = extract(rs);
                travels.add(t);
            }
            return travels;
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }
        finally {
            ConnectionFactory.closeConnection(con);
        }
        return null;
    }

    @Override
    public boolean insert(Travel t) {
        
        DAOConveyance dC = new DAOConveyance();
        if (!dC.validateOwnerVehicle(t.getMatricula_vehicle(), t.getCi_driver())) {
            return false;
        }
        
        Connection con = ConnectionFactory.getConnection();
        String query = "INSERT INTO travels(ci_driver, ci_passenger, date_ini, matricula_vehicle, seats_available, companions) VALUES (?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement ps = con.prepareStatement(query);
            if (ps != null) {
                ps.setInt(1, t.getCi_driver());
                ps.setInt(2, t.getCi_passenger());
                ps.setTimestamp(3, t.getDate_ini());
                ps.setString(4, t.getMatricula_vehicle());
                ps.setInt(5, t.getSeats_available());
                ps.setInt(6, t.getCompanions());
                
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
    public boolean update(Travel t) {
        Connection con = ConnectionFactory.getConnection();
        String query = String.format("UPDATE travels SET date_end = ?, progress = 'COMPLETE' WHERE ci_driver = ? AND ci_passenger = ? AND progress = 'INCOMPLETE'");
        try {
            PreparedStatement ps = con.prepareStatement(query);
            ps.setTimestamp(1, t.getDate_end());
            ps.setString(2, Integer.toString(t.getCi_driver()));
            ps.setString(3, Integer.toString(t.getCi_passenger()));
            
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
    public boolean delete(Travel t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
