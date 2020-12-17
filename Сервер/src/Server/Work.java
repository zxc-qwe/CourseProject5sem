package Server;


import DTO.*;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;


import java.io.*;
import java.lang.reflect.Type;
import java.net.Socket;
import java.net.SocketException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static Database.Database.*;

public class Work extends Thread {

    private static Socket cl = null;
    String REGISTERADMIN = "RegisterAdmin";
    String REGISTERUSER = "RegisterUser";
    String AUTHADMIN = "AuthAdmin";
    String AUTHUSER = "AuthUser";
    String ADDBRANCH = "AddBranch";
    String ADDRATIO = "AddRatio";
    String INFOCOMPANY = "InfoCompany";
    String INFOUSER = "InfoUser";
    String GETBRANCH = "GetBranches";
    String DELETECOMPANY = "DeleteCompany";
    String BRANCHSTAT = "BranchStat";
    String GETDIAGRSTABLE = "GetDiagrStable";
    String DELETEALLUSERS = "DeleteAllUsers";
    String GETUSERS = "GetUsers";
    String DELETEUSER = "DeleteUser";
    String BLOCKUSER = "BlockUser";
    String UNBLOCKUSER = "UnblockUser";
    String GETBLOCKUSERS = "GetBlockUsers";
    String GETUNBLOCKUSERS = "GetUnblockUsers";
    String GETCOMPANIES = "GetCompanies";
    String GETRATIO = "GetRatio";
    String SETBRANCH = "SetBranch";
    String CHECKSTABILITY = "CheckStability";
    BufferedReader sin;
    PrintStream sout;
    ArrayList<String> resultSplit;

    public Work(Socket cl) {
        this.cl = cl;
    }
    public void run() {
        try {
            sin = new BufferedReader(new InputStreamReader(cl.getInputStream()));
            sout = new PrintStream(cl.getOutputStream());

            while (true) {
                String input;
                input = sin.readLine();
                if (REGISTERADMIN.equals(input)) {
                    registerAdmin();
                }
                else if (REGISTERUSER.equals(input)) {
                    registerUser();
                }
                else if (AUTHADMIN.equals(input)) {
                    AuthAdmin();
                }
                else if (AUTHUSER.equals(input)) {
                    AuthUser();
                }
                else if (ADDBRANCH.equals(input)) {
                    AddBranch();
                }
                else if (ADDRATIO.equals(input)) {
                    AddRatio();
                }
                else if (INFOCOMPANY.equals(input)) {
                    InfoCompany();
                }
                else if (INFOUSER.equals(input)) {
                    InfoUser();
                }
                else if (GETBRANCH.equals(input)) {
                    GetBranch();
                }
                else if (DELETECOMPANY.equals(input)) {
                    DeleteCompany();
                }
                else if (BRANCHSTAT.equals(input)) {
                    BranchStat();
                }
                else if (GETDIAGRSTABLE.equals(input)) {
                    GetDiagrStable();
                }
                else if (DELETEALLUSERS.equals(input)) {
                    DeleteAllUsers();
                }
                else if (GETUSERS.equals(input)) {
                    GetUsers();
                }
                else if (DELETEUSER.equals(input)) {
                    DeleteUser();
                }
                else if (BLOCKUSER.equals(input)) {
                    BlockUser();
                }
                else if (UNBLOCKUSER.equals(input)) {
                    UnblockUser();
                }
                else if (GETBLOCKUSERS.equals(input)) {
                    GetBlockUsers();
                }
                else if (GETUNBLOCKUSERS.equals(input)) {
                    GetUnblockUsers();
                }
                else if (GETCOMPANIES.equals(input)) {
                    GetCompanies();
                }
                else if (GETRATIO.equals(input)) {
                    GetRatio();
                }
                else if (SETBRANCH.equals(input)) {
                    SetBranch();
                }
                else if (CHECKSTABILITY.equals(input)) {
                    CheckStability();
                }
            }
        } catch (SocketException ex) {
            System.out.println("Клиент отключился");
        } catch (IOException | SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                sin.close();
                sout.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            ServerStats.connectionNumber--;
        }
    }

