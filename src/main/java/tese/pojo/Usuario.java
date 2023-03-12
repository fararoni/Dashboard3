package tese.pojo;
public class Usuario {
    Integer id;
    String usuario;
    String nombre;
    String email;
    String password;
    String password2;
    public Usuario(){
    }
    public Usuario(String usuario, String nombre, String email, String password, String password2) {
        this.usuario = usuario;
        this.nombre = nombre;
        this.email = email;
        this.password = password;
        this.password2 = password2;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    
    
    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }
    
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword2() {
        return password2;
    }

    public void setPassword2(String password2) {
        this.password2 = password2;
    }

    @Override
    public String toString() {
        return "Usuario{" + "id=" + id + ", usuario=" + usuario + ", nombre=" + nombre + ", email=" + email + ", password=" + password + ", password2=" + password2 + '}';
    }
    
    
}
