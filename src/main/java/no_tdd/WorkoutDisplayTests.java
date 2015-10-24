package no_tdd;

import common.TestDatabase;
import common.User;
import common.WorkoutVo;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.List;

/**
 * Created by Rebeka on 2015. 10. 24..
 */
public class WorkoutDisplayTests {
    private static TestDatabase testDb;
    private User johnDoe;
    private User janeDoe;

    @BeforeTest
    public void setup() {
        testDb = new TestDatabase(5, 10);

        johnDoe = new User("John Doe", 180, 90, User.Sex.MALE);
        janeDoe = new User("Jane Doe", 167, 60, User.Sex.FEMALE);

        testDb.addUser(johnDoe);
        testDb.addUser(janeDoe);

        testDb.addWorkout(johnDoe, new WorkoutVo(80, 40, WorkoutVo.SportType.RUNNING));
        testDb.addWorkout(johnDoe, new WorkoutVo(70, 60, WorkoutVo.SportType.RUNNING));
        testDb.addWorkout(johnDoe, new WorkoutVo(90, 30, WorkoutVo.SportType.RUNNING));

        testDb.addWorkout(janeDoe, new WorkoutVo(80, 40, WorkoutVo.SportType.HIKING));
        testDb.addWorkout(janeDoe, new WorkoutVo(70, 60, WorkoutVo.SportType.HIKING));
        testDb.addWorkout(janeDoe, new WorkoutVo(90, 30, WorkoutVo.SportType.CLIMBING));
    }

    @Test
    public void testAllWorkoutDisplay() {
        WorkoutDisplay display = new WorkoutDisplay(testDb);
        List<WorkoutVo> allWorkouts = display.getAllWorkouts(johnDoe);

        TestDatabase expectedResult = new TestDatabase();
        expectedResult.addWorkout(johnDoe, new WorkoutVo(80, 40, WorkoutVo.SportType.RUNNING));
        expectedResult.addWorkout(johnDoe, new WorkoutVo(70, 60, WorkoutVo.SportType.RUNNING));
        expectedResult.addWorkout(johnDoe, new WorkoutVo(90, 30, WorkoutVo.SportType.RUNNING));

        Assert.assertTrue(allWorkouts.containsAll(expectedResult.getWorkouts().get(johnDoe.getName())), "Not all workouts have been found");
        Assert.assertTrue(expectedResult.getWorkouts().get(johnDoe.getName()).containsAll(allWorkouts), "More workouts have been found than expected");
    }

    @Test void testWorkoutPerSport() {
        WorkoutDisplay display = new WorkoutDisplay(testDb);
        List<WorkoutVo> climbing = display.getWorkouts(janeDoe, WorkoutVo.SportType.CLIMBING);
        Assert.assertEquals(climbing.size(), 1, "Filtering was not successfull");
        Assert.assertEquals(climbing.get(0), new WorkoutVo(90, 30, WorkoutVo.SportType.CLIMBING));

        List<WorkoutVo> hiking = display.getWorkouts(janeDoe, WorkoutVo.SportType.HIKING);
        Assert.assertEquals(hiking.size(), 2, "Filtering was not successfull");
        Assert.assertTrue(hiking.contains( new WorkoutVo(80, 40, WorkoutVo.SportType.HIKING)));
        Assert.assertTrue(hiking.contains( new WorkoutVo(70, 60, WorkoutVo.SportType.HIKING)));
    }

    @Test
    public void testWrongUser() {
        WorkoutDisplay display = new WorkoutDisplay(testDb);
        List<WorkoutVo> workouts = display.getAllWorkouts(new User("Not Existing", 1, 1, User.Sex.MALE));
        Assert.assertTrue(null == workouts, "Workouts have been found");
    }
}
