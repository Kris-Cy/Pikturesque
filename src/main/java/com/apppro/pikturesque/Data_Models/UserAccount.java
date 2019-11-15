package com.apppro.pikturesque.Data_Models;

public class UserAccount {
    private String bio;
    private String display_name;
    private long friends;
    private long posts;
    private String profile_photo;
    private String username;

    public UserAccount(String bio, String display_name, long friends, long posts, String profile_photo, String username) {
        this.bio = bio;
        this.display_name = display_name;
        this.friends = friends;
        this.posts = posts;
        this.profile_photo = profile_photo;
        this.username = username;
    }

    public UserAccount() {
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getDisplay_name() {
        return display_name;
    }

    public void setDisplay_name(String display_name) {
        this.display_name = display_name;
    }

    public long getFriends() {
        return friends;
    }

    public void setFriends(long friends) {
        this.friends = friends;
    }

    public long getPosts() {
        return posts;
    }

    public void setPosts(long posts) {
        this.posts = posts;
    }

    public String getProfile_photo() {
        return profile_photo;
    }

    public void setProfile_photo(String profile_photo) {
        this.profile_photo = profile_photo;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "UserAccount{" +
                "bio='" + bio + '\'' +
                ", display_name='" + display_name + '\'' +
                ", friends=" + friends +
                ", posts=" + posts +
                ", profile_photo='" + profile_photo + '\'' +
                ", username='" + username + '\'' +
                '}';
    }
}
