package com.example.dialogfragment;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements DialogFragment.OnPositiveClickListener,
        DialogFragment.OnNegativeClickListener, DialogFragment.OnNeutralClickListener {
    Button btn_show;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn_show = findViewById(R.id.btn_showDialog);
        btn_show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 DialogFragment fragment = DialogFragment.newInstance("Confirmation",
                                 "Are you sure",R.drawable.ic_lock);

                       fragment.show(getSupportFragmentManager(),null);





            }
        });
    }

    @Override
    public void onPositiveButtonClicked(String text) {
        Toast.makeText(this, "Yes clicked: "+text, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNegativeButtonClicked() {
        Toast.makeText(this, "No clicked", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNeutralButtonClicked() {
        Toast.makeText(this, "Cancel clicked", Toast.LENGTH_SHORT).show();
    }
}