package com.notadeveloper.app.ilovereadin

import android.app.Application
import com.facebook.stetho.Stetho
import com.squareup.leakcanary.LeakCanary


/**
 * Created by krsnv on 03-Jul-17.
 */
class MyApplication : Application() {

  override fun onCreate() {
    super.onCreate()
    if (LeakCanary.isInAnalyzerProcess(this)) {
      // This process is dedicated to LeakCanary for heap analysis.
      // You should not init your app in this process.
      return
    }
    LeakCanary.install(this)
    Stetho.initializeWithDefaults(this)
    // Normal app init code...
  }
}