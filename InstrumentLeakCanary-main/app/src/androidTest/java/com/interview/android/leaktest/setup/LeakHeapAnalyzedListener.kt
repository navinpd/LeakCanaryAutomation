package com.interview.android.leaktest.setup

import android.util.Log
import leakcanary.OnHeapAnalyzedListener
import shark.HeapAnalysis
import shark.HeapAnalysisFailure
import shark.HeapAnalysisSuccess

class LeakHeapAnalyzedListener : OnHeapAnalyzedListener {

    override fun onHeapAnalyzed(heapAnalysis: HeapAnalysis) {
        handleResult(heapAnalysis)
    }

    private fun handleResult(heapAnalysis: HeapAnalysis) {
        when (heapAnalysis) {
            is HeapAnalysisFailure -> {
                Log.e("TEST", "onHeapAnalyzed:failure info = $heapAnalysis")
            }
            is HeapAnalysisSuccess -> {
                val leakCount = heapAnalysis.allLeaks.count()
                if (leakCount > 0) {
                    Log.e("TEST", "onHeapAnalyzed: $heapAnalysis")
                } else {
                    Log.d("TEST", "onHeapAnalyzed:success No leaks detected")
                }
            }
        }
    }
}


