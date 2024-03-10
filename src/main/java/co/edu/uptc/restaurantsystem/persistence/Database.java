package co.edu.uptc.restaurantsystem.persistence;

import co.edu.uptc.restaurantsystem.model.User;

import java.util.ArrayList;

public class Database {
    private static final Database DATABASE = new Database();

    private ArrayList<User> users = new ArrayList<>();

    private Database() {

    }

    public static Database getDatabase() {
        return DATABASE;
    }
}