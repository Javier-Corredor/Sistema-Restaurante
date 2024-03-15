package co.edu.uptc.restaurantsystem.model;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.lang.reflect.Type;
import java.time.LocalDate;


public class User {
    public static final String ROLE_ADMIN = "ADMINISTRATOR";
    public static final String ROLE_STUDENT = "STUDENT";
    public static final String ROLE_MODERATOR = "MODERATOR";

    private static final String EMAIL_DOMAIN = "@uptc.edu.co";

    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
    private String names;
    private String surmanes;
    private String code;
    private String email;
    private String role;
    private String encryptedPassword;
    private int balance;

    private User(String names, String surmanes, String code, String email, String role, String encryptedPassword, int balance) {
        this.names = names;
        this.surmanes = surmanes;
        this.code = code;
        this.email = email;
        this.role = role;
        this.encryptedPassword = encryptedPassword;
        this.balance = balance;
    }

    public User(String names, String surmanes, String code, String role, String email, String password) {
        this.names = names.toUpperCase();
        this.surmanes = surmanes.toUpperCase();
        this.email = email;
        this.code = code;
        this.role = role;
        encryptPassword(password);
    }

    public User(String names, String surmanes, String code, String email, String password) {
        this(names, surmanes, code, ROLE_STUDENT, email, password);
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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void encryptPassword(String encryptedPassword) {
        this.encryptedPassword = encoder.encode(encryptedPassword);
    }

    public int getBalance() {
        return balance;
    }

    public void updateBalance(int amount) {
        this.balance += amount;
    }

    public boolean checkPassword(String password) {
        return encoder.matches(password, this.encryptedPassword);
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

    public static String getEmailDomain() {
        return EMAIL_DOMAIN;
    }

    public static class UserAdapter implements JsonSerializer<User>, JsonDeserializer<User> {

        @Override
        public User deserialize(JsonElement jsonElement, Type type,
                                JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
            JsonObject jsonObject = jsonElement.getAsJsonObject();
            String names = jsonObject.get("names").getAsString();
            String surnames = jsonObject.get("surnames").getAsString();
            String code = jsonObject.get("code").getAsString();
            String email = jsonObject.get("email").getAsString();
            String role = jsonObject.get("role").getAsString();
            String encryptedPassword = jsonObject.get("password").getAsString();
            int balance = jsonObject.get("balance").getAsInt();
            return new User(names, surnames, code, email, role, encryptedPassword, balance);
        }

        @Override
        public JsonElement serialize(User user, Type type, JsonSerializationContext jsonSerializationContext) {
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("names", user.names);
            jsonObject.addProperty("surnames", user.surmanes);
            jsonObject.addProperty("code", user.code);
            jsonObject.addProperty("email", user.email);
            jsonObject.addProperty("balance", user.balance);
            jsonObject.addProperty("role", user.role);
            jsonObject.addProperty("password", user.encryptedPassword);
            return jsonObject;
        }
    }
}
