package application.Listener;

import javafx.animation.Timeline;

import java.util.LinkedList;
/**
 * to manage the timeline
 * @version 0.1.0
 * @author ppcat
 * @since 0.1.0
 * @date 2022-12-23 16:00:49
 **/
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
