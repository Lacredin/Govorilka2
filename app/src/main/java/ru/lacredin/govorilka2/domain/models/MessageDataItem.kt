package ru.lacredin.govorilka2.domain.models

import java.util.*

data class MessageDataItem(
    var message: String,
    var date: Date,
    var userId: String,
    var userName: String,
    var mutableList: MutableList<MediaContent>,
    var isOwn: Boolean
)


interface MediaContent{

}