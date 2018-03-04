package org.elsys.remote_control_car;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;
import android.widget.ImageButton;

import org.elsys.remote_control_car.enums.ButtonType;
import org.elsys.remote_control_car.request.OnTouchListenerImpl;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private Map<ButtonType, ImageButton> buttons;

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
        buttons = new HashMap<ButtonType, ImageButton>();

        buttons.put(ButtonType.FORWARD, (ImageButton) findViewById(R.id.forward_btn));
        buttons.put(ButtonType.BACKWARD, (ImageButton) findViewById(R.id.backward_btn));
        buttons.put(ButtonType.RIGHT, (ImageButton) findViewById(R.id.right_btn));
        buttons.put(ButtonType.LEFT, (ImageButton) findViewById(R.id.left_btn));

        setListeners();
    }

    private void setListeners() {
        Iterator<ButtonType> iterator = buttons.keySet().iterator();

        while (iterator.hasNext()) {
            ButtonType key = iterator.next();
            ImageButton button = buttons.get(key);

            button.setOnTouchListener(new OnTouchListenerImpl());
        }
    }
}
