package io.github.ololx.mouse_twitch_timer;

import java.util.Timer;

public class MouseTwitchTimer {

    public static void main(String[] args) {
        long period = 5;
        if (args.length > 0) {
            period = Long.parseLong(args[0]);
        }

        Timer timer = new Timer();
        timer.schedule(new MouseTwitchTimerTask(), 0, period * 1000);
    }
}
