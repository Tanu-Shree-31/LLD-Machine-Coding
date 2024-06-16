package Service.Impl;

import Entity.Post;
import Entity.User;
import Exceptions.PostsNotFoundException;
import Exceptions.UserNotFoundException;
import Service.PostService;
import Service.UserService;


public class PostServiceImpl implements PostService {
    private final UserService userService;

    public PostServiceImpl(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void createPost(int userId, int postId) {
        User user = userService.getUser(userId);
        if (user == null) {
            throw new UserNotFoundException(userId);
        }
        user.createPost(postId);
        System.out.println("Created new postId: " + postId + " by userId: " + userId);
    }

    @Override
    public void deletePost(int userId, int postId) {
        User user = userService.getUser(userId);
        if (user != null) {
            user.deletePost(postId);
        }
    }

    @Override
    public void getPostsByUser(int userId) {
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
