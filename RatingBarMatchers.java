package com.eutechpro;


import android.support.test.espresso.matcher.BoundedMatcher;
import android.view.View;
import android.widget.RatingBar;

import org.hamcrest.Description;
import org.hamcrest.Matcher;

@SuppressWarnings("unused")
public class RatingBarMatchers {
    public static Matcher<View> viewWithIdHasRating(int rating) {
        return new BoundedMatcher<View, RatingBar>(RatingBar.class) {
            @Override
            public void describeTo(Description description) {
                description.appendText("that has rating = " + rating);
            }

            @Override
            public boolean matchesSafely(final RatingBar ratingBar) {
                return ratingBar.getRating() == rating;
            }
        };
    }
}
