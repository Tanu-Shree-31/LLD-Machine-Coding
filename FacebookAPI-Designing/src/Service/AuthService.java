package Service;

public interface AuthService {
    boolean authenticate(int userId, String password);
    boolean authorize(int userId, String action);
    void setPassword(int userId, String password);
}
