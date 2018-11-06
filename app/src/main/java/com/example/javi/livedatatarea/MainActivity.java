package com.example.javi.livedatatarea;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Observer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.*;
import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    private Integer mCount = 0;
    private Boolean mCasa;
    private Boolean mAprobar;
    private MutableLiveData<Integer> mLiveCount = new MutableLiveData<>();
    private MutableLiveData<Boolean> mLiveCasa = new MutableLiveData<>();
    private MutableLiveData<Boolean> mLiveAprobar = new MutableLiveData<>();
    private MutableLiveData<Integer> mLiveAños = new MutableLiveData<>();
    @BindView(R.id.switch1) Switch sUno;
    @BindView(R.id.trabajoCasa) TextView txvCasa;
    @BindView(R.id.switch2) Switch sDos;
    @BindView(R.id.txvAprobar) TextView txvAprobar;
    @BindView(R.id.etxTengo) EditText etxTengo;
    @BindView(R.id.txvTengo) TextView txvTengo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        configureObserversCasa();
        configureObserversAprobar();
        configureObserversTengo();
        onClickCasa();
        onClickAprobar();
        añosWatcher();
    }

    private void configureObserversCasa(){
        mLiveCasa.observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean bool) {
                if (bool == true){
                    txvCasa.setText("Si trabajo en casa");
                }else if (bool == false){
                    txvCasa.setText("No trabajo en casa");
                }
            }
        });
    }
    private void configureObserversAprobar(){
        mLiveAprobar.observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(@Nullable Boolean bool) {
                if (bool == true){
                    txvAprobar.setText("Si voy aprobar");
                }else if (bool == false){
                    txvAprobar.setText("No voy aprobar");
                }
            }
        });
    }
    private void configureObserversTengo(){
        mLiveAños.observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(@Nullable Integer s) {
                if (!s.equals(null)){
                    txvTengo.setText("Tengo " + s + " años");
                }
            }
        });
    }
    public void onClickCasa(){
        sUno.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked == true){
                    mLiveCasa.postValue(true);
                }else{
                    mLiveCasa.postValue(false);
                }
            }
        });
    }
    public void onClickAprobar(){
        sDos.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked == true){
                    mLiveAprobar.postValue(true);
                }else{
                    mLiveAprobar.postValue(false);
                }
            }
        });
    }
    public void añosWatcher() {
        etxTengo.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                try{
                    if(s.equals(null)) {
                        mLiveAños.postValue(Integer.valueOf(s.toString()));
                    }else{
                        mLiveAños.postValue(0);
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                try{
                    if(s!=null) {
                        mLiveAños.postValue(Integer.valueOf(s.toString()));
                    }else{
                        mLiveAños.postValue(0);
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                try{
                    if(s!=null) {
                        mLiveAños.postValue(Integer.valueOf(s.toString()));
                    }else{
                        mLiveAños.postValue(0);
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });
    }
    public void onClickIncrementa(View view){
        mLiveCount.postValue(1);
    }
}
