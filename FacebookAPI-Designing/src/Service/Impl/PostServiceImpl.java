package Service.Impl;

import Entity.Post;
import Entity.User;
import Exceptions.PostsNotFoundException;
import Exceptions.UserNotFoundException;
import Service.AuthService;
import Service.PostService;
import Service.UserService;


public class PostServiceImpl implements PostService {
    private final UserService userService;
    private final AuthService authService;

    public PostServiceImpl(UserService userService, AuthService authService) {
        this.userService = userService;
        this.authService = authService;
    }

    @Override
    public void createPost(int userId, int postId) {
        if (!authService.authorize(userId, "createPost")) {
            throw new SecurityException("User not authorized to create post");
        }
        User user = userService.getUser(userId);
        if (user == null) {
            throw new UserNotFoundException(userId);
        }
        user.createPost(postId);
        System.out.println("Created new postId: " + postId + " by userId: " + userId);
    }

    @Override
    public void deletePost(int userId, int postId) {
        if (!authService.authorize(userId, "deletePost")) {
            throw new SecurityException("User not authorized to delete post");
        }
        User user = userService.getUser(userId);
        if (user != null) {
            user.deletePost(postId);
        }
    }

    @Override
    public void getPostsByUser(int userId) {
        if (!authService.authorize(userId, "getPostsByUser")) {
            throw new SecurityException("User not authorized to get posts");
        }
        User user = userService.getUser(userId);
        if (user == null || user.getPostMap().isEmpty()) {
            throw new UserNotFoundException(userId);
        } else if (user.getPostMap().isEmpty()) {
            throw new PostsNotFoundException(userId);
        } else {
            System.out.println("Posts for userId:"+userId+" are ");
            for (Post i : user.getPostMap().values()) {
                System.out.println(i.getId());
            }
        }
    }
}
