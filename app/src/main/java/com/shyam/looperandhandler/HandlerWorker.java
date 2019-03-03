package com.shyam.looperandhandler;

import android.os.Handler;
import android.os.HandlerThread;

public class HandlerWorker extends HandlerThread {


    private Handler handler;
    private Handler handler2;
    private static final String TAG = "HandlerWorker";

    HandlerWorker() {
        super(TAG);
        start();
        handler = new Handler(getLooper());
        handler2 = new Handler(getLooper());
    }

    HandlerWorker execute(Runnable task) {
        handler.post(task);
        return this;
    }

    HandlerWorker executeViaOther(Runnable task) {
        handler2.post(task);
        return this;
    }

    @Override
    public void run() {
        super.run();
    }
}
