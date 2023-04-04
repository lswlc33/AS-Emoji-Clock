package testcom.ikun.myclock;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.WindowManager;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Setting extends AppCompatActivity {

    public static boolean isallowbgcolorchange = true;
    public static boolean isallowemojitextchange = true;
    public static boolean iscustombgcolor = false;
    public static boolean iscustomemojitext = false;
    public static int emojitextsize = 80;

    public static String customemojitext = "";

    public static String custom_bg_color = "#000000";
    public void init_setting(boolean a,boolean b, boolean c, boolean d){
        @SuppressLint("UseSwitchCompatOrMaterialCode") Switch switch1 = (Switch) findViewById(R.id.switch1);
        @SuppressLint("UseSwitchCompatOrMaterialCode") Switch switch2 = (Switch) findViewById(R.id.switch2);
        @SuppressLint("UseSwitchCompatOrMaterialCode") Switch switch3 = (Switch) findViewById(R.id.switch3);
        @SuppressLint("UseSwitchCompatOrMaterialCode") Switch switch4 = (Switch) findViewById(R.id.switch4);
        EditText editText1 = (EditText) findViewById(R.id.editTextTextPersonName);
        EditText editText2 = (EditText) findViewById(R.id.editTextTextPersonName2);
        SeekBar seekBar1 = (SeekBar) findViewById(R.id.seekBar);
        seekBar1.setProgress(emojitextsize);
        if(a){
            switch2.setEnabled(false);
            editText1.setEnabled(false);
        }else{
            switch2.setEnabled(true);
            if(c){
                editText1.setEnabled(true);
            }else{
                editText1.setEnabled(false);
            }
        }

        if(b){
            switch4.setEnabled(false);
            editText2.setEnabled(false);
        }else{
            switch4.setEnabled(true);
            if(d){
                editText2.setEnabled(true);
            }else{
                editText2.setEnabled(false);
            }
        }
        switch1.setChecked(a);
        switch2.setChecked(c);
        switch3.setChecked(b);
        switch4.setChecked(d);
        editText1.setText(custom_bg_color);
        editText2.setText(customemojitext);

    }




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        @SuppressLint("UseSwitchCompatOrMaterialCode") Switch switch1 = (Switch) findViewById(R.id.switch1);
        @SuppressLint("UseSwitchCompatOrMaterialCode") Switch switch2 = (Switch) findViewById(R.id.switch2);
        @SuppressLint("UseSwitchCompatOrMaterialCode") Switch switch3 = (Switch) findViewById(R.id.switch3);
        @SuppressLint("UseSwitchCompatOrMaterialCode") Switch switch4 = (Switch) findViewById(R.id.switch4);
        EditText editText1 = (EditText) findViewById(R.id.editTextTextPersonName);
        EditText editText2 = (EditText) findViewById(R.id.editTextTextPersonName2);
        SeekBar seekBar1 = (SeekBar) findViewById(R.id.seekBar);

        editText1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if(iscustombgcolor) {
                    Pattern pattern = Pattern.compile("^#?([a-fA-F0-9]{6}|[a-fA-F0-9]{8})$");
                    Matcher matcher = pattern.matcher(editText1.getText());
                    if (matcher.matches()) {
                        switch2.setText("自定义背景颜色     😋️️输入的颜色符合规范！");

                        custom_bg_color = String.valueOf(editText1.getText());
                    } else {
                        switch2.setText("自定义背景颜色     ⚠️输入的颜色不符合规范！");
                    }
                }else{switch2.setText("自定义背景颜色");}
            }
        });

        editText2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                customemojitext = String.valueOf(editText2.getText());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        seekBar1.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                emojitextsize = i;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        switch1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                isallowbgcolorchange = b;
                init_setting(isallowbgcolorchange, isallowemojitextchange, iscustombgcolor, iscustomemojitext);
            }
        });

        switch2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                iscustombgcolor = b;
                init_setting(isallowbgcolorchange, isallowemojitextchange, iscustombgcolor, iscustomemojitext);
            }
        });

        switch3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                isallowemojitextchange = b;
                init_setting(isallowbgcolorchange, isallowemojitextchange, iscustombgcolor, iscustomemojitext);
            }
        });

        switch4.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                iscustomemojitext = b;
                init_setting(isallowbgcolorchange, isallowemojitextchange, iscustombgcolor, iscustomemojitext);
            }
        });


        init_setting(isallowbgcolorchange, isallowemojitextchange, iscustombgcolor, iscustomemojitext);




    }

    @Override
    protected void onResume() {
        super.onResume();

    }
}