package project.model.commentfood;

import project.model.User;

import java.io.Serializable;

public class CommentFood implements Serializable {
    private int commentId;
    private User user;
   private String comment;

    public CommentFood() {
    }

    public CommentFood(int commentId, User user, String comment) {
        this.commentId = commentId;
        this.user = user;
        this.comment = comment;
    }

    public int getCommentId() {
        return commentId;
    }

    public void setCommentId(int commentId) {
        this.commentId = commentId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
