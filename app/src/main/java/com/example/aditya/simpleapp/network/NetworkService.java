package com.example.aditya.simpleapp.network;

import com.example.aditya.simpleapp.model.Content;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by aditya on 13/9/16.
 *
 * Used for accessing the network calls
 */
public class NetworkService {

    public void getListingData(final ServiceCallback<List<Content>> serviceCallback){
        IService service = RetrofitBuilder.getInstance().getRetrofit().create(IService.class);
        Call<List<Content>> listing = service.getData();
        listing.enqueue(new Callback<List<Content>>() {
            @Override
            public void onResponse(Call<List<Content>> call, Response<List<Content>> response) {
                if(response!=null && response.isSuccessful()){
                    serviceCallback.onSuccess(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<Content>> call, Throwable t) {
                serviceCallback.onFailure(new SimpleAppException(t));
            }
        });
    }

}
