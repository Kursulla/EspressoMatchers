package ch.teleboy.matchers;

import android.view.View;
import android.widget.SeekBar;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

/**
 * Small set of matchers for asserting SeekBar states.
 */
public class SeekBarMatchers {
    /**
     * Matches does SeekBar has set position (progress) at desired position.
     * @param position The value we want to validate against
     */
    public static Matcher<View> isAtPosition(final int position) {
        return new TypeSafeMatcher<View>() {
            @Override
            protected boolean matchesSafely(View view) {
                if (!(view instanceof SeekBar)) {
                    return false;
                }
                SeekBar seekBar = (SeekBar) view;

                return seekBar.getProgress() == position;
            }

            @Override
            public void describeTo(Description description) {
                description.appendText("expected to be at position: " + position);
            }
        };
    }

    /**
     * Matches does SeekBar has set max value at desired value.
     * @param maxValue The value we want to validate against
     */
    public static Matcher<View> hasMaxValue(final int maxValue) {
        return new TypeSafeMatcher<View>() {
            @Override
            protected boolean matchesSafely(View view) {
                if (!(view instanceof SeekBar)) {
                    return false;
                }
                SeekBar seekBar = (SeekBar) view;

                return seekBar.getMax() == maxValue;
            }

            @Override
            public void describeTo(Description description) {
                description.appendText("expected to have max value: " + maxValue);
            }
        };
    }
}
