package com.brahmakumari.powerofmind.network;

//import com.brahmakumari.powerofmind.model.LiveDarshan;

import com.brahmakumari.powerofmind.model.LiveDarshan;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.POST;

/**
 * Created by rishabhpanwar on 18/04/17.
 */

public interface APIServiceLiveDarshan {

    @POST("liveDarshan")
    Call<List<LiveDarshan>> getLiveList();
}
