package com.example.swiftpizza;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class confirmation extends AppCompatActivity {
TextView orderText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmation);

        Bundle extras = getIntent().getExtras();
        //System.out.print(extras.getString("name"));
       String order;
       float cost=0;

       orderText=findViewById(R.id.order);
       order="\nName : "+extras.getString("name");
       order+="\nEmail : "+extras.getString("email");
       order+="\nPhone : "+extras.getString("phone");
       order+="\nAddress : "+extras.getString("address");



       order+="\n\nSize : "+extras.getString("size");
        String value = extras.getString("size");
        String[] tokens = value.split("[$]");
        String[] tokens2= tokens[1].split("[)]");
        cost+= Float.parseFloat(tokens2[0]);


        order+="\nExtra Cheese : "+extras.getString("extraC");



        order+="\nTopping : "+extras.getString("topping");

        value = extras.getString("topping");
        tokens = value.split("[$]");
        tokens2= tokens[1].split("[)]");
        cost+= Float.parseFloat(tokens2[0]);

        order+="\n\nCost : "+cost;
        order+="\n";

        orderText.setText(order);




    }
    public void newOrder(View view)
    {
        Intent i2 =new Intent(this, MainActivity.class);
        startActivity(i2);


    }
}
