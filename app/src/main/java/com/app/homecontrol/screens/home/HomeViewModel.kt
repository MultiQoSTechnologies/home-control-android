package com.app.homecontrol.screens.home

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.homecontrol.core.utils.FirestoreKeys
import com.app.homecontrol.domain.data.Room
import com.app.homecontrol.domain.data.deviceList
import com.app.homecontrol.domain.data.roomList
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.toObject
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val firestore: FirebaseFirestore
) : ViewModel() {

    private val _rooms = mutableStateListOf<Room>()
    val rooms: List<Room>
        get() = _rooms.sortedBy { it.id }


    init {
        viewModelScope.launch {
//            addRoom()
//            addDevices()
            getRooms()
        }
    }

    private suspend fun getRooms() {
        try {
            firestore.collection(FirestoreKeys.COLLECTION_NAME_ROOM)
                .get()
                .await()
                .forEach {
                    _rooms.add(it.toObject<Room>())
                }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun addRoom() {
        try {
            for (tempRoom in roomList) {
                firestore.collection(FirestoreKeys.COLLECTION_NAME_ROOM)
                    .add(tempRoom)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun addDevices() {
        try {
            for (tempDevice in deviceList) {
                firestore.collection(FirestoreKeys.COLLECTION_NAME_DEVICE)
                    .add(tempDevice)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}