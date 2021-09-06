package com.salazarev.hw19mvvmkotlindocokhttprxjava.uitests

import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import com.salazarev.hw19mvvmkotlindocokhttprxjava.R
import com.salazarev.hw19mvvmkotlindocokhttprxjava.view.information.InformationActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@LargeTest
class InformationActivityTest {

    @get:Rule
    val activityRule = ActivityScenarioRule(InformationActivity::class.java)

    @Test
    fun checkDisplayedViews() {
        onView(ViewMatchers.withId(R.id.tv_gold_cost)).check(
            ViewAssertions.matches(ViewMatchers.isDisplayed())
        )
        onView(ViewMatchers.withId(R.id.tv_text_head)).check(
            ViewAssertions.matches(ViewMatchers.isDisplayed())
        )
    }
}