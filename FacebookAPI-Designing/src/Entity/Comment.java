package Entity;

import Service.TimestampService;

public class Comment {
    private Integer userId;
    private String text;
    private Integer timestamp;

    public Comment(Integer userId, String text) {
        this.userId = userId;
        this.text = text;
        this.timestamp = TimestampService.getTimestamp();
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Integer getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Integer timestamp) {
        this.timestamp = timestamp;
    }
}
