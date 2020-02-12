package com.alimasanov.appgeo.view.addActivity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.alimasanov.appgeo.R;
import com.alimasanov.appgeo.view.mapsActivity.MapsActivity;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class AddActivity extends AppCompatActivity {

    private PresenterAddActivity presenter;

    EditText etFio;
    EditText etCity;
    EditText etBirthday;
    TextView tvLocation;
    TextView tvLoading;
    Button button;
    ProgressBar progressBar;

    private Intent intent;
    private Calendar calendar = Calendar.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        etBirthday = findViewById(R.id.birthday);
        etCity = findViewById(R.id.city);
        etFio = findViewById(R.id.fio);
        tvLocation = findViewById(R.id.geolocation);
        tvLoading = findViewById(R.id.tv_load_geo);
        button = findViewById(R.id.button_add);
        progressBar = findViewById(R.id.progressBar);
        button.setVisibility(View.GONE);

        intent = new Intent(this, MapsActivity.class);
        LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        presenter = new PresenterAddActivity(this,
                locationManager,
                getApplicationContext());

        clickListeners();
    }

    private void clickListeners() {
        tvLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.putExtra("lat", presenter.getLatitude()).putExtra("lon", presenter.getLongitude());
                startActivity(intent);
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.setData(etFio.getText().toString(),
                        etCity.getText().toString(),
                        etBirthday.getText().toString(),
                        tvLocation.getText().toString());
                etBirthday.setText("");
                etFio.setText("");
                etCity.setText("");
            }
        });

        etBirthday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(AddActivity.this, date, calendar
                        .get(Calendar.YEAR), calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
    }

    private void updateLabel() {
        String myFormat = "MM.dd.yyyy";
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        etBirthday.setText(sdf.format(calendar.getTime()));
    }

    protected void onResume() {
        super.onResume();
        presenter.onResume();
    }

    public void showLocation(String location) {
        progressBar.setVisibility(View.GONE);
        tvLoading.setVisibility(View.GONE);
        button.setVisibility(View.VISIBLE);
        tvLocation.setText(location);
    }

    DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            calendar.set(Calendar.YEAR, year);
            calendar.set(Calendar.MONTH, month);
            calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            updateLabel();
        }
    };
}