    private void registerAdmin() throws IOException {
        String get;
        get = sin.readLine();
        Gson g = new Gson();
        Type Tip = new TypeToken<Admin>() {
        }.getType();
        Admin admin = g.fromJson(get, Tip);
        String sqlst = "INSERT INTO mydb.accounts (login, password, role)" +
                "VALUES ('" + admin.getLogin() + "', '" + admin.getPassword() + "', '" + "Администратор" + "')";
        openDatabase();
        execute(sqlst);
    }

    private void registerUser () throws IOException, SQLException{
        String get;
        get = sin.readLine();
        ResultSet set;
        int idaccounts;
        Gson g = new Gson();
        Type Tip = new TypeToken<User>() {
        }.getType();
        User user = g.fromJson(get, Tip);
        String sqlst = "INSERT INTO mydb.accounts (login, password, role)" +
                "VALUES ('" + user.getLogin() + "', '" + user.getPassword() + "', '" + "Пользователь" + "')";
        openDatabase();
        execute(sqlst);
        String sqlst2 = "SELECT idaccounts FROM mydb.accounts WHERE (login = '"+ user.getLogin() +
                "') and (password = '" + user.getPassword() + "')";
        set = getDatabase(sqlst2);
        set.next();
        idaccounts = set.getInt("idaccounts");
        String sqlst3 = "INSERT INTO mydb.users (idaccounts, name, position)" +
                "VALUES ('" + idaccounts + "','" + user.getName() + "', '" + user.getPosition() + "')";
        openDatabase();
        execute(sqlst3);
    }


    private void AuthAdmin(){
        String outline = null;
        try {
            this.resultSplit = new ArrayList<>();
            String get = "";
            get = sin.readLine();
            Gson g = new Gson();
            Type Tip = new TypeToken<Admin>() {
            }.getType();
            Admin admin = g.fromJson(get, Tip);
            String authAdminSQL = "SELECT DISTINCT * FROM mydb.accounts where accounts.login = '" + admin.getLogin() + "'" +
                    " and accounts.password = '" + admin.getPassword() + "'";
            openDatabase();
            ResultSet acc = getDatabase(authAdminSQL);

            if (acc.next()) {
                outline = acc.getString("role");
            } else {
                outline = "false";
            }
        } catch (SQLException | IOException ex) {
            ex.printStackTrace();
        }
        sout.println(outline);
    }

    private void AuthUser(){
        String outline = null;
        try {
            this.resultSplit = new ArrayList<>();
            String get = sin.readLine();
            Gson g = new Gson();
            Type Tip = new TypeToken<User>() {
            }.getType();
            User user = g.fromJson(get, Tip);
            String authAdminSQL = "SELECT DISTINCT * FROM mydb.accounts where accounts.login = '" + user.getLogin() + "'" +
                    " and accounts.password = '" + user.getPassword() + "'";
            openDatabase();
            ResultSet acc = getDatabase(authAdminSQL);
            if (acc.next()) {
                outline = acc.getString("role");
            } else {
                outline = "false";
            }
        } catch (SQLException | IOException ex) {
            ex.printStackTrace();
        }
        sout.println(outline);

    }

    private void AddBranch() throws IOException {
        String get;
        get = sin.readLine();
        Gson g = new Gson();
        Type Tip = new TypeToken<Branch>() {
        }.getType();
        Branch branch = g.fromJson(get, Tip);
        String sqlst = "INSERT INTO mydb.branches (name, vvp_percent, companiesNum)" +
                "VALUES ('" + branch.getName() + "', '" + branch.getVvp_percent() + "', '" + branch.getCompaniesNum() + "')";
        openDatabase();
        execute(sqlst);
    }

