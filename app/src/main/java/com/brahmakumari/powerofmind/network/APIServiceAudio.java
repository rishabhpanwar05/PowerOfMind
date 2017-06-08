package com.brahmakumari.powerofmind.network;

import com.brahmakumari.powerofmind.model.Audio;
import java.util.List;
import retrofit2.Call;
import retrofit2.http.POST;

/**
 * Created by ishitabhandari on 18/04/17.
 */

public interface APIServiceAudio {
    @POST("audios")
    Call<List<Audio>> getAudioList();

}
