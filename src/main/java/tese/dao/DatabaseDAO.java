package tese.dao;
///-- Conexion
import java.sql.Connection;
import java.sql.DriverManager;
//-- Consulta y acrtualizacion
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
//-- Errores
import java.sql.SQLException;
public class DatabaseDAO {
    //-- Conexion a BD
    private static String dbDatabase = "u849192389_tese";
    private static String dbURL = "jdbc:mysql://sql650.main-hosting.eu:3306/" + dbDatabase+ "?useSSL=false";
    private static String dbUser = "u849192389_tese";
    private static String dbPassword = "DraP7ewrl5rIW?S3Lja6";
    private static String dbDriver = "com.mysql.cj.jdbc.Driver";
    
    private static Connection conn = null;
    
    public static Connection openConnection() throws ClassNotFoundException, SQLException 
    { 
        System.out.println("..| Iniciar conexion a la base de datos [DatabaseDAO.getConnection()].");
        try {
            Class.forName(dbDriver);
            System.out.println("..| Se ha cargado el driver " + dbDriver);
            if (conn == null) {
                conn = DriverManager.getConnection(dbURL, dbUser, dbPassword);
                System.out.println("...| Conectando a la base de datos: " + dbDatabase);
                if (conn != null) {
                   System.out.println("...| Conectado a la base de datos ok." + dbDatabase);
                }
            }
        } catch (ClassNotFoundException e) {
            System.out.println("...| No se encontró el driver de base de datos");
            System.out.println("...|   " + e.getMessage());
            throw e;
        } catch (SQLException e) {
             System.out.println("...| Error de SQLException");
            System.out.println("...|   " + e.getMessage());
            throw e;
        } finally {
             
        }
        return conn;
    }
    
    public static void closeConnection() {
        System.out.println("Cerrar base de datos");
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
    }

    //-- Crear tala
    
     public static boolean crearTabla (String sql){
        boolean resultado = false;
        try {
            Statement stmt = conn.createStatement();
            stmt.execute(sql);
            System.out.println("...| Tabla creada." + sql);  
            resultado = true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }    
        return resultado;
    }
    //-- Consulta - Select
    
}
