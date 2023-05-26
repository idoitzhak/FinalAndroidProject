package com.example.finalandroidproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import org.checkerframework.checker.nullness.qual.NonNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button add_product_btn, get_all_products_btn,
           get_product_by_para_btn, delete_item_by_para_btn, listen_by_country_btn;
    private ListView products_lv;
    private EditText countryName;

    public static ArrayList<Country> countries;

    //FIREBASE-FIRESTORE
    private FirebaseFirestore database = FirebaseFirestore.getInstance();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        get_all_products_btn = findViewById(R.id.get_all_products_btn);
        add_product_btn = findViewById(R.id.add_product_btn);
        get_product_by_para_btn = findViewById(R.id.get_product_by_para_btn);
        delete_item_by_para_btn = findViewById(R.id.delete_item_by_para_btn);
        listen_by_country_btn = findViewById(R.id.listen_by_country_btn);

        countryName = findViewById(R.id.countryName);


        get_all_products_btn.setOnClickListener(this);
        add_product_btn.setOnClickListener(this);
        get_product_by_para_btn.setOnClickListener(this);
        delete_item_by_para_btn.setOnClickListener(this);
        listen_by_country_btn.setOnClickListener(this);


    }
    @Override
    public void onClick(View view){
        switch (view.getId()) {
            case R.id.get_all_products_btn:
                getAllProducts();
                break;
            case R.id.add_product_btn:
                createNewProduct();
                break;
            case R.id.get_product_by_para_btn:
                getProductByValue();
                break;
            case R.id.delete_item_by_para_btn:
                deleteItemValue();
                break;
            case R.id.listen_by_country_btn:
                PlaySong(countryName.getText().toString().trim(), "Country");
                break;
        }
    }
    private void createNewProduct() {
        Intent intent = new Intent(getApplicationContext(), CreateSong.class);
        startActivity(intent);
    }
    private void getAllProducts() {
        database
                .collection("Eurovision2023")
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

    private void getProductByValue() {

        Intent intent = new Intent(getApplicationContext(), SearchItem.class);
        startActivity(intent);
    }
    private void deleteItemValue() {

        Intent intent = new Intent(getApplicationContext(), DeleteItem.class);
        startActivity(intent);
    }
    public void PlaySong(String para, String searchFrom) {
        database.collection("Eurovision2023")
                .whereEqualTo(searchFrom, para)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        countries = new ArrayList<Country>();

                        if (task.isSuccessful()) {
                            countries = new ArrayList<Country>();

                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Country count = document.toObject(Country.class);

                                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(count.getLink()));
                                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                intent.setPackage("com.google.android.youtube");
                                startActivity(intent);
                            }

                        } else {
                            Toast.makeText(getApplicationContext(), "Error: " + task.getException(), Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }
}