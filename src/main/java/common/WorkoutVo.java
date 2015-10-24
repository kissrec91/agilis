package common;

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
    private Integer calories;

    public WorkoutVo(int heartRate, int minutes, SportType sport) {
        this.heartRate = heartRate;
        this.minutes = minutes;
        this.sport = sport;
    }

    private int calculateCalories(int weight, User.Sex sex) {
        //TODO random szamitas jelenleg
        int sportMultiplier;
        int sexMultiplier;
        switch (sport) {
            case RUNNING:
                sportMultiplier = 4;
                break;
            case CLIMBING:
                sportMultiplier = 8;
                break;
            case CYCLING:
                sportMultiplier = 6;
                break;
            case HIKING:
                sportMultiplier = 7;
                break;
            default:
                sportMultiplier = 1;
        }
        if(sex.equals(User.Sex.FEMALE)) {
            sexMultiplier = 2;
        } else {
            sexMultiplier = 4;
        }
        calories = sportMultiplier * minutes * sexMultiplier;
        return calories;
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

    public int getCalories(User user) {
        if(calories == null) {
            calculateCalories(user.getWeight(), user.getSex());
        }
        return calories;
    }

    @Override
    public boolean equals(Object o) {
        WorkoutVo other = (WorkoutVo) o;
        if(heartRate != other.getHeartRate()) {
            return false;
        }
        if (minutes != other.getMinutes()) {
            return false;
        }
        if (sport != other.getSport()) {
            return false;
        }
        return true;
    }
}
