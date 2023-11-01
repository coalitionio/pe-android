package com.phonglt.pe.service;

import com.phonglt.pe.dto.X;
import com.phonglt.pe.dto.Y;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface YService {
    String URL = "major";
    @GET(URL)
    Call<Y[]> getAll();
    @GET(URL + "/{id}")
    Call<Y> getById (@Path("id") String id);
    @POST(URL)
    Call<Y> create(@Body Y y);
    @DELETE(URL + "/{id}")
    Call<Y> delete(@Path("id") String id);
    @PUT(URL + "/{id}")
    Call<Y> update(@Path("id") String id, @Body Y y);
}

