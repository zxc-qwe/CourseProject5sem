package Database;

import com.mysql.fabric.jdbc.FabricMySQLDriver;

import java.sql.*;
import java.util.Properties;


public class Database {
    public static Database database;
    private static Connection connection;
    private static Statement statement;
    private static final String url = "jdbc:mysql://localhost:3306/mydb?useSSL=false";
    private static final String user = "root";
    private static final String password = "1234";

    public static Database openDatabase()
    {
        try {
            Class.forName ("com.mysql.jdbc.Driver").newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        if (database == null) {
            try {
                DriverManager.registerDriver(new FabricMySQLDriver ());
                connection = DriverManager.getConnection(url, SetSomeProperties(new Properties ()));
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            return database;
        } else {
            return database;
        }
    }

    public static Properties SetSomeProperties(Properties prop) {
        prop.put("user", user);
        prop.put("password", password);
        prop.put("acterEncodiutoReconnect\", \"true\");\n" +
                "        prop.put(\"charang", "UTF-8");
        prop.put("useUnicode", "true");
        return prop;
    }

    public static ResultSet getDatabase(String str) {
        ResultSet res = null;
        try {
            statement = connection.createStatement();
            res = statement.executeQuery(str);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return res;
    }

    public static void execute(String str)
    {
        try {
            statement = connection.createStatement();
            statement.execute(str);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}