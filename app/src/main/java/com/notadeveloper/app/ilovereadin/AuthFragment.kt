package com.notadeveloper.app.ilovereadin

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_auth.Name
import kotlinx.android.synthetic.main.fragment_auth.buttonLogin
import kotlinx.android.synthetic.main.fragment_auth.checkBox
import kotlinx.android.synthetic.main.fragment_auth.email
import kotlinx.android.synthetic.main.fragment_auth.password
import kotlinx.android.synthetic.main.fragment_auth.spinner
import kotlinx.android.synthetic.main.fragment_auth.spinner2
import kotlinx.android.synthetic.main.fragment_auth.textInputLayoutEmail
import kotlinx.android.synthetic.main.fragment_auth.textInputLayoutName
import kotlinx.android.synthetic.main.fragment_auth.textInputLayoutPass

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [AuthFragment.OnAuthFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [AuthFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class AuthFragment : Fragment() {


  // TODO: Rename and change types of parameters
  private var mParam1: Boolean = false

  private var mListener: OnAuthFragmentInteractionListener? = null

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    if (arguments != null) {
      mParam1 = arguments.getBoolean(ARG_PARAM1)

    }
  }

  override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
      savedInstanceState: Bundle?): View? {
    // Inflate the layout for this fragment
    return inflater!!.inflate(R.layout.fragment_auth, container, false)
  }

  // TODO: Rename method, update argument and hook method into UI event
  fun onButtonPressed(uri: Uri) {
    if (mListener != null) {
      mListener!!.onAuthFragmentInteraction(uri)
    }
  }

  override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    if (mParam1) {
      textInputLayoutName.visibility = View.VISIBLE
      spinner.visibility = View.VISIBLE
      spinner2.visibility = View.VISIBLE
      checkBox.visibility = View.VISIBLE
      buttonLogin.text = "Create New Free Account"
    } else {
      textInputLayoutName.visibility = View.GONE
      spinner.visibility = View.GONE
      spinner2.visibility = View.GONE
      checkBox.visibility = View.GONE
      buttonLogin.text = "Login"

    }
    buttonLogin.setOnClickListener {

      if (mParam1) {
        var cancel = false
        var focusView: View? = null
        textInputLayoutName.error = null
        textInputLayoutEmail.error = null
        textInputLayoutPass.error = null
        checkBox.error = null

        if (email.text.toString().isNullOrEmpty()) {
          cancel = true
          focusView = email
        } else if (password.text.toString().isNullOrEmpty()) {
          cancel = true
          focusView = password

        } else if (Name.text.toString().isNullOrEmpty()) {
          cancel = true
          focusView = Name
        } else if (spinner.selectedItemPosition == 0) {
          focusView = spinner
          cancel = true
        } else if (spinner2.selectedItemPosition == 0) {
          cancel = true
          focusView = spinner2
        } else if (!checkBox.isChecked) {
          cancel = true
          focusView = checkBox
        }
        if (cancel) {
          focusView?.requestFocus()
        } else {
          view?.snack("No Api End Point Yet!", Snackbar.LENGTH_SHORT)


        }


      } else {

        var cancel = false
        var focusView: View? = null
        textInputLayoutEmail.error = null
        textInputLayoutPass.error = null
        checkBox.error = null

        if (email.text.toString().isNullOrEmpty()) {
          cancel = true
          focusView = email
          view?.snack("Enter Valid Email")
        } else if (password.text.toString().isNullOrEmpty()) {
          cancel = true
          focusView = password
          view?.snack("Enter Valid Password")

        }
        if (cancel) {
          focusView?.requestFocus()
        } else {
          val compositeDisposable: CompositeDisposable = CompositeDisposable()
          val apiService = RetrofitInterface.create()
          compositeDisposable.add(
              apiService.authUser(email.text.toString(), password.text.toString())
                  .observeOn(AndroidSchedulers.mainThread())
                  .subscribeOn(Schedulers.io())
                  .subscribe({
                    result ->
                    view?.snack("Hello $result Login Sucessful")
                  }, { error ->
                    error.printStackTrace()
                  })
          )


        }

      }
    }

  }

  override fun onAttach(context: Context?) {
    super.onAttach(context)
    if (context is OnAuthFragmentInteractionListener) {
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
  interface OnAuthFragmentInteractionListener {
    // TODO: Update argument type and name
    fun onAuthFragmentInteraction(uri: Uri)
  }

  companion object {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private val ARG_PARAM1 = "param1"

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.

     * @param param1 Parameter 1.

     * *
     * @return A new instance of fragment AuthFragment.
     */
    // TODO: Rename and change types and number of parameters
    fun newInstance(param1: Boolean?): AuthFragment {
      val fragment = AuthFragment()
      val args = Bundle()
      args.putBoolean(ARG_PARAM1, param1!!)

      fragment.arguments = args
      return fragment
    }

  }

  fun View.snack(message: String, length: Int = Snackbar.LENGTH_LONG) {
    val snack = Snackbar.make(this, message, length)
    snack.show()
  }


}// Required empty public constructor
