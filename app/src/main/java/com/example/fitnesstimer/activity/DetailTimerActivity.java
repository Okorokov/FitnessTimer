package com.example.fitnesstimer.activity;

import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.fitnesstimer.MainContract;
import com.example.fitnesstimer.Presenter.PresenterDTA;
import com.example.fitnesstimer.R;
import com.example.fitnesstimer.common.Common;
import com.example.fitnesstimer.model.DetailTimer;
import com.example.fitnesstimer.service.ServiceTimer;
import com.rengwuxian.materialedittext.MaterialEditText;

public class DetailTimerActivity extends AppCompatActivity implements MainContract.ViewDTA {

    private ImageButton ibClose, ibSave;
    private TextView tvTime;
    private MaterialEditText etName, etPreparation, etJob, etRest, etRepeat, etSet, etRestSet, etRestAll;
    private MainContract.PresenterDTA presenterDTA;
    private DetailTimer detailTimer;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_timer);

        init();

        presenterDTA = new PresenterDTA(this);
        detailTimer = new DetailTimer();

        detailTimer.setPreparation(String.valueOf(etPreparation.getEditableText()));
        detailTimer.setJob(String.valueOf(etJob.getEditableText()));
        detailTimer.setRest(String.valueOf(etRest.getEditableText()));
        detailTimer.setRepeat(String.valueOf(etRepeat.getEditableText()));
        detailTimer.setSet(String.valueOf(etSet.getEditableText()));
        detailTimer.setRestSet(String.valueOf(etRestSet.getEditableText()));
        detailTimer.setRestAll(String.valueOf(etRestAll.getEditableText()));
        presenterDTA.calcTime(detailTimer);

        ibClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(DetailTimerActivity.this, ServiceTimer.class);
                stopService(intent);
            }
        });
        ibSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(DetailTimerActivity.class.getName(),"ibSave click");
                intent = new Intent(DetailTimerActivity.this, ServiceTimer.class);
                intent.putStringArrayListExtra(Common.ARRAY_NAME_STEP,presenterDTA.getNameStepArrayList());
                intent.putIntegerArrayListExtra(Common.ARRAY_NAMBER_STEP,presenterDTA.getNamberStepArrayList());
                intent.putIntegerArrayListExtra(Common.ARRAY_NEW_STEP,presenterDTA.getNewSteppArrayList());
                intent.putExtra(Common.KEY,"outactivity");

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    startForegroundService(intent);
                }else{
                    startService(intent);
                }
            }
        });
        etName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (presenterDTA.setNameError(s)) {
                    detailTimer.setName(String.valueOf(s));
                }
            }
        });
        etPreparation.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (presenterDTA.setPreparationError(s)) {
                    detailTimer.setPreparation(String.valueOf(s));
                    presenterDTA.calcTime(detailTimer);
                }
            }
        });
        etJob.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (presenterDTA.setJobError(s)) {
                    detailTimer.setJob(String.valueOf(s));
                    presenterDTA.calcTime(detailTimer);
                }
            }
        });
        etRest.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (presenterDTA.setRestError(s)) {
                    detailTimer.setRest(String.valueOf(s));
                    presenterDTA.calcTime(detailTimer);
                }
            }
        });
        etRepeat.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (presenterDTA.setRepeatError(s)) {
                    detailTimer.setRepeat(String.valueOf(s));
                    presenterDTA.calcTime(detailTimer);
                }
            }
        });
        etSet.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (presenterDTA.setSetError(s)) {
                    detailTimer.setSet(String.valueOf(s));
                    presenterDTA.calcTime(detailTimer);
                }
            }
        });
        etRestSet.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (presenterDTA.setRestSetError(s)) {
                    detailTimer.setRestSet(String.valueOf(s));
                    presenterDTA.calcTime(detailTimer);
                }
            }
        });
        etRestAll.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (presenterDTA.setRestAllError(s)) {
                    detailTimer.setRestAll(String.valueOf(s));
                    presenterDTA.calcTime(detailTimer);
                }
            }
        });
    }

    private void init() {
        ibClose = (ImageButton) findViewById(R.id.ibClose);
        ibSave = (ImageButton) findViewById(R.id.ibSave);

        tvTime = (TextView) findViewById(R.id.tvTime);

        etName = (MaterialEditText) findViewById(R.id.etName);
        etPreparation = (MaterialEditText) findViewById(R.id.etPreparation);
        etJob = (MaterialEditText) findViewById(R.id.etJob);
        etRest = (MaterialEditText) findViewById(R.id.etRest);
        etRepeat = (MaterialEditText) findViewById(R.id.etRepeat);
        etSet = (MaterialEditText) findViewById(R.id.etSet);
        etRestSet = (MaterialEditText) findViewById(R.id.etRestSet);
        etRestAll = (MaterialEditText) findViewById(R.id.etRestAll);

    }

    @Override
    public void onResultTime(String message) {
        tvTime.setText(message);
    }

    @Override
    public void onSetErroretName(String msgError) {
        etName.setError(msgError);
    }

    @Override
    public void onSetErroretPreparation(String msgError) {
        etPreparation.setError(msgError);
    }

    @Override
    public void onSetErroretJob(String msgError) {
        etJob.setError(msgError);
    }

    @Override
    public void onSetErroretRest(String msgError) {
        etRest.setError(msgError);
    }

    @Override
    public void onSetErroretRepeat(String msgError) {
        etRepeat.setError(msgError);
    }

    @Override
    public void onSetErroretSet(String msgError) {
        etSet.setError(msgError);
    }

    @Override
    public void onSetErroretRestSet(String msgError) {
        etRestSet.setError(msgError);
    }

    @Override
    public void onSetErroretRestAll(String msgError) {
        etRestAll.setError(msgError);
    }
}
