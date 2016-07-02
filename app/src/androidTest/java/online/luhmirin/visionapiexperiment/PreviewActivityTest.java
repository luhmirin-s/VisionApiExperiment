package online.luhmirin.visionapiexperiment;


import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static junit.framework.Assert.assertEquals;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class PreviewActivityTest {

    @Rule
    public ActivityTestRule<PreviewActivity> activityRule = new ActivityTestRule<>(PreviewActivity.class);

    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }
}