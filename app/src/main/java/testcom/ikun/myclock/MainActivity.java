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
    static ArrayList<String> bg_colors = new ArrayList<String>();
    static ArrayList<String> emoji_text = new ArrayList<String>();
    static {
        bg_colors.add("BLACK");
        bg_colors.add("BLUE");
        bg_colors.add("CYAN");
        bg_colors.add("GRAY");
        bg_colors.add("GREEN");
        bg_colors.add("MAGENTA");
        bg_colors.add("RED");
        emoji_text.add("ヾ(≧▽≦*)o");
        emoji_text.add("(●'◡'●))");
        emoji_text.add("q(≧▽≦q)");
        emoji_text.add("(≧∇≦)ﾉ)");
        emoji_text.add("ヾ(^▽^*)))");
        emoji_text.add("♪(´▽｀)");
    }





    public void init_main() {
        //
        FrameLayout background = (FrameLayout) findViewById(R.id.background);
        TextView text = (TextView) findViewById(R.id.textView);

        Random random = new Random();
        int index = random.nextInt(bg_colors.size());
        int index2 = random.nextInt(emoji_text.size());

        text.setTextSize(Setting.emojitextsize);

        if (Setting.isallowbgcolorchange) {
            background.setBackgroundColor(Color.parseColor(bg_colors.get(index)));
        } ;
        if (Setting.isallowemojitextchange) {
        }else{
            if(Setting.iscustomemojitext) {
                text.setText(Setting.customemojitext);
            }else {
                text.setText(emoji_text.get(index2));
            }
        }
    }



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
        init_main();

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
                init_main();
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
        init_main();
    }

}
