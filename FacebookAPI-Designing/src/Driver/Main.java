package Driver;

import Service.*;
import Service.Impl.*;

/*
1. create the new user
1. follow and unfollow the user
3. create new post and delete the post
4. fetch the news feed for a userId by newer posts at top.
5. fetches the news feed by page number.
6. like, share and comment.
 */

public class Main {
    public static void main(String[] args) {
        UserService userService = new UserServiceImpl();
        AuthService authService = new AuthServiceImpl(userService);
        PostService postService = new PostServiceImpl(userService,authService);
        NewsFeedService newsFeedService = new NewsFeedServiceImpl(userService);
        LikeService likeService = new LikeServiceImpl(userService);
        CommentService commentService = new CommentServiceImpl(userService);
        ShareService shareService = new ShareServiceImpl(userService);

        // Set passwords for users
        authService.setPassword(1, "test123");
        authService.setPassword(2, "sample45");
        authService.setPassword(3, "ABC@789");

        // Follow actions
        userService.follow(1, 2);
        userService.follow(1, 3);
        userService.follow(1, 4);
        userService.follow(1, 5);
        userService.follow(1, 6);
        userService.follow(1, 7);
        userService.follow(1, 8);
        userService.follow(1, 9);
        userService.follow(1, 10);
        userService.follow(1, 11);
        userService.follow(1, 12);
        userService.follow(1, 13);
        System.out.println("---------------------");


        // Create posts
        postService.createPost(1, 1000);
        postService.createPost(2, 1002);
        postService.createPost(3, 1003);
        postService.createPost(4, 1004);
        postService.createPost(5, 1005);
        postService.createPost(6, 1006);
        postService.createPost(7, 1007);
        postService.createPost(8, 1008);
        postService.createPost(9, 1009);
        postService.createPost(10, 1010);
        postService.createPost(11, 1011);
        postService.createPost(12, 1012);
        postService.createPost(13, 1013);
        // user not found exception
        //postService.createPost(16,4520);
        System.out.println("---------------------");


        // get posts for a user
        postService.getPostsByUser(3);
        System.out.println("---------------------");


        // Like a post
        likeService.likePost(2, 1002);
        System.out.println("---------------------");


        // Comment on posts
        commentService.commentOnPost(1, 1000, "Nice post!");
        commentService.commentOnPost(2, 1002, "Great post!");
        commentService.commentOnPost(3, 1003, "Interesting post!");
        System.out.println("---------------------");


        // Share posts
        shareService.sharePost(1, 1000);
        shareService.sharePost(2, 1002);
        shareService.sharePost(3, 1003);
        System.out.println("---------------------");


        // Get news feed
        newsFeedService.getNewsFeed(1);
        userService.unfollow(1, 13);
        newsFeedService.getNewsFeed(1);
        postService.deletePost(12, 1012);
        newsFeedService.getNewsFeed(1);
        System.out.println("---------------------");

        // Get paginated news feed
        newsFeedService.getNewsFeedPaginated(1, 2);
        newsFeedService.getNewsFeedPaginated(1, 5);
    }
}
