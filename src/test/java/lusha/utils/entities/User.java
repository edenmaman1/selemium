package lusha.utils.entities;

public class User {

    public String userName;
    public String password;

    public User(String userName,String password ) {
        this.userName = userName;
        this.password = password;
    }

    public User() {
        this.userName = null;
        this.password = null;
    }
}
