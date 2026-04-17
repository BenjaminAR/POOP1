package HMC_Modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement; // Agregado
import java.sql.ResultSet;         // Agregado
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class HMC_Conexion {

    private final String DRIVER = "oracle.jdbc.OracleDriver";
    private final String URL = "jdbc:oracle:thin:@localhost:1521/XEPDB1"; //RH_BASIC@//localhost:1521/XEPDB1
    private final String USER = "RH_BASIC";
    private final String PASSWORD = "rh123";
    public Connection cadena;

    public HMC_Conexion() {
        this.cadena = null;
    }

    public Connection obtenerConexion() {
        try {
            Class.forName(DRIVER);
            this.cadena = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (ClassNotFoundException | SQLException e) {
            JOptionPane.showMessageDialog(null, "Error de conexión: " + e.getMessage());
            System.out.println("Error: " + e.getMessage());
        }
        return this.cadena;
    }

    public boolean validarUsuario(String usuario, String contrasena) {

        String sql = "SELECT * FROM RH_BASIC.RH_EMPLEADOS WHERE ID_EMPLEADO = ? AND TELEFONO = ?";

        try (Connection conn = obtenerConexion(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, usuario);
            ps.setString(2, contrasena);

            try (ResultSet rs = ps.executeQuery()) {
                return rs.next(); // Si hay un resultado, las credenciales son válidas
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false; // Retorna false si no se encontro el query sql
        }
    }

    public String buscarEmpleadoporId(int idEmpleado) {
        String empleadoEncontrado = "";
        String sql = "SELECT * FROM VW_EMPLEADOS_DETALLE WHERE ID_EMPLEADO = ?";

        try (Connection conn = obtenerConexion(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, idEmpleado);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                empleadoEncontrado = empleadoEncontrado + rs.getString("ID_EMPLEADO") + "!";
                empleadoEncontrado = empleadoEncontrado + rs.getString("NOMBRE") + "!";
                empleadoEncontrado = empleadoEncontrado + rs.getString("APELLIDO_PATERNO") + "!";
                empleadoEncontrado = empleadoEncontrado + rs.getString("APELLIDO_MATERNO") + "!";
                empleadoEncontrado = empleadoEncontrado + rs.getString("email") + "!";
                empleadoEncontrado = empleadoEncontrado + rs.getString("departamento") + "!";
                empleadoEncontrado = empleadoEncontrado + rs.getString("puesto") + "!";
                empleadoEncontrado = empleadoEncontrado + rs.getString("salario") + "!";
                empleadoEncontrado = empleadoEncontrado + rs.getString("fecha_nacimiento") + "!";
                empleadoEncontrado = empleadoEncontrado + rs.getString("fecha_contratacion") + "!";
                empleadoEncontrado = empleadoEncontrado + rs.getString("estatus") + "!";
                empleadoEncontrado = empleadoEncontrado + rs.getString("TELEFONO") + "!";
                

                

// + rs.getString("APELLIDO_PATERNO") + " " + rs.getString("APELLIDO_MATERNO");
                return empleadoEncontrado;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return empleadoEncontrado;

    }

    public boolean insertarEmpleado(String NOMBRE, String APELLIDO_PATERNO, String APELLIDO_MATERNO, String EMAIL, String TELEFONO, String FECHA_NACIMIENTO, String FECHA_CONTRATACION, double SALARIO, int ID_DEPARTAMENTO, int ID_PUESTO) {

        String sql = "INSERT INTO rh_empleados (nombre, apellido_paterno, apellido_materno, email, telefono, FECHA_NACIMIENTO, FECHA_CONTRATACION, salario, id_departamento, id_puesto) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = obtenerConexion(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, NOMBRE);
            ps.setString(2, APELLIDO_PATERNO);
            ps.setString(3, APELLIDO_MATERNO);
            ps.setString(4, EMAIL);
            ps.setString(5, TELEFONO);
            ps.setString(6, FECHA_NACIMIENTO);
            ps.setString(7, FECHA_CONTRATACION);
            ps.setDouble(8, SALARIO);
            ps.setInt(9, ID_DEPARTAMENTO);
            ps.setInt(10, ID_PUESTO);

            try (ResultSet rs = ps.executeQuery()) {
                return rs.next(); // Si hay un resultado, las credenciales son válidas
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false; // Retorna false si no se encontro el query sql
        }
    }
                  //                  (idExistente, nombre, apePat, apeMat, email, tel, salario);
    public boolean actualizarEmpleado(int idEmpleado, String NOMBRE, String APELLIDO_PATERNO, String APELLIDO_MATERNO, String TELEFONO, double SALARIO) {
    String sql = "UPDATE rh_empleados SET nombre = ?, apellido_paterno = ?, apellido_materno = ?, telefono = ?, salario = ? WHERE id_empleado = ?";

    try (Connection conn = obtenerConexion(); 
         PreparedStatement ps = conn.prepareStatement(sql)) {
        
        ps.setString(1, NOMBRE);
        ps.setString(2, APELLIDO_PATERNO);
        ps.setString(3, APELLIDO_MATERNO);
        ps.setString(4, TELEFONO);
        ps.setDouble(5, SALARIO);
        ps.setInt(6, idEmpleado); // El ID va al final por el WHERE

        int filasAfectadas = ps.executeUpdate();
        return filasAfectadas > 0; // Retorna true si se actualizó al menos un registro
        
    } catch (SQLException e) {
        e.printStackTrace();
        return false;
    }
}

    public boolean eliminarEmpleado(int idEmpleado) {
        String sql = "DELETE FROM rh_empleados WHERE id_empleado = ?";
    
        try (Connection conn = obtenerConexion(); 
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setInt(1, idEmpleado);
    
            int filasAfectadas = ps.executeUpdate();
            return filasAfectadas > 0; // True si el empleado existía y fue borrado
            
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

        public String buscarNominaId(int idNomina) {
        String nominaEncontrada = "";
        String sql = "SELECT * FROM vw_nomina_detalle WHERE ID_NOMINA = ?";

        try (Connection conn = obtenerConexion(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, idNomina);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                nominaEncontrada = nominaEncontrada + rs.getString("ID_NOMINA") + "!";
                nominaEncontrada = nominaEncontrada + rs.getString("ID_EMPLEADO") + "!";
                nominaEncontrada = nominaEncontrada + rs.getString("NOMBRE") + "!";
                nominaEncontrada = nominaEncontrada + rs.getString("APELLIDO_PATERNO") + "!";
                nominaEncontrada = nominaEncontrada + rs.getString("APELLIDO_MATERNO") + "!";
                nominaEncontrada = nominaEncontrada + rs.getString("inicio") + "!";
                nominaEncontrada = nominaEncontrada + rs.getString("fin") + "!";
                nominaEncontrada = nominaEncontrada + rs.getString("sueldo_base") + "!";
                nominaEncontrada = nominaEncontrada + rs.getString("bonos") + "!";
                nominaEncontrada = nominaEncontrada + rs.getString("descuentos") + "!";
                nominaEncontrada = nominaEncontrada + rs.getString("total_pago") + "!";
                nominaEncontrada = nominaEncontrada + rs.getString("fecha_pago") + "!";
                return nominaEncontrada;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return nominaEncontrada;

    }

    
    
}
