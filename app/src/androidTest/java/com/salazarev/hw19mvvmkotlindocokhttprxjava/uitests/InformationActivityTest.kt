package com.salazarev.hw19mvvmkotlindocokhttprxjava.uitests

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.IdlingResource
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import com.salazarev.hw19mvvmkotlindocokhttprxjava.R
import com.salazarev.hw19mvvmkotlindocokhttprxjava.view.information.InformationActivity
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@LargeTest
class InformationActivityTest {
    private var idlingResource: IdlingResource? = null

    @Before
    fun registerIdlingResource(){
        val activityScenario = ActivityScenario.launch(InformationActivity::class.java)
        activityScenario.onActivity { activity ->
            idlingResource = activity.getIdlingResource()
            IdlingRegistry.getInstance().register(idlingResource)
        }
    }

    @Test
    fun checkDisplayedViews() {
        onView(ViewMatchers.withId(R.id.tv_gold_cost)).check(
            ViewAssertions.matches(ViewMatchers.isDisplayed())
        )
        onView(ViewMatchers.withId(R.id.tv_text_head)).check(
            ViewAssertions.matches(ViewMatchers.isDisplayed())
        )
    }

    @After
    fun unregisterIdlingResource(){
        if(idlingResource!=null) IdlingRegistry.getInstance().unregister(idlingResource)
    }
}