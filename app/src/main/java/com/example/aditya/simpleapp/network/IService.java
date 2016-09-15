package com.example.aditya.simpleapp.network;

import com.example.aditya.simpleapp.model.Content;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by aditya on 13/9/16.
 */
interface IService {

    @GET("/maclir/f715d78b49c3b4b3b77f/raw/8854ab2fe4cbe2a5919cea97d71b714ae5a4838d/items.json")
    Call<List<Content>> getData();

}
