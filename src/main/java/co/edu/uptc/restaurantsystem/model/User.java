package co.edu.uptc.restaurantsystem.model;

public class User {
    public static final String ADMINISTRATOR_ROLE = "";
    public static final String STUDENT_ROLE = "";
    public static final String MASTER_ROLE = "";
    private String names;
    private String surmanes;
    private String code;
    private String email;
    private String rol;
    private String password;

    public User(String names, String surmanes, String code, String email, String rol, String password) {
        this.names = names;
        this.surmanes = surmanes;
        this.code = code;
        this.email = email;
        this.rol = rol;
        this.password = password;
    }

    public String getNames() {
        return names;
    }

    public void setNames(String names) {
        this.names = names;
    }

    public String getSurmanes() {
        return surmanes;
    }

    public void setSurmanes(String surmanes) {
        this.surmanes = surmanes;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
