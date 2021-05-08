package gui;

import controler.UserDataControler;
import model.UserData;

import javax.swing.*;
import java.util.List;

public class UserDataFrame extends JFrame{
    private JList list1;
    private JPanel mainPanel;
    private JTextField textField1;
    private JButton addButton;

    private int userID;

    private DefaultListModel<UserData> model ;

    public UserDataFrame(int userID){
            this.userID = userID;
            add(mainPanel);

            model = new DefaultListModel<UserData>();
            list1.setModel(model);

            addButton.addActionListener(ev -> addUserData());
            afisareUserData();
            add(mainPanel);
            pack();
            setVisible(true);
            setLocationRelativeTo(null);
        }

        public void afisareUserData(){
            List<UserData> lista = UserDataControler.getInstance().getAllUserData(userID);
            model.clear();
            lista.forEach(e-> model.addElement(e));
        }

        public void addUserData(){
            String telefon = textField1.getText();

            //adaug datele pentru utilizatorul selectat
            UserData ud = new UserData(1, telefon, userID);
            UserDataControler.getInstance().addUserData(ud);
            model.clear();
            afisareUserData();
        }
}

