package controler;

import dao.UserDataDao;
import model.UserData;

import java.util.List;

public class UserDataControler {
    private UserDataDao userDao;

    private static final class SingletonHolder{
        private static final UserDataControler instance = new UserDataControler();
    }

    private UserDataControler(){
        userDao = new UserDataDao(ConnectionControler.getInstance().getConnection());
    }

    public static UserDataControler getInstance(){
        return SingletonHolder.instance;
    }

    public boolean addUserData(UserData userData){
        return userDao.addData(userData);
    }

    public List<UserData> getAllUserData(int userID){
        return userDao.getAllUserData(userID);
    }
}
