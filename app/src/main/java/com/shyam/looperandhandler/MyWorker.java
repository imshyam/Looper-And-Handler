package com.shyam.looperandhandler;

import android.util.Log;

import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicBoolean;

public class MyWorker extends Thread {

    private static final String TAG = "MyWorker";
    private AtomicBoolean alive = new AtomicBoolean(true);
    private ConcurrentLinkedQueue<Runnable> taskQueue = new ConcurrentLinkedQueue<>();

    public MyWorker() {
        super(TAG);
        start();
    }

    @Override
    public void run() {
        while (alive.get()) {
            Runnable task = taskQueue.poll();
            if (task != null) {
                task.run();
            }
        }
        Log.i(TAG, "run: Worker Terminated.");
    }

    MyWorker execute(Runnable task) {
        taskQueue.add(task);
        return this;
    }

    public void quit() {
        alive.set(false) ;
    }
}
