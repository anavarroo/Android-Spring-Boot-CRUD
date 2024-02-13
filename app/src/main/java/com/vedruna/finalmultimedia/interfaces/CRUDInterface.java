package com.vedruna.finalmultimedia.interfaces;

import com.vedruna.finalmultimedia.model.Product;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface CRUDInterface {


    @GET("api/products")
    Call<List<Product>> getAll();

    @POST("api/products")
    Call<Void> createProduct(@Body Product newProduct);

    @DELETE("api/products/{id}")
    Call<Void> deleteProduct(@Path("id") Long Id);

    @POST("api/updateProduct")
    Call<Void> updateProduct(@Body Product product);

}