    private  void AddRatio() throws IOException, SQLException{
        ResultSet set;
        int idaccounts;
        int idbranches;
        int idcompanies;
        String get1;
        get1 = sin.readLine();
        Gson g1 = new Gson();
        Type Tip1 = new TypeToken<Ratio>(){
        }.getType();
        Ratio ratio = g1.fromJson(get1, Tip1);
        String get2;
        get2 = sin.readLine();
        Gson g2 = new Gson();
        Type Tip2 = new TypeToken<Company>(){
        }.getType();
        Company company = g2.fromJson(get2, Tip2);
        String get3;
        get3 = sin.readLine();
        Gson g3 = new Gson();
        Type Tip3 = new TypeToken<User>(){
        }.getType();
        User user = g3.fromJson(get3, Tip3);
        String sqlst1 = "SELECT idaccounts FROM mydb.accounts WHERE (login = '"+ user.getLogin() +"') and (password = '" +
                user.getPassword() +"')";
        set = getDatabase(sqlst1);
        set.next();
        idaccounts = set.getInt("idaccounts");
        String sqlst2 = "SELECT idbranches FROM mydb.branches WHERE (name = '"+ company.getBranch() +"')";
        set = getDatabase(sqlst2);
        set.next();
        idbranches = set.getInt("idbranches");
        String sqlst3 = "INSERT INTO mydb.companies (idbranches, name, ownership, workersNum, market_percent)" +
                " VALUES ('" + idbranches + "', '" + company.getCompanyName() + "', '" + company.getOwnership() +
                "', '" + company.getWorkersNum() + "', '" + company.getMarket_percent() + "')";
        openDatabase();
        execute(sqlst3);
        String sqlst4 = "SELECT idcompanies FROM mydb.companies WHERE (name = '" +company.getCompanyName()+ "')";
        set = getDatabase(sqlst4);
        set.next();
        idcompanies = set.getInt("idcompanies");
        String sqlst5 = "INSERT INTO mydb.finratio (idaccounts, idcompanies, koef1, koef2, " +
                "koef3, koef4, koef5, koef6, koef7, koef8)"+
                " VALUES ('" + idaccounts + "', '" + idcompanies + "', '" + ratio.getRat1() + "', '" + ratio.getRat2() +
                "', '" + ratio.getRat3() + "', '" + ratio.getRat4() + "', '" + ratio.getRat5() +
                "', '" + ratio.getRat6() + "', '" + ratio.getRat7() + "', '" +ratio.getRat8() + "')";
        openDatabase();
        execute(sqlst5);

    }

    private void InfoCompany() throws IOException, SQLException{
        String get;
        ResultSet set;
        int idbranches;
        String name;
        get = sin.readLine();
        String str1 = "SELECT idbranches FROM mydb.companies WHERE (name = '" + get + "')";
        set = getDatabase(str1);
        if (set.next()) {

            idbranches = set.getInt("idbranches");
            String str2 = "SELECT name FROM mydb.branches WHERE (idbranches = '" + idbranches + "')";
            set = getDatabase(str2);
            if (set.next()) {

                name = set.getString("name");
                String str3 = "SELECT * FROM mydb.companies WHERE (name = '" + get + "')";
                openDatabase();
                ResultSet result = getDatabase(str3);

                if(result.next()) {

                    Company company = new Company();

                    company.setCompanyName(result.getString("name"));
                    company.setOwnership(result.getString("ownership"));
                    company.setWorkersNum(result.getString("workersNum"));
                    company.setMarket_percent(result.getString("market_percent"));
                    company.setBranch(name);
                    String send = new Gson().toJson(company);
                    sout.println(send);
                    String str4 = "SELECT idcompanies from mydb.companies WHERE (name = '" + company.getCompanyName() + "')";
                    set = getDatabase(str4);
                    if(set.next()) {
                        int idcompanies = set.getInt("idcompanies");
                        String str5 = "SELECT * FROM mydb.finratio WHERE (idcompanies = '" + idcompanies + "')";
                        openDatabase();
                        result = getDatabase(str5);
                        if(result.next()) {
                            Ratio ratio = new Ratio();
                            ratio.setRat1(result.getString("koef1"));
                            ratio.setRat2(result.getString("koef2"));
                            ratio.setRat3(result.getString("koef3"));
                            ratio.setRat4(result.getString("koef4"));
                            ratio.setRat5(result.getString("koef5"));
                            ratio.setRat6(result.getString("koef6"));
                            ratio.setRat7(result.getString("koef7"));
                            ratio.setRat8(result.getString("koef8"));
                            send = new Gson().toJson(ratio);
                            sout.println(send);
                        }else{
                            Company company1 = new Company();
                            company1.setCompanyName("Error");
                            String send1 = new Gson().toJson(company1);
                            sout.println(send1);
                            Ratio ratio = new Ratio();
                            ratio.setRat1("Error");
                            String send2 = new Gson().toJson(ratio);
                            sout.println(send2);
                        }
                    }else {
                        Company company1 = new Company();
                        company1.setCompanyName("Error");
                        String send1 = new Gson().toJson(company1);
                        sout.println(send1);
                        Ratio ratio = new Ratio();
                        ratio.setRat1("Error");
                        String send2 = new Gson().toJson(ratio);
                        sout.println(send2);
                    }
                }else{
                    Company company = new Company();
                    company.setCompanyName("Error");
                    String send1 = new Gson().toJson(company);
                    sout.println(send1);
                    Ratio ratio = new Ratio();
                    ratio.setRat1("Error");
                    String send2 = new Gson().toJson(ratio);
                    sout.println(send2);
                }
            } else {
                Company company = new Company();
                company.setCompanyName("Error");
                String send1 = new Gson().toJson(company);
                sout.println(send1);
                Ratio ratio = new Ratio();
                ratio.setRat1("Error");
                String send2 = new Gson().toJson(ratio);
                sout.println(send2);
            }
        } else {
            Company company = new Company();
            company.setCompanyName("Error");
            String send1 = new Gson().toJson(company);
            sout.println(send1);
            Ratio ratio = new Ratio();
            ratio.setRat1("Error");
            String send2 = new Gson().toJson(ratio);
            sout.println(send2);
        }
    }

