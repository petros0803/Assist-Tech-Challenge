package com.example.myhotel.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myhotel.R;
import com.example.myhotel.api.ApiClient;
import com.example.myhotel.api.models.Facilities;
import com.example.myhotel.api.models.Rooms;
import com.example.myhotel.data.Singleton;
import com.example.myhotel.recyview.RoomAdapter;
import com.example.myhotel.ui.MainMenu;
import com.example.myhotel.ui.RegisterActivity;
import com.example.myhotel.ui.ReserveActivity;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AvailableReservations extends Fragment {

    private ArrayList<Rooms> rom = new ArrayList<>();
    private ArrayList<Rooms> reservedrooms=new ArrayList<>();
    private RecyclerView rRecyclerView;
    private RoomAdapter rAdapter;
    private RecyclerView.LayoutManager rLayaoutManager;
    private TextView choose_description;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_available_reservations, container, false);
    }


    public void onViewCreated(View view, Bundle savedInstanceState) {

        choose_description=getActivity().findViewById(R.id.txt_choose);
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
                       if(!r.isReserved())
                       {
                           rom.add(r);

                           Log.d("onAddToList","Unreserved Room added");
                       }
                       if (r.isReserved())
                       {
                           reservedrooms.add(r);
                       }else
                         { Singleton.getInstance().setRooms(reservedrooms);}
                        Log.d("response", "returned this: " + response.body().toString());

                        Log.d("response", "and this " + rom.toString());
                    }

                    populateRV();

                } else { 
                    Log.d("onResponse", "responsebody was null");

                }
            }

            @Override
            public void onFailure(Call<List<Rooms>> call, Throwable t) {
                Log.d("onFailure", "Unsuccesfull response "+ t);

            }
        });
    }

    private void populateRV() {

        rRecyclerView = getView().findViewById(R.id.rview_rese);
        rLayaoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        rAdapter = new RoomAdapter(rom);
        rRecyclerView.setLayoutManager(rLayaoutManager);
        rRecyclerView.setAdapter(rAdapter);
        rAdapter.setOnItemClickListener(position ->
                Toast.makeText(getActivity(), "Clicked on cardView", Toast.LENGTH_SHORT).show());
        if (rAdapter.getItemCount()==0)
        {
         choose_description.setText("No rooms available for reservation\n Come back later");
        }
        rAdapter.setOnItemClickListener(position -> {
            rom.get(position);
            Intent intent = new Intent(getActivity(), ReserveActivity.class);
            intent.putExtra("room_name",rom.get(position).getBeds());
            intent.putExtra("room_id",rom.get(position).getId());
            startActivity(intent);
            getActivity().finish();

        });


    }
}