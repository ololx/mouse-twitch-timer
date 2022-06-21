package io.github.ololx.mouse_twitch_timer;

import java.awt.*;
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

        IntStream.range(0, 4)
                .map(twitchIndex -> twitchIndex % 2 == 0 ? + 5 : -5)
                .forEach(twitchDelta -> {
                    IntStream.range(twitchDelta, 0)
                            .forEach(newPos -> this.mouseMove(
                                    newX.decrementAndGet(),
                                    newY.decrementAndGet()
                            ));
                    IntStream.range(0, twitchDelta)
                            .forEach(newPos -> this.mouseMove(
                                    newX.incrementAndGet(),
                                    newY.incrementAndGet()
                            ));


                });

        this.mouseMove(currX, currY);
    }

    private void mouseMove(int x, int y) {
        this.robot.mouseMove(x, y);
        System.out.println(String.format(MESSAGE_FORMAT, x, y));

        try {
            Thread.sleep(50);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}