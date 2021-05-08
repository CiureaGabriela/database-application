package model;

public class UserData {
    private int id;
    private String telefon;
    private int userID;

    public UserData(int id, String telefon, int userID) {
        this.id = id;
        this.telefon = telefon;
        this.userID = userID;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    @Override
    public String toString() {
        return "UserData{" +
                "id=" + id +
                ", telefon='" + telefon + '\'' +
                ", userID=" + userID +
                '}';
    }
}
