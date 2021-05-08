package gui;

import controler.UserControler;
import model.User;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

//O clasa fereastra in aplicatie trebuie sa mosteneasca clasa JFrame
public class UserFrame extends JFrame {

    //Definim elementele ferestrei. Acestea sunt preluate din fisierul UserFrame.form
    private JList list1;
    private JPanel mainPanel;
    private JTextField textField1;
    private JPasswordField passwordField1;
    private JButton addButton;

    //Definesc un model pentru lista
    private DefaultListModel<User> model;
    public UserFrame(){
        //adaug mainPanel ca element al ferestrei. Pe mainPanel am puse toate celelalte elemente ale ferestrei
        add(mainPanel);

        //setam modelul pentru lista
        model = new DefaultListModel<User>();
        list1.setModel(model);

        //Adauc o actiune pentru buron
        addButton.addActionListener(ev -> addUser());

        //adaugam pe lista ferestrei un eveniment pentru click
        list1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                //metoda ce se va executa atunci cand se actioneaza asupra unui element al listei
                onMouseClick(e);
            }
        });

        //afisez userii din BD cand deschid fereastra pentru a vedea inca de la inceput
        //toate inregistrarile din baza de date
        afisareUseri();
        pack(); //-> dimensionarea automata a ferestrei
        setVisible(true); //-> in mod automat setVisible este flase. Daca nu specific true nu o sa vad fereastra
        setLocationRelativeTo(null); //va deschide fereastra in mijlocul ecranului
    }

    //Creez o metoda pentru adaugarea utilizatorului in baza de date

    public void addUser(){
        //Pentru crearea unui utilizator voi lua valorile pentru nume si parola din casutele ferestrei
        String nume = textField1.getText();
        String parola = String.valueOf(passwordField1.getPassword());

        //Creez instanta de utilizator. ID-ul va fi mai departe schimbat in baza de date prin autoincrementare
        User u = new User(0, nume, parola);

        boolean rez = UserControler.getInstance().addUser(u);
        if(rez){
            //Daca pot adauga o persoana in BD (numele utilzatorului este unic)
            //afisez useri din baza de date.
            model.clear();
            afisareUseri();
        }else{
            JOptionPane.showMessageDialog(null,"Utilizatorul deja exista");
        }


    }


    public void afisareUseri(){
        List<User> lista = UserControler.getInstance().getAllUsers();
        model.clear();
        lista.forEach(e-> model.addElement(e));
    }

    private void onMouseClick(MouseEvent ev){
        boolean isItemSelected =  list1.getSelectedValue() != null; //-> returneaza adevarat sau fals daca se selecteaza un element sau nu

        if(isItemSelected &&
                //verifica daca butonul de la mouse este click stanga
                ev.getButton() == MouseEvent.BUTTON1 &&
                //verifica daca am apasat butonul o singura data
                ev.getClickCount() == 1) {
            //preiau userul
            User u = (User) list1.getSelectedValue();

            //ceez o fereastra de tip UserDataFrame folosind is-ul utilizatorului selectat pentru ca vreau ca din baza de date, tabelul userData
            //sa preia doar datele utilizatorului selectat.
            new UserDataFrame(u.getId());
        }

    }

}
