package interfaces;

import android.os.CountDownTimer;

public interface ITimer {

    // Set the timer
    void setTimer(long seconds);

    // Start the timer
    CountDownTimer startTimer();

    // Update the timer to a new time
    void updateTimer();

    // Get the current time in a string format
    String getTime();
}
