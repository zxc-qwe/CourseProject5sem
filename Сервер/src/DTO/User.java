package DTO;

public class User {
    public String login;
    public String password;
    public String role;
    public String name;
    public String position;


    public User(String login, String password, String role, String name, String position) {
        this.login = login;
        this.password = password;
        this.role = role;
        this.name = name;
        this.position = position;
    }

    public User(String login){
        this.login = login;
    }

    public User(String login, String role){
        this.login = login;
        this.role = role;
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

    public void setLogin(String login){
        this.login = login;
    }
    public void setPassword(String password){
        this.password = password;
    }
    public void setName(String name){
        this.name = name;
    }
    public void setPosition(String position){
        this.position = position;
    }
    public void setRole(String role){
        this.role = role;
    }

    public User(){

    }
}
