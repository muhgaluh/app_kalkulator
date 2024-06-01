package com.example.aplikasikalkulator;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    EditText edtnilai1, edtnilai2;
    TextView txtHasil;
    Button btnHitung;
    RadioButton rbTambah, rbKurang, rbKali, rbBagi;
    RecyclerView rvHistory;
    HistoryAdapter historyAdapter;
    ArrayList<HistoryModel> listHistory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtnilai1 = findViewById(R.id.edtnilai1);
        edtnilai2 = findViewById(R.id.edtnilai2);
        txtHasil = findViewById(R.id.txtHasil);
        btnHitung = findViewById(R.id.btnHitung);
        rbTambah = findViewById(R.id.rbTambah);
        rbKurang = findViewById(R.id.rbKurang);
        rbKali = findViewById(R.id.rbKali);
        rbBagi = findViewById(R.id.rbBagi);
        rvHistory = findViewById(R.id.rvHistory);

        AmbilData();

        historyAdapter = new HistoryAdapter(listHistory);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        rvHistory.setLayoutManager(layoutManager);
        rvHistory.setAdapter(historyAdapter);

        btnHitung.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Hitung();
            }
        });
    }

    private void SimpanData(int nilai1, int nilai2, int hasil, String operator) {

        listHistory.add(new HistoryModel(nilai1, nilai2, hasil, operator));

        SharedPreferences sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE);

        SharedPreferences.Editor editor = sharedPreferences.edit();

        Gson gson = new Gson();

        String json = gson.toJson(listHistory);

        editor.putString("hasil", json);
        editor.apply();

        finish();
        startActivity(getIntent());

        Toast.makeText(this, "Data Tersimpan ", Toast.LENGTH_SHORT).show();

    }

    private void AmbilData() {
        SharedPreferences sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE);

        Gson gson = new Gson();

        String json = sharedPreferences.getString("hasil", null);

        Type type = new TypeToken<ArrayList<HistoryModel>>() {}.getType();

        listHistory = gson.fromJson(json, type);

        if (listHistory == null) {
            listHistory = new ArrayList<>();
        }
    }

    public void Hitung(){

        int nilai1 = Integer.valueOf(edtnilai1.getText().toString());
        int nilai2 = Integer.valueOf(edtnilai2.getText().toString());
        int hasil;

        if(edtnilai1.getText().toString().isEmpty())
        {
            Toast.makeText(MainActivity.this, "Silahkan Pilih Operator yang tersedia", Toast.LENGTH_LONG).show();
        }
        else if(edtnilai2.getText().toString().isEmpty())
        {
            Toast.makeText(MainActivity.this, "Silahkan Pilih Operator yang tersedia", Toast.LENGTH_LONG).show();
        }
        else
        {
            if(rbTambah.isChecked())
            {
                hasil = nilai1 + nilai2;
                SimpanData(nilai1, nilai2, hasil, "+");
                txtHasil.setText(String.valueOf(hasil));
            }
            else if(rbKurang.isChecked())
            {
                hasil = nilai1 - nilai2;
                SimpanData(nilai1, nilai2, hasil, "-");
                txtHasil.setText(String.valueOf(hasil));
            }
            else if(rbKali.isChecked())
            {
                hasil = nilai1 * nilai2;
                SimpanData(nilai1, nilai2, hasil, "x");
                txtHasil.setText(String.valueOf(hasil));
            }
            else if(rbBagi.isChecked())
            {
                hasil = nilai1 / nilai2;
                SimpanData(nilai1, nilai2, hasil, ":");
                txtHasil.setText(String.valueOf(hasil));
            }
            else
            {
                Toast.makeText(MainActivity.this, "Silahkan Pilih Operator yang tersedia", Toast.LENGTH_LONG).show();
            }
        }
    }
}