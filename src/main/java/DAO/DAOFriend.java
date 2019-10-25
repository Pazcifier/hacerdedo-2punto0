/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Model.Friend;
import com.mycompany.hacerdedo.punto0.ConnectionFactory;
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
                
        f.setUser_id(rs.getInt("user_id"));
        f.setFriend_id(rs.getInt("friend_id"));
                
        return f;
    }
    
    @Override
    public Friend get(int id) {
        Connection con = ConnectionFactory.getConnection();
        String query = String.format("SELECT * FROM friends WHERE plate = %s", id);
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
        String query = "INSERT INTO friends(user_id, friend_id) VALUES (?, ?)";
        try {
            con.setAutoCommit(false);
            PreparedStatement ps = con.prepareStatement(query);
            if (ps != null) {
                ps.setInt(1, f.getUser_id());
                ps.setInt(2, f.getFriend_id());
                
                int i = ps.executeUpdate();
                
                ps.setInt(1, f.getFriend_id());
                ps.setInt(2, f.getUser_id());
                
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

    @Override
    public boolean delete(Friend f) {
        Connection con = ConnectionFactory.getConnection();
        String query = String.format("DELETE FROM friends WHERE user_id = '%s'", f.getUser_id());
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
