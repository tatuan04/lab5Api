package com.tuan04.lab5api.retrofit1;

import retrofit2.Call;
import retrofit2.http.GET;

public interface InterSelect {
    @GET("getAll_product.php")
    Call<SvResponseSelect> selectPrd();
}
