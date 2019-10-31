/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Model.Friend;
import DB.ConnectionFactory;
import java.sql.*;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author estudiante.fit
 */
public class DAOFriend implements DAOInterface<Friend>{

    private Friend extract(ResultSet rs) throws SQLException {
        Friend f = new Friend();
                
        f.setCi_user(rs.getInt("ci_user"));
        f.setCi_friend(rs.getInt("ci_friend"));
                
        return f;
    }
    
    @Override
    public Friend get(int id) {
        return null;
    }

    public List<Friend> getAllFromUser(int ci) {
        Connection con = ConnectionFactory.getConnection();
        String query = String.format("SELECT * FROM friends WHERE ci_user = '%s'", ci);
        List<Friend> friends = new ArrayList<Friend>();
        try {
            Statement selectAll = con.createStatement();
            ResultSet rs = selectAll.executeQuery(query);
            
            while (rs.next()) {
                Friend v = extract(rs);
                friends.add(v);
            }
            return friends;
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }
        finally {
            ConnectionFactory.closeConnection(con);
        }
        return null;
    }
    
    @Override
    public List<Friend> getAll() {
        Connection con = ConnectionFactory.getConnection();
        String query = "SELECT * FROM friends";
        List<Friend> friends = new ArrayList<Friend>();
        try {
            Statement selectAll = con.createStatement();
            ResultSet rs = selectAll.executeQuery(query);
            
            while (rs.next()) {
                Friend v = extract(rs);
                friends.add(v);
            }
            return friends;
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }
        finally {
            ConnectionFactory.closeConnection(con);
        }
        return null;
    }

    @Override
    public boolean insert(Friend f) {
        Connection con = ConnectionFactory.getConnection();
        String query = "INSERT INTO friends(ci_user, ci_friend) VALUES (?, ?)";
        try {
            con.setAutoCommit(false);
            PreparedStatement ps = con.prepareStatement(query);
            if (ps != null) {
                ps.setInt(1, f.getCi_user());
                ps.setInt(2, f.getCi_friend());
                
                int i = ps.executeUpdate();
                
                ps.setInt(1, f.getCi_friend());
                ps.setInt(2, f.getCi_user());
                
                int j = ps.executeUpdate();
                
                con.setAutoCommit(true);
                
                if (i == 1 && j == 1) {
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
    public boolean update(Friend f) {
        return false;
    }

    
    //Revisar bien este
    @Override
    public boolean delete(Friend f) {
        Connection con = ConnectionFactory.getConnection();
        String query = "DELETE FROM friends WHERE ci_user = ? AND ci_friend = ?";
        try {
            con.setAutoCommit(false);
            PreparedStatement ps = con.prepareStatement(query);
            if (ps != null) {
                ps.setInt(1, f.getCi_user());
                ps.setInt(2, f.getCi_friend());
                
                int i = ps.executeUpdate();
                
                ps.setInt(1, f.getCi_friend());
                ps.setInt(2, f.getCi_user());
                
                int j = ps.executeUpdate();
                
                con.setAutoCommit(true);
                
                if (i == 1 && j == 1) {
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
}
