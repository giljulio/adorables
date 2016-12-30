package com.giljulio.adorables.net;

import com.giljulio.adorables.net.model.User;

import java.util.List;

import retrofit2.http.GET;
import rx.Observable;

public interface JsonPlaceholderService {

    String BASE_URL = "http://jsonplaceholder.typicode.com";

    @GET("/users")
    Observable<List<User>> getUsers();

}
