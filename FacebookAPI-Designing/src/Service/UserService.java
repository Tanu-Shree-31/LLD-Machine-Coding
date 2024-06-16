package Service;

import Entity.User;

import java.util.Map;

public interface UserService {
    void follow(int userId, int followeeId);
    void unfollow(int userId, int followeeId);
    User getUser(int userId);
    Map<Integer, User> getAllUsers();

}
