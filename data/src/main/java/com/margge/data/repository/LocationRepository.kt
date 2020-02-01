package com.margge.data.repository

import com.margge.data.source.LocationDataSource

class LocationRepository(
    private val locationDataSource: LocationDataSource,
    private val permissionHelper: PermissionHelper
) {

    companion object {
        private const val DEFAULT_REGION = "C0"
    }

    suspend fun getLastLocation(): String {
        return if (permissionHelper.checkPermission()) {
            locationDataSource.getLastLocation() ?: DEFAULT_REGION
        } else {
            DEFAULT_REGION
        }
    }
}

interface PermissionHelper {

    suspend fun checkPermission(): Boolean
}