package com.example.finalandroidproject;

import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import org.checkerframework.checker.nullness.qual.NonNull;

import java.util.ArrayList;

public class SearchItem extends AppCompatActivity implements View.OnClickListener {

    private EditText searchCountry,searchSinger,searchSong, searchPosition;
    private Button searchCountry_btn,searchSinger_btn,searchSong_btn,searchPosition_btn;

    public static ArrayList<Country> countries;
    private FirebaseFirestore database = FirebaseFirestore.getInstance();
    MainActivity mainActivity = new MainActivity();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_item);

        searchCountry_btn = findViewById(R.id.searchCountry_btn);
        searchSinger_btn = findViewById(R.id.searchSinger_btn);
        searchSong_btn = findViewById(R.id.searchSong_btn);
        searchPosition_btn = findViewById(R.id.searchPosition_btn);

        searchCountry = findViewById(R.id.searchCountry);
        searchSinger = findViewById(R.id.searchSinger);
        searchSong = findViewById(R.id.searchSong);
        searchPosition = findViewById(R.id.searchPosition);

        searchCountry_btn.setOnClickListener(this);
        searchSinger_btn.setOnClickListener(this);
        searchSong_btn.setOnClickListener(this);
        searchPosition_btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.searchCountry_btn:
                getProductByValue(searchCountry.getText().toString().trim(), "Country");
                break;
            case R.id.searchSinger_btn:
                getProductByValue(searchSinger.getText().toString().trim(), "Singer");
                break;
            case R.id.searchSong_btn:
                getProductByValue(searchSong.getText().toString().trim(), "Song");
                break;
            case R.id.searchPosition_btn:
                getProductByValue(searchPosition.getText().toString().trim(), "Position");
                break;
        }
    }

    public void getProductByValue(String para, String searchFrom) {
        database.collection("Eurovision2023")
                .whereEqualTo(searchFrom, para)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        countries = new ArrayList<Country>();

                        if(task.isSuccessful()){
                            countries = new ArrayList<Country>();

                            for (QueryDocumentSnapshot document : task.getResult()){
                                Country count = document.toObject(Country.class);
                                countries.add(count);
                            }
                            Intent intent = new Intent(getApplicationContext(), Countries.class);
                            intent.putParcelableArrayListExtra("list", countries);
                            startActivity(intent);
                        }   else {
                            Toast.makeText(getApplicationContext(), "Error: " + task.getException(), Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }
}
