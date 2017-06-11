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

import com.brahmakumari.powerofmind.R;
import com.brahmakumari.powerofmind.adapter.LDAdapter;
import com.brahmakumari.powerofmind.adapter.LiveDarshanAdapter;
import com.brahmakumari.powerofmind.model.LiveDarshan;
import com.brahmakumari.powerofmind.network.APIService;
import com.brahmakumari.powerofmind.network.APIServiceLiveDarshan;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link DarshanFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link DarshanFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DarshanFragment extends Fragment {


    public RecyclerView rv;
    private LDAdapter ldAdapter;
    protected RecyclerView.LayoutManager layoutManager;

    List<LiveDarshan> darshans;

    private OnFragmentInteractionListener mListener;

    public DarshanFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DarshanFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static DarshanFragment newInstance(String param1, String param2) {
        DarshanFragment fragment = new DarshanFragment();
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
        View view=inflater.inflate(R.layout.fragment_darshan, container, false);
        rv=(RecyclerView)view.findViewById(R.id.rv);
        layoutManager=new LinearLayoutManager(getActivity());
        rv.setLayoutManager(layoutManager);
        return view;
    }

    public void onResume()
    {
        super.onResume();
        getLiveList();
    }
    public void showList()
    {
        ldAdapter=new LDAdapter(getContext(),darshans);
        rv.setAdapter(ldAdapter);
    }

    public void getLiveList()
    {

        Retrofit retrofit= APIService.getClient();
        APIServiceLiveDarshan apiServiceLiveDarshan =
                retrofit.create(APIServiceLiveDarshan.class);

        Call<List<LiveDarshan>> call = apiServiceLiveDarshan.getLiveList();
        call.enqueue(new Callback<List<LiveDarshan>>() {
            @Override
            public void onResponse(Call<List<LiveDarshan>> call, Response<List<LiveDarshan>> response)
            {
                darshans=response.body();
                showList();
            }

            @Override
            public void onFailure(Call<List<LiveDarshan>> call, Throwable t) {
            }
        });
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
