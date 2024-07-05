package com.app.homecontrol.domain.data

import com.app.homecontrol.core.utils.FirestoreKeys


data class Room(
    val id: Int? = 0,
    val roomName: String? = null,
    val totalDevices: Int? = 0
)

data class Device(
    val id: Int? = 0,
    val roomId: Int? = 0,
    val roomName: String? = null,
    val deviceName: String? = null,
    val deviceType: String? = null,
    val status: Int? = 0,
    val fanLevel: Int? = 0,
    val lightIntensity: Int? = 0,
    val acTemperature: Int? = 0
)

val roomList: List<Room> = arrayListOf(
    Room(1, "Living Room", 8), // 4 lights, 2 fan, 1 ac, 1 tv
    Room(2, "Bedroom", 4), // 2 lights, 1 fan, 1 ac
    Room(3, "Kitchen", 4), // 2 lights, 1 fan, 1 ac
    Room(4, "Drawing Room", 4), // 4 lights, 1 fan, 1 ac
    Room(5, "Gaming Room", 5), // 4 lights, 1 fan, 1 ac, 1 tv
)

val deviceList: List<Device> = arrayListOf(
    Device(1, 1, "Living Room", "Front Left Light", FirestoreKeys.LIGHT, 0),
    Device(2, 1, "Living Room", "Front Right Light", FirestoreKeys.LIGHT, 0),
    Device(3, 1, "Living Room", "Back Left Light", FirestoreKeys.LIGHT, 0),
    Device(4, 1, "Living Room", "Back Right Light", FirestoreKeys.LIGHT, 0),
    Device(5, 1, "Living Room", "Front Fan", FirestoreKeys.FAN, 0, 0),
    Device(6, 1, "Living Room", "Back Fan", FirestoreKeys.FAN, 0, 0),
    Device(7, 1, "Living Room", "AC", FirestoreKeys.AC, 0, 0, 0, 20),
    Device(8, 1, "Living Room", "TV", FirestoreKeys.TV, 0, 0),

    Device(9, 2, "Bedroom", "Left Light", FirestoreKeys.LIGHT, 0),
    Device(10, 2, "Bedroom", "Right Light", FirestoreKeys.LIGHT, 0),
    Device(11, 2, "Bedroom", "Fan", FirestoreKeys.FAN, 0, 0),
    Device(12, 2, "Bedroom", "AC", FirestoreKeys.AC, 0, 0, 0, 20),

    Device(13, 3, "Kitchen", "Left Light", FirestoreKeys.LIGHT, 0),
    Device(14, 3, "Kitchen", "Right Light", FirestoreKeys.LIGHT, 0),
    Device(15, 3, "Kitchen", "Fan", FirestoreKeys.FAN, 0, 0),
    Device(16, 3, "Kitchen", "Refrigerator", FirestoreKeys.REFRIGERATOR, 0),

    Device(17, 4, "Drawing Room", "Front Left Light", FirestoreKeys.LIGHT, 0),
    Device(18, 4, "Drawing Room", "Front Right Light", FirestoreKeys.LIGHT, 0),
    Device(19, 4, "Drawing Room", "Back Left Light", FirestoreKeys.LIGHT, 0),
    Device(20, 4, "Drawing Room", "Back Right Light", FirestoreKeys.LIGHT, 0),
    Device(21, 4, "Drawing Room", "Fan", FirestoreKeys.FAN, 0, 0),
    Device(22, 4, "Drawing Room", "AC", FirestoreKeys.AC, 0, 0, 0, 20),

    Device(23, 5, "Gaming Room", "Front Left Light", FirestoreKeys.LIGHT, 0),
    Device(24, 5, "Gaming Room", "Front Right Light", FirestoreKeys.LIGHT, 0),
    Device(25, 5, "Gaming Room", "Back Left Light", FirestoreKeys.LIGHT, 0),
    Device(26, 5, "Gaming Room", "Back Right Light", FirestoreKeys.LIGHT, 0),
    Device(27, 5, "Gaming Room", "Fan", FirestoreKeys.FAN, 0, 0),
    Device(28, 5, "Gaming Room", "AC", FirestoreKeys.AC, 0, 0, 0, 20),
    Device(29, 5, "Gaming Room", "TV", FirestoreKeys.TV, 0),
)


