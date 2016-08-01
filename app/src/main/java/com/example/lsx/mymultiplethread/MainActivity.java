package com.example.lsx.mymultiplethread;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private ImageView mImageView;
    private Button mLoadImageButton;
    private Button mToastButton;
    private ProgressBar mProgressBar;

    private Handler mHandler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case 0:
                    mProgressBar.setVisibility(View.VISIBLE);
                    break;
                case 1:
                    mProgressBar.setProgress((int)msg.obj);
                    break;
            }

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mImageView= (ImageView) findViewById(R.id.activity_main_image_view);
        mLoadImageButton= (Button) findViewById(R.id.activity_main_imageview_button);
        mToastButton= (Button) findViewById(R.id.activity_main_toast_button);
        mProgressBar= (ProgressBar) findViewById(R.id.activity_main_progress_bar);

        mLoadImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               new Thread(
                       new Runnable() {
                           @Override
                           public void run() {
                               Message msg=new Message();
                               msg.what=0;//表示显示progressbar
                               mHandler.sendMessage(msg);

                               for (int i = 1; i <11 ; i++) {
                                   sleep();
                                   Message msg1=new Message();
                                   msg1.what=1;//表示progressbar向前走
                                   msg1.obj=i*10;
                                   mHandler.sendMessage(msg1);


                               }
                           }

                           private void sleep() {
                               try {
                                   Thread.sleep(500);
                               } catch (InterruptedException e) {
                                   e.printStackTrace();
                               }
                           }
                       }
               ).start();
            }
        });

        mToastButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this,"XXXXXXXXXXX",Toast.LENGTH_SHORT).show();
            }
        });
    }


   /* class LoadImageTask extends AsyncTask<Void,Integer,Bitmap>{

        @Override
        protected void onPreExecute() {
            mProgressBar.setVisibility(View.VISIBLE);
        }

        @Override
        protected Bitmap doInBackground(Void... params) {

            for (int i = 1; i <11 ; i++) {
                sleep();
                publishProgress(i*10);
            }

            Bitmap bitmap= BitmapFactory.decodeResource(getResources(),R.drawable.ic_launcher);
            return bitmap;
        }

        private void sleep() {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            mProgressBar.setProgress(values[0]);
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            mImageView.setImageBitmap(bitmap);
            mProgressBar.setVisibility(View.INVISIBLE);
        }
    }*/

}
