package com.brahmakumari.powerofmind.network;

import com.brahmakumari.powerofmind.model.Message;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.POST;


/**
 * Created by rishabhpanwar on 14/04/17.
 */

public interface APIServiceMessage {

    @POST("messagelast")
    Call<List<Message>> getMessageList();

}
