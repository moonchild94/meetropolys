package ru.daryasoft.meetropolys.model

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import java.util.*

open class Account @JvmOverloads constructor(
    @PrimaryKey var id: String = UUID.randomUUID().toString(),
    var email: String? = null,
    var password: String? = null,
    var isActive: Boolean = false
) : RealmObject()