package common;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Rebeka on 2015. 10. 23..
 */
public class TestDatabase {
    protected Map<String, User> users;
    protected Map<String, List<WorkoutVo>> workouts;

    public TestDatabase() {
        this(10, 2);
    }

    public TestDatabase(int userNum, int workoutNumPerUser) {
        users = new HashMap<String, User>();
        workouts = new HashMap<String, List<WorkoutVo>>();

        createUsers(userNum);
        createWorkouts(workoutNumPerUser);
    }

    public void addUser(User user) {
        users.put(user.getName(), user);
    }

    public void addWorkout(User user, WorkoutVo workout) {
        List<WorkoutVo> workoutList = workouts.get(user.getName());
        if(workoutList != null) {
            workoutList.add(workout);
        } else {
            workoutList = new ArrayList<>();
            workoutList.add(workout);
            workouts.put(user.getName(), workoutList);
        }
    }

    private void createUsers(int userNum) {
        for(int i = 0; i<userNum ; i++) {
            //create new user with random parameters
            User u = new User("Vki" +i, (int)Math.random()*70 + 150, (int)Math.random()*50 + 50, Math.random()<0.5d ? User.Sex.MALE : User.Sex.FEMALE);
            users.put(u.getName(), u);
        }
    }

    private void createWorkouts(int workoutNum) {
        for(String username : users.keySet()) {
            List<WorkoutVo> workoutUser = new ArrayList<WorkoutVo>();
            for(int i=0; i<workoutNum; i++) {
                WorkoutVo.SportType sport;
                if (Math.random() < 0.25d) {
                    sport = WorkoutVo.SportType.CLIMBING;
                } else if (Math.random() < 0.5d) {
                    sport = WorkoutVo.SportType.CYCLING;
                } else if (Math.random() < 0.75d) {
                    sport = WorkoutVo.SportType.HIKING;
                } else {
                    sport = WorkoutVo.SportType.RUNNING;
                }
                WorkoutVo workout = new WorkoutVo((int) Math.random() * 60 + 60, (int) Math.random() * 90 + 10, sport);
                workoutUser.add(workout);
            }
            workouts.put(username, workoutUser);
        }
    }

    public Map<String, User> getUsers() {
        return users;
    }

    public Map<String, List<WorkoutVo>> getWorkouts() {
        return workouts;
    }
}
