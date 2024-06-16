package Service.Impl;

import Entity.Post;
import Entity.User;
import Exceptions.UserNotFoundException;
import Service.NewsFeedService;
import Service.UserService;

import java.util.*;

public class NewsFeedServiceImpl implements NewsFeedService {
    private final UserService userService;

    public NewsFeedServiceImpl(UserService userService) {
        this.userService = userService;
    }
    private static final int PAGE_SIZE = 2;
    private static final int FEED_SIZE = 10;
    @Override
    public void getNewsFeed(int userId) {
        List<Integer> feed = fetchTopNPosts(userId, FEED_SIZE);
        System.out.println("Feed for user: "+userId);
        for(int i=0; i<feed.size(); i++){
            System.out.println("Post: "+(i+1)+" "+feed.get(i));
        }
    }

    @Override
    public void getNewsFeedPaginated(int userId, int pageNumber) {
        User user=userService.getUser(userId);
        if(user!=null) {
            List<Integer> feed = fetchTopNPosts(userId, Integer.MAX_VALUE);
            Integer start = pageNumber * PAGE_SIZE;
            Integer end = Math.min(start + PAGE_SIZE, feed.size());
            if (start > end) {
                return;
            }
            List<Integer> paginatedFeed = feed.subList(start, end);
            System.out.println("Page number " + pageNumber + " of user " + userId + " feed");
            for (int i = 0; i < paginatedFeed.size(); i++) {
                System.out.println("Post " + (i + 1) + " " + paginatedFeed.get(i));
            }
        } else {
            throw new UserNotFoundException(userId);
        }
    }

    private List<Integer> fetchTopNPosts(int userId, int feedSize) {
        User user = userService.getUser(userId);
        if (user != null) {
            List<Integer> posts = new LinkedList<>();
            Set<Integer> followed = user.getFollowed();
            PriorityQueue<Post> pq = new PriorityQueue<>((a, b) -> b.getTime() - a.getTime());

            for (Integer currUserId : followed) {
                User currUser = userService.getUser(currUserId);
                Post head = currUser.getHead();
                Post tail = currUser.getTail();
                if (head.getNext() != tail) {
                    pq.add(head.getNext());
                }
            }

            int n = 0;
            while (!pq.isEmpty() && n < feedSize) {
                Post curr = pq.poll();
                n++;
                posts.add(curr.getId());
                if (curr.getNext().getId() != -1) {
                    pq.add(curr.getNext());
                }
            }
            return posts;
        } else {
            throw new UserNotFoundException(userId);
        }
    }
}
