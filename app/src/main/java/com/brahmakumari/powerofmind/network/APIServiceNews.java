package com.brahmakumari.powerofmind.network;

import com.brahmakumari.powerofmind.model.News;
import java.util.List;
import retrofit2.Call;
import retrofit2.http.POST;

/**
 * Created by ishitabhandari on 08/06/17.
 */

public interface APIServiceNews {
    @POST("news")
    Call<List<News>> getNewsList();
}
