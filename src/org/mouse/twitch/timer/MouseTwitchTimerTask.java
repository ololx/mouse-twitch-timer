package org.mouse.twitch.timer;

import java.awt.*;
import java.util.TimerTask;

/**
 * @project mouse-twitch-timer
 * @created 2021-10-20 10:46
 * <p>
 * @author Alexander A. Kropotin
 */
public class MouseTwitchTimerTask extends TimerTask {

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
        int newX = currX + 1;
        int newY = currY + 1;

        this.robot.mouseMove(newX, newY);
        this.robot.mouseMove(currX, currY);

    }
}