package testcom.ikun.emojim;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;


public class MainActivity extends AppCompatActivity {
    static ArrayList<String> emoji_text = new ArrayList<String>();

    static {
        // 预设颜色和表情
        emoji_text.add("ヾ(≧▽≦*)o");
        emoji_text.add("(●'◡'●))");
        emoji_text.add("q(≧▽≦q)");
        emoji_text.add("(≧∇≦)ﾉ)");
        emoji_text.add("ヾ(^▽^*)))");
        emoji_text.add("♪(´▽｀)");
    }



    public static boolean is_dark_color(String color) {
        // Convert the hex color string to RGB values
        int red = Integer.parseInt(color.substring(0, 2), 16);
        int green = Integer.parseInt(color.substring(2, 4), 16);
        int blue = Integer.parseInt(color.substring(4, 6), 16);

        // Calculate the brightness using the formula (0.2126*R + 0.7152*G + 0.0722*B)
        double brightness = 0.2126 * red + 0.7152 * green + 0.0722 * blue;

        // If the brightness is greater than or equal to 128, classify the color as dark. Otherwise, classify it as light.
        if (brightness >= 128) {
            return true;
        } else {
            return false;
        }

    }

    public static void auto_color(){
        Random rand = new Random();
        int num = rand.nextInt(0xffffff); // 生成一个24位二进制数
        Setting.custom_bg_color = String.format("%06x", num);
    }
    public void init_main() {
        // 设置背景颜色和文字
        FrameLayout background = (FrameLayout) findViewById(R.id.background);
        TextView text = (TextView) findViewById(R.id.textView);

        Random random = new Random();
        int index2 = random.nextInt(emoji_text.size());






        text.setTextSize(Setting.emojitextsize);
        if(Setting.is_flash_mode){
        }else {
            if (Setting.isallowbgcolorchange) {
                auto_color();
                background.setBackgroundColor(Color.parseColor("#" + Setting.custom_bg_color));
                if (is_dark_color(Setting.custom_bg_color)) {
                    text.setTextColor(Color.parseColor("black"));
                } else {
                    text.setTextColor(Color.parseColor("white"));
                }
            } else {
                if (Setting.iscustombgcolor) {
                    background.setBackgroundColor(Color.parseColor("#" + Setting.custom_bg_color));
                    if (is_dark_color(Setting.custom_bg_color)) {
                        text.setTextColor(Color.parseColor("black"));
                    } else {
                        text.setTextColor(Color.parseColor("white"));
                    }
                }
            }
        }


        if (Setting.isallowemojitextchange) {
            text.setText(emoji_text.get(index2));
        }else{
            if(Setting.iscustomemojitext) {
                text.setText(Setting.customemojitext);
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


        Handler handler = new Handler();
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                if(Setting.is_flash_mode){
                    auto_color();
                    background.setBackgroundColor(Color.parseColor("#" + Setting.custom_bg_color));
                }
                // 继续执行下一个5秒切换事件
                handler.postDelayed(this, 100);
            }
        };












        handler.postDelayed(runnable, 1000);
        image.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                image.setVisibility(View.INVISIBLE);
                return true;
            }
        });

        FrameLayout frameLayout = (FrameLayout) findViewById(R.id.frame1);
        frameLayout.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                image.setVisibility(View.VISIBLE);
                return true;
            }
        });

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
