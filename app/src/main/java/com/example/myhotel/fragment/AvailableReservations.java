package com.example.myhotel.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.myhotel.R;
import com.example.myhotel.api.ApiClient;
import com.example.myhotel.api.models.Rooms;
import com.example.myhotel.recyview.RoomAdapter;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AvailableReservations extends Fragment {

    private ArrayList<Rooms> rom = new ArrayList<>();
    private RecyclerView rRecyclerView;
    private RecyclerView.Adapter rAdapter;
    private RecyclerView.LayoutManager rLayaoutManager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_available_reservations, container, false);
    }


    public void onViewCreated(View view, Bundle savedInstanceState) {

        getRoomsData();
    }

    private void getRoomsData() {
        Call<List<Rooms>> call = ApiClient.getApiInterface().getRooms();
        call.enqueue(new Callback<List<Rooms>>() {
            @Override
            public void onResponse(Call<List<Rooms>> call, Response<List<Rooms>> response) {
                Log.d("response", "response succesful");
                if (response.body() != null) {
                    for (Rooms r : response.body()) {
                        Log.d("response", "returned this: " + response.body().toString());
                        rom.add(r);
                        Log.d("response", "and this " + rom.toString());
                    }
                    populateRV();

                } else {
                    Log.d("onResponse", "responsebody was null");
                }
            }

            @Override
            public void onFailure(Call<List<Rooms>> call, Throwable t) {
                Log.d("onFailure", "Unsuccesfull response");

            }
        });
    }

    private void populateRV() {
        rRecyclerView = getView().findViewById(R.id.rview_rese);
        rLayaoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        rAdapter = new RoomAdapter(rom);
        rRecyclerView.setLayoutManager(rLayaoutManager);
        rRecyclerView.setAdapter(rAdapter);
    }
}