package common;

import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

/**
 * Created by Rebeka on 2015. 10. 23..
 */
public class PeldaTest {

    @Test
    @Parameters( {"userNum", "workoutPerUser"})
    public void peldaTest(final int userNum, final int workoutPerUser) {
        TestDatabase db = new TestDatabase(userNum, workoutPerUser);

        Assert.assertEquals(db.users.size(), userNum, "common.User number is not as expected");
        int workoutSize = db.workouts.values().stream().mapToInt(l -> l.size()).sum();
        Assert.assertEquals(workoutSize, userNum*workoutPerUser, "Workout number is not as expected");
    }
}
