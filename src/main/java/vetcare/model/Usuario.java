package vetcare.model;

public class Usuario extends Persona {

    private String nombreUsuario;
    private String contrasena;
    private RolUsuario rol;
    private boolean activo;

    public Usuario(String cedula, String nombre, String apellido, String telefono, 
            String email, String nombreUsuario, String contrasena, RolUsuario rol) {
        super(cedula, nombre, apellido, telefono, email);
        this.nombreUsuario = nombreUsuario;
        this.contrasena = contrasena;
        this.rol = rol;
        this.activo = true;
    }

    @Override
    public String getRol() {
        return rol.name();
    }

    public boolean autenticar(String contrasena) {
        return this.activo && this.contrasena.equals(contrasena);
    }

    public boolean tienePermiso(RolUsuario rolRequerido) {
        return this.activo && this.rol == rolRequerido;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public RolUsuario getRolUsuario() {
        return rol;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public void setRolUsuario(RolUsuario rol) {
        this.rol = rol;
    }

    @Override
    public String toString() {
        return nombreUsuario + " [" + rol.name() + "]";
    }
}