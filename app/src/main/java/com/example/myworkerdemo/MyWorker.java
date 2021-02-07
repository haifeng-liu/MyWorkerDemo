package com.example.myworkerdemo;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.work.Data;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

import java.util.Date;

/**
 * 创建人：Liuhaifeng
 * 时间：2021/1/26
 * 描述：
 */
public class MyWorker extends Worker {
    public MyWorker(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
    }

    @NonNull
    @Override
    public Result doWork() {
        Data data=getInputData();
         String name=data.getString("name");
         int age=data.getInt("age",0);

        Log.i("tag","name:"+name+";age:"+age);

         Data out=new Data.Builder()
                 .putString("success","hhhhh")
                 .putInt("status",200)
                 .build();
        return Result.success(out);

    }
}
