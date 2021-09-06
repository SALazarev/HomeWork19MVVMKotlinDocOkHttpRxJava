package com.salazarev.hw19mvvmkotlindocokhttprxjava.uitests

import android.content.Intent
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.Espresso.pressBack
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.IdlingResource
import androidx.test.espresso.PerformException
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.core.internal.deps.guava.collect.Iterables
import androidx.test.espresso.intent.Intents
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.ext.truth.content.IntentSubject.assertThat
import androidx.test.filters.LargeTest
import com.salazarev.hw19mvvmkotlindocokhttprxjava.R
import com.salazarev.hw19mvvmkotlindocokhttprxjava.view.information.InformationActivity
import com.salazarev.hw19mvvmkotlindocokhttprxjava.view.list.ListActivity
import com.salazarev.hw19mvvmkotlindocokhttprxjava.view.list.rv.ItemViewHolder
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
@LargeTest
class ListActivityTest {

    companion object {
        const val PRICE = "165.83 PLN"
        const val FAIL_DATE = "2013-01-01"
    }

    private var idlingResource: IdlingResource? = null

    @Before
    fun intentsInit() {
        Intents.init()
    }

    @Before
    fun registerIdlingResource() {
        val activityScenario = ActivityScenario.launch(ListActivity::class.java)
        activityScenario.onActivity { activity ->
            idlingResource = activity.getIdlingResource()
            IdlingRegistry.getInstance().register(idlingResource)
        }
    }

    @Test(expected = PerformException::class)
    fun checkListOutItem() {
        onView(withId(R.id.rv_items)).perform(
            RecyclerViewActions.scrollTo<ItemViewHolder>(
                hasDescendant(withText(FAIL_DATE))
            )
        )
    }


    @Test
    fun rvScrollToItemAndClick() {
        onView(withId(R.id.rv_items))
            .check(matches(isDisplayed()))
            .perform(RecyclerViewActions.actionOnItemAtPosition<ItemViewHolder>(0, click()))

        val intent: Intent = Iterables.getOnlyElement(Intents.getIntents())
        assertThat(intent).hasComponentClass(InformationActivity::class.java)

        onView(withId(R.id.tv_gold_cost))
            .check(matches(isDisplayed()))
            .check(matches(withText(PRICE)))
        pressBack()
        rvIsVisible()
    }

    @Test
    fun rvIsVisible() {
        onView(withId(R.id.rv_items)).check(matches(isDisplayed()))
    }

    @After
    fun intentsTeardown() {
        Intents.release()
    }

    @After
    fun unregisterIdlingResource() {
        if (idlingResource != null) IdlingRegistry.getInstance().unregister(idlingResource)
    }
}