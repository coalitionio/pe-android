package com.phonglt.pe.service;

import com.phonglt.pe.dto.X;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface XService {
    String URL = "student";
    @GET(URL)
    Call<X[]> getAll();
    @GET(URL + "/{id}")
    Call<X> getById (@Path("id") String id);
    @POST(URL)
    Call<X> create(@Body X x);
    @PUT(URL + "/{id}")
    Call<X> update(@Path("id") String id, @Body X x);
    @DELETE(URL + "/{id}")
    Call<X> delete(@Path("id") String id);
}
