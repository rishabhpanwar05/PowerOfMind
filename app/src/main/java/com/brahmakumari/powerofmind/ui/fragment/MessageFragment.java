package com.brahmakumari.powerofmind.ui.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.brahmakumari.powerofmind.R;
import com.brahmakumari.powerofmind.adapter.MessageAdapter;
import com.brahmakumari.powerofmind.model.Message;
import com.brahmakumari.powerofmind.network.APIService;
import com.brahmakumari.powerofmind.network.APIServiceMessage;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MessageFragment extends Fragment {

    Button share;
    List<Message> messages;

    public RecyclerView recyclerView;
    private MessageAdapter messageAdapter;
    protected RecyclerView.LayoutManager layoutManager;

    private OnFragmentInteractionListener mListener;

    public MessageFragment() {

    }

    public static MessageFragment newInstance(String param1, String param2) {
        MessageFragment fragment = new MessageFragment();
        Bundle args = new Bundle();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view=inflater.inflate(R.layout.fragment_message, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.rv);
        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        return view;
    }

    public void onResume()
    {
        super.onResume();
        Call<List<Message>> call= getMessageList();
        call.enqueue(new Callback<List<Message>>() {
            @Override
            public void onResponse(Call<List<Message>> call, Response<List<Message>> response)
            {
                messages=response.body();
                showList();
            }

            @Override
            public void onFailure(Call<List<Message>> call, Throwable t) {
            }
        });
    }

    public void showList()
    {
        messageAdapter = new MessageAdapter(getActivity(), messages);
        recyclerView.setAdapter(messageAdapter);
    }

    public Call<List<Message>> getMessageList()
    {
        Retrofit retrofit= APIService.getClient();

        APIServiceMessage apiServiceMessage =
                retrofit.create(APIServiceMessage.class);

        return apiServiceMessage.getMessageList();
    }

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

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
