package Service;

import Entity.Post;
import Entity.User;
import Exceptions.UserNotFoundException;

import java.util.*;

public class FacebookService {
    public static Map<Integer, User> userMap = new HashMap<>();
    public static Integer PAGE_SIZE = 2;
    public static Integer FEED_SIZE = 10;

    /*
    1. create the new user
    1. follow and unfollow the user
    3. create new post and delete the post
    4. fetch the news feed for a userId by newer posts at top.
    5. fetches the news feed by page number.
    6. like, share and comment.
     */

    public void follow(int userId, int followeeId) {
        User follower = getOrCreateUser(userId);
        User followee = getOrCreateUser(followeeId);
        follower.follow(followeeId);
        System.out.println(userId+" is following "+followeeId);
    }

    public void unfollow(int userId, int followeeId){
        User follower = userMap.get(userId);
        if(follower!=null){
            follower.unfollow(followeeId);
        }
    }

    /*
    1. check if userId is present in the userMap, if not create new user and put in userMap
    2. call createPost method with postId.
     */
    public void  createPost(int userId, int postId) {
        User user = getOrCreateUser(userId);
        user.createPost(postId);
        System.out.println("Created new postId: "+postId+" by userId: "+userId);
    }

    public void deletePost(int userId, int postId){
        User user = userMap.get(userId);
        if (user != null) {
            user.deletePost(postId);
        }
    }

    public void getNewsFeed(int userId) {
       List<Integer> feed = fetchTopNPosts(userId, FEED_SIZE);
        System.out.println("Feed for user: "+userId);
        for(int i=0; i<feed.size(); i++){
            System.out.println("Post: "+(i+1)+" "+feed.get(i));
        }
    }

    private List<Integer> fetchTopNPosts(int userId, Integer feedSize) {
        User user=userMap.get(userId);
        if(user==null){
            return new LinkedList<>();
        }
        int n=0;
        List<Integer> posts = new LinkedList<>();
        Set<Integer> followed= user.getFollowed();
        PriorityQueue<Post> pq = new PriorityQueue<>((a,b)->(b.getTime()-a.getTime()));
        for(Integer currUserId: followed){ //iterate over followed users
            User currUser = userMap.get(currUserId);
            Post head = currUser.getHead();
            Post tail = currUser.getTail();
            if(head.getNext()!=tail){
                pq.add(head.getNext());
            }
        }
        while (!pq.isEmpty()&& n<feedSize){
            Post curr = pq.poll();
            n++;
            posts.add(curr.getId());
            //If the next post after the polled post exists, add it back to the priority queue for further consideration
            if(curr.getNext().getId()!=-1){
                pq.add(curr.getNext());
            }
        }
        return posts;
    }

    public void getNewsFeedPaginated(int userId, int pageNumber) {
        User user=userMap.get(userId);
        if(user==null){
            return;
        }
        List<Integer> feed=fetchTopNPosts(userId,Integer.MAX_VALUE);
        Integer start = pageNumber*PAGE_SIZE;
        Integer end = Math.min(start+PAGE_SIZE, feed.size());
        if(start>end){
            return;
        }
        List<Integer> paginatedFeed = feed.subList(start,end);
        System.out.println("Page number "+pageNumber+" of user "+userId+" feed");
        for(int i=0; i<paginatedFeed.size(); i++){
            System.out.println("Post "+(i+1)+" "+paginatedFeed.get(i));
        }
    }

    public void likePost(int userId, int postId) {
        User user = userMap.get(userId);
        if (user != null) {
            user.likePost(postId);
            System.out.println("User " + userId + " liked post " + postId);
        } else {
            throw new UserNotFoundException(userId);
        }
    }
    public void unlikePost(int userId, int postId) {
        User user = userMap.get(userId);
        if (user != null) {
            user.unlikePost(postId);
            System.out.println("User " + userId + " unliked post " + postId);
        } else {
            throw new UserNotFoundException(userId);
        }
    }

    public void commentOnPost(int userId, int postId, String commentText) {
        User user = userMap.get(userId);
        if (user != null) {
            user.commentOnPost(postId, commentText);
            System.out.println("User " + userId + " commented on post " + postId + ": " + commentText);
        } else {
            throw new UserNotFoundException(userId);
        }
    }

    public void sharePost(int userId, int postId) {
        User user = userMap.get(userId);
        if (user != null) {
            user.sharePost(postId);
            System.out.println("User " + userId + " shared post " + postId);
        } else {
            throw new UserNotFoundException(userId);
        }
    }

    private User getOrCreateUser(int userId) {
        System.out.println("Created userId: "+userId);
        return userMap.computeIfAbsent(userId, User::new);
    }
}