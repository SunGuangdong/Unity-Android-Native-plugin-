package com.example.dong.androidnative;

        import android.os.BatteryManager;

        import android.content.Context;
        import android.content.Intent;
        import android.app.Activity;
        import android.content.IntentFilter;

        import com.unity3d.player.UnityPlayer;

        public class GetBattery {

            //状态：是说，满电、正在充电，正在使用等
            public static int BatteryState() {
                final Activity activity = UnityPlayer.currentActivity;
                final Context context = activity.getApplicationContext();

                IntentFilter ifilter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
                Intent batteryStatus = context.registerReceiver(null, ifilter);

                int status = batteryStatus.getIntExtra(BatteryManager.EXTRA_STATUS, -1);
                int nowState = -1;

                switch (status) {
                    case BatteryManager.BATTERY_STATUS_FULL:
//Full
                        nowState = 2;
                        break;
                    case BatteryManager.BATTERY_STATUS_CHARGING:
//Charging
                        nowState = 1;
                        break;
                    case BatteryManager.BATTERY_STATUS_DISCHARGING:
//Unplugged
                        nowState = 0;
                        break;
                    case BatteryManager.BATTERY_STATUS_NOT_CHARGING:
//Unplugged
                        nowState = 0;
                        break;
                    case BatteryManager.BATTERY_STATUS_UNKNOWN:
//Unknown
                        nowState = -1;
                        break;
                }

                return nowState;
            }

            //剩余电量，满电是100
            public static int BatteryLevel() {
                final Activity activity = UnityPlayer.currentActivity;
                final Context context = activity.getApplicationContext();

                IntentFilter ifilter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
                Intent batteryStatus = context.registerReceiver(null, ifilter);

                int nowLevel = batteryStatus.getIntExtra(BatteryManager.EXTRA_LEVEL, -1);

                return nowLevel;
            }

        }
