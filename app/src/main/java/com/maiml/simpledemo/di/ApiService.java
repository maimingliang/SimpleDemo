package com.maiml.simpledemo.di;


import com.maiml.common.http.HttpResult;
import com.maiml.simpledemo.GankIoBean;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * @author maimingliang
 */
public interface ApiService {

    @GET("random/data/{category}/{num}")
    Observable<HttpResult<List<GankIoBean>>> findList(@Path("category") String category, @Path("num") int num);

}
