package be;

public class User {

    private String Username;
    private String Password;
    private String Role;

    public User(String Username, String Password, String Role) {
        this.Username = Username;
        this.Password = Password;
        this.Role = Role;
    }

    public String getUsername() {
        return Username;
    }

    public String getPassword() {
        return Password;
    }

    public String getRole() {
        return Role;
    }

    //I am not gonna create setter for id bc I don't think we shouldn't change id

    public void setUsername(String Username) {
        this.Username = Username;
    }
    public void setPassword(String Password) {
        this.Password = Password;
    }

    public void setRole(String Role) {
        this.Role = Role;
    }

    @Override
    public String toString() {
        return
                " Username='" + Username + '\'' +
                ", Password='" + Password + '\'' +
                ", Role='" + Role + '\''
                ;
    }

}
