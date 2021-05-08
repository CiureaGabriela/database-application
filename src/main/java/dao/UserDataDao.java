package dao;

import model.UserData;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDataDao {
    private Connection con;
    private PreparedStatement addUserData;
    private PreparedStatement getAllDataForUSer;


    public UserDataDao(Connection con){
        this.con = con;

        try {
            addUserData = con.prepareStatement("INSERT INTO userdata VALUES (null,?, ?)");

            getAllDataForUSer = con.prepareStatement("SELECT * FROM userdata WHERE userID = ?");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean addData(UserData userData){
        try {
            addUserData.setString(1,userData.getTelefon());
            addUserData.setInt(2, userData.getUserID());
            return addUserData.executeUpdate() != 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public List<UserData> getAllUserData(int userID){
        ArrayList<UserData> lista = new ArrayList<UserData>();
        try {
            getAllDataForUSer.setInt(1, userID);
            ResultSet rs = getAllDataForUSer.executeQuery();
            while(rs.next()){
                UserData ud = new UserData(rs.getInt("id"),
                                            rs.getString("telefon"),
                                            rs.getInt("userID"));
                lista.add(ud);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }
}
