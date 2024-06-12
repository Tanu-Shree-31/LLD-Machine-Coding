import java.util.*;

public class Facebook {
    public static int timestamp;
    public static Map<Integer, User> userMap;
    public static Integer PAGE_SIZE;
    public static Integer FEED_SIZE;

    public Facebook(){
        timestamp=0;
        userMap=new HashMap<>();
        PAGE_SIZE = 2;
        FEED_SIZE = 10;
    }
    /*
    1. create the new user
    1. follow and unfollow the user
    3. create new post and delete the post
    4. fetch the news feed for a userId by newer posts at top.
    5. fetches the news feed by page number.
    6. like, share and comment.
     */

    private void follow(int userId, int followeeId) {
        User follower = userMap.get(userId);
        User followee = userMap.get(followeeId);
        if(follower==null){ //if userId is null or not present
            follower=new User(userId);
            userMap.put(userId, follower);
            System.out.println("Created userId: "+userId);
        }
        if(followee==null){
            followee=new User(followeeId);
            userMap.put(followeeId,followee);
            System.out.println("Created userId: "+followeeId);
        }
        follower.follow(followeeId);
        System.out.println(userId+" is following "+followeeId);
    }

    private void unfollow(int userId, int followeeId){
        User follower = userMap.get(userId);
        if(follower!=null){
            follower.unfollow(followeeId);
        }
    }

    private void  createPost(int userId, int postId) {
        /*
        1. check if userId is present in the userMap, if not create new user and put in userMap
        2. call createPost method with postId.
         */
        User user = userMap.get(userId);
        if(user==null){
            user = new User(userId);
            userMap.put(userId,user);
            System.out.println("Created userId: "+userId);
        }
        user.createPost(postId);
        System.out.println("Created new postId: "+postId+" by userId: "+userId);
    }
    private void deletePost(int userId, int postId){
        User user = userMap.get(userId);
        if (user != null) {
            user.deletePost(postId);
        }
    }

    private void getNewsFeed(int userId) {
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

    private void getNewsFeedPaginated(int userId, int pageNumber) {
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
            System.out.println("User " + userId + " does not exist.");
        }
    }
    public void unlikePost(int userId, int postId) {
        User user = userMap.get(userId);
        if (user != null) {
            user.unlikePost(postId);
            System.out.println("User " + userId + " unliked post " + postId);
        } else {
            System.out.println("User " + userId + " does not exist.");
        }
    }

    public void commentOnPost(int userId, int postId, String commentText) {
        User user = userMap.get(userId);
        if (user != null) {
            user.commentOnPost(postId, commentText);
            System.out.println("User " + userId + " commented on post " + postId + ": " + commentText);
        } else {
            System.out.println("User " + userId + " does not exist.");
        }
    }

    public void sharePost(int userId, int postId) {
        User user = userMap.get(userId);
        if (user != null) {
            user.sharePost(postId);
            System.out.println("User " + userId + " shared post " + postId);
        } else {
            System.out.println("User " + userId + " does not exist.");
        }
    }

    public static void main(String[] args) {
        Facebook facebook = new Facebook();
        facebook.follow(1, 2);
        facebook.follow(1, 3);
        facebook.follow(1, 4);
        facebook.follow(1, 5);
        facebook.follow(1, 6);
        facebook.follow(1, 7);
        facebook.follow(1, 8);
        facebook.follow(1, 9);
        facebook.follow(1, 10);
        facebook.follow(1, 11);
        facebook.follow(1, 12);
        facebook.follow(1, 13);
//        facebook.signUp(1, "tanu", "tanu@123");
//        facebook.logIn(1, "tanu", "tanu@123");
        facebook.createPost(1, 1000);
        facebook.createPost(2, 1002);
        facebook.createPost(3, 1003);
        facebook.createPost(4, 1004);
        facebook.createPost(5, 1005);
        facebook.createPost(6, 1006);
        facebook.createPost(7, 1007);
        facebook.createPost(8, 1008);
        facebook.createPost(9, 1009);
        facebook.createPost(10, 1010);
        facebook.createPost(11, 1011);
        facebook.createPost(12, 1012);
        facebook.createPost(13, 1013);
        facebook.likePost(2,1002);
        facebook.getNewsFeed(1);
        facebook.unfollow(1, 13);
        facebook.getNewsFeed(1);
        facebook.deletePost(12, 1012);
        facebook.getNewsFeed(1);
        facebook.getNewsFeedPaginated(1, 2);
        facebook.getNewsFeedPaginated(1, 5);
    }
}