package org.elsys.remote_control_car;

import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.util.Log;
import android.view.WindowManager;
import android.webkit.WebView;
import android.widget.ImageButton;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.github.niqdev.mjpeg.DisplayMode;
import com.github.niqdev.mjpeg.Mjpeg;
import com.github.niqdev.mjpeg.MjpegView;

import org.elsys.remote_control_car.enums.ButtonType;
import org.elsys.remote_control_car.request.OnTouchListenerImpl;
import org.elsys.remote_control_car.request.RequestUrls;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

import static junit.runner.BaseTestRunner.getPreference;

public class MainActivity extends AppCompatActivity {

    private Map<ButtonType, ImageButton> buttons;

    MjpegView mjpegView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mjpegView = (MjpegView) findViewById(R.id.mjpegViewDefault);

        loadIpCam();

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

    private void loadIpCam() {
        Mjpeg.newInstance()
                //.credential(getPreference(PREF_AUTH_USERNAME), getPreference(PREF_AUTH_PASSWORD))
                .open("http://" + RequestUrls.RPI_STATIC_IP + ":58081/stream.mjpg", 10)
                .subscribe(
                        inputStream -> {
                            mjpegView.setSource(inputStream);
                            mjpegView.setDisplayMode(DisplayMode.BEST_FIT);
                            mjpegView.showFps(true);
                        },
                        throwable -> {
                            Log.e(getClass().getSimpleName(), "mjpeg error", throwable);
                            Toast.makeText(this, "Error", Toast.LENGTH_LONG).show();
                        });
    }

}
