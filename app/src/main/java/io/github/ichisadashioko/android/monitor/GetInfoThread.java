package io.github.ichisadashioko.android.monitor;

import android.os.HardwarePropertiesManager;

public class GetInfoThread extends Thread {
    public static final long DEFAULT_UPDATE_INTERVAL_MILLISECONDS = 1000L;

    public HardwarePropertiesManager hardware_properties_manager;
    public long update_interval_milliseconds;

    public GetInfoThread(HardwarePropertiesManager hardware_properties_manager) {
        this.hardware_properties_manager = hardware_properties_manager;
        this.update_interval_milliseconds = DEFAULT_UPDATE_INTERVAL_MILLISECONDS;
    }

    public void run() {
        try {
            while (true) {
                float[] cpu_temperatures =
                        hardware_properties_manager.getDeviceTemperatures(
                                HardwarePropertiesManager.DEVICE_TEMPERATURE_CPU,
                                HardwarePropertiesManager.TEMPERATURE_CURRENT);
                for (int i = 0; i < cpu_temperatures.length; i++) {
                    System.out.println(String.format("CPU %d: %.2f", i, cpu_temperatures[i]));
                }

                if (this.update_interval_milliseconds < 0) {
                    continue;
                } else {
                    Thread.sleep(this.update_interval_milliseconds);
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace(System.err);
        }
    }
}
