package com.telpirion.demo.quiz2answerkey;

import android.widget.TextView;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;

import static com.google.common.truth.Truth.assertThat;
import static org.junit.Assert.assertEquals;

@RunWith(RobolectricTestRunner.class)
public class TestTatooineActivityRobolectric {

    // +1 point for having the @Test annotation
    @Test
    public void testJediMindTrick() {

        /*
         +1 point for creating the activity using Robolectric

         Deductions:
         -1: Need to create instance of activity using Robolectric.setupActivity().
         -0.5: Need to create instance of TatooineActivity
          */
        TatooineActivity activity =
                Robolectric.setupActivity(TatooineActivity.class);

        /*
        +1 point for selecting the views correctly

        Deductions:
        -1: With Robolectric, you select views using Activity.findViewById().
        */
        TextView textView = activity.findViewById(R.id.lbl_stormtrooper_brain);
        activity.findViewById(R.id.btn_use_the_force).performClick();

        /*
         +2 point for having a correct assert

        Deductions:
        -1: The correct way to get a view's text with Robolectric is with View.getText().getString().
            onView() is an Espresso test selector.
        -1: assertTrue() takes a single boolean value or expression. assertThat() is best for this.
        -0.5: assertThat().isEqualTo() can fail when comparing strings. Better to use assertThat().contains().
        */

        assertThat(textView.getText().toString())
                .contains("These are not the droids you're looking for.");

        /*
        Misc deductions:
        -0.5: The same test needed to check that the button was clicked AND the text changed to "These are not the droids you're looking for."
         */
    }

}
