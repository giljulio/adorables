package com.giljulio.adorables.net;

import com.giljulio.adorables.model.Adorable;

import java.util.List;

import retrofit2.http.GET;
import rx.Observable;

public interface FakeApiService {

    String BASE_URL = "http;//jsonplaceholder.typicode.com";

    @GET("/users")
    Observable<List<Adorable>> getAdorables();

}
