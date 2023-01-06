package com.dedrick.utilities;
/*
 *  author: Derrick
 *  Time: 2019/12/9 16:18
 */

import android.app.Activity;

//import com.verifone.demo.emv_fbn.MenuActivity;

import java.util.ArrayList;
import java.util.List;

public class ActivityCollector  {

    public static List<Activity> activityList = new ArrayList<>();

    public static void addActivity (Activity activity){
        activityList.add(activity);
    }

    public static void removeActivity (Activity activity){
        activityList.remove(activity);
    }

    public static void finishAll(){
        for (Activity activity : activityList){
            if (!activity.isFinishing()){
                activity.finish();
            }
        }
    }

    public static void finishAllTransActivity(){
        for (Activity activity : activityList){
           /* if (!activity.isFinishing() && !(activity instanceof MenuActivity)){
                activity.finish();
            }*/
        }
    }



}
