package com.eutechpro.matchers;

import android.content.res.Resources;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.annotation.DrawableRes;
import android.support.v4.content.res.ResourcesCompat;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;


public class ImageViewMatcher {
    public static Matcher<View> hasResource(@DrawableRes final int resourceId) {
        return new TypeSafeMatcher<View>() {
            Resources resources = null;

            @Override
            public boolean matchesSafely(View view) {
                this.resources = view.getResources();
                return match(view, resourceId);
            }

            @Override
            public void describeTo(Description description) {
                description.appendText("ImageView has no resource: " + getResourceName(resources, resourceId));
            }


        };
    }


    private static boolean match(View view, int resourceId) {
        if (!(view instanceof Button)) {
            return false;
        }

        Drawable drawableInImageView = ((ImageView) view).getDrawable();
        if (drawableInImageView == null) {
            return false;
        }

        Drawable drawableToCheck = ResourcesCompat.getDrawable(view.getResources(), resourceId, null);
        if (drawableToCheck == null) {
            return false;
        }
        return ((BitmapDrawable) drawableInImageView).getBitmap().equals(((BitmapDrawable) drawableToCheck).getBitmap());
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
