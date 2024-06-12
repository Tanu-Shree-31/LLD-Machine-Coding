import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

class Post {
//    private Set<Integer> likes;
//    private List<Comment> comments;
//    private List<Integer> shares;
    private Integer id;
    private Integer time;
    private Post prev;
    private Post next;

    public Post() {

    }

    public Post(Integer id) {
        this.id = id;
        this.time = Facebook.timestamp++;
//        this.likes = new HashSet<>();
//        this.comments = new ArrayList<>();
//        this.shares = new ArrayList<>();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTime() {
        return time;
    }

    public void setTime(Integer time) {
        this.time = time;
    }

    public Post getPrev() {
        return prev;
    }

    public void setPrev(Post prev) {
        this.prev = prev;
    }

    public Post getNext() {
        return next;
    }

    public void setNext(Post next) {
        this.next = next;
    }
}