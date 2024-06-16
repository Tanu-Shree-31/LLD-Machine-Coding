package Service.Impl;

import Entity.User;
import Service.UserService;

import java.util.HashMap;
import java.util.Map;

public class UserServiceImpl implements UserService {
    private static Map<Integer, User> userMap = new HashMap<>();
    @Override
    public void follow(int userId, int followeeId) {
        User follower = getOrCreateUser(userId);
        User followee = getOrCreateUser(followeeId);
        follower.follow(followeeId);
        System.out.println(userId + " is following " + followeeId);
    }

    @Override
    public void unfollow(int userId, int followeeId) {
        User follower = userMap.get(userId);
        if (follower != null) {
            follower.unfollow(followeeId);
        }
    }

    @Override
    public User getUser(int userId) {
        return userMap.get(userId);
    }

    public User createUser(int userId) {
        return getOrCreateUser(userId);
    }

    private User getOrCreateUser(int userId) {
        return userMap.computeIfAbsent(userId, User::new);
    }

    @Override
    public Map<Integer, User> getAllUsers() {
        return userMap;
    }

}
