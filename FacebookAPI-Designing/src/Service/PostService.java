package Service;

import Entity.Post;

import java.util.List;

public interface PostService {
    void createPost(int userId, int postId);
    void deletePost(int userId, int postId);
    void getPostsByUser(int userId);
}
