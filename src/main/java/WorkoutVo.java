/**
 * Created by Rebeka on 2015. 10. 22..
 */
public class WorkoutVo {
    public static enum SportType {
        RUNNING,
        CYCLING,
        CLIMBING,
        HIKING;
    }
    private int heartRate;
    private int minutes;
    private SportType sport;
    private int calories;

    public WorkoutVo(int heartRate, int minutes, SportType sport) {
        this.heartRate = heartRate;
        this.minutes = minutes;
        this.sport = sport;
    }

    private int calculateCalories(int height, int weight, User.Sex sex) {
        //TODO
        return 0;
    }

    public int getHeartRate() {
        return heartRate;
    }

    public int getMinutes() {
        return minutes;
    }

    public SportType getSport() {
        return sport;
    }

    public int getCalories() {
        return calories;
    }
}
