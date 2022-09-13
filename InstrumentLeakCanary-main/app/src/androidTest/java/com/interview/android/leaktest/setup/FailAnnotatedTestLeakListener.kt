package com.interview.android.leaktest.setup

import leakcanary.FailTestOnLeakRunListener
import leakcanary.LeakCanary
import org.junit.runner.Description

class FailAnnotatedTestLeakListener : FailTestOnLeakRunListener() {

    override fun skipLeakDetectionReason(description: Description): String? {
        // Check if test method is annotated with our custom annotation
        val result = if (description.getAnnotation(IdentifyMemoryLeak::class.java) == null) {
            "Test is not annotated with @IdentifyMemoryLeak"
        } else {
            null
        }

        if (result == null) {
            // If method is annotated setup HeapAnalyzerListener
            LeakCanary.config = LeakCanary.config.copy(
                // Provide custom Heap analyser
                onHeapAnalyzedListener = LeakHeapAnalyzedListener()
            )
        }

        return result
    }
}



