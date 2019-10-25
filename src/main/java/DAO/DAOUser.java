/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;
import Model.Usuario;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import com.mycompany.hacerdedo.punto0.ConnectionFactory;

/**
 *
 * @author estudiante.fit
 */
public class DAOUser implements DAOInterface<Usuario> {
    
    private Usuario extraer(ResultSet rs) throws SQLException {
        Usuario u = new Usuario();
                
        u.setCedula(rs.getInt("cedula"));
        u.setNombre(rs.getString("nombre"));
        u.setApellido(rs.getString("apellido"));
        u.setTelefono(rs.getString("telefono"));
        u.setRating(rs.getInt("rating"));
        u.setTipo(rs.getString("tipo"));
        u.setPassword(rs.getString("password"));
                
        return u;
    }
    
    @Override
    public Usuario get(int cedula) {
        Connection con = ConnectionFactory.getConnection();
        String query = "SELECT * FROM usuario WHERE cedula = " + cedula;
        try {
            Statement selectOne = con.createStatement();
            ResultSet rs = selectOne.executeQuery(query);
            
            while(rs.next()) {
                extraer(rs);
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
    public List<Usuario> getAll() {
        Connection con = ConnectionFactory.getConnection();
        String query = "SELECT * FROM usuario";
        List<Usuario> usuarios = new ArrayList<Usuario>();
        try {
            Statement selectAll = con.createStatement();
            ResultSet rs = selectAll.executeQuery(query);
            
            while (rs.next()) {
                Usuario u = extraer(rs);
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
    public boolean insert(Usuario u) {
        Connection con = ConnectionFactory.getConnection();
        String query = "INSERT INTO usuario(cedula, nombre, apellido, telefono, password) VALUES (?, ?, ?, ?, ?)";
        try {
            PreparedStatement ps = con.prepareStatement(query);
            if (ps != null) {
                ps.setInt(1, u.getCedula());
                ps.setString(2, u.getNombre());
                ps.setString(3, u.getApellido());
                ps.setString(4, u.getTelefono());
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
    public boolean update(Usuario u) {
        Connection con = ConnectionFactory.getConnection();
        String query = String.format("UPDATE usuario SET nombre = ?, apellido = ?, telefono = ?, password = ? WHERE cedula = ?");
        try {
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, u.getNombre());
            ps.setString(2, u.getApellido());
            ps.setString(3, u.getTelefono());
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
    public boolean delete(Usuario u) {
        Connection con = ConnectionFactory.getConnection();
        String query = String.format("DELETE FROM usuario WHERE cedula = %s", u.getCedula());
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
