package Service;

public interface CommentService {
    void commentOnPost(int userId, int postId, String commentText);
}
