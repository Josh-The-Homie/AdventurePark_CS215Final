import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class CustomTimer {
    private Timer timer;
    private TimerTask task;

    public CustomTimer() {
        this.timer = new Timer();
    }

    public void scheduleTask(TimerTask task, int delay) {
        if (task == null || delay < 0) {
            throw new IllegalArgumentException("Invalid task or delay");
        }

        if (this.task != null) {
            throw new IllegalStateException("Task was already scheduled");
        }

        this.task = task;
        timer.schedule(task, delay);
    }

    public boolean isTaskScheduled() {
        return task != null;
    }

    public void cancel() {
        if (task != null) {
            task.cancel();
        }
        timer.cancel();
    }

    public static void main(String[] args) {
        // Example usage
        CustomTimer customTimer = new CustomTimer();

        TimerTask myTask = new TimerTask() {
            int counter = 0;

            @Override
            public void run() {
                System.out.println("Task executed: " + counter++);
                // Add your task logic here
            }
        };

        // Set the delay to be 1000 milliseconds (1 second)
        int delay = 1000;

        customTimer.scheduleTask(myTask, delay);

        // Let the timer run for 5 seconds
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Cancel the timer after 5 seconds
        customTimer.cancel();
    }
}
