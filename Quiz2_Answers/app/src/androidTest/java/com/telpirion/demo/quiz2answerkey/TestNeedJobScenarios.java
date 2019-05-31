package com.telpirion.demo.quiz2answerkey;

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

public class TestNeedJobScenarios {

    @Rule
    public ActivityTestRule<NeedJobActivity> activityTestRule =
            new ActivityTestRule<>(NeedJobActivity.class);

    /*
    +1 points for @Test annotation above method signature.

    Deductions:
    -1: Method must have @Test Java annotation.
     */
    @Test
    public void testGetRobotBuilderJob() {

        /*
        +1 points for typing text in correctly.

        Deductions:
        -1: Method needs to use onView() to select the EditText and
          perform(), typeText() to type in the EditText.
        -0.5: Test needs to type "Robot Builder" into EditText with ID need_a_job

         */
        onView(withId(R.id.need_a_job))
                .perform(typeText("Robot Builder"));

        /*
        +1 points for clicking button correctly.

        Deductions:
        -1: Method needs to use onView() to select the Button and
          perform(), click() to click it.
        -0.5: Test needs to click a button with ID get_a_job
         */
        onView(withId(R.id.get_a_job))
                .perform(click());

        /*
        +2 points for checking results correctly

        Deductions:
        -2: Method needs to use onView() to select the TextView and
          check(), matches(), and withText() to check value of TextView.
        -0.5: Method needs to check value of TextView with ID ur_new_job
        -0.5: Method needs to preface ID with `R.id.`
         */
        onView(withId(R.id.ur_new_job))
                .check(matches(withText("Congratulations! You are a robot builder.")));
    }

}
