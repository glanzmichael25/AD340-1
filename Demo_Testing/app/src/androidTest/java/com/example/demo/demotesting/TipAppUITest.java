package com.example.demo.demotesting;

import androidx.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;
import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class TipAppUITest {

    @Rule
    public ActivityTestRule<MainActivity> activityRule =
            new ActivityTestRule<>(MainActivity.class);

    @Test
    public void testFoodServiceGoodTipGeneration() {
        onView(withId(R.id.txtbx_svc_amt))
                .perform(typeText("10.00"));

        // Set the value of the service type
        // spinner
        onView(withId(R.id.spn_svc_type))
                .perform(click());
        onData(allOf(is(instanceOf(String.class)), is("Food server")))
                .perform(click());

        // Set the value of the quality
        // spinner
        onView(withId(R.id.spn_quality))
                .perform(click());
        onData(allOf(is(instanceOf(String.class)), is("Good")))
                .perform(click());

        onView(withId(R.id.button)).perform(click());

        onView(withId(R.id.tip_output)).check(matches(withText("$2.00")));

    }

}
