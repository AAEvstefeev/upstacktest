package com.example.base.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import com.example.base.MainActivity
import com.example.base.R

class LoginFragment : Fragment() {

    lateinit var button : Button
    lateinit var editText: EditText

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.login_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        button = view.findViewById(R.id.button_login)
        editText = view.findViewById(R.id.login_edit_text)


        button.setOnClickListener {
            val login = editText.text.toString()
            val mainActivity  = activity as MainActivity
            mainActivity.showList(login)
        }
    }
}