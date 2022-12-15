package com.x64tech.mordenelection.models;

import java.io.Serializable;
import java.util.List;

public class PostModel implements Serializable {

    String postID;
    String caption;
    String postImage;
    List<Comment> comments;
    String createdDate;

    public PostModel(String postID, String caption,
                     String postImage, List<Comment> comments,
                     String createdDate) {
        this.postID = postID;
        this.caption = caption;
        this.postImage = postImage;
        this.comments = comments;
        this.createdDate = createdDate;
    }

    public String getPostID() {
        return postID;
    }

    public void setPostID(String postID) {
        this.postID = postID;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public String getPostImage() {
        return postImage;
    }

    public void setPostImage(String postImage) {
        this.postImage = postImage;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public static class Comment implements Serializable {
        String postID;
        String commentID;
        String commenterID;
        String commenterName;
        String Comment;
        String createdDate;

        public Comment(String postID, String commentID, String commenterID,
                       String commenterName, String comment, String createdDate) {
            this.postID = postID;
            this.commentID = commentID;
            this.commenterID = commenterID;
            this.commenterName = commenterName;
            Comment = comment;
            this.createdDate = createdDate;
        }

        public String getPostID() {
            return postID;
        }

        public void setPostID(String postID) {
            this.postID = postID;
        }

        public String getCommentID() {
            return commentID;
        }

        public void setCommentID(String commentID) {
            this.commentID = commentID;
        }

        public String getCommenterID() {
            return commenterID;
        }

        public void setCommenterID(String commenterID) {
            this.commenterID = commenterID;
        }

        public String getCommenterName() {
            return commenterName;
        }

        public void setCommenterName(String commenterName) {
            this.commenterName = commenterName;
        }

        public String getComment() {
            return Comment;
        }

        public void setComment(String comment) {
            Comment = comment;
        }

        public String getCreatedDate() {
            return createdDate;
        }

        public void setCreatedDate(String createdDate) {
            this.createdDate = createdDate;
        }
    }
}
