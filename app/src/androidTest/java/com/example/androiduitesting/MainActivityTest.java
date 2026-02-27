package com.example.androiduitesting;



import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;

import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.anything;



import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import org.junit.runner.RunWith;


import org.junit.Rule;
import org.junit.Test;


import androidx.test.espresso.action.ViewActions;

@RunWith(AndroidJUnit4.class)
public class MainActivityTest {
    // Create the actual 'screen'
    @Rule
    public ActivityScenarioRule<MainActivity> scenario = new ActivityScenarioRule<>(MainActivity.class);

    public void addCity(String cityName){

        // Click on Add City button
        onView(withId(R.id.button_add)).perform(click());
        // Type "Edmonton" in the editText
        onView(withId(R.id.editText_name)).perform(ViewActions.typeText(cityName));
        // Click on Confirm
        onView(withId(R.id.button_confirm)).perform(click());
    }

    @Test
    public void testIfSwitches(){
        addCity("Edmonton");

        // Click the city
        onData(anything())
                .inAdapterView(withId(R.id.city_list))
                .atPosition(0)
                .perform(click());

        // doing the id CityName only exists in the show_activity so if it finds it then it has switched
        onView(withId(R.id.cityName)).check(matches(isDisplayed()));

        // Now check if the city name is also Edmonton
        onView(withId(R.id.cityName)).check(matches(withText("Edmonton")));


    }

    @Test
    public void testIfBack(){
        addCity("Edmonton");

        // Click the city
        onData(anything())
                .inAdapterView(withId(R.id.city_list))
                .atPosition(0)
                .perform(click());

        onView(withId(R.id.backButton)).perform(click());
        // doing the id CityName only exists in the show_activity so if it finds it then it has switched
        onView(withId(R.id.city_list)).check(matches(isDisplayed()));

    }

}

