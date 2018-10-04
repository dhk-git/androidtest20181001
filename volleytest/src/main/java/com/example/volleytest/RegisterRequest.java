package com.example.volleytest;

import android.support.annotation.Nullable;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

public class RegisterRequest extends StringRequest {

    final static private String URL = "http://jims.jeongdo.co.kr/jims2scm/home/json";
    public RegisterRequest(String userid, String userPassword, String userName, int userAge, Response.Listener<String> listener) {
        super(Method.GET, URL, listener, null);


    }
}
