package com.ranjeet.sharedpreferenceapps;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

public class MainActivity1 extends AppCompatActivity implements View.OnClickListener {
    private Person person;
    private EditText mName;
    private EditText mAge;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main1);
        mName = (EditText) findViewById(R.id.name_editText);
        mAge = (EditText) findViewById(R.id.age_editText);
        findViewById(R.id.save_preference_button).setOnClickListener(this);
        findViewById(R.id.save_default_preference_button).setOnClickListener(this);
        findViewById(R.id.restore_preference_button).setOnClickListener(this);
        findViewById(R.id.restore_default_preference_button).setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.save_preference_button:
                Utils.saveSharedPreferences(
                        this,
                        new Person(mName.getText().toString(), Integer.valueOf(mAge.getText().toString())));
                break;
            case R.id.save_default_preference_button:
                Utils.saveDefaultSharedPreferences(
                        this,
                        new Person(mName.getText().toString(), Integer.valueOf(mAge.getText().toString())));
                break;
            case R.id.restore_preference_button:
                person = Utils.restoreSharedPreferences(this);
                mName.setText(person.getName());
                mAge.setText(String.valueOf(person.getAge()));
                break;
            case R.id.restore_default_preference_button:
                person = Utils.restoreDefaultSharedPreferences(this);
                mName.setText(person.getName());
                mAge.setText(String.valueOf(person.getAge()));
                break;
        }
    }
}