    private void InfoUser() throws IOException, SQLException{
        String log, pass;
        log = sin.readLine();
        pass = sin.readLine();
        int idaccounts;
        ResultSet set;
        String str1 = "SELECT idaccounts FROM mydb.accounts WHERE (login = '" + log + "') and (password = '" + pass + "')";
        set = getDatabase(str1);
        set.next();
        idaccounts = set.getInt("idaccounts");
        String str2 = "SELECT * FROM mydb.users WHERE (idaccounts = '" + idaccounts + "')";
        openDatabase();
        ResultSet result = getDatabase(str2);
        result.next();
        User user = new User();
        user.setLogin(log);
        user.setPassword(pass);
        user.setName(result.getString("name"));
        user.setPosition(result.getString("position"));
        user.setRole("Пользователь");
        String send = new Gson().toJson(user);
        sout.println(send);
    }

    private void GetBranch() throws SQLException{
        String str = "SELECT name FROM mydb.branches";
        openDatabase();
        ResultSet result = getDatabase(str);
        ArrayList<String> branches = new ArrayList<>();
        while (result.next()){
            branches.add(result.getString("name"));
        }
        String send = new Gson().toJson(branches);
        sout.println(send);
    }

    private void DeleteCompany() throws IOException, SQLException{
        String company = sin.readLine();
        String str1 = "SELECT idcompanies FROM mydb.companies WHERE (name = '" + company + "')";
        int idcompanies;
        ResultSet set = getDatabase(str1);
        set.next();
        idcompanies = set.getInt("idcompanies");
        String str2 = "DELETE FROM mydb.finratio WHERE (idcompanies = '" + idcompanies + "')";
        openDatabase();
        execute(str2);
        String str3 = "DELETE FROM mydb.companies WHERE (name = '" + company + "')";
        openDatabase();
        execute(str3);

    }

    private void BranchStat() throws IOException, SQLException{
        String branch1 = sin.readLine();
        String str1 = "SELECT * FROM mydb.branches WHERE (name = '" + branch1 + "')";
        ResultSet result = getDatabase(str1);
        result.next();
        Branch branch = new Branch();
        int idbranches = result.getInt("idbranches");
        branch.setName(result.getString("name"));
        branch.setVvp_percent(result.getString("vvp_percent"));
        branch.setCompaniesNum(result.getString("companiesNum"));
        String send1 = new Gson().toJson(branch);
        sout.println(send1);
        String str2 = "SELECT name FROM mydb.companies WHERE (idbranches = '" + idbranches + "')";
        ResultSet set = getDatabase(str2);
        int compnum = 0;
        while (set.next()){
            compnum++;
        }
        String strnum = String.valueOf(compnum);
        sout.println(strnum);
    }

