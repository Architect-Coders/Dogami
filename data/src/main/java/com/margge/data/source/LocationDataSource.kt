package com.margge.data.source

interface LocationDataSource {
    suspend fun getLastLocation(): String?
}