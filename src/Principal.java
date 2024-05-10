import java.sql.SQLException;

public class Principal {
    public static void main(String[] args) {
        try {
            Vista miVista = new Vista();
            Modelo miModelo = new Modelo();
            Controlador miControlador = new Controlador(miModelo, miVista);

            miControlador.iniciarVista();
        } catch (ClassNotFoundException cnfe) {
            cnfe.printStackTrace();
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }
    }
}