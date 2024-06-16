package Entity;

import Service.TimestampService;

public class Post {
    private Integer id;
    private Integer time;
    private Post prev;
    private Post next;

    public Post(Integer id) {
        this.id = id;
        this.time = TimestampService.getTimestamp();
    }

    public Integer getId() {
        return id;
    }

    public Integer getTime() {
        return time;
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