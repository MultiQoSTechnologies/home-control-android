package com.app.homecontrol.navigation

import com.app.homecontrol.core.utils.toPrettyJson
import com.app.homecontrol.navigation.ArgsConstant.roomArgs
import com.app.homecontrol.navigation.nav_args.RoomArgsModel

const val ROOT_GRAPH_ROUTE = "root"

enum class ScreenName {
    SPLASH,
    HOME,
    ROOM,
    BULB,
    FAN
}

sealed class Screen(val route: String){
    data object Splash: Screen(ScreenName.SPLASH.name)
    data object Home: Screen(ScreenName.HOME.name)
    data object Room: Screen("${ScreenName.ROOM.name}/{$roomArgs}"){
        fun passArguments(roomArgsModel: RoomArgsModel): String {
            return this.route.replace(
                oldValue = "{${roomArgs}}",
                newValue = roomArgsModel.toPrettyJson()
            )
        }
    }
    data object Bulb: Screen(ScreenName.BULB.name)
    data object Fan: Screen(ScreenName.FAN.name)
}

object ArgsConstant{
    const val roomArgs = "roomArgs"
}