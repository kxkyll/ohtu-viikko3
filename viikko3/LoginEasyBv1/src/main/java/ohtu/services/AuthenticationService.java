package ohtu.services;
import ohtu.data_access.UserDao;
import ohtu.domain.User;

//@Component
public class AuthenticationService {

    private UserDao userDao;
    
    //@Autowired kommentoitu FileUserDaon käyttöönoton vuoksi
    public AuthenticationService(UserDao userDao) {
        this.userDao = userDao;
    }

    public boolean logIn(String username, String password) {
        for (User user : userDao.listAll()) {
            if (user.getUsername().equals(username)
                    && user.getPassword().equals(password)) {
                return true;
            }
        }
        return false;
    }

    public boolean createUser(String username, String password) {
        if (userDao.findByName(username) != null) {
            return false;
        }

        if (invalid(username, password)) {
            return false;
        }

        userDao.add(new User(username, password));

        return true;
    }

    private boolean invalid(String username, String password) {

        Boolean usernameInvalid = true;
        Boolean passwordInvalid = true;

        if (username.length() < 3) {
            return true;
        }
        if (password.length() < 8) {
            return true;
        }

        if (username.matches("[a-zA-Z]*")) {
            usernameInvalid = false;
        }

        if (password.matches(".*\\d.*")
                || password.matches(".*[!”#$%&’()*+,./;:=?_@>-].*")) {
            if (password.matches(".*[a-z].*")
                    || password.matches(".*[A-Z].*")) {
                passwordInvalid = false;
            }
        }
        if (!usernameInvalid && !passwordInvalid) {
            return false;
        }
        return true;
    }
}
