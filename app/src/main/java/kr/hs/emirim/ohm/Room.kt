package kr.hs.emirim.ohm

import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
data class Room(val roomname: String? = null, val roomtopic : String? = null) {
}
