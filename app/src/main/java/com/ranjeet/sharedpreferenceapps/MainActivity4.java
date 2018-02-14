package com.ranjeet.sharedpreferenceapps;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class MainActivity4 extends AppCompatActivity {
    Button btnStoreImage, btnRetrieveImage;
    ImageView ivResult;
    SharedPreferences sharedPreferences;
    String encodedImage;
    static final int REQUEST_CODE_TO_BROWSE_IMAGE = 1;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Views
        btnStoreImage = (Button) findViewById(R.id.btnStoreImage);
        btnRetrieveImage = (Button) findViewById(R.id.btnRetrieveImage);
        ivResult = (ImageView) findViewById(R.id.ivResult);
        //initializing shardPreferences
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        //setting onClickListener to Open the Gallery and getting the Image for Storing
        btnStoreImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                intent.addCategory(Intent.CATEGORY_OPENABLE);
                startActivityForResult(intent,
                        REQUEST_CODE_TO_BROWSE_IMAGE);//Storing Image in SharedPreferences
            }
        });
        //setting onClickListener to Retreive the Iage from SharedPreferences
        btnRetrieveImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Retrieving Image form SharedPreferences
                encodedImage = sharedPreferences.getString("my_image", "");
                if (!encodedImage.equalsIgnoreCase("")) {
                    //Decoding the Image and display in ImageView
                    byte[] b = Base64.decode(encodedImage, Base64.DEFAULT);
                    Bitmap bitmap = BitmapFactory.decodeByteArray(b, 0, b.length);
                    ivResult.setImageBitmap(bitmap);
                }
                else
                {
                    Toast.makeText(getApplicationContext(),"You don't have Image in SharedPreferences!",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    public void onActivityResult(int requestCode, int resultCode,
                                 Intent intent) {
        if (resultCode == Activity.RESULT_OK) {
            InputStream stream;
            try {
                stream = getContentResolver().openInputStream(intent.getData());
                // Encoding Image into Base64
                Bitmap realImage = BitmapFactory.decodeStream(stream);
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                realImage.compress(Bitmap.CompressFormat.JPEG, 100, baos);
                byte[] b = baos.toByteArray();
                //Converting Base64 into String to Store in SharedPreferences
                encodedImage = Base64.encodeToString(b, Base64.DEFAULT);
                //NOw storing String to SharedPreferences
                sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("my_image", encodedImage);
                editor.commit();
                Toast.makeText(getApplicationContext(),"Image has been Stored!",Toast.LENGTH_LONG).show();
            } catch (FileNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }
}
