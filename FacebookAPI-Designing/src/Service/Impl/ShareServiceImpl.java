package Service.Impl;

import Entity.User;
import Exceptions.UserNotFoundException;
import Service.ShareService;
import Service.UserService;

public class ShareServiceImpl implements ShareService {
    private final UserService userService;

    public ShareServiceImpl(UserService userService) {
        this.userService = userService;
    }
    @Override
    public void sharePost(int userId, int postId) {
        User user = userService.getUser(userId);
        if (user != null) {
            user.sharePost(postId);
            System.out.println("User " + userId + " shared post " + postId);
        } else {
            throw new UserNotFoundException(userId);
        }
    }
}
