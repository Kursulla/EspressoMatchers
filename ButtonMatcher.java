package com.eutechpro.matchers;

import android.content.res.Resources;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.annotation.DrawableRes;
import android.support.v4.content.res.ResourcesCompat;
import android.view.View;
import android.widget.Button;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

/**
 * Created by Kursulla on 14/01/16.
 */
public class ButtonMatcher {
    private static final int POSITION_LEFT = 0;
    private static final int POSITION_TOP = 1;
    private static final int POSITION_RIGHT = 2;
    private static final int POSITION_BOTTOM = 3;

    /**
     * Match does target {@link Button} has appropriate {@link Drawable} resource on place of Left compound background.
     *
     * @param resourceId
     * @return Does it match or not.
     */
    public static Matcher<View> buttonHasLeftCompoundBackground(@DrawableRes final int resourceId) {
        return new TypeSafeMatcher<View>() {
            Resources resources = null;

            @Override
            public boolean matchesSafely(View view) {
                this.resources = view.getResources();
                return match(view, resourceId, POSITION_LEFT);
            }

            @Override
            public void describeTo(Description description) {
                description.appendText("Compound image with id: " + getResourceName(resources, resourceId));
            }


        };
    }

    /**
     * Match does target {@link Button} has appropriate {@link Drawable} resource on place of Top compound background.
     *
     * @param resourceId
     * @return Does it match or not.
     */
    public static Matcher<View> buttonHasTopCompoundBackground(@DrawableRes final int resourceId) {
        return new TypeSafeMatcher<View>() {
            Resources resources = null;

            @Override
            public boolean matchesSafely(View view) {
                this.resources = view.getResources();
                return match(view, resourceId, POSITION_TOP);
            }

            @Override
            public void describeTo(Description description) {
                description.appendText("Compound image with id: " + getResourceName(resources, resourceId));
            }
        };
    }

    /**
     * Match does target {@link Button} has appropriate {@link Drawable} resource on place of Right compound background.
     *
     * @param resourceId
     * @return Does it match or not.
     */
    public static Matcher<View> buttonHasRightCompoundBackground(@DrawableRes final int resourceId) {
        return new TypeSafeMatcher<View>() {
            Resources resources = null;

            @Override
            public boolean matchesSafely(View view) {
                this.resources = view.getResources();
                return match(view, resourceId, POSITION_RIGHT);
            }

            @Override
            public void describeTo(Description description) {
                description.appendText("Compound image with id: " + getResourceName(resources, resourceId));
            }
        };
    }

    /**
     * Match does target {@link Button} has appropriate {@link Drawable} resource on place of Bottom compound background.
     *
     * @param resourceId
     * @return Does it match or not.
     */
    public static Matcher<View> buttonHasBottomCompoundBackground(@DrawableRes final int resourceId) {
        return new TypeSafeMatcher<View>() {
            Resources resources = null;

            @Override
            public boolean matchesSafely(View view) {
                this.resources = view.getResources();
                return match(view, resourceId, POSITION_BOTTOM);
            }

            @Override
            public void describeTo(Description description) {
                description.appendText("Compound image with id: " + getResourceName(resources, resourceId));
            }
        };
    }

    public static Matcher<View> buttonIsEnabled(){
        return new TypeSafeMatcher<View>() {
            Resources resources = null;
            int buttonId;
            @Override
            protected boolean matchesSafely(View view) {
                this.resources = view.getResources();
                this.buttonId = view.getId();
                return !view.isEnabled();
            }

            @Override
            public void describeTo(Description description) {
                description.appendText("Button with id "+getResourceName(resources, buttonId));
            }
        };
    }





    private static boolean match(View view, int resourceId, int position) {
        if (!(view instanceof Button)) {
            return false;
        }

        Drawable buttonDrawable = ((Button) view).getCompoundDrawables()[position];
        if(buttonDrawable == null){
            return false;
        }

        Drawable drawableToCheck = ResourcesCompat.getDrawable(view.getResources(), resourceId, null);
        if(drawableToCheck == null){
            return false;
        }
        return ((BitmapDrawable) buttonDrawable).getBitmap().equals(((BitmapDrawable) drawableToCheck).getBitmap());
    }

    private static String getResourceName(Resources resources, int resourceId) {
        String resourceName = Integer.toString(resourceId);
        if (resources != null) {
            try {
                resourceName = resources.getResourceName(resourceId);
            } catch (Resources.NotFoundException var4) {
                resourceName = String.format("%d (resource name not found)", resourceId);
            }
        }
        return resourceName;
    }
}
