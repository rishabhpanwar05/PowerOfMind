package com.brahmakumari.powerofmind.network;

import com.brahmakumari.powerofmind.model.Events;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.POST;

/**
 * Created by ishitabhandari on 12/06/17.
 */

public interface APIServiceEvents {
    @POST("events")
    Call<List<Events>> getEventsList();
}
