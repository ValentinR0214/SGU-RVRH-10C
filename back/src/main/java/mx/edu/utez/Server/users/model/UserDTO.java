package mx.edu.utez.Server.users.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class UserDTO {

    @NotNull(groups = {Modify.class,ChangeStatus.class},message = "El id no puede ser nulo")
    private Long id;

    @NotBlank(groups = {Register.class,Modify.class},message = "El nombre no puede estar vacio")
    private String nombre;

    @NotBlank(groups = {Register.class,Modify.class},message = "El apellido no puede estar vacio")
    private String apellido;

    @NotBlank(groups = {Register.class,Modify.class},message = "El email no puede estar vacio")
    private String email;

    @NotBlank(groups = {Register.class,Modify.class},message = "El telefono no puede estar vacio")
    private String telefono;

    public UserDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public interface Register{}
    public interface Modify{}
    public interface ChangeStatus{}
}
