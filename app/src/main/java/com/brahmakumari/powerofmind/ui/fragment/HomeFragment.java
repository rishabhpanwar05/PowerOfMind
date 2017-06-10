package com.brahmakumari.powerofmind.ui.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.brahmakumari.powerofmind.R;
import com.brahmakumari.powerofmind.adapter.MiniAudioAdapter;
import com.brahmakumari.powerofmind.adapter.MiniVideoAdapter;
import com.brahmakumari.powerofmind.model.Audio;
import com.brahmakumari.powerofmind.model.Message;
import com.brahmakumari.powerofmind.model.Video;
import com.brahmakumari.powerofmind.network.APIService;
import com.brahmakumari.powerofmind.network.APIServiceAudio;
import com.brahmakumari.powerofmind.network.APIServiceVideo;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link HomeFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    ImageView ic_up_arrow,ic_down_arrow,ic_right_arrow,ic_left_arrow;


    //private static final String[] aud_titles={"Channa Mereya","Shape of You","Humnava","Bailando"
           // ,"Humsafar","RockABye","Te Amo","Break Up Song"};

    public RecyclerView rv_mini_audio;
    private MiniAudioAdapter miniAudioAdapter;
    protected RecyclerView.LayoutManager audiolayoutManager;


    public RecyclerView rv_mini_video;
    private MiniVideoAdapter miniVideoAdapter;
    protected RecyclerView.LayoutManager videolayoutManager;
    List<Video> videos;
    List<Audio> audios;
    List<Message> messages;
    TextView thoughttextView;

    private OnFragmentInteractionListener mListener;

    public HomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_home, container, false);

        homeAnimation(view);

        rv_mini_video=(RecyclerView)view.findViewById(R.id.rv_mini_video);
        videolayoutManager=new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL, false);
        rv_mini_video.setLayoutManager(videolayoutManager);
        rv_mini_video.setAdapter(miniVideoAdapter);

        rv_mini_audio=(RecyclerView)view.findViewById(R.id.rv_mini_audio);
        audiolayoutManager=new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        rv_mini_audio.setLayoutManager(audiolayoutManager);

        thoughttextView = (TextView) view.findViewById(R.id.thoughttextView);
        thoughttextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = new MessageFragment();
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.mainFrame, fragment);
                ft.commit();
            }
        });
        return view;

    }
    public void onResume()
    {
        super.onResume();
        getVideoList();
        getAudioList();
    }
    public void showList()
    {
        miniVideoAdapter=new MiniVideoAdapter(getActivity(),videos);
        rv_mini_video.setAdapter(miniVideoAdapter);

        miniAudioAdapter=new MiniAudioAdapter(getActivity(),audios);
        rv_mini_audio.setAdapter(miniAudioAdapter);
    }

    public void getVideoList()
    {
        Retrofit retrofit= APIService.getClient();

        APIServiceVideo apiServiceVideo =
                retrofit.create(APIServiceVideo.class);

        Call<List<Video>> call = apiServiceVideo.getVideoList();

        call.enqueue(new Callback<List<Video>>() {
            @Override
            public void onResponse(Call<List<Video>> call, Response<List<Video>> response)
            {
                videos=response.body();
                showList();
            }

            @Override
            public void onFailure(Call<List<Video>> call, Throwable t) {

            }
        });
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
    public void homeAnimation(View view){

        ic_down_arrow=(ImageView)view.findViewById(R.id.ic_down_arrow);
        ic_up_arrow=(ImageView)view.findViewById(R.id.ic_up_arrow);
        ic_right_arrow=(ImageView)view.findViewById(R.id.ic_right_arrow);
        ic_left_arrow=(ImageView)view.findViewById(R.id.ic_left_arrow);
        Animation ani_hor_left= AnimationUtils.loadAnimation(getActivity(),R.anim.move_horizontal_left);
        Animation ani_hor_right= AnimationUtils.loadAnimation(getActivity(),R.anim.move_horizontal_right);
        Animation ani_ver_down= AnimationUtils.loadAnimation(getActivity(),R.anim.move_vertical_down);
        Animation ani_ver_up= AnimationUtils.loadAnimation(getActivity(),R.anim.move_vertical_up);
        ic_left_arrow.startAnimation(ani_hor_left);
        ic_right_arrow.startAnimation(ani_hor_right);
        ic_down_arrow.startAnimation(ani_ver_down);
        ic_up_arrow.startAnimation(ani_ver_up);
    }

    public void setThought(){


    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
