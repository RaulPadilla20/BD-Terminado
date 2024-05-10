import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

public class Vista {
    // Declarar la variable de Ventana
    MiVentana miVentana;
    // Declara la variable del Cuadro de Diálogo
    MiDialogo miDialogo;

    // Constructor
    public Vista() {
        // Crear un objeto Ventana
        miVentana = new MiVentana("Aplicación de Catálogo - MVC");
        miVentana.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

        // miVentana.setVisible(true);
    }
}

class MiVentana extends JFrame {
    // Atributos del menú
    JMenuItem mnuArcActualizar;
    JMenuItem mnuArcSalir;
    JMenuItem mnuAyuAcercaDe;

    public MiVentana(String titulo) {
        super(titulo);

        // Establecer menu
        JMenuBar barraMenu = new JMenuBar();
        setJMenuBar(barraMenu);

        // Crear menú Archivo
        JMenu menuArchivo = new JMenu("Archivo");
        menuArchivo.setMnemonic('A');

        // Crear opciones del menú
        mnuArcActualizar = new JMenuItem("Actualizar...");
        mnuArcActualizar.setMnemonic('c');
        mnuArcSalir = new JMenuItem("Salir");
        mnuArcSalir.setMnemonic('S');

        // Agregar opciones al menú de archivo
        menuArchivo.add(mnuArcActualizar);
        menuArchivo.addSeparator();
        menuArchivo.add(mnuArcSalir);

        // Agregar el menú Archivo a la barra de menu
        barraMenu.add(menuArchivo);

        // Crear menú Archivo
        JMenu menuAyuda = new JMenu("Ayuda");
        menuAyuda.setMnemonic('u');

        // Crear opciones del menú
        mnuAyuAcercaDe = new JMenuItem("Acerca de...");
        mnuAyuAcercaDe.setMnemonic('A');

        // Agregar opciones al menú de ayuda
        menuAyuda.add(mnuAyuAcercaDe);

        // Agregar el menú Archivo a la barra de menu
        barraMenu.add(menuAyuda);

        setSize(600, 400);
    }
}

// Clase MiDialogo para catálogo de trabajadores
class MiDialogo extends JDialog {
    MiPanel miPanel;
    Estado miEstado;

    // Constructor
    public MiDialogo(JFrame padre, String titulo) {
        super(padre, titulo);
        setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);

        // Agregar panel al cuadro de diálogo
        miPanel = new MiPanel();
        add(miPanel);

