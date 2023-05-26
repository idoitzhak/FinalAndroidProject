package com.example.finalandroidproject;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import org.checkerframework.checker.nullness.qual.NonNull;

import java.util.HashMap;
import java.util.Map;

public class CreateSong extends AppCompatActivity implements View.OnClickListener {

    private EditText singerName,countryName,songName,points,position,link, image;
    private Button create_song_btn;

    private FirebaseFirestore database = FirebaseFirestore.getInstance();
    public static final String KEY_COUNTRY_COUNTRY = "Country";
    public static final String KEY_COUNTRY_LINK = "Link";
    public static final String KEY_COUNTRY_POINTS = "Points";
    public static final String KEY_COUNTRY_POSITION = "Position";
    public static final String KEY_COUNTRY_SINGER = "Singer";
    public static final String KEY_COUNTRY_SONG = "Song";
    public static final String KEY_COUNTRY_IMAGE = "Image";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_song);

        create_song_btn = findViewById(R.id.create_song_btn);

        singerName = findViewById(R.id.singerName);
        countryName = findViewById(R.id.countryName);
        points = findViewById(R.id.points);
        position = findViewById(R.id.position);
        songName = findViewById(R.id.songName);
        link = findViewById(R.id.link);
        image = findViewById(R.id.imageLink);

        create_song_btn.setOnClickListener(this);
    }
    @Override
    public void onClick(View view) {
        createNewProduct();
    }
    private void createNewProduct() {
        //GET PARAMETERS
        String pCountry = countryName.getText().toString().trim();
        String pSinger = singerName.getText().toString().trim();
        String pSong = songName.getText().toString().trim();
        String pPoints = points.getText().toString().trim();
        String pPosition = position.getText().toString().trim();
        String pLink = link.getText().toString().trim();
        String pImage = image.getText().toString().trim();

        //MAPPING
        Map<String, Object> data = new HashMap<>();
        data.put(KEY_COUNTRY_COUNTRY, pCountry);
        data.put(KEY_COUNTRY_SINGER, pSinger);
        data.put(KEY_COUNTRY_SONG, pSong);
        data.put(KEY_COUNTRY_SONG, pSong);
        data.put(KEY_COUNTRY_POINTS, pPoints);
        data.put(KEY_COUNTRY_POSITION, pPosition);
        data.put(KEY_COUNTRY_LINK, pLink);
        data.put(KEY_COUNTRY_IMAGE, pImage);


        database
                .collection("Eurovision2023")
                .add(data)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Toast.makeText(getApplicationContext(), "Country created", Toast.LENGTH_LONG).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getApplicationContext(), "Error: " + e, Toast.LENGTH_LONG).show();
                    }
                });
    }
}
