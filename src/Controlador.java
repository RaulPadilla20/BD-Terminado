import java.awt.Dialog.ModalityType;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

public class Controlador {
    Modelo miModelo;
    Vista miVista;

    public Controlador(Modelo miModelo, Vista miVista) {
        this.miModelo = miModelo;
        this.miVista = miVista;
    }

    public void iniciarVista() {
        // Oyente de elementos del menú
        MiOyente miOyente = new MiOyente();
        // Registrar oyente de eventos
        miVista.miVentana.mnuArcActualizar.addActionListener(miOyente);
        miVista.miVentana.mnuAyuAcercaDe.addActionListener(miOyente);
        miVista.miVentana.mnuArcSalir.addActionListener(miOyente);

        miVista.miVentana.setVisible(true);
    }

    // Clase interna para oyente de eventos
    class MiOyente implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            // Preguntar cual de las tres opciones generó el evento
            String cual = e.getActionCommand();

            if (cual.equals("Actualizar...")) {
                // Mostrar el cuadro de diálogo
                miVista.miDialogo = new MiDialogo(miVista.miVentana, "Catálogo de Empleados");

                // Oyente de botones de navegación
                OyenteBotonesNavegacion obn = new OyenteBotonesNavegacion();
                miVista.miDialogo.miPanel.btnPrimero.addActionListener(obn);
                miVista.miDialogo.miPanel.btnAnterior.addActionListener(obn);
                miVista.miDialogo.miPanel.txtClave.addActionListener(obn);
                miVista.miDialogo.miPanel.btnSiguiente.addActionListener(obn);
                miVista.miDialogo.miPanel.btnUltimo.addActionListener(obn);

                // Oyente de botones de acción
                OyenteBotonesAccion oba = new OyenteBotonesAccion();
                miVista.miDialogo.miPanel.btnNuevo.addActionListener(oba);
                miVista.miDialogo.miPanel.btnModificar.addActionListener(oba);
                miVista.miDialogo.miPanel.btnGuardar.addActionListener(oba);
                miVista.miDialogo.miPanel.btnCancelar.addActionListener(oba);
                miVista.miDialogo.miPanel.btnSalir.addActionListener(oba);

                // Preguntar si tenemos registros
                miVista.miDialogo.miPanel.totalRegistros = miModelo.getTotalEmpleados();
                if (miVista.miDialogo.miPanel.totalRegistros > 0) {
                    Empleado actual = miModelo.crearEmpleado(1);
                    miVista.miDialogo.miPanel.mostrarEmpleado(actual);
                    miVista.miDialogo.miPanel.registroActual = 1;
                }

                // Establecer estado del Catálogo en VER
                miVista.miDialogo.miPanel.establecerEstado(Estado.VER);

                // Hacer diálogo modal y mostrar
                miVista.miDialogo.setModalityType(ModalityType.APPLICATION_MODAL);
                miVista.miDialogo.setVisible(true);

            } else if (cual.equals("Salir")) {
                // Preguntar si desea salir...
                int opcion = JOptionPane.showConfirmDialog(miVista.miVentana,
                        "¿Deseas salir?",
                        "Aplicación de Catálogo - MVC",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE);

                if (opcion == JOptionPane.YES_OPTION)
                    miVista.miVentana.dispose();

            } else if (cual.equals("Acerca de..."))
                JOptionPane.showMessageDialog(miVista.miVentana,
                        "Catálogo - MVC\nAutor: Jaime A. Félix M.",
                        "Acerca de...",
                        JOptionPane.INFORMATION_MESSAGE);
        }

    }

    // Clase interna para oyentes de los botones de acción
    class OyenteBotonesAccion implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            // Preguntar cual botón generó el evento
            String cual = e.getActionCommand();

            if (cual.equals("Nuevo")) {
                miVista.miDialogo.miPanel.limpiarCampos();
                miVista.miDialogo.miPanel.establecerEstado(Estado.AGREGAR);
            } else if (cual.equals("Modificar")) {
                miVista.miDialogo.miPanel.establecerEstado(Estado.MODIFICAR);
            } else if (cual.equals("Guardar")) {
                // Obtener los valores de los componentes
                String nombre = miVista.miDialogo.miPanel.txtNombre.getText();
                char genero;
                if (miVista.miDialogo.miPanel.rbtMasculino.isSelected())
                    genero = 'M';
                else
                    genero = 'F';
                int edad = (int) miVista.miDialogo.miPanel.spnEdad.getValue();
                int indiceDepartamento = miVista.miDialogo.miPanel.cmbDepartamento.getSelectedIndex();
                char departamento;
                if (indiceDepartamento == 0)
                    departamento = 'F';
                else if (indiceDepartamento == 1)
                    departamento = 'A';
                else if (indiceDepartamento == 2)
                    departamento = 'P';
                else
                    departamento = 'V';
                int indiceTurno = miVista.miDialogo.miPanel.cmbTurnoLaboral.getSelectedIndex();
                char turnoLaboral;
                if (indiceTurno == 0)
                    turnoLaboral = 'M';
                else if (indiceTurno == 1)
                    turnoLaboral = 'V';
                else
                    turnoLaboral = 'N';
                boolean lectura = miVista.miDialogo.miPanel.chkLectura.isSelected();
                boolean deportes = miVista.miDialogo.miPanel.chkDeportes.isSelected();
                boolean cines = miVista.miDialogo.miPanel.chkCines.isSelected();
                boolean teatros = miVista.miDialogo.miPanel.chkTeatros.isSelected();
                boolean juegosDeSalon = miVista.miDialogo.miPanel.chkJuegosDeSalon.isSelected();
                boolean conciertos = miVista.miDialogo.miPanel.chkConciertos.isSelected();
                boolean otros = miVista.miDialogo.miPanel.chkOtros.isSelected();
                String especificar = miVista.miDialogo.miPanel.txtEspecificar.getText();
                boolean activo = miVista.miDialogo.miPanel.chkActivo.isSelected();

                boolean resultado;
                if (miVista.miDialogo.miEstado == Estado.AGREGAR) {
                    resultado = miModelo.ejecutarInsertar(nombre, genero, edad, departamento, turnoLaboral,
                            lectura, deportes, cines, teatros, juegosDeSalon,
                            conciertos, otros, especificar, activo);
                    miVista.miDialogo.miPanel.totalRegistros++;
                    miVista.miDialogo.miPanel.registroActual = miVista.miDialogo.miPanel.totalRegistros;

                    miModelo.setEmpleadoActual(miVista.miDialogo.miPanel.registroActual);
                    miModelo.setTotalEmpleados(miVista.miDialogo.miPanel.totalRegistros);
                } else {
                    resultado = miModelo.ejecutarModificar(miVista.miDialogo.miPanel.registroActual,
                            nombre, genero, edad, departamento, turnoLaboral,
                            lectura, deportes, cines, teatros, juegosDeSalon,
                            conciertos, otros, especificar, activo);
                }

                if (resultado) {
                    // Volver a ejecutar la consulta
                    miModelo.setRSEmpleados(
                            miModelo.ejecutarConsulta());
                    Empleado actual = miModelo.crearEmpleado(
                            miVista.miDialogo.miPanel.registroActual);
                    miVista.miDialogo.miPanel.mostrarEmpleado(actual);
                    miModelo.setEmpleadoActual(
                            miVista.miDialogo.miPanel.registroActual);

                    JOptionPane.showMessageDialog(null, "Registro modificado");
                } else {
                    JOptionPane.showMessageDialog(null, "Error al actualizar registro");
                }
                miVista.miDialogo.miPanel.establecerEstado(Estado.VER);

            } else if (cual.equals("Cancelar")) {
                // Deshacer cualquier cambio
                if (miModelo.getEmpleadoActual() == 0) {
                    miVista.miDialogo.miPanel.limpiarCampos();
                } else {
                    Empleado actual = miModelo.crearEmpleado(
                            miVista.miDialogo.miPanel.registroActual);
                    miVista.miDialogo.miPanel.mostrarEmpleado(actual);
                    miModelo.setEmpleadoActual(
                            miVista.miDialogo.miPanel.registroActual);
                }
                miVista.miDialogo.miPanel.establecerEstado(Estado.VER);
            } else if (cual.equals("Salir")) {
                // Eliminar el cuadro de diálogo
                miVista.miDialogo.dispose();
            }

        }

    } // fin de la clase OyenteBotonesAccion

    // Clase interna para oyentes de botones de navegación
    class OyenteBotonesNavegacion implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            // Preguntar cual botón generó el evento
            String cual = e.getActionCommand();

            if (cual.equals("Primero")) {
                miVista.miDialogo.miPanel.registroActual = 1;
            } else if (cual.equals("Anterior")) {
                if (miVista.miDialogo.miPanel.registroActual > 1)
                    miVista.miDialogo.miPanel.registroActual--;
            } else if (cual.equals("Siguiente")) {
                if (miVista.miDialogo.miPanel.registroActual < miVista.miDialogo.miPanel.totalRegistros)
                    miVista.miDialogo.miPanel.registroActual++;
            } else if (cual.equals("Ultimo")) {
                miVista.miDialogo.miPanel.registroActual = miVista.miDialogo.miPanel.totalRegistros;
            } else {
                // Es el <Enter> del txtClave

                // Validar que el contenido del txtClave sea un entero
                // entre 1 y totalRegistros
                int nuevoValor = 0;
                try {
                    nuevoValor = Integer.parseInt(
                            miVista.miDialogo.miPanel.txtClave.getText());
                } catch (Exception ex) {
                    // El contenido de txtClave no es un entero
                    // nuevoValor = 0
                }

                if ((nuevoValor >= 1) && (nuevoValor <= miVista.miDialogo.miPanel.totalRegistros))
                    miVista.miDialogo.miPanel.registroActual = nuevoValor;
            } // Fin del if principal

            Empleado actual = miModelo.crearEmpleado(miVista.miDialogo.miPanel.registroActual);
            miVista.miDialogo.miPanel.mostrarEmpleado(actual);

            miModelo.setEmpleadoActual(miVista.miDialogo.miPanel.registroActual);
        }
    }
}
