package com.brahmakumari.powerofmind.ui.fragment;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.brahmakumari.powerofmind.adapter.AudioAdapter;
import com.brahmakumari.powerofmind.R;

import com.brahmakumari.powerofmind.model.Audio;
import com.brahmakumari.powerofmind.network.APIService;
import com.brahmakumari.powerofmind.network.APIServiceAudio;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link AudioFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AudioFragment extends Fragment {

    public RecyclerView rv;
    private AudioAdapter aa;
    List<Audio> audios;
    protected RecyclerView.LayoutManager layoutManager;


    public AudioFragment() {
        // Required empty public constructor
    }

    public static AudioFragment newInstance() {
        AudioFragment fragment = new AudioFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_audio, container, false);
        rv=(RecyclerView)view.findViewById(R.id.rv);
        layoutManager=new LinearLayoutManager(getActivity());
        rv.setLayoutManager(layoutManager);
        return view;
    }
    public void onResume(){
        super.onResume();
        getAudioList();
    }
    public void showList(){

        aa = new AudioAdapter(getActivity(), audios);
        rv.setAdapter(aa);
    }

    private void getAudioList()
    {
        Retrofit retrofit = APIService.getClient();

        APIServiceAudio apiServiceAudio =
                retrofit.create(APIServiceAudio.class);

        Call<List<Audio>> call = apiServiceAudio.getAudioList();

        call.enqueue(new Callback<List<Audio>>() {
            @Override
            public void onResponse(Call<List<Audio>> call, Response<List<Audio>> response)
            {
                audios=response.body();
                showList();
            }

            @Override
            public void onFailure(Call<List<Audio>> call, Throwable t) {
            }
        });

    }
   /* public void playAudio(){

        MediaPlayer mediaPlayer = new MediaPlayer();
        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        try {
            mediaPlayer.setDataSource(audios.get());
        }catch (Exception e){
            e.printStackTrace();
        }
        try{
            mediaPlayer.prepareAsync();
            mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener(){
                @Override
                public void onPrepared(MediaPlayer mediaPlayer){
                    Common.dismissLoading(progressDialog);
                    if (mediaPlayer != null) {
                        mediaPlayer.start();
                    }
                }

            });
        }catch (Exception e){
            e.printStackTrace();
        }
        mediaPlayer.start();
    }*/

    @Override
    public String toString(){
        return "AudioFragment";
    }
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
