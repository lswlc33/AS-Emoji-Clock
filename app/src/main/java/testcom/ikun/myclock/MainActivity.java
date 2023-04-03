package testcom.ikun.myclock;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // 全屏窗口
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        FrameLayout background = (FrameLayout) findViewById(R.id.background);
        TextView text = (TextView) findViewById(R.id.textView);
        ImageView image = (ImageView) findViewById(R.id.imageView2);
        text.setTextSize(Setting.emojitextsize);

        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MainActivity.this, Setting.class);
                startActivity(intent);

            }
        });

        background.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ArrayList<String> colors;
                colors = new ArrayList<String>();
                colors.add("BLACK");
                colors.add("BLUE");
                colors.add("CYAN");
                colors.add("GRAY");
                colors.add("GREEN");
                colors.add("MAGENTA");
                colors.add("RED");

                ArrayList<String> emojitext;
                emojitext = new ArrayList<String>();
                emojitext.add("ヾ(≧▽≦*)o");
                emojitext.add("(●'◡'●))");
                emojitext.add("q(≧▽≦q)");
                emojitext.add("(≧∇≦)ﾉ)");
                emojitext.add("ヾ(^▽^*)))");
                emojitext.add("♪(´▽｀)");


                Random random = new Random();
                int index = random.nextInt(colors.size());
                int index2 = random.nextInt(emojitext.size());

                text.setTextSize(Setting.emojitextsize);

                if (Setting.isallowbgcolorchange) {
                    background.setBackgroundColor(Color.parseColor(colors.get(index)));
                } ;
                if (Setting.isallowemojitextchange) {
                    text.setText(emojitext.get(index2));
                }
            }

        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        TextView text = (TextView) findViewById(R.id.textView);
        text.setTextSize(Setting.emojitextsize);
        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION;
        decorView.setSystemUiVisibility(uiOptions);
    }

}
