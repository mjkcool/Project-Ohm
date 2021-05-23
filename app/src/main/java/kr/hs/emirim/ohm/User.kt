package kr.hs.emirim.ohm

import android.net.Uri
import com.google.firebase.database.IgnoreExtraProperties


@IgnoreExtraProperties
    data class User(val nickname: String? = null, val introTxt : String? = null) {
    }
