package com.northmendo.Appzuku;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import java.util.concurrent.Executors;

import static com.northmendo.Appzuku.PreferenceKeys.*;

public class KillTriggerReceiver extends BroadcastReceiver {
    private static final String TAG = "KillTriggerReceiver";

    /**
     * Broadcast action that external apps (e.g. Macrodroid, Tasker) can send to trigger
     * an immediate kill of all non-whitelisted, non-hidden background apps.
     *
     * Example adb command:
     *   adb shell am broadcast -a com.northmendo.Appzuku.ACTION_KILL_ALL
     *
     * In Macrodroid: Broadcast Intent action → "com.northmendo.Appzuku.ACTION_KILL_ALL"
     */
    public static final String ACTION_KILL_ALL = "com.northmendo.Appzuku.ACTION_KILL_ALL";

    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();

        if (Intent.ACTION_SCREEN_OFF.equals(action)) {
            SharedPreferences prefs = context.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE);
            if (prefs.getBoolean(KEY_KILL_ON_SCREEN_OFF, false)) {
                Log.d(TAG, "Screen off detected, starting kill cycle");
                Intent serviceIntent = new Intent(context, ShappkyService.class);
                serviceIntent.setAction("TRIGGER_KILL");
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    context.startForegroundService(serviceIntent);
                } else {
                    context.startService(serviceIntent);
                }
            }

        } else if (ACTION_KILL_ALL.equals(action)) {
            Log.d(TAG, "ACTION_KILL_ALL received – triggering immediate kill");
            // BroadcastReceivers must complete quickly; spin up our own executor.
            // We use a goAsync pattern so Android gives us more time if needed.
            final PendingResult pendingResult = goAsync();
            Handler handler = new Handler(Looper.getMainLooper());
            Executors.newSingleThreadExecutor().execute(() -> {
                try {
                    ShellManager shellManager = new ShellManager(context, handler,
                            Executors.newSingleThreadExecutor());
                    BackgroundAppManager appManager = new BackgroundAppManager(
                            context, handler, Executors.newSingleThreadExecutor(), shellManager);
                    // loadBackgroundApps populates currentAppsList, then we kill
                    appManager.loadBackgroundApps(apps ->
                            appManager.killAllNonWhitelisted(pendingResult::finish));
                } catch (Exception e) {
                    Log.e(TAG, "Error during ACTION_KILL_ALL", e);
                    pendingResult.finish();
                }
            });
        }
    }
}
