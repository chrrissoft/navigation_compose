package com.chrrissoft.navigation

import android.os.Parcelable
import kotlinx.serialization.Serializable


@Serializable
abstract class AbstractSerializableCustomArg : Parcelable {
    abstract val data: String
}
