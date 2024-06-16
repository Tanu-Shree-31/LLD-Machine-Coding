package Service;

public interface NewsFeedService {
    void getNewsFeed(int userId);
    void getNewsFeedPaginated(int userId, int pageNumber);
}
