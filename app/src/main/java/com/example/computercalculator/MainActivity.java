//Assignment Number 3
//File Name: Computer Calculator
//Susama Saha, Priyanka Mehta, Krishna Phuyel

package com.example.computercalculator;

import android.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText userBudget;
    TextView currentTip;
    SeekBar seekBarCurrent;
    TextView priceValue;
    RadioGroup memoryRadioGrp;
    RadioGroup storageRadioGrp;
    CheckBox mouseChk;
    CheckBox flashDriveChk;
    CheckBox clngPadChk;
    CheckBox crngCaseChk;
    Switch   deliveryOption;
    Button calButton;
    TextView budgetStatus;
    Button resetBtn;
    RadioButton radio2GB;
    RadioButton radio4GB;
    RadioButton radio8GB;
    RadioButton radio16GB;
    RadioButton radio250GB;
    RadioButton radio500GB;
    RadioButton radio750GB;
    RadioButton radio1TB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setIcon(R.mipmap.ic_launcher);

        currentTip = (TextView) findViewById(R.id.text_currentTip);
        userBudget = (EditText) findViewById(R.id.entrBudget);
        seekBarCurrent = (SeekBar) findViewById(R.id.seekBar);
        priceValue = (TextView) findViewById(R.id.textView_price);
        userBudget = (EditText) findViewById(R.id.entrBudget);
        calButton = (Button) findViewById(R.id.button_calculate);
        deliveryOption = (Switch) findViewById(R.id.switch_delivery);
        budgetStatus = (TextView) findViewById(R.id.textView_budgetStatus);
        resetBtn = (Button) findViewById(R.id.button_reset);
        storageRadioGrp = (RadioGroup) findViewById(R.id.storage);
        radio2GB = (RadioButton)findViewById(R.id.radio2GB);
        radio4GB = (RadioButton)findViewById(R.id.radio4GB);
        radio8GB = (RadioButton)findViewById(R.id.radio8GB);
        radio16GB = (RadioButton)findViewById(R.id.radio16GB);
        radio250GB = (RadioButton)findViewById(R.id.radio_250GB);
        radio500GB = (RadioButton)findViewById(R.id.radio_500GB);
        radio750GB = (RadioButton)findViewById(R.id.radio_750GB);
        radio1TB = (RadioButton)findViewById(R.id.radio_1TB);
        seekbar();
        priceValCal();
        calButton.setOnClickListener(calculateHandler);
        resetBtn.setOnClickListener(resetHandler);

    }

    View.OnClickListener resetHandler = new View.OnClickListener(){
      public void onClick(View v){
          userBudget.setText("");
          radio2GB.setChecked(true);
          radio250GB.setChecked(true);
          clngPadChk.setChecked(false);
          crngCaseChk.setChecked(false);
          flashDriveChk.setChecked(false);
          mouseChk.setChecked(false);
          seekBarCurrent.setProgress(15);
          deliveryOption.setChecked(true);
          priceValue.setText("Price: $0.00");
          budgetStatus.setText("");
          budgetStatus.setBackgroundColor(0xffffffff);


      }
    };

    View.OnClickListener calculateHandler = new View.OnClickListener(){
        public void onClick(View v) {
          // int usrBudget = Integer.parseInt(userBudget.getText().toString());

            if(userBudget.getText().toString().isEmpty()== true)
            {
              userBudget.setError("Enter a Dollar Amount");
            }
            else
            {

                int m = 10*memoryValue();
                Double s = (0.75)*storageValue();
                int a = 20*accValue();
                int tip = seekBarCurrent.getProgress();
                Double t= 1+(tip)/100.00;
                Double d = 5.95 *deliveryValue();
                Double totalPrice = ((m + s + a)*(t))+d;
                priceValue.setText("Price: "+totalPrice);

                if(Integer.parseInt(userBudget.getText().toString())> totalPrice)
                {
                    budgetStatus.setText("Within Budget");
                    budgetStatus.setBackgroundColor(0xff12ff45);
                }
                else
                {
                    budgetStatus.setText("Over Budget");
                    budgetStatus.setBackgroundColor(0xffff0000);
                }

            }

        }

    };

    public int deliveryValue(){

        int dValue = 0;
        if( deliveryOption.isChecked()== true)
                    dValue = 1;
        else
                    dValue = 0;

        return dValue;
    }

    public int accValue(){
        mouseChk = (CheckBox)findViewById(R.id.check_mouse);
        flashDriveChk = (CheckBox)findViewById(R.id.check_flashDrive);
        clngPadChk = (CheckBox)findViewById(R.id.check_coolingPad);
        crngCaseChk = (CheckBox)findViewById(R.id.check_carryingCase);
        int aValue = 0;

        if (mouseChk.isChecked()== true)
            aValue= aValue+1;
        if (flashDriveChk.isChecked()==true)
            aValue= aValue+1;
        if (clngPadChk.isChecked()==true)
            aValue= aValue+1;
        if (crngCaseChk.isChecked()== true)
            aValue= aValue+1;

        return aValue;
    }

    public int storageValue(){

        final String storageSelected = ((RadioButton)findViewById(storageRadioGrp.getCheckedRadioButtonId())).getText().toString();

        int sValue=0;
        if(storageSelected.equals("250GB"))
            sValue = 250;
        if (storageSelected.equals("500GB"))
            sValue = 500;
        if (storageSelected.equals("750GB"))
            sValue = 750;
        if (storageSelected.equals("1TB"))
            sValue = 1000;

        return sValue;
    }



    public int memoryValue(){
        memoryRadioGrp = (RadioGroup)findViewById(R.id.grp_Memory);
        final String memorySelected = ((RadioButton)findViewById(memoryRadioGrp.getCheckedRadioButtonId() )).getText().toString();
        int mValue=0;
        if(memorySelected.equals("2GB"))
            mValue = 2;
        if (memorySelected.equals("4GB"))
            mValue = 4;
        if (memorySelected.equals("8GB"))
            mValue = 8;
        if (memorySelected.equals("16GB"))
            mValue = 16;

        return mValue;
    }


    public void priceValCal(){
        priceValue.setText("Price: $0.00");

    }


     public void seekbar(){

        seekBarCurrent.setProgress(15);
        seekBarCurrent.setMax(25);
        currentTip.setText(seekBarCurrent.getProgress()+"%");

        seekBarCurrent.setOnSeekBarChangeListener(
                new SeekBar.OnSeekBarChangeListener() {
                    int progressChange;
                    int stepSize=5;
                    @Override
                    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                        //progressChange = progress;
                        progressChange = ((int)Math.round(progress/stepSize))*stepSize;
                        currentTip.setText(progressChange+"%");
                    }

                    @Override
                    public void onStartTrackingTouch(SeekBar seekBar) {
                        currentTip.setText(progressChange+"%");
                    }

                    @Override
                    public void onStopTrackingTouch(SeekBar seekBar) {
                        currentTip.setText(progressChange+"%");
                    }
                }
        );
    }
}
