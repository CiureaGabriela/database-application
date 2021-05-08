package dao;

import model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

//Facem un obiect specializat care se ocupa cu executarea interogarilor in baza de date.
//DAO - DATA ACCESS OBJECT
public class UserDao {
    //Pentru excutarea interogarilor avem nevoie de o conexiune la baza de date
    //DAO nu se ocupa cu crearea si managementul conexiunii la baza de date, deci va primi din exterior aceasta conexiune
    private Connection con;

    //Ne definim statement-urile (interogarile) - ce operatii vrem pe baza de date
    //Operatii de adaugare, stergere, preluare date, etc -> operatii simple cu baza de date
    private PreparedStatement addUser;
    private PreparedStatement findAllUsers;
    private PreparedStatement findUserByName;
    private PreparedStatement deleteUser;

    public UserDao(Connection con){
        this.con = con;

        try {
            addUser = con.prepareStatement("INSERT INTO users VALUES (null, ?, ?)");

            findAllUsers = con.prepareStatement("SELECT * FROM users");

            findUserByName = con.prepareStatement("SELECT * FROM users WHERE nume = ?");

            deleteUser = con.prepareStatement("DELETE FROM users WHERE id = ?");
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    //Cream metodele care executa statement-urile. Fiecare metoda va avea o singura operatie
    //pe baza de date (adauga useri, sterge sau preia useri)

    public boolean addUser(User user){
        try {
            addUser.setString(1,user.getNume());
            addUser.setString(2,user.getParola());
            return addUser.executeUpdate() != 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    //Pentru a prelua datele din baza de date voi retuna o lista
    //a tuturor utilizatorilor
    public List<User> getAllUsers(){
        ArrayList<User> list = new ArrayList<User>();

        try {
            //interogarea de preluare a datelor imi returneaza un ResultSet
            //pe care il folosesc pentru a parcurge toate inregistrarile din baza de date
            ResultSet rs = findAllUsers.executeQuery();
            while(rs.next()){
                User u = new User(rs.getInt("id"),
                                rs.getString("nume"),
                                rs.getString("parola"));

                list.add(u);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }


    //Pentru returnarea unui utilizator voi folosi un Optional
    //pentru situatiile in care este posibil sa returneze null
    public Optional<User> findUserByName(String nume){
        try {
            findUserByName.setString(1, nume);
            ResultSet rs = findUserByName.executeQuery();
            if(rs.next()){
                return Optional.of(new User(rs.getInt("id"),
                                            rs.getString("nume"),
                                            rs.getString("parola")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    public boolean deleteUser(int id){
        try {
            deleteUser.setInt(1, id);
            return deleteUser.executeUpdate() != 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }







}
