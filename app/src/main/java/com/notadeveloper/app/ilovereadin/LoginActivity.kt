package com.notadeveloper.app.ilovereadin

import android.graphics.Typeface
import android.os.Bundle
import android.os.Handler
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.view.ContextThemeWrapper
import android.view.animation.AnimationUtils
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_login.view.*
import java.util.*
import android.view.Gravity
import android.widget.ViewSwitcher
import android.support.v4.view.LayoutInflaterCompat.setFactory
import android.view.WindowManager


class LoginActivity : AppCompatActivity() {
    private var index = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        textswitcher.setFactory {
            TextView(ContextThemeWrapper(this@LoginActivity,
                    R.style.myTextStyle), null, 0)
        }
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        val anim_in = AnimationUtils.loadAnimation(this,
                android.R.anim.slide_in_left)
        val anim_out = AnimationUtils.loadAnimation(this,
                android.R.anim.slide_out_right)
        val introtext = getResources().getStringArray(R.array.intro_text);
        textswitcher.setInAnimation(anim_in)
        textswitcher.setOutAnimation(anim_out)

        val handler = Handler()
        val Update = Runnable {
            if (index == introtext.size) {
                index = 0
            }
            textswitcher.setText(introtext[index])

            index++
        }

        val timer = Timer() // This will create a new Thread
        timer.schedule(object : TimerTask() { // task to be scheduled

            override fun run() {
                handler.post(Update)
            }
        }, 1000, 2500)

    }


}
