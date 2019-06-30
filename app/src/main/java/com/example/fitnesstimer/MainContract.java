package com.example.fitnesstimer;

import com.example.fitnesstimer.model.DetailTimer;
import com.example.fitnesstimer.model.StructTimer;

import java.util.ArrayList;
import java.util.List;

public interface MainContract {
    interface ViewMA {
        void onAuthResult(String message);

        void onSetEnabled(int setVis, Boolean spNameBaseSE,
                          Boolean edtLoginSE,
                          Boolean edtPasswordSE,
                          Boolean btnInSE);

        void onSetErrorAddrSer(String message);

        void onSetErrorLogin(String message);

        void onSetErrorPassword(String message);

        void onSetErrorBaseName(String message);
    }
    interface ViewDTA {
        void onResultTime(String message);
        void onSetErroretName(String msgError);
        void onSetErroretPreparation(String msgError);
        void onSetErroretJob(String msgError);
        void onSetErroretRest(String msgError);
        void onSetErroretRepeat(String msgError);
        void onSetErroretSet(String msgError);
        void onSetErroretRestSet(String msgError);
        void onSetErroretRestAll(String msgError);

    }
    interface PresenterDTA {

        void onCreate();

        void onDestroy();

        void calcTime(DetailTimer detailTimer);

        boolean setNameError(CharSequence s);
        boolean setPreparationError(CharSequence s);
        boolean setJobError(CharSequence s);
        boolean setRestError(CharSequence s);
        boolean setRepeatError(CharSequence s);
        boolean setSetError(CharSequence s);
        boolean setRestSetError(CharSequence s);
        boolean setRestAllError(CharSequence s);
        ArrayList<String> getNameStepArrayList();
        ArrayList<Integer> getNamberStepArrayList();
        ArrayList<Integer> getNewSteppArrayList();
    }

    interface Repository {
        //на перспективу
    }
}
