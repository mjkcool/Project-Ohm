package kr.hs.emirim.ohm

import android.os.Build
import androidx.appcompat.app.AppCompatDelegate

class ThemeManager {
    enum class ThemeMode { LIGHT, DARK, DEFAULT }

    fun applyTheme(themeMode: ThemeMode) = when(themeMode){
        ThemeMode.LIGHT -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        ThemeMode.DARK -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        else ->
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q){
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
            }else{
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_AUTO_BATTERY)
            }
    }
}