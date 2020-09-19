package my.users.models

import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
data class UserMO(
    var D1: String? = "",
    var D2: String? = ""
)