package co.edu.uptc.restaurantsystem.model;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.time.LocalDate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class User {
    public static final String ROLE_ADMIN = "ADMINISTRATOR";
    public static final String ROLE_STUDENT = "STUDENT";
    public static final String ROLE_MODERATOR = "MODERATOR";

    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
    private String names;
    private String surmanes;
    private String code;
    private String email;
    private String rol;
    private String password;
    private int balance;

    public User(String names, String surmanes, String code, String email, String rol, String password) {
        this.names = names;
        this.surmanes = surmanes;
        this.code = code;
        this.email = email;
        this.rol = rol;
        setPassword(password);
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

    public void setPassword(String password) {
        this.password = encoder.encode(password);
    }

    public int getBalance() {
        return balance;
    }

    public void updateBalance(int amount) {
        this.balance += amount;
    }

    public boolean checkPassword(String password) {
        return encoder.matches(password, this.password);
    }

    public static boolean isPasswordValid(String password) {
        String regex = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{8,}$";
        return password.matches(regex);
    }

    public static boolean isCodeValid(String code) {
        // Verificar que el código sea una cadena de 9 dígitos
        if (!code.matches("^\\d{9}$")) return false;

        LocalDate now = LocalDate.now();

        // Verificar el año del código
        int currentYear = now.getYear();
        int codeYear = Integer.parseInt(code.substring(0, 4));
        int minYear = 2000;
        if (codeYear < minYear || codeYear > currentYear) return false;

        // Finalmente verificar el semestre
        int codeSemester = Character.getNumericValue(code.charAt(4));
        int currentMonth = now.getMonthValue();
        if (currentYear == codeYear && currentMonth <= 6) return codeSemester == 1;
        return codeSemester == 1 || codeSemester == 2;
    }
}
