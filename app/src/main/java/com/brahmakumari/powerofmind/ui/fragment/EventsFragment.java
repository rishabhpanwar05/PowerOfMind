package com.brahmakumari.powerofmind.ui.fragment;


import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.brahmakumari.powerofmind.R;
import com.brahmakumari.powerofmind.adapter.EventsAdapter;
import com.brahmakumari.powerofmind.model.Events;
import com.brahmakumari.powerofmind.network.APIService;
import com.brahmakumari.powerofmind.network.APIServiceEvents;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * A simple {@link Fragment} subclass.
 */
public class EventsFragment extends Fragment {

    private OnFragmentInteractionListener eListener;
    RecyclerView recyclerView;
    List<Events> events;

    public EventsFragment() {
        // Required empty public constructor
    }

    public static EventsFragment newInstance(String param1, String param2) {
        EventsFragment fragment = new EventsFragment();
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
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_events, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.events_rv);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        return view;
    }

    public void onResume()
    {
        super.onResume();
        getEventsList();
    }

    public void showList()
    {
        EventsAdapter eventsAdapter = new EventsAdapter(getActivity(), events);
        recyclerView.setAdapter(eventsAdapter);
    }

    public void getEventsList()
    {
        Retrofit retrofit= APIService.getClient();

        APIServiceEvents apiServiceEvents =
                retrofit.create(APIServiceEvents.class);

        Call<List<Events>> call = apiServiceEvents.getEventsList();

        call.enqueue(new Callback<List<Events>>() {
            @Override
            public void onResponse(Call<List<Events>> call, Response<List<Events>> response)
            {
                events=response.body();
                Log.i("demo:",events.toString());
                showList();
            }

            @Override
            public void onFailure(Call<List<Events>> call, Throwable t) {
            }
        });
    }



    public void onButtonPressed(Uri uri) {
        if (eListener != null) {
            eListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            eListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        eListener = null;
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

}
