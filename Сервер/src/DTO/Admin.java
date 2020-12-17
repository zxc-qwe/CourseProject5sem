package DTO;

public class Admin {

    public String login;
    public String password;
    public String role;

    public Admin(String login, String password, String role) {
        this.login = login;
        this.password = password;
        this.role = role;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword(){
        return password;
    }
}