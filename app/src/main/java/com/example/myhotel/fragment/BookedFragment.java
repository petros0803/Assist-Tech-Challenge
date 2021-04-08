package com.example.myhotel.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myhotel.R;
import com.example.myhotel.api.ApiClient;
import com.example.myhotel.api.models.Reservations;
import com.example.myhotel.data.Singleton;
import com.example.myhotel.recyview.MyReservationsAdapter;
import com.example.myhotel.ui.MainMenu;
import com.example.myhotel.ui.UnlockActivity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class BookedFragment extends Fragment {

    private RecyclerView rRecyclerView;
    private MyReservationsAdapter rAdapter;
    private RecyclerView.LayoutManager rLayaoutManager;
    private TextView no_bookings;
    private ImageView no_booking_img;
    private ArrayList<Reservations> ares = new ArrayList<>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_my_reservations, container, false);
    }

    public void onViewCreated(View view, Bundle savedInstanceState) {
        no_bookings = getActivity().findViewById(R.id.txt_norese);
        no_booking_img = getActivity().findViewById(R.id.img_norese);

        getBookingsData();
    }

    private void getBookingsData() {
        Call<List<Reservations>> call = ApiClient.getApiInterface().getReservations();
        call.enqueue(new Callback<List<Reservations>>() {
            @Override
            public void onResponse(Call<List<Reservations>> call, Response<List<Reservations>> response) {
                if (response.isSuccessful()) {
                    for (Reservations r : response.body()) {

                        if (r.getUserID().equals(Singleton.getInstance().getUserID())) {
                            ares.add(r);
                        }
                    }
                    populateRV();
                }
            }

            @Override
            public void onFailure(Call<List<Reservations>> call, Throwable t) {

            }
        });
    }

    private void populateRV() {
        rRecyclerView = getView().findViewById(R.id.booked_rw);
        rLayaoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        rAdapter = new MyReservationsAdapter(ares);
        rRecyclerView.setLayoutManager(rLayaoutManager);
        rRecyclerView.setAdapter(rAdapter);

        if (rAdapter.getItemCount() == 0) {
            no_booking_img.setVisibility(View.VISIBLE);
            no_bookings.setVisibility(View.VISIBLE);
        } else {
            no_booking_img.setVisibility(View.INVISIBLE);
            no_bookings.setVisibility(View.INVISIBLE);
        }

        rAdapter.setOnItemClickListener(new MyReservationsAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                startActivity(new Intent(getContext(), UnlockActivity.class));
            }
        });

    }

}