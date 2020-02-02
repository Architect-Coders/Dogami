package com.margge.data.repository

import com.margge.data.source.LocationDataSource
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.verifyZeroInteractions
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class LocationRepositoryTest {

    private lateinit var locationRepository: LocationRepository
    @Mock
    lateinit var locationDataSource: LocationDataSource
    @Mock
    lateinit var permissionHelper: PermissionHelper

    @Before
    fun setUp() {
        locationRepository = LocationRepository(locationDataSource, permissionHelper)
    }

    @Test
    fun `given checkPermission is true when getLastLocation then locationDataSource should call getLastLocation`() {
        runBlocking {
            //Given
            whenever(permissionHelper.checkPermission()).thenReturn(true)
            whenever(locationDataSource.getLastLocation()).thenReturn("CO")

            //When
            locationRepository.getLastLocation()

            //Then
            verify(locationDataSource).getLastLocation()
        }
    }

    @Test
    fun `given checkPermission is false when getLastLocation then verify zero interactions with locationDataSource`() {
        runBlocking {
            //Given
            whenever(permissionHelper.checkPermission()).thenReturn(false)

            //When
            locationRepository.getLastLocation()

            //Then
            verifyZeroInteractions(locationDataSource)
        }
    }
}