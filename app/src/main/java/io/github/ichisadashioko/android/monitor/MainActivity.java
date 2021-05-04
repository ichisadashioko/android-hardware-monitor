package io.github.ichisadashioko.android.monitor;

import android.app.Activity;
import android.os.Bundle;
import android.os.HardwarePropertiesManager;

public class MainActivity extends Activity {

    public GetInfoThread get_info_thread = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (get_info_thread == null) {
            get_info_thread =
                    new GetInfoThread(this.getSystemService(HardwarePropertiesManager.class));
            get_info_thread.start();
        } else {
            if (!get_info_thread.isAlive()) {
                get_info_thread =
                        new GetInfoThread(this.getSystemService(HardwarePropertiesManager.class));
                get_info_thread.start();
            }
        }
    }
}
