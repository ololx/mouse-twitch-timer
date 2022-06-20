package io.github.ololx.mouse_twitch_timer;

import java.awt.*;
import java.util.TimerTask;
import java.util.stream.IntStream;

/**
 * project mouse-twitch-timer
 * created 2021-10-20 10:46
 *
 * @author Alexander A. Kropotin
 */
public class MouseTwitchTimerTask extends TimerTask {

    private static final String MESSAGE_FORMAT = "Move to (%s, %s);";

    private Robot robot;

    public MouseTwitchTimerTask() {
        super();

        try {
            this.robot = new Robot();
        } catch (AWTException e) {
            e.printStackTrace();
        }
    }

    public void run() {
        int currX = (int) MouseInfo.getPointerInfo().getLocation().getX();
        int currY = (int) MouseInfo.getPointerInfo().getLocation().getY();

        IntStream.range(0, 4)
                .map(twitchIndex -> twitchIndex % 2 == 0 ? + 200 : -200)
                .forEach(twitchDelta -> {
                    this.robot.mouseMove(currX + twitchDelta, currY + twitchDelta);
                    System.out.println(
                            String.format(
                                    MESSAGE_FORMAT,
                                    (currX + twitchDelta),
                                    (currY + twitchDelta)
                            )
                    );
                });

        this.robot.mouseMove(currX, currY);
        System.out.println(String.format(MESSAGE_FORMAT, currX, currY));
    }
}