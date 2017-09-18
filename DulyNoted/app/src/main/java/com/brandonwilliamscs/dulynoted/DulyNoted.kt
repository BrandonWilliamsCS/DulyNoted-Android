package com.brandonwilliamscs.dulynoted

import com.facebook.litho.stetho.LithoWebKitInspector
import com.facebook.stetho.Stetho
import com.facebook.soloader.SoLoader
import android.app.Application



/**
 * Application class houses the initialization code for SoLoader and Stetho.
 * Created by Brandon on 9/15/2017.
 */
class DulyNoted : Application() {
    override fun onCreate() {
        super.onCreate()
        // Side-effect! But it's initialization, so don't count it.
        SoLoader.init(this, false)
        Stetho.initialize(
                Stetho.newInitializerBuilder(this)
                        .enableWebKitInspector(LithoWebKitInspector(this))
                        .build())
    }
}
