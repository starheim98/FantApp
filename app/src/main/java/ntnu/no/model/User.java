package ntnu.no.model;

import java.util.ArrayList;
import java.util.List;

public class User {
    private String token;
    private String username;
    private boolean loggedIn = false;

    public User(String token, String username) {
        this.token = token;
        this.username = username;
    }

    public User(){}

    public static User user = null;
    private List<UserObserver> userObservers = new ArrayList<>();

    public static User getInstance(){
        if (user == null){
            user = new User();
        }
        return user;
    }

    private void notifyObservers(){
        for (UserObserver userObserver : userObservers){
            userObserver.updateUser();
        }
    }

    public void observe(UserObserver userObserver){
        userObservers.add(userObserver);
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

    public void clearAll(){
        User user = User.getInstance();
        user.setLoggedIn(false);
        user.setUsername(null);
        user.setToken(null);
    }

    public void setLoggedIn(Boolean isLoggedIn){
        this.loggedIn = isLoggedIn;
    }

    public Boolean isLoggedIn(){
        return this.loggedIn;
    }

}

