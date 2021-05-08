package controler;

import dao.UserDao;
import model.User;

import javax.swing.*;
import java.util.List;
import java.util.Optional;


//Logica principala a programului va sta intr-o clasa de tip controler

public class UserControler {
    //Pe baza unei UserDao voi face operatii pe baza de date.
    //Operatiile din controller pot fi operatii mai complexe.
    private UserDao userDao;

    //Vom avea mereu un singur controller in aplicatie, deci clasele vor fi de tip singleton
    private static final class SingletonHolder{
        private static final UserControler instance = new UserControler();
    }

    private UserControler(){
        userDao = new UserDao(ConnectionControler.getInstance().getConnection());
    }

    public static final UserControler getInstance(){
        return SingletonHolder.instance;
    }


    //Cu ajutorul lui UserDao creez metoda de adaugrae in baza de date
    //Aici putem veni cu mai multe particularitati ale metodei. De exemplu sa nu adaugam in baza de date
    //doi useri cu acelasi nume, sau daca vrem ca username-ul sa respecte anumite cerinte (ex sa contina 2 cifre)

    public boolean addUser(User user){
        Optional<User> op = findUserByName(user.getNume());
        if(op.isEmpty()) {
            return userDao.addUser(user);
        }
        return false;
    }

    public List<User> getAllUsers(){
        return userDao.getAllUsers();
    }

    public Optional<User> findUserByName(String name){
        return userDao.findUserByName(name);
    }

    public boolean deleteUser(int id){
        return userDao.deleteUser(id);
    }
}
