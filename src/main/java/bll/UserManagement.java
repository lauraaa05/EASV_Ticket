package BLL;

import be.User;

public class UserManagement {

    public User createAdmin(String Username, String Password) {
        return new User(Username, Password, "Admin");
    }

    public User createEventCoordinator(String Username, String Password) {
        return new User(Username, Password, "Coordinator");
    }
}
