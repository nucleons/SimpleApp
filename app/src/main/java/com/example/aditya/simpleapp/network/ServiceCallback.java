package com.example.aditya.simpleapp.network;

import java.util.Map;

/**
 * Created by aditya on 13/9/16.
 */
public interface ServiceCallback<T> {
    void onSuccess(T t);
    void onFailure(SimpleAppException exception);
}
