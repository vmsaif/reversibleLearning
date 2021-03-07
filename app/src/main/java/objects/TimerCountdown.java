package objects;

import android.os.CountDownTimer;

public class TimerCountdown {

    private long duration;
    private String timeLeft;

    public TimerCountdown(long seconds){
        this.duration = seconds * 1000;
        this.timeLeft = String.valueOf(this.duration / 60000) + ":" + String.valueOf(this.duration % 60000 / 1000);
    }

    public void setTimer(long seconds){
        this.duration = seconds * 1000;
    }

    public CountDownTimer startTimer(){
        return new CountDownTimer(duration, 1000){
            public void onTick(long l){
                duration = l;
                updateTimer();
            }
            public void onFinish(){
                // has yet to be decided, maybe a text or passing a function from the parameter
            }
        }.start();
    }

    private void updateTimer(){
        int min = (int) duration / 60000;
        int sec = (int) duration % 60000 / 1000;

        timeLeft = "" + min;
        timeLeft += ":";
        if(sec < 10) timeLeft += "0";
        timeLeft += sec;
    }

    public String getTime(){
        return timeLeft;
    }


}
