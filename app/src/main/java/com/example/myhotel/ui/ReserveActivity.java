package com.example.myhotel.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.util.Pair;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myhotel.R;
import com.example.myhotel.api.ApiClient;
import com.example.myhotel.api.models.MakeReservation;
import com.example.myhotel.data.Singleton;
import com.google.android.material.datepicker.CalendarConstraints;
import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener;

import java.text.SimpleDateFormat;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.google.android.material.resources.MaterialAttributes.resolveOrThrow;

public class ReserveActivity extends AppCompatActivity {

    private Button pickDate;
    private String sDate;
    private String eDate;
    private TextView txtreservation;
    private ImageView back;
    private int bednumb;
    private String roomID;
    private Button sendData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservation);
        back=findViewById(R.id.back_btn);
        back.setOnClickListener((View.OnClickListener) v -> {
            startActivity(new Intent(getApplicationContext(), MainMenu.class));
            finish();
        });
        pickingDate();
        getDataFromFragment();
        sendReservedData();
    }












private void sendReservedData()
{
    sendData=findViewById(R.id.send_reservation);

    sendData.setOnClickListener(v -> {
        MakeReservation makeReservation=new MakeReservation(Singleton.getInstance().getUserID(),roomID,sDate,eDate);

        Call<MakeReservation> call= ApiClient.getApiInterface().performReservation(makeReservation);
        call.enqueue(new Callback<MakeReservation>() {
            @Override
            public void onResponse(Call<MakeReservation> call, Response<MakeReservation> response) {
                if (response.isSuccessful())
                {
                    Log.d("reservation", "reserved succesfully");
                    startActivity(new Intent(getApplicationContext(), MainMenu.class));
                    Toast.makeText(ReserveActivity.this,"Good job",Toast.LENGTH_LONG).show();
                    finish();
                }
            }

            @Override
            public void onFailure(Call<MakeReservation> call, Throwable t) {
                Log.d("reservation", "FAILED  "+t);
            }
        });

    });

}





    private void getDataFromFragment() {
        Intent intent = getIntent();
        bednumb= (int) intent.getSerializableExtra("room_name");
        roomID= (String) intent.getSerializableExtra("room_id");
        if (bednumb!=0) {
            Log.d("rooms", "rooms retrieved corectrly"+ bednumb + " " +roomID);

            TextView roomtype = findViewById(R.id.room_type_book);
            switch (bednumb) {
                case 1:
                    roomtype.setText("Single room");
                    break;
                case 2:
                    roomtype.setText("Double room");
                    break;
                case 3:
                    roomtype.setText("Triple room");
                    break;
                case 4:
                    roomtype.setText("Quad room");
                    break;
                case 5:
                    roomtype.setText("Master Suite");
                    break;}

        } else
            Log.d("rooms", "dosent work");
    }



    private void pickingDate()
    {   txtreservation=findViewById(R.id.reservation_period_txt);

        MaterialDatePicker.Builder<Pair<Long, Long>> builder = MaterialDatePicker.Builder.dateRangePicker();
        CalendarConstraints.Builder constraintsBuilder = new CalendarConstraints.Builder();
        builder.setCalendarConstraints(constraintsBuilder.build());
        builder.setTheme(R.style.Theme_MaterialComponents);
        MaterialDatePicker materialDatePicker = builder.build();

        pickDate = findViewById(R.id.select_dates);
        pickDate.setOnClickListener(v -> materialDatePicker.show(getSupportFragmentManager(), "Date_picker"));

        materialDatePicker.addOnPositiveButtonClickListener(new MaterialPickerOnPositiveButtonClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onPositiveButtonClick(Object selection) {
                Pair selectedDates = (Pair) materialDatePicker.getSelection();
                final Pair<Date, Date> rangeDate = new Pair<>(new Date((Long) selectedDates.first), new Date((Long) selectedDates.second));
                Date startDate = rangeDate.first;
                Date endDate = rangeDate.second;
                SimpleDateFormat simpleFormat = new SimpleDateFormat("yyyy-MM-dd");
                sDate = simpleFormat.format(startDate);
                eDate = simpleFormat.format(endDate);
                Log.d("simpleformat",startDate+ " "+endDate);
                txtreservation.setText("From: "+sDate+" to: "+ eDate);

                Log.d("selected dates","from "+sDate+" to "+ eDate);

            }
        });
    }
}