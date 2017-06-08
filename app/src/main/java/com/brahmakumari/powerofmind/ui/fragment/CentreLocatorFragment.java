package com.brahmakumari.powerofmind.ui.fragment;

import android.content.Context;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.brahmakumari.powerofmind.R;
import com.brahmakumari.powerofmind.ui.activity.MapsActivity;

import java.io.IOException;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link CentreLocatorFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link CentreLocatorFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CentreLocatorFragment extends Fragment {

    double lat;
    double lng;

    Spinner select_country,select_city;
    Button submitToMap;
    TextView city_tv;

    private OnFragmentInteractionListener mListener;

    public CentreLocatorFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CentreLocatorFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CentreLocatorFragment newInstance(String param1, String param2) {
        CentreLocatorFragment fragment = new CentreLocatorFragment();
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

        View view=inflater.inflate(R.layout.fragment_centre_locator, container, false);

        city_tv=(TextView)view.findViewById(R.id.city_label);
        select_country= (Spinner) view.findViewById(R.id.select_country);
        select_city= (Spinner) view.findViewById(R.id.select_city);
        ArrayAdapter<CharSequence> adapter_country = ArrayAdapter.createFromResource(getContext(), R.array.country_arrays, android.R.layout.simple_spinner_item);
        adapter_country.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        select_country.setAdapter(adapter_country);


        select_country.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selected_country = select_country.getSelectedItem().toString();
                if(!selected_country.contentEquals("Select Country"))
                {
                    city_tv.setVisibility(View.VISIBLE);
                    select_city.setVisibility(View.VISIBLE);
                    if(selected_country.contentEquals("India"))
                    {
                        ArrayAdapter<CharSequence> adapter_city_india = ArrayAdapter.createFromResource(getContext(), R.array.city_arrays_india, android.R.layout.simple_spinner_item);
                        adapter_city_india.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        select_city.setAdapter(adapter_city_india);
                    }else{
                        ArrayAdapter<CharSequence> adapter_city_usa = ArrayAdapter.createFromResource(getContext(), R.array.city_arrays_usa, android.R.layout.simple_spinner_item);
                        adapter_city_usa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        select_city.setAdapter(adapter_city_usa);
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        submitToMap=(Button) view.findViewById(R.id.btnSubmit);


        submitToMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String selected_city= select_city.getSelectedItem().toString();
                if(Geocoder.isPresent())
                {
                    try
                    {
                        String location = "";
                        Geocoder gc = new Geocoder(getContext());
                        List<Address> addresses= gc.getFromLocationName(selected_city,1); // get the found Address Objects
                        for(Address a : addresses)
                        {
                            if(a.hasLatitude() && a.hasLongitude())
                            {
                                lat=a.getLatitude();
                                lng=a.getLongitude();
                            }
                        }
                    } catch (IOException e) {
                    }
                }
                Intent intent=new Intent(getContext(),MapsActivity.class);
                intent.putExtra("latitude",Double.toString(lat));
                intent.putExtra("longitude",Double.toString(lng));
                startActivity(intent);
            }
        });
        return view;
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
