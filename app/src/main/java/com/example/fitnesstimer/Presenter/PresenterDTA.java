package com.example.fitnesstimer.Presenter;

import android.text.TextUtils;
import android.util.Log;

import com.example.fitnesstimer.MainContract;
import com.example.fitnesstimer.common.Common;
import com.example.fitnesstimer.model.DetailTimer;
import com.example.fitnesstimer.model.StructTimer;

import java.util.ArrayList;
import java.util.List;

public class PresenterDTA implements MainContract.PresenterDTA {

    private static final String TAG = "PresenterDTA";
    private MainContract.ViewDTA viewDTA;
    private List<StructTimer> structTimerList = new ArrayList<>();

    public PresenterDTA(MainContract.ViewDTA viewDTA) {
        this.viewDTA = viewDTA;
        Log.d(TAG, "Constructor");
    }

    @Override
    public void onCreate() {
        structTimerList = new ArrayList<>();
    }

    @Override
    public void onDestroy() {

    }

    @Override
    public void calcTime(DetailTimer detailTimer) {
        structTimerList = new ArrayList<>();

        int preparation = stringToInt(detailTimer.getPreparation());
        int job = stringToInt(detailTimer.getJob());
        int rest = stringToInt(detailTimer.getRest());
        int repeat = stringToInt(detailTimer.getRepeat());
        int set = stringToInt(detailTimer.getSet());
        int restSet = stringToInt(detailTimer.getRestSet());
        int restAll = stringToInt(detailTimer.getRestAll());
        int result = 0;
        int repeats = 0;
        int sets = 0;
        int nextTime = 0;

        nextTime = preparation;
        structTimerList.add(new StructTimer("preparation", preparation, preparation));

        if (repeat == 1) {
            repeats = job + rest;

            nextTime = nextTime + job;
            structTimerList.add(new StructTimer("job", job, nextTime));

            nextTime = nextTime + rest;
            structTimerList.add(new StructTimer("rest", rest, nextTime));

            if (set == 1) {
                sets = 0;
            } else {
                sets = (job * (set - 1)) + (restSet * (set - 1));

                for (int j = 2; j <= set; j++) {

                    Log.d(TAG, "set " + j);

                    nextTime = nextTime + job;
                    structTimerList.add(new StructTimer("job", job, nextTime));

                    nextTime = nextTime + restSet;
                    structTimerList.add(new StructTimer("restset", restSet, nextTime));
                }
                //structTimerList.add(new StructTimer("job",job,preparation+job));
            }
        } else {
            repeats = (job * repeat) + (rest * (repeat - 1));
            if (set == 1) {
                sets = 0;
                for (int j = 1; j <= repeat; j++) {

                    nextTime = nextTime + job;
                    structTimerList.add(new StructTimer("job", job, nextTime));

                    if (repeat != j) {
                        nextTime = nextTime + rest;
                        structTimerList.add(new StructTimer("rest", rest, nextTime));
                    }
                }
            } else {
                sets = (repeats * (set - 1)) + (restSet * (set - 1));
                for (int i = 1; i <= set; i++) {
                    for (int j = 1; j <= repeat; j++) {

                        nextTime = nextTime + job;
                        structTimerList.add(new StructTimer("job", job, nextTime));

                        if (repeat != j) {
                            nextTime = nextTime + rest;
                            structTimerList.add(new StructTimer("rest", rest, nextTime));
                        }
                    }
                    if (set != i) {
                        nextTime = nextTime + restSet;
                        structTimerList.add(new StructTimer("restset", restSet, nextTime));
                    }
                }
            }
        }
        nextTime = nextTime + restAll;
        structTimerList.add(new StructTimer("restall", restAll, nextTime));

        result = repeats + sets + preparation + restAll;

        int min = 0;
        int sec = 0;
        int resint = 0;
        min = result / 60;
        sec = result - (min * 60);
        viewDTA.onResultTime(String.format("%02d:%02d", min, sec));

        Log.d(TAG, "result = " + result + " repeats= " + repeats + " sets= " + sets);
        for (int j = 0; j <= structTimerList.size() - 1; j++) {
            Log.d(TAG, structTimerList.get(j).getNameStep() + " = " + structTimerList.get(j).getNewStep());
        }

    }

    @Override
    public boolean setNameError(CharSequence s) {
        boolean flError = !TextUtils.isEmpty(s) && s.length() > 0 && s.length() < 15;
        if (!flError) {
            viewDTA.onSetErroretName(Common.ERROR);
        }
        return flError;
    }

    @Override
    public boolean setPreparationError(CharSequence s) {
        boolean flError = !TextUtils.isEmpty(s) && s.length() > 0;
        if (!flError) {
            viewDTA.onSetErroretPreparation(Common.ERROR);
        }
        return flError;
    }

    @Override
    public boolean setJobError(CharSequence s) {
        boolean flError = !TextUtils.isEmpty(s) && s.length() > 0;
        if (!flError) {
            viewDTA.onSetErroretJob(Common.ERROR);
        }
        return flError;
    }

    @Override
    public boolean setRestError(CharSequence s) {
        boolean flError = !TextUtils.isEmpty(s) && s.length() > 0;
        if (!flError) {
            viewDTA.onSetErroretRest(Common.ERROR);
        }
        return flError;
    }

    @Override
    public boolean setRepeatError(CharSequence s) {
        boolean flError = !TextUtils.isEmpty(s) && s.length() > 0;
        if (!flError) {
            viewDTA.onSetErroretRepeat(Common.ERROR);
        }
        return flError;
    }

    @Override
    public boolean setSetError(CharSequence s) {
        boolean flError = !TextUtils.isEmpty(s) && s.length() > 0;
        if (!flError) {
            viewDTA.onSetErroretSet(Common.ERROR);
        }
        return flError;
    }

    @Override
    public boolean setRestSetError(CharSequence s) {
        boolean flError = !TextUtils.isEmpty(s) && s.length() > 0;
        if (!flError) {
            viewDTA.onSetErroretRestSet(Common.ERROR);
        }
        return flError;
    }

    @Override
    public boolean setRestAllError(CharSequence s) {
        boolean flError = !TextUtils.isEmpty(s) && s.length() > 0;
        if (!flError) {
            viewDTA.onSetErroretRestAll(Common.ERROR);
        }
        return flError;
    }

    @Override
    public ArrayList<String> getNameStepArrayList() {
        ArrayList<String> nameStepArrayList=new ArrayList<>();
        for (int j = 0; j <= structTimerList.size() - 1; j++) {
            nameStepArrayList.add(structTimerList.get(j).getNameStep());
        }
        return nameStepArrayList;
    }

    @Override
    public ArrayList<Integer> getNamberStepArrayList() {
        ArrayList<Integer> namberStepArrayList=new ArrayList<>();
        for (int j = 0; j <= structTimerList.size() - 1; j++) {
            namberStepArrayList.add(structTimerList.get(j).getNamberStep());
        }
        return namberStepArrayList;
    }

    @Override
    public ArrayList<Integer> getNewSteppArrayList() {
        ArrayList<Integer> newSteppArrayList=new ArrayList<>();
        for (int j = 0; j <= structTimerList.size() - 1; j++) {
            newSteppArrayList.add(structTimerList.get(j).getNewStep());
        }
        return newSteppArrayList;
    }


    private int stringToInt(String s) {
        int res = 0;
        if (s != null) {
            res = Integer.parseInt(s);
        }
        return res;
    }
}
