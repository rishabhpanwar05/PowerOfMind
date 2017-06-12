package com.brahmakumari.powerofmind.network;

import com.brahmakumari.powerofmind.model.Articles;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.POST;

/**
 * Created by ishitabhandari on 10/06/17.
 */

public interface APIServiceArticles {
    @POST("articles")
    Call<List<Articles>> getArticlesList();
}
