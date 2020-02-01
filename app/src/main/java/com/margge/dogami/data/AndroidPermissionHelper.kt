package com.margge.dogami.data

import android.Manifest
import android.app.Application
import android.content.pm.PackageManager
import androidx.core.content.ContextCompat
import com.margge.data.repository.PermissionHelper

class AndroidPermissionHelper(private val application: Application) : PermissionHelper {

    override suspend fun checkPermission(): Boolean {
        return (ContextCompat.checkSelfPermission(
            application,
            Manifest.permission.ACCESS_COARSE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED)
    }
}