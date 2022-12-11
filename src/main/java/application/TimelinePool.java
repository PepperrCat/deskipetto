package application;

import javafx.animation.Timeline;

import java.util.LinkedList;

public class TimelinePool {
    private LinkedList<Timeline> timelines;
    public TimelinePool() {
        timelines = new LinkedList<>();
    }
    public void addTimeLine(Timeline tl) {
        timelines.add(tl);
    }
    public void stopAll() {
        if (timelines.size() == 0)
            return;
        for (Timeline tl : timelines) {
            tl.stop();
        }
        timelines.clear();
    }
}
