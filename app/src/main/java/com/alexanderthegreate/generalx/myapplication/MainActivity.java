package com.alexanderthegreate.generalx.myapplication;

import android.os.Bundle;
import android.app.Activity;
import android.os.Environment;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.support.v4.app.NavUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;


import android.content.ContentValues;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.net.Uri;

import android.provider.MediaStore.Images;
import android.provider.MediaStore.Images.Media;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.alexanderthegreate.generalx.myapplication.R;


public class MainActivity extends Activity {
    Button send;
    Bitmap thumbnail;
    File pic;
    String address, subject, emailtext;
    protected static final int CAMERA_PIC_REQUEST = 0;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        send = (Button) findViewById(R.id.emailsendbutton);
//        address=(EditText) findViewById(R.id.emailaddress);
//        subject=(EditText) findViewById(R.id.emailsubject);
//        emailtext=(EditText) findViewById(R.id.emailtext);

        address = "antonis.zalonis@gmail.com";
        subject = "Photo frm Android";
        emailtext = "This is a photo from my android app";

        Button camera = (Button) findViewById(R.id.button1);
        camera.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(cameraIntent, CAMERA_PIC_REQUEST);

            }
        });

        send.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {

                Intent i = new Intent(Intent.ACTION_SEND);
                i.putExtra(Intent.EXTRA_EMAIL, new String[]{"antonis.zalonis@gmail.com"});
                i.putExtra(Intent.EXTRA_SUBJECT, "Photo frm Android");
                //i.putExtra(Intent.EXTRA_TEXT,subject);
                //Log.d("URI@!@#!#!@##!", Uri.fromFile(pic).toString() + "   " + pic.exists());
                i.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(pic));

                i.setType("image/png");
                startActivity(Intent.createChooser(i, "Share you on the jobing"));
            }
        });


    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CAMERA_PIC_REQUEST) {
            thumbnail = (Bitmap) data.getExtras().get("data");
            ImageView image = (ImageView) findViewById(R.id.imageView1);
            image.setImageBitmap(thumbnail);


            try {
                File root = Environment.getExternalStorageDirectory();
                if (root.canWrite()) {
                    pic = new File(root, "pic.png");
                    FileOutputStream out = new FileOutputStream(pic);
                    thumbnail.compress(CompressFormat.PNG, 100, out);
                    out.flush();
                    out.close();
                }
            } catch (IOException e) {
                Log.e("BROKEN", "Could not write file " + e.getMessage());
            }

        }
    }
}