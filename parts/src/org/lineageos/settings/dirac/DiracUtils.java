/*
 * Copyright (C) 2019 The LineageOS Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.lineageos.settings.dirac;

import android.content.Context;
import android.content.Intent;
<<<<<<< HEAD
import android.os.Handler;
import android.os.UserHandle;
import android.os.SystemClock;
import android.view.KeyEvent;
import android.media.session.MediaController;
import android.media.session.MediaSessionManager;
import android.media.session.PlaybackState;
<<<<<<< HEAD
import java.lang.IllegalArgumentException;
=======
>>>>>>> 57e7749... ugglite: Dirac fixes
import java.util.List;

public final class DiracUtils {

<<<<<<< HEAD
    protected DiracSound mDiracSound;
<<<<<<< HEAD
    private static DiracUtils mInstance;
=======
>>>>>>> 845e444... ugglite: parts: Refactor Dirac setup
    private MediaSessionManager mMediaSessionManager;
    private Handler mHandler = new Handler();
    private Context mContext;

<<<<<<< HEAD
    public static DiracUtils getInstance() {
        if (mInstance == null) {
            throw new IllegalArgumentException("Trying to get instance without initializing!");
=======
    protected static DiracSound mDiracSound;
    private static boolean mInitialized;
    private static MediaSessionManager mMediaSessionManager;
    private static Handler mHandler = new Handler();
    private static Context mContext;

    public static void initialize(Context context) {
        if (!mInitialized) {
            mContext = context;
            mMediaSessionManager = (MediaSessionManager) context.getSystemService(Context.MEDIA_SESSION_SERVICE);
            mInitialized = true;
            mDiracSound = new DiracSound(0, 0);
            setEnabled(mDiracSound.getMusic() == 1);
            mDiracSound.setHeadsetType(mDiracSound.getHeadsetType());
            setLevel(getLevel());
>>>>>>> 57e7749... ugglite: Dirac fixes
        }
        return mInstance;
    }

<<<<<<< HEAD
    public DiracUtils(final Context context) {
        mContext = context;
        mMediaSessionManager = (MediaSessionManager) context.getSystemService(Context.MEDIA_SESSION_SERVICE);
        mDiracSound = new DiracSound(0, 0);
    }

<<<<<<< HEAD
    public void onBootCompleted() {
=======
    public void onBootCompleted(){
>>>>>>> b5eb671... ugglite: dirac: Only call setEnabled on boot completed
        setEnabled(mDiracSound.getMusic() == 1);
        mDiracSound.setHeadsetType(mDiracSound.getHeadsetType());
        setLevel(getLevel());
        mInstance = this;
    }

=======
    public DiracUtils(final Context context) {
        mContext = context;
        mMediaSessionManager = (MediaSessionManager) context.getSystemService(Context.MEDIA_SESSION_SERVICE);
        mDiracSound = new DiracSound(0, 0);
        setEnabled(mDiracSound.getMusic() == 1);
        mDiracSound.setHeadsetType(mDiracSound.getHeadsetType());
        setLevel(getLevel());
    }

>>>>>>> 845e444... ugglite: parts: Refactor Dirac setup
    protected void refreshPlaybackIfNecessary(){
=======
    protected static void refreshPlaybackIfNecessary(){
>>>>>>> 57e7749... ugglite: Dirac fixes
        if (mMediaSessionManager == null) {
            mMediaSessionManager = (MediaSessionManager) mContext.getSystemService(Context.MEDIA_SESSION_SERVICE);
        }
        final List<MediaController> sessions
                = mMediaSessionManager.getActiveSessionsForUser(
                null, UserHandle.USER_ALL);
        for (MediaController aController : sessions) {
            if (PlaybackState.STATE_PLAYING ==
                    getMediaControllerPlaybackState(aController)) {
                triggerPlayPause(aController);
                break;
            }
        }
    }

<<<<<<< HEAD
    private void triggerPlayPause(MediaController controller) {
=======
    private static void triggerPlayPause(MediaController controller) {
>>>>>>> 57e7749... ugglite: Dirac fixes
        long when = SystemClock.uptimeMillis();
        final KeyEvent evDownPause = new KeyEvent(when, when, KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_MEDIA_PAUSE, 0);
        final KeyEvent evUpPause = KeyEvent.changeAction(evDownPause, KeyEvent.ACTION_UP);
        final KeyEvent evDownPlay = new KeyEvent(when, when, KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_MEDIA_PLAY, 0);
        final KeyEvent evUpPlay = KeyEvent.changeAction(evDownPlay, KeyEvent.ACTION_UP);
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                controller.dispatchMediaButtonEvent(evDownPause);
            }
        });
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                controller.dispatchMediaButtonEvent(evUpPause);
            }
        }, 20);
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                controller.dispatchMediaButtonEvent(evDownPlay);
            }
<<<<<<< HEAD
        }, 1000);
=======
        }, 500);
>>>>>>> 57e7749... ugglite: Dirac fixes
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                controller.dispatchMediaButtonEvent(evUpPlay);
            }
<<<<<<< HEAD
        }, 1020);
    }

    private int getMediaControllerPlaybackState(MediaController controller) {
=======
        }, 520);
    }

    private static int getMediaControllerPlaybackState(MediaController controller) {
>>>>>>> 57e7749... ugglite: Dirac fixes
        if (controller != null) {
            final PlaybackState playbackState = controller.getPlaybackState();
            if (playbackState != null) {
                return playbackState.getState();
            }
        }
        return PlaybackState.STATE_NONE;
    }
<<<<<<< HEAD
    protected void setEnabled(boolean enable) {
        mDiracSound.setEnabled(enable);
        mDiracSound.setMusic(enable ? 1 : 0);
        if (enable) {
=======
    protected static void setEnabled(boolean enable) {
        mDiracSound.setEnabled(enable);
        mDiracSound.setMusic(enable ? 1 : 0);
        if (enable){
>>>>>>> 57e7749... ugglite: Dirac fixes
            refreshPlaybackIfNecessary();
        }
    }

<<<<<<< HEAD
    protected boolean isDiracEnabled() {
=======
    protected static boolean isDiracEnabled() {
>>>>>>> 57e7749... ugglite: Dirac fixes
        return mDiracSound.getMusic() == 1;
    }

    protected void setLevel(String preset) {
=======
import android.os.UserHandle;

public final class DiracUtils {

    protected static DiracSound mDiracSound;
    private static boolean mInitialized;

    public static void initialize() {
        if (!mInitialized) {
            mInitialized = true;
            mDiracSound = new DiracSound(0, 0);
            mDiracSound.setMusic(mDiracSound.getMusic());
            mDiracSound.setHeadsetType(mDiracSound.getHeadsetType());
            setLevel(getLevel());
        }
    }

    protected static void setMusic(boolean enable) {
        mDiracSound.setMusic(enable ? 1 : 0);
    }

    protected static boolean isDiracEnabled(Context context) {
        return mDiracSound.getMusic() == 1;
    }

    protected static void setLevel(String preset) {
>>>>>>> a0d2387... ugglite: parts: Add DiracSound backend
        String[] level = preset.split("\\s*,\\s*");

        for (int band = 0; band <= level.length - 1; band++) {
            mDiracSound.setLevel(band, Float.valueOf(level[band]));
        }
    }

<<<<<<< HEAD
    protected String getLevel() {
=======
    protected static String getLevel() {
>>>>>>> a0d2387... ugglite: parts: Add DiracSound backend
        String selected = "";
        for (int band = 0; band <= 6; band++) {
            int temp = (int) mDiracSound.getLevel(band);
            selected += String.valueOf(temp);
            if (band != 6) selected += ",";
        }
        return selected;
    }

<<<<<<< HEAD
    protected void setHeadsetType(int paramInt) {
=======
    protected static void setHeadsetType(int paramInt) {
>>>>>>> a0d2387... ugglite: parts: Add DiracSound backend
         mDiracSound.setHeadsetType(paramInt);
    }
}
