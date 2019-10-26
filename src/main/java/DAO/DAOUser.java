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
                
        u.setId(rs.getInt("id"));
        u.setName(rs.getString("name"));
        u.setLast_name(rs.getString("last_name"));
        u.setPhone(rs.getString("phone"));
        u.setRating(rs.getDouble("rating"));
        u.setType(rs.getString("type"));
        u.setPassword(rs.getString("password"));
                
        return u;
    }
    
    @Override
    public User get(int id) {
        Connection con = ConnectionFactory.getConnection();
        String query = String.format("SELECT * FROM users WHERE id = %s", id);
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
        String query = "INSERT INTO users(id, name, last_name, phone, password) VALUES (?, ?, ?, ?, ?)";
        try {
            PreparedStatement ps = con.prepareStatement(query);
            if (ps != null) {
                ps.setInt(1, u.getId());
                ps.setString(2, u.getName());
                ps.setString(3, u.getLast_name());
                ps.setString(4, u.getPhone());
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
        String query = String.format("UPDATE users SET name = ?, last_name = ?, phone = ?, password = ? WHERE id = ?");
        try {
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, u.getName());
            ps.setString(2, u.getLast_name());
            ps.setString(3, u.getPhone());
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
        String query = String.format("DELETE FROM users WHERE id = %s", u.getId());
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
