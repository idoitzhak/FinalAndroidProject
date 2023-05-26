package com.example.finalandroidproject;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import org.checkerframework.checker.nullness.qual.NonNull;

import java.util.ArrayList;

public class DeleteItem extends AppCompatActivity implements View.OnClickListener{

    private EditText deleteCountry,deleteSinger,deleteSong;
    private Button deleteCountry_btn,deleteSinger_btn,deleteSong_btn;
    public static ArrayList<Country> countries;

    //FIREBASE-FIRESTORE
    private FirebaseFirestore database = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.delete_item);

        deleteCountry_btn = findViewById(R.id.deleteCountry_btn);
        deleteSinger_btn = findViewById(R.id.deleteSinger_btn);
        deleteSong_btn = findViewById(R.id.deleteSong_btn);

        deleteCountry = findViewById(R.id.deleteCountry);
        deleteSinger = findViewById(R.id.deleteSinger);
        deleteSong = findViewById(R.id.deleteSong);

        deleteCountry_btn.setOnClickListener(this);
        deleteSinger_btn.setOnClickListener(this);
        deleteSong_btn.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.deleteCountry_btn:
                deleteProductById(deleteCountry.getText().toString().trim(), "Country");
                break;
            case R.id.deleteSong_btn:
                deleteProductById(deleteSong.getText().toString().trim(), "Song");
                break;
            case R.id.deleteSinger_btn:
                deleteProductById(deleteSinger.getText().toString().trim(), "Singer");
                break;
        }

    }

    private void deleteProductById(String para, String dbPara) {

        CollectionReference usersRef = database.collection("Eurovision2023");
        Query query = usersRef.whereEqualTo(dbPara,para);
        query.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        // Delete the document
                        document.getReference().delete();
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "Error: " + task.getException(), Toast.LENGTH_LONG).show();
                }
            }
        });
    }


}
