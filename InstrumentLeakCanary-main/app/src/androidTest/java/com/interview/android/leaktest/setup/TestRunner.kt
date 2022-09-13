package com.interview.android.leaktest.setup

import android.os.Bundle
import androidx.test.runner.AndroidJUnitRunner

@Suppress("unused")
open class TestRunner : AndroidJUnitRunner() {
    override fun onCreate(arguments: Bundle) {
        val listeners = listOfNotNull(
            arguments.getCharSequence("listener"),
            FailAnnotatedTestLeakListener::class.qualifiedName,
        ).joinToString(",")

        arguments.putCharSequence("listener", listeners)
        super.onCreate(arguments)
    }
}


