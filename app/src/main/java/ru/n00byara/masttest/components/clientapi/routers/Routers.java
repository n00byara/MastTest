package ru.n00byara.masttest.components.clientapi.routers;

import retrofit2.Call;
import retrofit2.http.GET;
import ru.n00byara.masttest.components.clientapi.pojo.Quotes;
import ru.n00byara.masttest.components.clientapi.pojo.Products;

public interface Routers {
    @GET("quotes")
    Call<Quotes> getQuotes();

    @GET("products")
    Call<Products> getProducts();
}
