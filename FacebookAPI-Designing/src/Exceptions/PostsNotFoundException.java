package Exceptions;

public class PostsNotFoundException extends RuntimeException {
    public PostsNotFoundException(int userId) {
        super("No posts found for user with id " + userId);
    }
}
