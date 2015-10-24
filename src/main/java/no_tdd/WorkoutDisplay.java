package no_tdd;

import common.TestDatabase;
import common.User;
import common.WorkoutVo;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Rebeka on 2015. 10. 24..
 */
public class WorkoutDisplay {
    //hivatkozas a db-re, jelenleg TestDB
    private TestDatabase db;

    public WorkoutDisplay(TestDatabase db) {
        this.db = db;
    }

    public List<WorkoutVo> getAllWorkouts(User user) {
        return db.getWorkouts().get(user.getName());
    }

    public List<WorkoutVo> getWorkouts(User user, WorkoutVo.SportType sport) {
        List<WorkoutVo> workoutList = db.getWorkouts().get(user.getName());
        if (workoutList != null) {
            return workoutList.stream().filter(workoutVo -> workoutVo.getSport().equals(sport)).collect(Collectors.toList());
        } else {
            return null;
        }
    }
}