    public void GetDiagrStable() throws SQLException{
        String str1 = "SELECT name FROM mydb.branches";
        ArrayList<String> branches = new ArrayList<>();
        ResultSet set = getDatabase(str1);
        while (set.next()){
            branches.add(set.getString("name"));
        }
        String str2 = "SELECT idbranches FROM mydb.branches";
        ArrayList<String> idbranches = new ArrayList<>();
        set = getDatabase(str2);
        while (set.next()){
            idbranches.add(set.getString("idbranches"));
        }
        int n = idbranches.size();
        int i = 0;
        ArrayList<String> compNumUnstable = new ArrayList<>();
        String str;
        ArrayList<String> idcompanies = new ArrayList<>();
        while (i < n){
            str = "SELECT idcompanies FROM mydb.companies WHERE (idbranches = '" + idbranches.get(i) + "')";
            set = getDatabase(str);
            int compNumBr = 0;
            while (set.next()){
                compNumBr++;
                idcompanies.add(set.getString("idcompanies"));
            }
            compNumUnstable.add(String.valueOf(compNumBr));
            i++;
        }
        i = 0;
        ArrayList<String> compNumStable = new ArrayList<>();
        int compNumBr = 0;
        int m = idcompanies.size();
        int flag;
        while (i < m){
            int current, next;
            String idc, idn;
            str = "SELECT koef2, koef3, koef8 FROM mydb.finratio WHERE (idcompanies = '" + idcompanies.get(i) +
                    "')";
            set = getDatabase(str);
            set.next();
            double koef2 = Double.parseDouble((set.getString("koef2")).replaceAll(",", "."));
            double koef3 = Double.parseDouble((set.getString("koef3")).replaceAll(",", "."));
            double koef8 = Double.parseDouble((set.getString("koef8")).replaceAll(",", "."));
            if(koef3 < 0.67d || koef2 < 0.8d || koef8 >= 1.0d) {
                compNumBr++;
            }
            idc = idcompanies.get(i);


            flag = 0;
            if (m - i == 1){
                flag = 1;
                idn = "";
            } else {
                idn = idcompanies.get(i+1);
            }
            str = "SELECT idbranches FROM mydb.companies WHERE (idcompanies = '" + idc + "')";
            set = getDatabase(str);
            set.next();
            current = set.getInt("idbranches");
            if (flag == 1){
                next = 0;
            } else {
                str = "SELECT idbranches FROM mydb.companies WHERE (idcompanies = '" + idn + "')";
                set = getDatabase(str);
                set.next();
                next = set.getInt("idbranches");
            }
            if (current == next){

            } else {
                compNumStable.add(String.valueOf(compNumBr));
                compNumBr = 0;
            }
            i++;
        }
        ArrayList<CompanyDiagr> result = new ArrayList<>();
        i = 0;
        while (i < n) {
            CompanyDiagr add = new CompanyDiagr(branches.get(i), Integer.parseInt(compNumStable.get(i)),
                    Integer.parseInt(compNumUnstable.get(i)));
            result.add(add);
            i++;
        }
        String send = new Gson().toJson(result);
        sout.println(send);
    }

    private void DeleteAllUsers() throws SQLException{
        String str = "SELECT idaccounts FROM mydb.accounts WHERE (role = 'Пользователь') or (role = 'Блокировка')";
        ArrayList<String> idaccounts = new ArrayList<>();
        ResultSet set = getDatabase(str);
        while (set.next()){
            idaccounts.add(set.getString("idaccounts"));
        }
        int n = idaccounts.size();
        int i = 0;
        while (i < n) {
            str = "DELETE FROM mydb.users WHERE (idaccounts = '" + idaccounts.get(i) + "')";
            openDatabase();
            execute(str);
            i++;
        }
        i = 0;
        while (i < n) {
            str = "DELETE FROM mydb.finratio WHERE (idaccounts = '" + idaccounts.get(i) + "')";
            openDatabase();
            execute(str);
            i++;
        }
        str = "DELETE FROM mydb.accounts WHERE (role = 'Пользователь') or (role = 'Блокировка')";
        openDatabase();
        execute(str);

    }

    private void GetUsers() throws SQLException{
        String str = "SELECT login FROM mydb.accounts WHERE (role = 'Пользователь') or (role = 'Блокировка')";
        ArrayList<User> result = new ArrayList<>();
        ResultSet set = getDatabase(str);
        while (set.next()){
            result.add(new User(set.getString("login")));
        }
        String send = new Gson().toJson(result);
        sout.println(send);
    }

    private void DeleteUser() throws IOException, SQLException{
        String login = sin.readLine();
        String str = "SELECT idaccounts FROM mydb.accounts WHERE (login = '" + login + "')";
        ResultSet set = getDatabase(str);
        set.next();
        int idaccounts = set.getInt("idaccounts");
        str = "DELETE FROM mydb.finratio WHERE (idaccounts = '" + idaccounts + "')";
        openDatabase();
        execute(str);
        str = "DELETE FROM mydb.users WHERE (idaccounts = '" + idaccounts + "')";
        openDatabase();
        execute(str);
        str = "DELETE FROM mydb.accounts WHERE (idaccounts = '" + idaccounts + "')";
        openDatabase();
        execute(str);

    }

