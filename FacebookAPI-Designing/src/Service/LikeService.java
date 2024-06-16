package Service;

public interface LikeService {
    void likePost(int userId, int postId);
    void unlikePost(int userId, int postId);
}
