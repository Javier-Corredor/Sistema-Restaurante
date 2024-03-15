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
        DATABASE = new Database();
        URL url = Objects.requireNonNull(Database.class.getResource("/database/users.json"));
        FILE_PATH = url.getPath();
    }

    private ArrayList<User> users;
    private Gson gson;

    private Database() {
        gson = new GsonBuilder()
                .setPrettyPrinting()
                .registerTypeAdapter(User.class, new User.UserAdapter())
                .create();
    }

    private void load() throws IOException {
        try (BufferedReader bf = new BufferedReader(new FileReader(FILE_PATH))) {
            Type type = new TypeToken<ArrayList<User>>() {
            }.getType();
            users = gson.fromJson(bf, type);
        }
    }

    private void save() throws IOException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_PATH))) {
            bw.write(gson.toJson(users));
            bw.flush();
            users = null;
        }
    }

    public void addRegister(User user) throws IOException {
        load();
        users.add(user);
        save();
    }

    public static Database getDatabase() { // Acceso global a la única instancia de la clase
        return DATABASE;
    }
}