    private void BlockUser () throws IOException{
        String login = sin.readLine();
        String str = "UPDATE mydb.accounts SET role = 'Блокировка' WHERE (login = '" + login + "')";
        openDatabase();
        execute(str);
    }

    private void UnblockUser () throws IOException{
        String login = sin.readLine();
        String str = "UPDATE mydb.accounts SET role = 'Пользователь' WHERE (login = '" + login + "')";
        openDatabase();
        execute(str);
    }

    private void GetBlockUsers() throws SQLException{
        String str = "SELECT login FROM mydb.accounts WHERE (role = 'Пользователь')";
        ArrayList<User> result = new ArrayList<>();
        ResultSet set1 = getDatabase(str);
        while (set1.next()){
            result.add(new User(set1.getString("login")));
        }
        String send = new Gson().toJson(result);
        sout.println(send);
    }

    private void GetUnblockUsers() throws SQLException{
        String str = "SELECT login FROM mydb.accounts WHERE (role = 'Блокировка')";
        ArrayList<User> result = new ArrayList<>();
        ResultSet set1 = getDatabase(str);
        while (set1.next()){
            result.add(new User(set1.getString("login")));
        }
        String send = new Gson().toJson(result);
        sout.println(send);
    }

    private void GetCompanies() throws SQLException{
        String str = "SELECT name FROM mydb.companies";
        ArrayList<Company> result = new ArrayList<>();
        ResultSet set = getDatabase(str);
        while (set.next()){
            result.add(new Company(set.getString("name")));
        }
        String send = new Gson().toJson(result);
        sout.println(send);
    }

    private void GetRatio() throws IOException, SQLException{
        String get = sin.readLine();
        String str = "SELECT idcompanies FROM mydb.companies WHERE (name = '" + get + "')";
        ResultSet set = getDatabase(str);
        set.next();
        int idcompanies = set.getInt("idcompanies");
        str = "SELECT * FROM mydb.finratio WHERE (idcompanies = '" + idcompanies + "')";
        set = getDatabase(str);
        set.next();
        Ratio ratio = new Ratio();
        ratio.setRat1(set.getString("koef1"));
        ratio.setRat2(set.getString("koef2"));
        ratio.setRat3(set.getString("koef3"));
        ratio.setRat4(set.getString("koef4"));
        ratio.setRat5(set.getString("koef5"));
        ratio.setRat6(set.getString("koef6"));
        ratio.setRat7(set.getString("koef7"));
        ratio.setRat8(set.getString("koef8"));
        sout.println(get);
        String send = new Gson().toJson(ratio);
        sout.println(send);
    }

    private void SetBranch() throws IOException, SQLException{
        String get = sin.readLine();
        Branch branch = new Gson().fromJson(get, new TypeToken<Branch>(){
        }.getType());
        String str = "INSERT INTO mydb.branches (name, vvp_percent, companiesNum)" +
                "VALUES ('" + branch.getName() + "', '" + branch.getVvp_percent() + "', '" + branch.getCompaniesNum() + "')";
        openDatabase();
        execute(str);
    }

    private void CheckStability() throws IOException, SQLException{
        String get = sin.readLine();
        String str = "SELECT idcompanies FROM mydb.companies WHERE (name = '" + get + "')";
        ResultSet set = getDatabase(str);
        set.next();
        int idcompanies = set.getInt("idcompanies");
        str = "SELECT koef2, koef3, koef8 FROM mydb.finratio WHERE (idcompanies = '" + idcompanies + "')";
        set = getDatabase(str);
        set.next();
        double koef2 = Double.parseDouble((set.getString("koef2")).replaceAll(",", "."));
        double koef3 = Double.parseDouble((set.getString("koef3")).replaceAll(",", "."));
        double koef8 = Double.parseDouble((set.getString("koef8")).replaceAll(",", "."));
        if(koef3 < 0.67d || koef2 < 0.8d || koef8 >= 1.0d) {
            sout.println("Stable");
        } else {
            sout.println("Unstable");
        }
    }
}