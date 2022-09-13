package com.interview.android.leaktest

import androidx.test.InstrumentationRegistry
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import com.interview.android.guesscount.R
import com.interview.android.leaktest.setup.IdentifyMemoryLeak
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class LeakDetectionTest {

    @get:Rule
    var mainActivityActivityTestRule = ActivityTestRule(MainActivity::class.java)

    @Test
    @IdentifyMemoryLeak
    fun memoryLeakDetection() {
        // AppContext under test
        val appContext = InstrumentationRegistry.getTargetContext()

        Espresso.onView(withId(R.id.editTextCount)).perform(typeText("120"))
        Espresso.onView(withId(R.id.buttonStart)).perform(click())
        Espresso.pressBack()
    }

}
