package kr.hs.emirim.ohm.fragments

import android.content.Context
import android.content.res.AssetManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.fragment.app.Fragment
import kr.hs.emirim.ohm.R

class FragInitNickname: Fragment() {

    companion object{
        lateinit var nicknameInput: EditText
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_nickname_set, container, false) //setContentPane
        nicknameInput = view.findViewById(R.id.input_nickname_set)

        return view
    }
}