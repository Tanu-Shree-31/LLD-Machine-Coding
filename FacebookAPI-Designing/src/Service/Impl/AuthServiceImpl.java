package Service.Impl;

import Entity.User;
import Service.AuthService;
import Service.UserService;

import java.util.HashMap;
import java.util.Map;

public class AuthServiceImpl implements AuthService {
    private final UserService userService;
    private final Map<Integer, String> userPasswords = new HashMap<>();

    public AuthServiceImpl(UserService userService) {
        this.userService = userService;
    }

    // For simplicity, assume passwords are set when users are created
    public void setPassword(int userId, String password) {
        userPasswords.put(userId, password);
    }

    @Override
    public boolean authenticate(int userId, String password) {
        User user = userService.getUser(userId);
        return user != null && userPasswords.get(userId).equals(password);
    }

    @Override
    public boolean authorize(int userId, String action) {
        // For simplicity, authorize all actions
        // In a real application, you would have more complex logic here
        return userService.getUser(userId) != null;
    }
}
