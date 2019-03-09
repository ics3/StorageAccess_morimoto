package com.example.owner.storageaccess;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.InputStream;

import static android.app.Activity.RESULT_OK;

public class MainActivity extends AppCompatActivity {
    private static final int REQUEST_GALLERY = 0;
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageView = findViewById(R.id.imageViewBlank);
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(intent.ACTION_GET_CONTENT);
                startActivityForResult(intent, REQUEST_GALLERY);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == REQUEST_GALLERY && resultCode == RESULT_OK) {
            try{
                InputStream is = getContentResolver().openInputStream(data.getData());
                Bitmap img = BitmapFactory.decodeStream(is);
                is.close();

                imageView.setImageBitmap(img);
            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }
}