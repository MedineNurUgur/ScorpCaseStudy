package com.example.scorpcasestudy

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class ScorpCaseStudyApp : Application(){

    companion object {
        var instance: ScorpCaseStudyApp? = null
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }
}