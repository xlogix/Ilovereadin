package com.notadeveloper.app.ilovereadin

import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.support.v4.app.Fragment
import android.view.ContextThemeWrapper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.TextView
import kotlinx.android.synthetic.main.fragment_welcome.login
import kotlinx.android.synthetic.main.fragment_welcome.signup
import kotlinx.android.synthetic.main.fragment_welcome.textswitcher
import java.util.Timer
import java.util.TimerTask


/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [WelcomeFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [WelcomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class WelcomeFragment : Fragment() {
  private var index = 0


  private var mListener: OnWelcomeFragmentInteractionListener? = null
  private val handler = Handler()
  private var timer = Timer()


  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

  }

  override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
    textswitcher.setFactory {
      TextView(ContextThemeWrapper(context,
          R.style.myTextStyle), null, 0)
    }

    val anim_in = AnimationUtils.loadAnimation(context,
        android.R.anim.slide_in_left)
    val anim_out = AnimationUtils.loadAnimation(context,
        android.R.anim.slide_out_right)
    val introtext = resources.getStringArray(R.array.array_intro_text)
    textswitcher.inAnimation = anim_in
    textswitcher.outAnimation = anim_out


    val myRunnable = Runnable {
      if (index == introtext.size) {
        index = 0
      }
      textswitcher.setText(introtext[index])

      index++
    }


    // This will create a new Thread
    timer = Timer()
    timer.schedule(object : TimerTask() { // task to be scheduled

      override fun run() {
        handler.post(myRunnable)

      }
    }, 1000, 2500)

    signup.setOnClickListener {
      if (mListener != null) {
        mListener!!.onWelcomeFragmentInteraction(true)
      }

    }
    login.setOnClickListener {
      if (mListener != null) {
        mListener!!.onWelcomeFragmentInteraction(false)
      }
    }

  }

  override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
      savedInstanceState: Bundle?): View? {

    // Inflate the layout for this fragment
    return inflater!!.inflate(R.layout.fragment_welcome, container, false)

  }


  override fun onAttach(context: Context?) {
    super.onAttach(context)
    if (context is OnWelcomeFragmentInteractionListener) {
      mListener = context
    } else {
      throw RuntimeException(context!!.toString() + " must implement OnFragmentInteractionListener")
    }
  }

  override fun onDetach() {
    super.onDetach()
    mListener = null

  }

  /**
   * This interface must be implemented by activities that contain this
   * fragment to allow an interaction in this fragment to be communicated
   * to the activity and potentially other fragments contained in that
   * activity.
   *
   *
   * See the Android Training lesson [Communicating with Other Fragments](http://developer.android.com/training/basics/fragments/communicating.html) for more information.
   */
  interface OnWelcomeFragmentInteractionListener {
    // TODO: Update argument type and name
    fun onWelcomeFragmentInteraction(type: Boolean)
  }

  companion object {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER


    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.

     * @param param1 Parameter 1.
     * *
     * @param param2 Parameter 2.
     * *
     * @return A new instance of fragment WelcomeFragment.
     */

    fun newInstance(): WelcomeFragment {
      val fragment = WelcomeFragment()
      return fragment
    }
  }


  override fun onStop() {
    super.onStop()
    handler.removeCallbacksAndMessages(null)
    timer.cancel()
  }
}// Required empty public constructor
