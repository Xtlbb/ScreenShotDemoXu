package com.sy.screenshotdemoxu.activity;
import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import com.cj.ScreenShotUtil.ScreentShotUtil;
import com.sy.screenshotdemoxu.R;
import java.text.SimpleDateFormat;
/**
 * 创建时间： 2017/9/12 0012.
 * 编写人：Tina
 * 邮箱：1208156801@qq.com
 * 功能描述：这个是下载其他这是原作者的项目https://github.com/Android-ScreenShot/AndroidScreenShotService
 * 在项目中添加jar，然后再使用方法 ScreentShotUtil.getInstance().takeScreenshot(this,nameImage);
 * 这个必须要root过的程序
 */

public class ThreeActivityDemo extends Activity implements View.OnClickListener {
    private Button bt_three_tostart;
    private SimpleDateFormat dateFormat = null;
    private String strDate = null;
    private String pathImage = null;
    private String nameImage = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.three_activity_demo);
        bt_three_tostart =(Button) findViewById(R.id.bt_three_tostart);
        bt_three_tostart.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
                dateFormat = new SimpleDateFormat("yyyy_MM_dd_hh_mm_ss");
                strDate = dateFormat.format(new java.util.Date());
                pathImage = Environment.getExternalStorageDirectory().getPath()+"/Pictures/";
                nameImage = pathImage+strDate+".png";
                ScreentShotUtil.getInstance().takeScreenshot(this,nameImage);

    }
}
