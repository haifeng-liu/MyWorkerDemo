package com.example.myworkerdemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.work.BackoffPolicy;
import androidx.work.Constraints;
import androidx.work.Data;
import androidx.work.NetworkType;
import androidx.work.OneTimeWorkRequest;
import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkInfo;
import androidx.work.WorkManager;
import androidx.work.WorkRequest;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.os.WorkSource;
import android.provider.SyncStateContract;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {
//
//    private String name = "name";
//    private int age = 18;
//    Data data = new Data.Builder().putString("name", name).putInt("age", age).build();
//    Constraints constraints = new Constraints.Builder()
////            .setRequiredNetworkType(NetworkType.CONNECTED)//联网状态
//            .setRequiresBatteryNotLow(true)//低电量不操作
//            .setRequiresCharging(false)//充电时候才开始
//            .setRequiresStorageNotLow(true)//存储空间不能太小
//            .build();
//
//    private WorkRequest workRequest=new OneTimeWorkRequest.Builder(MyWorker.class)
//            .setConstraints(constraints)
//            .setInputData(data)
//            .build();
//
//    private WorkRequest request=new PeriodicWorkRequest.Builder(MyWorker.class,15, TimeUnit.MINUTES)
//            .setBackoffCriteria(BackoffPolicy.LINEAR,20,TimeUnit.MINUTES).build();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }


    public void worker(){
        //        WorkManager.getInstance().enqueue(workRequest);
//
//         WorkManager.getInstance().getWorkInfoByIdLiveData(workRequest.getId())
//                 .observe(this, new Observer<WorkInfo>() {
//                     @Override
//                     public void onChanged(WorkInfo workInfo) {
//                         if (workInfo.getState()== WorkInfo.State.SUCCEEDED){
//                             Data data=workInfo.getOutputData();
//                             String da=data.getString("success");
//                             int status=data.getInt("status",0);
//                             Log.i("tag","request:"+da+";   status:"+status);
//                         }
//                     }
//                 });
    }

}