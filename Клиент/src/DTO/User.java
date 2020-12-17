package DTO;

public class User {
    String login;
    String password;
    String role;
    String name;
    String position;

    public static String log;
    public static String pass;

    public User(String login, String password, String role, String name, String position) {
        this.login = login;
        this.password = password;
        this.role = role;
        this.name = name;
        this.position = position;
    }

    public User (String login, String password, String role){
        this.login = login;
        this.password = password;
        this.role = role;
    }

    public User (String login){
        this.login = login;
    }

    public User(){

    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public String getName(){
        return name;
    }

    public String getPosition(){
        return position;
    }
}
