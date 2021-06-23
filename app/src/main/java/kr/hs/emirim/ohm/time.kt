package kr.hs.emirim.ohm

import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
data class time( val hour : String , val min : String , val sec : String)