package com.giljulio.adorables.net;

import com.giljulio.adorables.net.model.Comment;
import com.giljulio.adorables.net.model.Post;
import com.giljulio.adorables.net.model.User;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

public interface JsonPlaceholderService {

    String BASE_URL = "http://jsonplaceholder.typicode.com";

    @GET("/users")
    Observable<List<User>> getUsers();

    @GET("/posts")
    Observable<List<Post>> getPosts(@Query("userId") int userId);

    @GET("/comments")
    Observable<List<Comment>> getComments(@Query("postId") int postId);

}
