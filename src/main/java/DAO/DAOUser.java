/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;
import Model.User;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import DB.ConnectionFactory;

/**
 *
 * @author estudiante.fit
 */
public class DAOUser implements DAOInterface<User> {
    
    private User extract(ResultSet rs) throws SQLException {
        User u = new User();
                
        u.setCi(Integer.parseInt(rs.getString("ci")));
        u.setName(rs.getString("name"));
        u.setSurname(rs.getString("surname"));
        u.setTelephone(rs.getString("telephone"));
        u.setRating(rs.getDouble("rating"));
        u.setType(rs.getString("type"));
        u.setPassword(rs.getString("password"));
                
        return u;
    }
    
    @Override
    public User get(int id) {
        Connection con = ConnectionFactory.getConnection();
        String query = String.format("SELECT * FROM users WHERE ci = '%s'", id);
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

    @Override
    public List<User> getAll() {
        Connection con = ConnectionFactory.getConnection();
        String query = "SELECT * FROM users";
        List<User> usuarios = new ArrayList<User>();
        try {
            Statement selectAll = con.createStatement();
            ResultSet rs = selectAll.executeQuery(query);
            
            while (rs.next()) {
                User u = extract(rs);
                usuarios.add(u);
            }
            return usuarios;
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }
        finally {
            ConnectionFactory.closeConnection(con);
        }
        return null;
    }

    @Override
    public boolean insert(User u) {
        Connection con = ConnectionFactory.getConnection();
        String query = "INSERT INTO users(ci, name, surname, telephone, password) VALUES (?, ?, ?, ?, ?)";
        try {
            PreparedStatement ps = con.prepareStatement(query);
            if (ps != null) {
                ps.setInt(1, u.getCi());
                ps.setString(2, u.getName());
                ps.setString(3, u.getSurname());
                ps.setString(4, u.getTelephone());
                ps.setString(5, u.getPassword());
                
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
    public boolean update(User u) {
        Connection con = ConnectionFactory.getConnection();
        String query = String.format("UPDATE users SET name = ?, surname = ?, telephone = ?, password = ? WHERE ci = ?");
        try {
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, u.getName());
            ps.setString(2, u.getSurname());
            ps.setString(3, u.getTelephone());
            ps.setString(4, u.getPassword());
            
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
    public boolean delete(User u) {
        Connection con = ConnectionFactory.getConnection();
        String query = String.format("DELETE FROM users WHERE ci = '%s'", u.getCi());
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
