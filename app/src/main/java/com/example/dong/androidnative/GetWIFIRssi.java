package com.example.dong.androidnative;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.telephony.CellInfo;
import android.telephony.CellInfoCdma;
import android.telephony.CellInfoGsm;
import android.telephony.CellInfoLte;
import android.telephony.CellInfoWcdma;
import android.telephony.CellSignalStrength;
import android.telephony.CellSignalStrengthCdma;
import android.telephony.CellSignalStrengthGsm;
import android.telephony.CellSignalStrengthLte;
import android.telephony.TelephonyManager;

import com.unity3d.player.UnityPlayer;

        public class GetWIFIRssi {


            public static int GetWIFISignalStrength() {
                final Activity activity = UnityPlayer.currentActivity;
                final Context context = activity.getApplicationContext();

//得到的值是一个0到-100的区间值，是一个int型数据，
//其中0到-50表示信号最好，
//-50到-70表示信号偏差，
//小于-70表示最差，有可能连接不上或者掉线。
//这个函数是返回5个级别！！！！！！
                WifiManager wifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
                int numberOfLevels = 5;
                WifiInfo wifiInfo = wifiManager.getConnectionInfo();
                int level = WifiManager.calculateSignalLevel(wifiInfo.getRssi(), numberOfLevels);

                return level;
            }

            @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR2)
            public static int GetTeleSignalStrength() {
                final Activity activity = UnityPlayer.currentActivity;
                final Context context = activity.getApplicationContext();

                int level = 0;

                final TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
                for (final CellInfo info : tm.getAllCellInfo()) {
                    if (info instanceof CellInfoGsm) {
                        final CellSignalStrengthGsm gsm = ((CellInfoGsm) info).getCellSignalStrength();
                        level = gsm.getLevel();
                    } else if (info instanceof CellInfoCdma) {
                        final CellSignalStrengthCdma cdma = ((CellInfoCdma) info).getCellSignalStrength();
                        level = cdma.getLevel();
                    } else if (info instanceof CellInfoLte) {
                        final CellSignalStrengthLte lte = ((CellInfoLte) info).getCellSignalStrength();
                        level = lte.getLevel();
                    } else if (info instanceof CellInfoWcdma) {
                        final CellSignalStrength wcdma = ((CellInfoWcdma) info).getCellSignalStrength();
                        level = wcdma.getLevel();
                    }
                }

                return level;
            }

        }
