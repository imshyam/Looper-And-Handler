package com.shyam.looperandhandler;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.shyam.looperandhandler.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

//    private MyWorker worker;
    private HandlerWorker handlerWorker;
    private ActivityMainBinding binding;
    private Handler handler = new Handler(Looper.getMainLooper()) {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            binding.setText((String) msg.obj);
            Toast.makeText(MainActivity.this, (String) msg.obj, Toast.LENGTH_SHORT).show();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        binding.setText("This is bound");

//        worker = new MyWorker();

        handlerWorker = new HandlerWorker();
        handlerWorker.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Message msg = new Message();
                msg.obj = "Task 1 Complete";
                handler.sendMessage(msg);
            }
        })
        .execute(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Message msg = new Message();
                msg.obj = "Task 2 Complete";
                handler.sendMessage(msg);
            }
        })
        .execute(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Message msg = new Message();
                msg.obj = "Task 3 Complete";
                handler.sendMessage(msg);
            }
        })
        .executeViaOther(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(800);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Message msg = new Message();
                msg.obj = "Task 1 Complete, From Other Handler";
                handler.sendMessage(msg);
            }
        })
        .executeViaOther(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1600);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Message msg = new Message();
                msg.obj = "Task 2 Complete, From Other Handler";
                handler.sendMessage(msg);
            }
        });



    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
//        worker.quit();
        handlerWorker.quit();
    }
}
