package com.eutechpro;


import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.test.espresso.matcher.BoundedMatcher;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import org.hamcrest.Description;
import org.hamcrest.Matcher;

@SuppressWarnings("unused")
public class RecyclerViewMatchers {
    public static Matcher<View> atPosition(final int position, @NonNull final Matcher<View> itemMatcher) {
        return new BoundedMatcher<View, RecyclerView>(RecyclerView.class) {
            @Override
            public void describeTo(Description description) {
                description.appendText(createDescription(itemMatcher, position));
                itemMatcher.describeTo(description);
            }

            @Override
            protected boolean matchesSafely(final RecyclerView view) {
                RecyclerView.ViewHolder viewHolder = view.findViewHolderForAdapterPosition(position);
                if (viewHolder == null) {
                    return false;
                }
                return itemMatcher.matches(viewHolder.itemView);
            }
        };
    }


    public static Matcher<View> atPositionOnView(final int position, final Matcher<View> itemMatcher, @IdRes final int targetViewId) {
        return new BoundedMatcher<View, RecyclerView>(RecyclerView.class) {
            @Override
            public void describeTo(Description description) {
                description.appendText(createDescription(itemMatcher, position));
            }

            @Override
            public boolean matchesSafely(final RecyclerView recyclerView) {
                RecyclerView.ViewHolder viewHolder = recyclerView.findViewHolderForAdapterPosition(position);
                View                    targetView = viewHolder.itemView.findViewById(targetViewId);
                return itemMatcher.matches(targetView);
            }
        };
    }

    private static String createDescription(@NonNull Matcher<View> itemMatcher, int position) {
        return "that there is a view " + itemMatcher + " at position " + position + " in a RecyclerView";
    }
}
