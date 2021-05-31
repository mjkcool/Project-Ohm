package kr.hs.emirim.ohm.fragments

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.ContactsContract
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.theartofdev.edmodo.cropper.CropImage
import de.hdodenhof.circleimageview.CircleImageView
import kr.hs.emirim.ohm.ProfileInitActivity.Companion.ProfileInitContext
import kr.hs.emirim.ohm.R
import kr.hs.emirim.ohm.fragments.FragInitNickname.Companion.nicknameInput

class FragInitImage: Fragment() {

    companion object{
        lateinit var profileImageView: CircleImageView
        lateinit var resetImageButton: FloatingActionButton
    }
    lateinit var nicknameView: TextView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_image_set, container, false) //setContentPane

        profileImageView = view.findViewById(R.id.init_profileimg_view)
        resetImageButton = view.findViewById(R.id.init_profileimg_reset_btn)
        nicknameView = view.findViewById(R.id.init_profileimg_nickname_view)
        nicknameView.text = nicknameInput.text

        resetImageButton.setOnClickListener{
            CropImage.activity().setAspectRatio(1, 1).start(ProfileInitContext as Activity)
        }

        return view
    }

}