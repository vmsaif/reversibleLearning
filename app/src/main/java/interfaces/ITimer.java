package interfaces;

import android.os.CountDownTimer;

public interface ITimer {

    // Set the timer
    void setTimer(long seconds);

    // Start the timer
    CountDownTimer startTimer();


    // Get the current time in a string format
    String getTime();
}
