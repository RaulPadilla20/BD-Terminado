// Empleado.java
// Clase Empleado con los datos (atributos) a manejar

public class Empleado {
    // Atributos
    private int idClave;
    private String nombre;
    private char genero;
    private int edad;
    private char departamento;
    private char turnoLaboral;
    private boolean lectura;
    private boolean deportes;
    private boolean cines;
    private boolean teatros;
    private boolean juegosDeSalon;
    private boolean conciertos;
    private boolean otros;
    private String especificar;
    private boolean activo;

    // Constructor
    public Empleado(int idClave, String nombre, char genero, int edad, char departamento,
            char turnoLaboral, boolean lectura, boolean deportes, boolean cines, boolean teatros,
            boolean juegosDeSalon, boolean conciertos, boolean otros, String especificar, boolean activo) {
        setIdClave(idClave);
        setNombre(nombre);
        setGenero(genero);
        setEdad(edad);
        setDepartamento(departamento);
        setTurnoLaboral(turnoLaboral);
        setLectura(lectura);
        setDeportes(deportes);
        setCines(cines);
        setTeatros(teatros);
        setJuegosDeSalon(juegosDeSalon);
        setConciertos(conciertos);
        setOtros(otros);
        setEspecificar(especificar);
        setActivo(activo);
    }

    // Métodos set/get
    public void setIdClave(int idClave) {
        this.idClave = idClave;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setGenero(char genero) {
        this.genero = genero;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public void setDepartamento(char departamento) {
        this.departamento = departamento;
    }

    public void setTurnoLaboral(char turnoLaboral) {
        this.turnoLaboral = turnoLaboral;
    }

    public void setLectura(boolean lectura) {
        this.lectura = lectura;
    }

    public void setDeportes(boolean deportes) {
        this.deportes = deportes;
    }

    public void setCines(boolean cines) {
        this.cines = cines;
    }

    public void setTeatros(boolean teatros) {
        this.teatros = teatros;
    }

    public void setJuegosDeSalon(boolean juegosDeSalon) {
        this.juegosDeSalon = juegosDeSalon;
    }

    public void setConciertos(boolean conciertos) {
        this.conciertos = conciertos;
    }

    public void setOtros(boolean otros) {
        this.otros = otros;
    }

    public void setEspecificar(String especificar) {
        this.especificar = especificar;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public int getIdClave() {
        return idClave;
    }

    public String getNombre() {
        return nombre;
    }

    public char getGenero() {
        return genero;
    }

    public int getEdad() {
        return edad;
    }

    public char getDepartamento() {
        return departamento;
    }

    public char getTurnoLaboral() {
        return turnoLaboral;
    }

    public boolean getLectura() {
        return lectura;
    }

    public boolean getDeportes() {
        return deportes;
    }

    public boolean getCines() {
        return cines;
    }

    public boolean getTeatros() {
        return teatros;
    }

    public boolean getJuegosDeSalon() {
        return juegosDeSalon;
    }

    public boolean getConciertos() {
        return conciertos;
    }

    public boolean getOtros() {
        return otros;
    }

    public String getEspecificar() {
        return especificar;
    }

    public boolean getActivo() {
        return activo;
    }

}
