package io.github.ololx.mousetwitchtimer;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.TimerTask;
import java.util.concurrent.atomic.AtomicInteger;
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
        AtomicInteger newX = new AtomicInteger(currX);
        AtomicInteger newY = new AtomicInteger(currY);

        for (int twitchIndex = 0; twitchIndex < 4; twitchIndex++) {
            int twitchDelta = twitchIndex % 2 == 0 ? + 5 : -5;
            int moveStep = -1;

            while (++moveStep <= twitchDelta && this.checkIfInMovement(newX.get(), newY.get())) {
                this.mouseMove(newX.incrementAndGet(), newY.incrementAndGet());
            }

            while (--moveStep >= 0 && this.checkIfInMovement(newX.get(), newY.get())) {
                this.mouseMove(newX.decrementAndGet(), newY.decrementAndGet());
            }
        }

        this.mouseMove(currX, currY);
    }

    private void mouseMove(int x, int y) {
        this.robot.mouseMove(x, y);
        this.robot.delay(50);

        System.out.printf((MESSAGE_FORMAT) + "%n", x, y);
    }

    private boolean checkIfInMovement(int x, int y) {
        return MouseInfo.getPointerInfo().getLocation().distance(x, y) <= 1;
    }
}