package com.notadeveloper.app.ilovereadin

import android.net.Uri
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.WindowManager


class LoginActivity : AppCompatActivity(), WelcomeFragment.OnWelcomeFragmentInteractionListener, AuthFragment.OnAuthFragmentInteractionListener {


  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_login)
    window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
        WindowManager.LayoutParams.FLAG_FULLSCREEN)
    val fragment = WelcomeFragment.newInstance()
    supportFragmentManager.beginTransaction().setCustomAnimations(android.R.anim.fade_in,
        android.R.anim.fade_out, android.R.anim.fade_in, android.R.anim.fade_out)
        .replace(R.id.fragment_login, fragment).addToBackStack(null).commit()


  }

  override fun onWelcomeFragmentInteraction(type: Boolean) {
    val fragment = AuthFragment.newInstance(type)
    supportFragmentManager.beginTransaction().setCustomAnimations(android.R.anim.fade_in,
        android.R.anim.fade_out, android.R.anim.fade_in, android.R.anim.fade_out)
        .replace(R.id.fragment_login, fragment).addToBackStack(null).commit()

  }


  override fun onAuthFragmentInteraction(uri: Uri) {
    TODO(
        "not implemented") //To change body of created functions use File | Settings | File Templates.
  }
}
