package ch.teleboy.matchers;

import android.graphics.Typeface;
import android.support.annotation.ColorInt;
import android.view.View;
import android.widget.TextView;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

/**
 * Created by Kursulla on 14/01/16.
 */
public class TextViewMatchers {
    /**
     * Match does target {@link TextView} has {@link Typeface#BOLD} style set
     * @return Does it match or not.
     */
    public static Matcher<View> isBold() {
        return new TypeSafeMatcher<View>() {

            @Override
            public boolean matchesSafely(View view) {
                if (!(view instanceof TextView)) {
                    return false;
                }
                TextView textView = (TextView)view;

                if (textView.getTypeface() != null) {
                   return textView.getTypeface().getStyle() == Typeface.BOLD;
                }

                return false;
            }

            @Override
            public void describeTo(Description description) {
            }
        };
    }

    /**
     * Match does target {@link TextView} has desired color set.
     * <br/>
     * @see ColorInt
     * @param colorInt {@link ColorInt} int we want to math.
     * @return Does it match or not.
     */
    public static Matcher<View> hasColor(@ColorInt final int colorInt) {
        return new TypeSafeMatcher<View>() {

            @Override
            public boolean matchesSafely(View view) {
                if (!(view instanceof TextView)) {
                    return false;
                }
                TextView textView = (TextView) view;

                return textView.getCurrentTextColor()== colorInt;
            }

            @Override
            public void describeTo(Description description) {
            }
        };
    }
}
