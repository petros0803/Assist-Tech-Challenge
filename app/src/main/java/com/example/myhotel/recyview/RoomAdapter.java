package com.example.myhotel.recyview;

import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myhotel.R;
import com.example.myhotel.api.models.Rooms;

import java.util.ArrayList;

import static android.os.Build.VERSION_CODES.R;

public class RoomAdapter extends RecyclerView.Adapter<RoomAdapter.RoomViewHolder> {
    private ArrayList<Rooms> mrooms;

    public static class RoomViewHolder extends RecyclerView.ViewHolder {

        private TextView rnum;
        private TextView bnum;
        private TextView price;
        private ImageView ac;
        private ImageView wifi;
        private ImageView tv;
        private ImageView nsr;


        public RoomViewHolder(@NonNull View itemView) {
            super(itemView);
            rnum = itemView.findViewById(com.example.myhotel.R.id.txt_room_number);
            bnum = itemView.findViewById(com.example.myhotel.R.id.txt_type_room);
            ac = itemView.findViewById(com.example.myhotel.R.id.ac_true);
            wifi = itemView.findViewById(com.example.myhotel.R.id.wifi_true);
            tv = itemView.findViewById(com.example.myhotel.R.id.tv_true);
            nsr = itemView.findViewById(com.example.myhotel.R.id.smk_true);
            price = itemView.findViewById(com.example.myhotel.R.id.txt_price);
        }
    }

    public RoomAdapter(ArrayList<Rooms> rooms) {
        mrooms = rooms;
    }

    @NonNull
    @Override
    public RoomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(com.example.myhotel.R.layout.room_type_card, parent, false);
        RoomViewHolder rvh = new RoomViewHolder(v);
        return rvh;
    }

    @Override
    public void onBindViewHolder(@NonNull RoomViewHolder holder, int position) {

        Rooms currentRoom = mrooms.get(position);
        holder.rnum.setText("Room: " + currentRoom.getNumber());


        switch (currentRoom.getBeds()) {
            case 1:
                holder.bnum.setText("Single room");
                break;
            case 2:
                holder.bnum.setText("Double room");
                break;
            case 3:
                holder.bnum.setText("Triple room");
                break;
            case 4:
                holder.bnum.setText("Quad room");
                break;
            case 5:
                holder.bnum.setText("Master Suite");
        }
        holder.price.setText(currentRoom.getPrice() + " $/per day");

        if (!currentRoom.getF().isAc()) {
            holder.ac.setImageResource(com.example.myhotel.R.drawable.ic_akar_icons_check);
        } else holder.ac.setImageResource(com.example.myhotel.R.drawable.ic_akar_icons_circle_x);
        if (currentRoom.getF().isWifi()) {
            holder.wifi.setImageResource(com.example.myhotel.R.drawable.ic_akar_icons_check);
        } else holder.wifi.setImageResource(com.example.myhotel.R.drawable.ic_akar_icons_circle_x);
        if (currentRoom.getF().isTv()) {
            holder.tv.setImageResource(com.example.myhotel.R.drawable.ic_akar_icons_check);
        } else holder.tv.setImageResource(com.example.myhotel.R.drawable.ic_akar_icons_circle_x);
        if (currentRoom.getF().isNsr()) {
            holder.nsr.setImageResource(com.example.myhotel.R.drawable.ic_akar_icons_check);
        } else holder.nsr.setImageResource(com.example.myhotel.R.drawable.ic_akar_icons_circle_x);
    }

    @Override
    public int getItemCount() {
        return mrooms.size();
    }
}
