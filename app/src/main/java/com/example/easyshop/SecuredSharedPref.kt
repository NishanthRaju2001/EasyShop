package com.example.easyshop

import android.content.Context
import android.content.SharedPreferences
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKeys
import com.example.easyshop.PrefConst.SECURED_LOGIN
import com.example.easyshop.PrefConst.SECURED_REGISTER

object SecuredSharedPref {
    fun getSecuredSharePref(context: Context): SharedPreferences {
        val masterAlias = MasterKeys.getOrCreate(MasterKeys.AES256_GCM_SPEC)
        return EncryptedSharedPreferences.create(
            SECURED_LOGIN,
            masterAlias,
            context,
            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
        )
    }
    fun getRegisterSecuredSharePref(context: Context): SharedPreferences {
        val masterAlias = MasterKeys.getOrCreate(MasterKeys.AES256_GCM_SPEC)
        return EncryptedSharedPreferences.create(
            SECURED_REGISTER,
            masterAlias,
            context,
            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
        )
    }
}