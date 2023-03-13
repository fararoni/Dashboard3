package tese.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import tese.pojo.Usuario;

public class UsuarioDAO  {
    static String sqlCrearTabla = "CREATE TABLE USUARIOS "
            + "(ID PRIMARY KEY,"
            + "nombre  text,"
            + " email text,"
            + " password text"
            + ")";
    
    String sqlInsert = "INSERT INTO  usuarios (usuario, nombre, email, password)" + 
            "   VALUES ( ?, ?, ?, ? )";
    String sqlUpdate  = "update usuarios set nombre = ?,email= ? where id = ? ";
    String sqlDelete = "delete from usuarios where id = ?;";
    String sqlSelectById = "select id,usuario, nombre, email, password from usuarios where id =?";
    String sqlSelectByUsuario = "select id,usuario, nombre, email, password from usuarios where usuario =?";
    String sqlSelectAll ="select * from usuarios";
    
    public boolean crearTabla (){
        return DatabaseDAO.crearTabla(sqlCrearTabla);
        
    }
    
    public int  insert ( Usuario u ) throws SQLException, ClassNotFoundException{
        int cuantos;
        PreparedStatement ps=DatabaseDAO.openConnection().prepareStatement( sqlInsert );
            ps.setString(1,u.getUsuario());  
            ps.setString(2,u.getNombre());  
            ps.setString(3,u.getEmail());  
            ps.setString(4,u.getPassword());  
        cuantos = ps.executeUpdate();
        DatabaseDAO.closeConnection();
        return cuantos;
    }
    
     public int update (Usuario u ) throws SQLException, ClassNotFoundException {
        int cuantos;
        PreparedStatement ps = DatabaseDAO.openConnection().prepareStatement(sqlUpdate);        
            ps.setString(1,u.getNombre());  
            ps.setString(2,u.getEmail());  
            ps.setInt(3,u.getId());  
            cuantos = ps.executeUpdate();
            DatabaseDAO.closeConnection();
        return cuantos;
    }
     
    public int delete(int id) throws SQLException, ClassNotFoundException {
        int cuantos;
        PreparedStatement ps  = DatabaseDAO.openConnection().prepareStatement(sqlDelete);
            ps.setInt(1, id);
            cuantos = ps.executeUpdate();
            DatabaseDAO.closeConnection();
        return cuantos;
    }
    //---
    public Usuario select(int id) throws SQLException, ClassNotFoundException {
        Usuario user = null;
        PreparedStatement preparedStatement = DatabaseDAO.openConnection().prepareStatement(sqlSelectById);
            preparedStatement.setInt(1, id);
            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                user = new Usuario();
                user.setId(rs.getInt("id"));
                user.setUsuario(rs.getString("usuario"));
                user.setNombre(rs.getString("nombre"));
                user.setEmail(rs.getString("email"));
            }
            DatabaseDAO.closeConnection();
        return user;
    }
    
    public Usuario select(String usuario) throws SQLException, ClassNotFoundException {
        Usuario user = null;
        PreparedStatement preparedStatement = DatabaseDAO.openConnection().prepareStatement(sqlSelectByUsuario);
            preparedStatement.setString(1, usuario);
            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                user = new Usuario();
                user.setId(rs.getInt("id"));
                user.setUsuario(rs.getString("usuario"));
                user.setNombre(rs.getString("nombre"));
                user.setEmail(rs.getString("email"));
            }
            DatabaseDAO.closeConnection();
        return user;
    }
    
    public List selectAll() throws ClassNotFoundException, SQLException {
        List users = new ArrayList ();
        PreparedStatement ps  = DatabaseDAO.openConnection().prepareStatement(sqlSelectAll);
        System.out.println(ps);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            Usuario user = new Usuario();
                user.setId(rs.getInt("id"));
                user.setUsuario(rs.getString("usuario"));
                user.setNombre(rs.getString("nombre"));
                user.setEmail(rs.getString("email"));
              users.add(user);
        
        }
        DatabaseDAO.closeConnection();
        return users;
    }
}
