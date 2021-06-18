package kr.hs.emirim.ohm

import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
data class Calendars( val title : String? = null, val subject : String? = null, val time : String? = null, val memo : String? = null)


