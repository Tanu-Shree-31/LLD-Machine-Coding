package Service.Impl;

import Entity.User;
import Exceptions.UserNotFoundException;
import Service.CommentService;
import Service.UserService;

public class CommentServiceImpl implements CommentService {
    private final UserService userService;

    public CommentServiceImpl(UserService userService) {
        this.userService = userService;
    }
    @Override
    public void commentOnPost(int userId, int postId, String commentText) {
        User user = userService.getUser(userId);
        if (user != null) {
            user.commentOnPost(postId, commentText);
            System.out.println("User " + userId + " commented on post " + postId + ": " + commentText);
        } else {
            throw new UserNotFoundException(userId);
        }
    }
}
