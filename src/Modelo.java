import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Modelo {
    // Nombre del controlador de JDBC y URL de la base de datos
    private static final String CONTROLADOR = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    private static final String URL_BASEDATOS = "jdbc:sqlserver://localhost;databaseName=MVC;trustServerCertificate=true;";
    private static final String NOMBREUSUARIO = "jhtp7";
    private static final String CONTRASENIA = "jhtp7";

    // Conexión y sentencias preparadas
    private Connection conexion; // Manejar la conexión con la BD
    private PreparedStatement seleccionarTodosLosEmpleados;
    private PreparedStatement insertarNuevoEmpleado;
    private PreparedStatement modificarEmpleadoActual;

    // Atributos para manejar los datos de la tabla
    private ResultSet RSEmpleados;
    private int empleadoActual;
    private int totalEmpleados;

    // Constructor
    public Modelo() throws SQLException, ClassNotFoundException {
        // Cargar el driver de SQLServer
        Class.forName(CONTROLADOR);

        // Conectarse a la BD
        conexion = DriverManager.getConnection(URL_BASEDATOS, NOMBREUSUARIO, CONTRASENIA);

        // Crear sentencias preparadas para interactuar con la base de datos
        seleccionarTodosLosEmpleados = conexion.prepareStatement(
                "SELECT * FROM Empleados",
                ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_READ_ONLY);

        insertarNuevoEmpleado = conexion.prepareStatement(
                "INSERT INTO Empleados (Nombre, Genero, Edad, Departamento, " +
                        "TurnoLaboral, Lectura, Deportes, Cines, Teatros, JuegosDeSalon, " +
                        "Conciertos, Otros, Especificar, Activo) " +
                        "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");

        modificarEmpleadoActual = conexion.prepareStatement(
                "UPDATE Empleados SET " +
                        "Nombre = ?, " +
                        "Genero = ?, " +
                        "Edad = ?, " +
                        "Departamento = ?, " +
                        "TurnoLaboral = ?, " +
                        "Lectura = ?, " +
                        "Deportes = ?, " +
                        "Cines = ?, " +
                        "Teatros = ?, " +
                        "JuegosDeSalon = ?, " +
                        "Conciertos = ?, " +
                        "Otros = ?, " +
                        "Especificar = ?, " +
                        "Activo = ? " +
                        "WHERE IdClave = ?");

        // Obtener los datos de la base de datos
        RSEmpleados = ejecutarConsulta();

        // Inicializar totalEmpleados y empleadoActual
        if (RSEmpleados.last()) {
            totalEmpleados = RSEmpleados.getRow();
            empleadoActual = 1;
        } else // La tabla no tiene filas
        {
            totalEmpleados = 0;
            empleadoActual = 0;
        }
    }

    // Métodos set/get
    public void setRSEmpleados(ResultSet RSEmpleados) {
        this.RSEmpleados = RSEmpleados;
    }

    public void setEmpleadoActual(int empleadoActual) {
        this.empleadoActual = empleadoActual;
    }

    public void setTotalEmpleados(int totalEmpleados) {
        this.totalEmpleados = totalEmpleados;
    }

    public ResultSet getRSEmpleados() {
        return RSEmpleados;
    }

    public int getEmpleadoActual() {
        return empleadoActual;
    }

    public int getTotalEmpleados() {
        return totalEmpleados;
    }

    // Métodos de instancia
    public ResultSet ejecutarConsulta() {
        try {
            return seleccionarTodosLosEmpleados.executeQuery();
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }

        return null;
    }

    public boolean ejecutarModificar(
            int idClave, String nombre, char genero, int edad, char departamento,
            char turnoLaboral, boolean lectura, boolean deportes, boolean cines, boolean teatros,
            boolean juegosDeSalon, boolean conciertos, boolean otros, String especificar, boolean activo) {
        try {
            // Establecer los argumentos de la sentencia preparada
            modificarEmpleadoActual.setString(1, nombre);
            modificarEmpleadoActual.setString(2, genero + "");
            modificarEmpleadoActual.setInt(3, edad);
            modificarEmpleadoActual.setString(4, departamento + "");
            modificarEmpleadoActual.setString(5, turnoLaboral + "");
            modificarEmpleadoActual.setBoolean(6, lectura);
            modificarEmpleadoActual.setBoolean(7, deportes);
            modificarEmpleadoActual.setBoolean(8, cines);
            modificarEmpleadoActual.setBoolean(9, teatros);
            modificarEmpleadoActual.setBoolean(10, juegosDeSalon);
            modificarEmpleadoActual.setBoolean(11, conciertos);
            modificarEmpleadoActual.setBoolean(12, otros);
            modificarEmpleadoActual.setString(13, especificar);
            modificarEmpleadoActual.setBoolean(14, activo);
            modificarEmpleadoActual.setInt(15, idClave);

            modificarEmpleadoActual.executeUpdate();
            return true;
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }

        return false;
    }

    public boolean ejecutarInsertar(
            String nombre, char genero, int edad, char departamento,
            char turnoLaboral, boolean lectura, boolean deportes, boolean cines, boolean teatros,
            boolean juegosDeSalon, boolean conciertos, boolean otros, String especificar, boolean activo) {
        try {
            // Establecer los argumentos de la sentencia preparada
            insertarNuevoEmpleado.setString(1, nombre);
            insertarNuevoEmpleado.setString(2, genero + "");
            insertarNuevoEmpleado.setInt(3, edad);
            insertarNuevoEmpleado.setString(4, departamento + "");
            insertarNuevoEmpleado.setString(5, turnoLaboral + "");
            insertarNuevoEmpleado.setBoolean(6, lectura);
            insertarNuevoEmpleado.setBoolean(7, deportes);
            insertarNuevoEmpleado.setBoolean(8, cines);
            insertarNuevoEmpleado.setBoolean(9, teatros);
            insertarNuevoEmpleado.setBoolean(10, juegosDeSalon);
            insertarNuevoEmpleado.setBoolean(11, conciertos);
            insertarNuevoEmpleado.setBoolean(12, otros);
            insertarNuevoEmpleado.setString(13, especificar);
            insertarNuevoEmpleado.setBoolean(14, activo);

            insertarNuevoEmpleado.executeUpdate();
            return true;
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }

        return false;
    }

    public Empleado crearEmpleado(int empleadoActual) {
        try {
            // Posicionarnos en la fila del empleado actual
            RSEmpleados.absolute(empleadoActual);

            // Crear objeto Empleado y regresarlo
            return new Empleado(
                    RSEmpleados.getInt("IDClave"),
                    RSEmpleados.getString("Nombre"),
                    RSEmpleados.getString("Genero").charAt(0),
                    RSEmpleados.getInt("Edad"),
                    RSEmpleados.getString("Departamento").charAt(0),
                    RSEmpleados.getString("TurnoLaboral").charAt(0),
                    RSEmpleados.getBoolean("Lectura"),
                    RSEmpleados.getBoolean("Deportes"),
                    RSEmpleados.getBoolean("Cines"),
                    RSEmpleados.getBoolean("Teatros"),
                    RSEmpleados.getBoolean("JuegosDeSalon"),
                    RSEmpleados.getBoolean("Conciertos"),
                    RSEmpleados.getBoolean("Otros"),
                    RSEmpleados.getString("Especificar"),
                    RSEmpleados.getBoolean("Activo"));
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }

        return null;
    }
}
