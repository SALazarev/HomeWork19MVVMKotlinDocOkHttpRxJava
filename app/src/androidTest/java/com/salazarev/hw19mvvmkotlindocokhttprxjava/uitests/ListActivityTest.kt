package com.salazarev.hw19mvvmkotlindocokhttprxjava.uitests

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.Espresso.pressBack
import androidx.test.espresso.PerformException
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import com.salazarev.hw19mvvmkotlindocokhttprxjava.R
import com.salazarev.hw19mvvmkotlindocokhttprxjava.view.list.ListActivity
import com.salazarev.hw19mvvmkotlindocokhttprxjava.view.list.rv.ItemViewHolder
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@LargeTest
class ListActivityTest {

    @get:Rule
    val activityRule =ActivityScenarioRule(ListActivity::class.java)

    @Test(expected = PerformException::class)
    fun listGoesOverTheFold() {
        onView(withId(R.id.rv_items)).perform(RecyclerViewActions.scrollTo<ItemViewHolder>(hasDescendant(withText("2021-07-23"))))
    }


    @Test
    fun rvScrollToItemAndClick(){
        onView(withId(R.id.rv_items))
            .check(matches(isDisplayed()))
            .perform(RecyclerViewActions.actionOnItemAtPosition<ItemViewHolder>(0,click()))
        onView(withId(R.id.tv_gold_cost))
            .check(matches(isDisplayed()))
            .check(matches(withText("225.24 PLN")))
        pressBack()
        rvIsVisible()
    }

    @Test
    fun rvIsVisible(){
        onView(withId(R.id.rv_items)).check(matches(isDisplayed()))
    }
}