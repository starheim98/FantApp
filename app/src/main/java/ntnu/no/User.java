package ntnu.no;

import java.util.ArrayList;

public class User {
    private String token;
    private String username;

    public User(String token, String username) {
        this.token = token;
        this.username = username;
    }

    public User(){}

    public static User user = null;
    private ArrayList<Observer> observers = new ArrayList<>();

    public static User getInstance(){
        if (user == null){
            user = new User();
        }
        return user;
    }

    private void notifyObservers(){
        for (Observer observer : observers){
            observer.updateUser();
        }
    }

    public void observe(Observer observer){
        observers.add(observer);
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
        notifyObservers();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
        notifyObservers();
    }
}

