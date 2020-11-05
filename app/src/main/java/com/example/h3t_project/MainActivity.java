package com.example.h3t_project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.h3t_project.DAO.UserDAO;
import com.example.h3t_project.model.User;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private EditText editName;
    private EditText editPrice;
    private Button add;
    private TextView alert;
    private FirebaseFirestore firestore;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editName = findViewById(R.id.name);
        editPrice = findViewById(R.id.price);
      alert = findViewById(R.id.alert);



//        add = findViewById(R.id.add);
//        firestore  = FirebaseFirestore.getInstance();
//        final CollectionReference reference = firestore.collection("product");
//
//        add.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Map<String, Object> product = new HashMap<>();
//                product.put("name", editName.getText().toString());
//                product.put("price", editPrice.getText().toString());
//                reference.add(product).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
//                    @Override
//                    public void onSuccess(DocumentReference documentReference) {
//                        Toast.makeText(MainActivity.this,"Add Ok !", Toast.LENGTH_SHORT).show();
//                    }
//                }).addOnFailureListener(new OnFailureListener() {
//                    @Override
//                    public void onFailure(@NonNull Exception e) {
//                        Toast.makeText(MainActivity.this,"Add Failed ! ", Toast.LENGTH_SHORT).show();
//                    }
//                });
//            }
//        });
    }

  public void getData(View view){
    UserDAO userDAO = new UserDAO();
    User user;
    try {
      user = userDAO.checkLogin(editName.getText().toString(), editPrice.getText().toString());
      if(user != null){
//        Intent intent = new Intent(MainActivity.this, HomeActivity.class);
//        startActivity(intent);
        alert.setText("Connect OK!");
      } else {
        alert.setText("Email or Password is wrong!");
      }
    } catch (Exception e){
      e.printStackTrace();
      Log.i("messagee: ", "AAAAAA");
    }
  }
}
