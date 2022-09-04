package com.cibertec.semana02f.service;

import com.cibertec.semana02f.entity.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface UserService {

    @GET("users")
    public abstract Call<List<User>> listaUsuarios();


}
