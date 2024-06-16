package Service.Impl;

import Entity.User;
import Exceptions.UserNotFoundException;
import Service.LikeService;
import Service.UserService;

public class LikeServiceImpl implements LikeService {
    private final UserService userService;

    public LikeServiceImpl(UserService userService) {
        this.userService = userService;
    }
    @Override
    public void likePost(int userId, int postId) {
        User user = userService.getUser(userId);
        if (user != null) {
            user.likePost(postId);
            System.out.println("User " + userId + " liked post " + postId);
        } else {
            throw new UserNotFoundException(userId);
        }
    }

    @Override
    public void unlikePost(int userId, int postId) {
        User user = userService.getUser(userId);
        if (user != null) {
            user.unlikePost(postId);
            System.out.println("User " + userId + " unliked post " + postId);
        } else {
            throw new UserNotFoundException(userId);
        }
    }
}
