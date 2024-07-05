package com.app.homecontrol.screens.room

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.homecontrol.core.utils.FirestoreKeys
import com.app.homecontrol.core.utils.toBoolean
import com.app.homecontrol.domain.data.Device
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions
import com.google.firebase.firestore.toObject
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

@HiltViewModel
class RoomViewModel @Inject constructor(
    private val firestore: FirebaseFirestore
) : ViewModel() {

    private val _devices = mutableStateListOf<Device>()
    val devices: List<Device>
        get() = _devices.sortedBy { it.id }

    private val _isBulbOn = mutableStateOf(false)
    val isBulbOn: State<Boolean> = _isBulbOn

    private val _bulbIntensity = mutableStateOf(0)
    val bulbIntensity: State<Int> = _bulbIntensity

    private val _fanSpeed = mutableStateOf(0)
    val fanSpeed: State<Int> = _fanSpeed

    private val _isFanOn = mutableStateOf(false)
    val isFanOn: State<Boolean> = _isFanOn

    init {
        viewModelScope.launch {
            observeBulbStatus()
            observeFan()
        }
    }

    suspend fun getDevices(roomId: Int) {
        try {
            firestore.collection(FirestoreKeys.COLLECTION_NAME_DEVICE)
                .whereEqualTo("roomId", roomId)
                .get()
                .await()
                .forEach {
                    _devices.add(it.toObject<Device>())
                }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun updateDeviceStatus(device: Device, status: Int) = viewModelScope.launch {
        try {
            val deviceQuery = firestore.collection(FirestoreKeys.COLLECTION_NAME_DEVICE)
                .whereEqualTo("id", device.id)
                .get()
                .await()

            val map = mutableMapOf<String, Any>()
            map["status"] = status

            for (document in deviceQuery) {
                try {
                    firestore.collection(FirestoreKeys.COLLECTION_NAME_DEVICE).document(document.id).set(
                        map,
                        SetOptions.merge()
                    ).await()

                    val index = _devices.indexOf(device)
                    _devices[index] = device.copy(
                        status = status
                    )
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }

        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun updateDeviceValue(device: Device, value: Int) = viewModelScope.launch {
        try {
            val deviceQuery = firestore.collection(FirestoreKeys.COLLECTION_NAME_DEVICE)
                .whereEqualTo("id", device.id)
                .get()
                .await()

            val map = mutableMapOf<String, Any>()
            if (device.deviceType == FirestoreKeys.FAN) {
                map["fanLevel"] = value
            }else if (device.deviceType == FirestoreKeys.AC){
                map["acTemperature"] = value
            }else if (device.deviceType == FirestoreKeys.LIGHT){
                map["lightIntensity"] = value
            }

            for (document in deviceQuery) {
                try {
                    firestore.collection(FirestoreKeys.COLLECTION_NAME_DEVICE).document(document.id).set(
                        map,
                        SetOptions.merge()
                    ).await()

                    val index = _devices.indexOf(device)
                    _devices[index] = device.copy(
                        fanLevel = value
                    )
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }

        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun observeBulbStatus() {
        firestore.collection(FirestoreKeys.COLLECTION_NAME_DEVICE)
            .whereEqualTo("id", 1)
            .addSnapshotListener { value, error ->
                error?.let {
                    return@addSnapshotListener
                }

                value?.let { querySnapshot ->
                    for (document in querySnapshot.documents) {
                        document.toObject<Device>()?.let {
                            _isBulbOn.value = it.status.toBoolean()
                            _bulbIntensity.value = it.lightIntensity ?: 0
                        }
                    }
                }

            }
    }

    private fun observeFan() {
        firestore.collection(FirestoreKeys.COLLECTION_NAME_DEVICE)
            .whereEqualTo("id", 5)
            .addSnapshotListener { value, error ->
                error?.let {
                    return@addSnapshotListener
                }

                value?.let { querySnapshot ->
                    for (document in querySnapshot.documents) {
                        document.toObject<Device>()?.let {
                            _isFanOn.value = it.status.toBoolean()

                            _fanSpeed.value = when(it.fanLevel ?: 0){
                                1 -> 2000
                                2 -> 1500
                                3 -> 1000
                                4 -> 500
                                5 -> 200
                                else -> 0
                            }
                        }
                    }
                }

            }
    }
}