        setSize(550, 350);
        setResizable(false);
        // setVisible(true);
    }

    // Clase interna personalizada MiPanel
    class MiPanel extends JPanel {
        // Declarar componentes del panel de navegación
        JButton btnPrimero;
        JButton btnAnterior;
        JTextField txtClave;
        JTextField txtTotalRegistros;
        JButton btnSiguiente;
        JButton btnUltimo;

        // Declarar componentes del panel de datos generales
        JTextField txtNombre;
        JRadioButton rbtMasculino;
        JRadioButton rbtFemenino;
        JSpinner spnEdad;
        JComboBox<String> cmbDepartamento;
        JComboBox<String> cmbTurnoLaboral;
        JCheckBox chkLectura;
        JCheckBox chkDeportes;
        JCheckBox chkCines;
        JCheckBox chkTeatros;
        JCheckBox chkJuegosDeSalon;
        JCheckBox chkConciertos;
        JCheckBox chkOtros;
        JTextField txtEspecificar;
        JCheckBox chkActivo;

        // Declarar componentes del panel de botones de acción
        JButton btnNuevo;
        JButton btnModificar;
        JButton btnGuardar;
        JButton btnCancelar;
        JButton btnSalir;

        // Declarar registro actual y total de registros
        int registroActual = 0;
        int totalRegistros = 0;

        // Constructor para crear los componentes del cuadro de diálogo
        public MiPanel() {
            // Establecer administrador de esquema de tipo borde
            setLayout(new BorderLayout());

            // Panel de Navegación
            btnPrimero = new JButton("Primero");
            btnPrimero.setMnemonic('P');
            btnAnterior = new JButton("Anterior");
            btnAnterior.setMnemonic('A');
            JLabel etClave = new JLabel("Clave:");
            txtClave = new JTextField(3);
            txtClave.setHorizontalAlignment(JTextField.CENTER);
            JLabel etDe = new JLabel("de");
            txtTotalRegistros = new JTextField(3);
            txtTotalRegistros.setHorizontalAlignment(JTextField.CENTER);
            txtTotalRegistros.setEditable(false);
            btnSiguiente = new JButton("Siguiente");
            btnSiguiente.setMnemonic('i');
            btnUltimo = new JButton("Ultimo");
            btnUltimo.setMnemonic('U');

            JPanel panelNavegacion = new JPanel();
            panelNavegacion.add(btnPrimero);
            panelNavegacion.add(btnAnterior);
            panelNavegacion.add(etClave);
            panelNavegacion.add(txtClave);
            panelNavegacion.add(etDe);
            panelNavegacion.add(txtTotalRegistros);
            panelNavegacion.add(btnSiguiente);
            panelNavegacion.add(btnUltimo);

            // Panel de Datos Generales
            JLabel etNombre = new JLabel("Nombre:");
            txtNombre = new JTextField(30);
            JLabel etGenero = new JLabel("Genero:");
            rbtMasculino = new JRadioButton("Masculino", true);
            rbtFemenino = new JRadioButton("Femenino");
            // Grupo de botones para hacer a los RadioButton mutuamente excluyentes
            ButtonGroup bg = new ButtonGroup();
            bg.add(rbtMasculino);
            bg.add(rbtFemenino);

            JLabel etEdad = new JLabel("Edad:");
            // Crear modelo numérico para JSpinner
            SpinnerNumberModel snm = new SpinnerNumberModel(0, 0, 100, 1);
            spnEdad = new JSpinner(snm);
            JLabel etDepartamento = new JLabel("Departamento:");
            // Arreglo de opciones para el combobox departamento
            String[] opcionesDepartamentos = { "Finanzas", "Administración", "Producción", "Vigilancia" };
            cmbDepartamento = new JComboBox<String>(opcionesDepartamentos);
            JLabel etTurnoLaboral = new JLabel("Turno Laboral:");
            // Arreglo de opciones para el combobox turnolaboral
            String[] opcionesTurnoLaboral = { "Matutino", "Vespertino", "Nocturno" };
            cmbTurnoLaboral = new JComboBox<String>(opcionesTurnoLaboral);
            // JLabel etActividades = new JLabel("Actividades");
            chkLectura = new JCheckBox("Lectura");
            chkDeportes = new JCheckBox("Deportes");
            chkCines = new JCheckBox("Cines");
            chkTeatros = new JCheckBox("Teatros");
            chkJuegosDeSalon = new JCheckBox("Juegos de Salón");
            chkConciertos = new JCheckBox("Conciertos");
            chkOtros = new JCheckBox("Otros");
            JLabel etEspecificar = new JLabel("Especificar");
            txtEspecificar = new JTextField(10);
            JLabel etEstatus = new JLabel("Estatus:");
            chkActivo = new JCheckBox("Activo");

            // Panel para contener los componentes
            JPanel panelGeneral = new JPanel();

            FlowLayout esquemaSimple = new FlowLayout(FlowLayout.LEFT);
            panelGeneral.setLayout(esquemaSimple);

            panelGeneral.add(Box.createHorizontalStrut(2000));
            panelGeneral.add(Box.createHorizontalStrut(40));
            panelGeneral.add(etNombre);
            panelGeneral.add(txtNombre);
            panelGeneral.add(Box.createHorizontalStrut(40));
            panelGeneral.add(Box.createHorizontalStrut(40));
            panelGeneral.add(etGenero);
            panelGeneral.add(rbtMasculino);
            panelGeneral.add(rbtFemenino);
            panelGeneral.add(Box.createHorizontalStrut(65));
            panelGeneral.add(etEdad);
            panelGeneral.add(spnEdad);
            panelGeneral.add(Box.createHorizontalStrut(40));
            panelGeneral.add(Box.createHorizontalStrut(40));
            panelGeneral.add(etDepartamento);
            panelGeneral.add(cmbDepartamento);
            panelGeneral.add(etTurnoLaboral);
            panelGeneral.add(cmbTurnoLaboral);
            panelGeneral.add(Box.createHorizontalStrut(40));
            panelGeneral.add(Box.createHorizontalStrut(40));
            // Panel para agrupar los chkbox de actividades
            JPanel panelActividades = new JPanel();
            panelGeneral.add(panelActividades);
            GridLayout esquemaCuadricula = new GridLayout(3, 3);
            panelActividades.setLayout(esquemaCuadricula);
            // Borde Negro con título
            Border bordeNegro = BorderFactory.createLineBorder(Color.BLACK);
            TitledBorder bordeTitulo = BorderFactory.createTitledBorder(bordeNegro,
                    "Actividades Recreativas");
            panelActividades.setBorder(bordeTitulo);

            panelActividades.add(chkLectura);
            panelActividades.add(chkDeportes);
            panelActividades.add(chkCines);
            panelActividades.add(chkTeatros);
            panelActividades.add(chkJuegosDeSalon);
            panelActividades.add(chkConciertos);
            panelActividades.add(chkOtros);
            panelActividades.add(etEspecificar);
            panelActividades.add(txtEspecificar);

            panelGeneral.add(Box.createHorizontalStrut(40));
            panelGeneral.add(Box.createHorizontalStrut(80));
            panelGeneral.add(etEstatus);
            panelGeneral.add(chkActivo);

            // Botones de acción
            btnNuevo = new JButton("Nuevo");
            btnNuevo.setMnemonic('N');
            btnModificar = new JButton("Modificar");
            btnModificar.setMnemonic('M');
            btnGuardar = new JButton("Guardar");
            btnGuardar.setMnemonic('G');
            btnCancelar = new JButton("Cancelar");
            btnCancelar.setMnemonic('C');
            btnSalir = new JButton("Salir");
            btnSalir.setMnemonic('S');

            // Panel para contener los botones
            JPanel panelBotones = new JPanel();
            panelBotones.add(btnNuevo);
            panelBotones.add(btnModificar);
            panelBotones.add(btnGuardar);
            panelBotones.add(btnCancelar);
            panelBotones.add(btnSalir);

            // Agregar panel de navegación en la parte Norte del cuadro de diálogo
            add(panelNavegacion, BorderLayout.NORTH);
            // Agregar panel de datos generales en la parte Central del cuadro de diálogo
            add(panelGeneral, BorderLayout.CENTER);
            // Agregar panel botones en la parte Sur del cuadro de diálogo
            add(panelBotones, BorderLayout.SOUTH);
        }

        public void establecerEstado(Estado cual) {
            // Guardar el Estado en la variable
            miEstado = cual;

            // Habilitar/Deshabilitar componente de la interfaz de usuario
            // según el Estado del catálogo
            switch (miEstado) {
                case VER:
                    // Componentes del panel de navegación
                    btnPrimero.setEnabled(true);
                    btnAnterior.setEnabled(true);
                    txtClave.setEnabled(true);
                    txtTotalRegistros.setEnabled(true);
                    btnSiguiente.setEnabled(true);
                    btnUltimo.setEnabled(true);

                    // Componentes del panel general
                    txtNombre.setEnabled(false);
                    rbtMasculino.setEnabled(false);
                    rbtFemenino.setEnabled(false);
                    spnEdad.setEnabled(false);
                    cmbDepartamento.setEnabled(false);
                    cmbTurnoLaboral.setEnabled(false);
                    chkLectura.setEnabled(false);
                    chkDeportes.setEnabled(false);
                    chkCines.setEnabled(false);
                    chkTeatros.setEnabled(false);
                    chkJuegosDeSalon.setEnabled(false);
                    chkConciertos.setEnabled(false);
                    chkOtros.setEnabled(false);
                    txtEspecificar.setEnabled(false);
                    chkActivo.setEnabled(false);

                    // Componentes del panel de botones
                    btnNuevo.setEnabled(true);
                    btnModificar.setEnabled(totalRegistros > 0);
                    btnGuardar.setEnabled(false);
                    btnCancelar.setEnabled(false);
                    btnSalir.setEnabled(true);

                    break;

                default:
                    // Componentes del panel de navegación
                    btnPrimero.setEnabled(false);
                    btnAnterior.setEnabled(false);
                    txtClave.setEnabled(false);
                    txtTotalRegistros.setEnabled(false);
                    btnSiguiente.setEnabled(false);
                    btnUltimo.setEnabled(false);

                    // Componentes del panel general
                    txtNombre.setEnabled(true);
                    rbtMasculino.setEnabled(true);
                    rbtFemenino.setEnabled(true);
                    spnEdad.setEnabled(true);
                    cmbDepartamento.setEnabled(true);
                    cmbTurnoLaboral.setEnabled(true);
                    chkLectura.setEnabled(true);
                    chkDeportes.setEnabled(true);
                    chkCines.setEnabled(true);
                    chkTeatros.setEnabled(true);
                    chkJuegosDeSalon.setEnabled(true);
                    chkConciertos.setEnabled(true);
                    chkOtros.setEnabled(true);
                    txtEspecificar.setEnabled(true);
                    chkActivo.setEnabled(true);

                    // Componentes del panel de botones
                    btnNuevo.setEnabled(false);
                    btnModificar.setEnabled(false);
                    btnGuardar.setEnabled(true);
                    btnCancelar.setEnabled(true);
                    btnSalir.setEnabled(false);

                    break;

            }
        }

        public void mostrarEmpleado(Empleado e) {
            // Mostrar total de empleados
            txtTotalRegistros.setText(totalRegistros + "");

            // Obtener indice del Departamento
            int indiceDepartamento = 0;
            if (e.getDepartamento() == 'F')
                indiceDepartamento = 0;
            else if (e.getDepartamento() == 'A')
                indiceDepartamento = 1;
            else if (e.getDepartamento() == 'P')
                indiceDepartamento = 2;
            else if (e.getDepartamento() == 'V')
                indiceDepartamento = 3;

            // Obtener indice del turno laboral
            int indiceTurno = 0;
            if (e.getTurnoLaboral() == 'M')
                indiceTurno = 0;
            else if (e.getTurnoLaboral() == 'V')
                indiceTurno = 1;
            else if (e.getTurnoLaboral() == 'N')
                indiceTurno = 2;

            txtClave.setText(e.getIdClave() + "");
            txtNombre.setText(e.getNombre());
            rbtMasculino.setSelected(e.getGenero() == 'M');
            rbtFemenino.setSelected(e.getGenero() == 'F');
            spnEdad.setValue(e.getEdad());
            cmbDepartamento.setSelectedIndex(indiceDepartamento);
            cmbTurnoLaboral.setSelectedIndex(indiceTurno);
            chkLectura.setSelected(e.getLectura());
            chkDeportes.setSelected(e.getDeportes());
            chkCines.setSelected(e.getCines());
            chkTeatros.setSelected(e.getTeatros());
            chkJuegosDeSalon.setSelected(e.getJuegosDeSalon());
            chkConciertos.setSelected(e.getConciertos());
            chkOtros.setSelected(e.getOtros());
            txtEspecificar.setText(e.getEspecificar());
            chkActivo.setSelected(e.getActivo());
        }

        public void limpiarCampos() {
            // Mostrar total de empleados
            txtTotalRegistros.setText(totalRegistros + "");

            txtClave.setText("");
            txtNombre.setText("");
            rbtMasculino.setSelected(true);
            rbtFemenino.setSelected(false);
            spnEdad.setValue(0);
            cmbDepartamento.setSelectedIndex(0);
            cmbTurnoLaboral.setSelectedIndex(0);
            chkLectura.setSelected(false);
            chkDeportes.setSelected(false);
            chkCines.setSelected(false);
            chkTeatros.setSelected(false);
            chkJuegosDeSalon.setSelected(false);
            chkConciertos.setSelected(false);
            chkOtros.setSelected(false);
            txtEspecificar.setText("");
            chkActivo.setSelected(true);
        }

    }
}