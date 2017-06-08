package com.brahmakumari.powerofmind.network;

import com.brahmakumari.powerofmind.model.Video;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.POST;

/**
 * Created by rishabhpanwar on 17/04/17.
 */

public interface APIServiceVideo {

        @POST("videos")
        Call<List<Video>> getVideoList();
}
