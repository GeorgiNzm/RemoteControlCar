package org.elsys.remote_control_car;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.WindowManager;
import android.widget.ImageButton;

import org.elsys.remote_control_car.implementation.OnTouchListenerImpl;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private Map<String, ImageButton> buttons;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initializeButtons();
    }

    private void enableFullScreenLandscape() {
        //Enable fullscreen and change window orientation
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                             WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
    }

    private void initializeButtons() {
        buttons = new HashMap<String, ImageButton>();

        buttons.put("Forward", (ImageButton) findViewById(R.id.forward_btn));
        buttons.put("Backward", (ImageButton) findViewById(R.id.backward_btn));
        buttons.put("Right", (ImageButton) findViewById(R.id.right_btn));
        buttons.put("Left", (ImageButton) findViewById(R.id.left_btn));

        setListeners();
    }

    private void setListeners() {
        Iterator<String> iterator = buttons.keySet().iterator();

        while (iterator.hasNext()) {
            String key = iterator.next();
            ImageButton button = buttons.get(key);

            button.setOnTouchListener(new OnTouchListenerImpl());
        }
    }
}
