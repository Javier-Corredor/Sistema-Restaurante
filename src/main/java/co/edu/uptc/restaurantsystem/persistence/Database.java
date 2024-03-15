package co.edu.uptc.restaurantsystem.persistence;

import co.edu.uptc.restaurantsystem.model.User;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.net.URL;
import java.util.ArrayList;
import java.util.Objects;

public class Database {

    // Patron de diseño singleton usado (Una sola instancia de la clase)
    private static final Database DATABASE;
    private static final String FILE_PATH;

    static {
        URL url = Objects.requireNonNull(Database.class.getResource("/database/users.json"));
        FILE_PATH = url.getPath();
        DATABASE = new Database();
    }

    private ArrayList<User> users;
    private Gson gson = new GsonBuilder()
            .setPrettyPrinting()
            .registerTypeAdapter(User.class, new User.UserAdapter())
            .create();

    private Database() {
        load();
    }

    private void load() {
        try (BufferedReader bf = new BufferedReader(new FileReader(FILE_PATH))) {
            Type type = new TypeToken<ArrayList<User>>() {
            }.getType();
            users = gson.fromJson(bf, type);
        } catch (IOException ignored) {

        }
    }

    public void save() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_PATH))) {
            bw.write(gson.toJson(users));
            bw.flush();
        } catch (IOException ignored) {

        }
    }

    public void addRegister(User user) {
        users.add(user);
    }

    public boolean verifyCredentials(String emailOrCode, String password) {
        User found = search(emailOrCode);
        return found != null && found.checkPassword(password);
    }

    private User search(String emailOrCode) {
        for (User user : users) {
            if (user.getCode().equals(emailOrCode) || user.getEmail().equals(emailOrCode)) {
                return user;
            }
        }
        return null;
    }

    public String generateUniqueEmail(String name, String lastName) {
        String domain = User.getEmailDomain();
        String email = String.format("%s%s%s", name, lastName, domain);
        int i = 0;
        while (searchByEmail(email) != null) {
            i++;
            email = String.format("%s.%s%02d%s", name, lastName, i, domain);
        }
        return email;
    }

    public User searchByEmail(String email) {
        for (User user : users) {
            if (user.getEmail().equals(email)) return user;
        }

        return null;
    }

    public User searchByCode(String code) {
        for (User user : users) {
            if (user.getCode().equals(code)) return user;
        }

        return null;
    }

    public static Database getDatabase() { // Acceso global a la única instancia de la clase
        return DATABASE;
    }
}