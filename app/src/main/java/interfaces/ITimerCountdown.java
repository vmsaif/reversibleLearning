package interfaces;

import android.os.CountDownTimer;

public interface ITimerCountdown {

    // set the time for the timer
    void setTimer(long seconds);

    // start the timer
    CountDownTimer startTimer();

    // update the time left every second locally
    void updateTimer();

    // get the current time left in the format of String
    String getTime();

}
