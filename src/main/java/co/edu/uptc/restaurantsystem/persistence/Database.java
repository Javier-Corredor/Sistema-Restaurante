package co.edu.uptc.restaurantsystem.persistence;

import co.edu.uptc.restaurantsystem.model.User;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.net.URL;
import java.util.ArrayList;

public class Database {

    // Patron de diseño singleton usado (Una sola instancia de la clase)
    private static final Database DATABASE = new Database();

    private static final URL FILE_PATH = Database.class.getResource("/database/users.json");

    private ArrayList<User> users;
    private Gson gson;

    private Database() {
        gson = new GsonBuilder()
                .setPrettyPrinting()
                .registerTypeAdapter(User.class, new User.UserAdapter())
                .create();
        System.out.println(FILE_PATH);
    }

    private void load() throws IOException {
        try (BufferedReader bf = new BufferedReader(new FileReader("FILE_PATH"));) {
            Type type = new TypeToken<ArrayList<User>>() {
            }.getType();
            users = gson.fromJson(bf, type);
        }
    }

    private void save() {

    }

    private boolean addRegister(User user) {
        return false;
    }

    public static Database getDatabase() { // Acceso global a la única instancia de la clase
        return DATABASE;
    }
}