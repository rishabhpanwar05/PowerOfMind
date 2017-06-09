package com.brahmakumari.powerofmind.ui.fragment;


import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.brahmakumari.powerofmind.R;
import com.brahmakumari.powerofmind.adapter.NewsAdapter;
import com.brahmakumari.powerofmind.model.News;
import com.brahmakumari.powerofmind.network.APIService;
import com.brahmakumari.powerofmind.network.APIServiceNews;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * A simple {@link Fragment} subclass.
 */
public class NewsFragment extends Fragment {

    List<News> news;

    public RecyclerView recyclerView;
    private NewsAdapter newsAdapter;
    private OnFragmentInteractionListener nListener;


    public NewsFragment() {
        // Required empty public constructor
    }

    public static NewsFragment newInstance(String param1, String param2) {
        NewsFragment fragment = new NewsFragment();
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
        View view = inflater.inflate(R.layout.fragment_news, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.news_rv);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        return view;
    }

    public void onResume()
    {
        super.onResume();
        //getNewsList();
    }

    public void showList()
    {
        NewsAdapter newsAdapter = new NewsAdapter(getActivity(), news);
        recyclerView.setAdapter(newsAdapter);
    }

    public void getNewsList()
    {
        Retrofit retrofit= APIService.getClient();

        APIServiceNews apiServiceNews =
                retrofit.create(APIServiceNews.class);

        Call<List<News>> call = apiServiceNews.getNewsList();

        call.enqueue(new Callback<List<News>>() {
            @Override
            public void onResponse(Call<List<News>> call, Response<List<News>> response)
            {
                news=response.body();
                showList();
            }

            @Override
            public void onFailure(Call<List<News>> call, Throwable t) {
            }
        });
    }

    public void onButtonPressed(Uri uri) {
        if (nListener != null) {
            nListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            nListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        nListener = null;
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

}
