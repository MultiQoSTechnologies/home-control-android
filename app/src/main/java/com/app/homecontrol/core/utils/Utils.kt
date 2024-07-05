package com.app.homecontrol.core.utils

fun Int?.toBoolean(): Boolean = this == 1

fun Boolean?.toInt(): Int = if (this == true) 1 else 0