package service;

import DTO.*;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import javafx.scene.control.Alert;

import javax.print.DocFlavor;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.lang.reflect.Type;
import java.net.InetAddress;
import java.net.Socket;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserService {

    static String ERROR = "Error";
    public static UserService userService;

    private static Socket cl;
    private static PrintStream clout;
    private static BufferedReader clin;

    public static UserService getInstanceUser() {
        if (userService == null) {
            try {
                cl = new Socket(InetAddress.getLocalHost(), 8080);
                clout = new PrintStream(cl.getOutputStream());
                clin = new BufferedReader(new InputStreamReader(cl.getInputStream()));
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return userService;
    }

    public static void addUser(DTO.User acc) {
        String send = "RegisterUser";
        clout.println(send);
        String str = new Gson().toJson(acc);
        clout.println(str);
    }

    public static void addAdmin(DTO.Admin acc){
        String send = "RegisterAdmin";
        clout.println(send);
        String str = new Gson().toJson(acc);
        clout.println(str);
    }

    public static String AuthAdmin(String login, String password){
        String send = "AuthAdmin";
        Admin admin = new Admin(login, password, "Администратор");
        clout.println(send);
        String str = new Gson().toJson(admin);
        clout.println(str);
        try {
            return clin.readLine();
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
        return "false";
    }

    public static String AuthUser(String login, String password){
        String send = "AuthUser";
        User user = new User(login, password, "Пользователь");
        clout.println(send);
        String str = new Gson().toJson(user);
        clout.println(str);
        try {
            return clin.readLine();
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
        return "false";
    }

    public static void addBranch(DTO.Branch branch) {
        String send = "AddBranch";
        clout.println(send);
        String str = new Gson().toJson(branch);
        clout.println(str);
    }

    public static void addRatio(DTO.Ratio ratio, DTO.Company company, DTO.User user){
        String send = "AddRatio";
        clout.println(send);
        String str1 = new Gson().toJson(ratio);
        clout.println(str1);
        String str2 = new Gson().toJson(company);
        clout.println(str2);
        String str3 = new Gson().toJson(user);
        clout.println(str3);
    }

    public static void infoCompany (String str) {
        String send = "InfoCompany";
        clout.println(send);
        clout.println(str);
    }

    public static Company InfoCompanyRecv() throws IOException{
        String get;
        get = clin.readLine();
        return new Gson().fromJson(get, new TypeToken<Company>() {
        }.getType());
    }

    public static void infoUser(String str1, String str2) {
        String send = "InfoUser";
        clout.println(send);
        clout.println(str1);
        clout.println(str2);
    }

    public static User showInfoUser() throws IOException{
        String get;
        get = clin.readLine();
        return new Gson().fromJson(get, new TypeToken<User>(){
        }.getType());
    }

    public static Ratio showRatioCompany() throws IOException{
        String get;
        get = clin.readLine();
        return new Gson().fromJson(get, new TypeToken<Ratio>() {
        }.getType());
    }


    public static ArrayList<String> GetBranches() throws IOException{
        String send = "GetBranches";
        clout.println(send);
        String get;
        get = clin.readLine();
        return new Gson().fromJson(get, new TypeToken<ArrayList<String>>(){
        }.getType());
    }

    public static void DeleteCompany (String send) {
        clout.println("DeleteCompany");
        clout.println(send);
    }

    public static void BranchStatAsk (String send){
        clout.println("BranchStat");
        clout.println(send);
    }

    public static Branch BranchStatRecv() throws IOException{
        String get;
        get = clin.readLine();
        return new Gson().fromJson(get, new TypeToken<Branch>(){
        }.getType());
    }

    public static String BranchStatCompNum() throws IOException{
        String get;
        get = clin.readLine();
        return get;
    }

    public static ArrayList<CompanyDiagr> getCompanyStat() throws IOException{
        String send = "GetDiagrStable";
        clout.println(send);
        String get = clin.readLine();
        return new Gson().fromJson(get, new TypeToken<ArrayList<CompanyDiagr>>(){
        }.getType());
    }

    public static void deleteAll() {
        String send = "DeleteAllUsers";
        clout.println(send);
    }

    public static ArrayList<User> getUsers() throws IOException{
        String send = "GetUsers";
        clout.println(send);
        String get = clin.readLine();
        return new Gson().fromJson(get, new TypeToken<ArrayList<User>>(){
        }.getType());
    }

    public static void deleteUser(String login){
        String str = "DeleteUser";
        clout.println(str);
        clout.println(login);
    }

    public static void blockUser(String login){
        String str = "BlockUser";
        clout.println(str);
        clout.println(login);
    }

    public static void unblockUser(String login){
        String str = "UnblockUser";
        clout.println(str);
        clout.println(login);
    }

    public static ArrayList<User> getBlockUsers() throws IOException{
        String str = "GetBlockUsers";
        clout.println(str);
        String get = clin.readLine();
        return new Gson().fromJson(get, new TypeToken<ArrayList<User>>(){
        }.getType());
    }

    public static ArrayList<User> getUnblockUsers() throws IOException{
        String str = "GetUnblockUsers";
        clout.println(str);
        String get = clin.readLine();
        return new Gson().fromJson(get, new TypeToken<ArrayList<User>>(){
        }.getType());
    }

    public static ArrayList<Company> getCompanies() throws IOException{
        String str = "GetCompanies";
        clout.println(str);
        String get = clin.readLine();
        return new Gson().fromJson(get, new TypeToken<ArrayList<Company>>(){
        }.getType());
    }

    public static void getRatio(String name){
        String str = "GetRatio";
        clout.println(str);
        clout.println(name);
    }

    public static String recvName() throws IOException {
        String get = clin.readLine();
        return get;
    }

    public static Ratio showRatio() throws IOException{
        String get = clin.readLine();
        return new Gson().fromJson(get, new TypeToken<Ratio>(){
        }.getType());
    }

    public static void setBranch(Branch branch) {
        String send = "SetBranch";
        clout.println(send);
        String str = new Gson().toJson(branch);
        clout.println(str);
    }

    public static String checkStability (String send) throws IOException{
        String str = "CheckStability";
        clout.println(str);
        clout.println(send);
        str  = clin.readLine();
        return str;
    }
}

