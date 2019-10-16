package com.example.swiftpizza;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.FocusFinder;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

public class MainActivity extends AppCompatActivity {
private EditText value;
private     RadioGroup slice;

    Spinner spinner;
    int spinnerVal;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       spinner = (Spinner) findViewById(R.id.toppingSpinner);
// Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.toppings, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        spinner.setAdapter(adapter);

        slice= (RadioGroup)findViewById(R.id.slice);



    }
    public void confirmed(View view)
    {



        Intent i = new Intent(this,confirmation.class);

        value= findViewById (R.id.name);
        i.putExtra("name",value.getText().toString());

        value= findViewById (R.id.email);
        i.putExtra("email",value.getText().toString());
        value= findViewById (R.id.address);
        i.putExtra("address",value.getText().toString());
        value= findViewById (R.id.phone);
        i.putExtra("phone",value.getText().toString());



        CheckBox extra = (CheckBox)findViewById(R.id.extraCheese);
        i.putExtra("extraC",String.valueOf(extra.isChecked()));




        RadioButton sliceSelect=(RadioButton)findViewById(slice.getCheckedRadioButtonId());

        i.putExtra("size",sliceSelect.getText().toString());

        spinnerVal=spinner.getSelectedItemPosition();
        String string= (String) spinner.getItemAtPosition(spinnerVal);
        i.putExtra("topping",string);

        startActivity(i);
    }


}
