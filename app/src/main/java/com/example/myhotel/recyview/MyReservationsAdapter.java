package com.example.myhotel.recyview;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myhotel.R;
import com.example.myhotel.api.ApiClient;
import com.example.myhotel.api.models.MakeReservation;
import com.example.myhotel.api.models.RemoveReservation;
import com.example.myhotel.api.models.Reservations;
import com.example.myhotel.api.models.Rooms;
import com.example.myhotel.data.Singleton;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MyReservationsAdapter extends RecyclerView.Adapter<MyReservationsAdapter.MyReservationsViewHolder> {
    private ArrayList<Reservations> myReservationsList;
    private ArrayList<Rooms> camere = Singleton.getInstance().getRooms();
    private OnItemClickListener listen;
    public interface OnItemClickListener{
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener)
    {
        listen=listener;
    }
    public static class MyReservationsViewHolder extends RecyclerView.ViewHolder {
        private TextView rtype;
        public TextView timeline;
        public Button cancel;
        public TextView price;

        public MyReservationsViewHolder(@NonNull View itemView,OnItemClickListener listener) {
            super(itemView);
            timeline = itemView.findViewById(R.id.bookedroom_dates);
            cancel = itemView.findViewById(R.id.cancel_booking);
            rtype = itemView.findViewById(R.id.bookedroom_type);
            price = itemView.findViewById(R.id.paid_price);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(listener!=null)
                    {
                        int position=getAdapterPosition();
                        if(position!=RecyclerView.NO_POSITION)
                        {
                            listener.onItemClick(position);
                        }
                    }
                }
            });
        }
    }

    @NonNull
    @Override
    public MyReservationsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.booked_card, parent, false);
        MyReservationsViewHolder mvh = new MyReservationsViewHolder(v,listen);
        return mvh;
    }

    public MyReservationsAdapter(ArrayList<Reservations> myReservations) {
        myReservationsList = myReservations;
    }

    @Override
    public void onBindViewHolder(@NonNull MyReservationsViewHolder holder, int position) {

        Reservations currentReservation = myReservationsList.get(position);
        holder.timeline.setText("From: " + currentReservation.getStartDate() + "\nTo: " + currentReservation.getExpirationDate());
        for (Rooms r : camere) {

            if (currentReservation.getRoomID().equals(r.getId())) {
                switch (r.getBeds()) {
                    case 1:
                        holder.rtype.setText("Single room");
                        break;
                    case 2:
                        holder.rtype.setText("Double room");
                        break;
                    case 3:
                        holder.rtype.setText("Triple room");
                        break;
                    case 4:
                        holder.rtype.setText("Quad room");
                        break;
                    case 5:
                        holder.rtype.setText("Master Suite");
                }
                holder.price.setText(r.getPrice() + " $/day");

            }

        }
        holder.cancel.setOnClickListener(v -> {
            Call<RemoveReservation> call = ApiClient.getApiInterface().remove(currentReservation.getReservationID());
            call.enqueue(new Callback<RemoveReservation>() {
                @Override
                public void onResponse(Call<RemoveReservation> call, Response<RemoveReservation> response) {
                    Log.d("remove", "reservation removed succesfully");
                }

                @Override
                public void onFailure(Call<RemoveReservation> call, Throwable t) {
                    Log.d("remove", "reservation Failed " + t);

                }
            });

        });

    }

    @Override
    public int getItemCount() {
        return myReservationsList.size();
    }


}
