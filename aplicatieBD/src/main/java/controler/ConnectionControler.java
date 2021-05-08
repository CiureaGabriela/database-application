package controler;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

//Controlerul pentru conexiune -> o clasa de tip singleton care se ocupa struct cu managementul conexinuii
//la baza de date
public class ConnectionControler {
    private Connection connection;


    private static final class SingletonHolder{
        private static final ConnectionControler instance = new ConnectionControler();
    }

    private ConnectionControler(){
        String url = "jdbc:mysql://localhost:3306/java2c10";
        try {
            connection = DriverManager.getConnection(url, "root", "");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static final ConnectionControler getInstance(){
        return SingletonHolder.instance;
    }

    public Connection getConnection(){
        return this.connection;
    }
}
