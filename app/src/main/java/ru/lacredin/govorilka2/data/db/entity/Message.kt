package ru.lacredin.govorilka2.data.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Message(
    @PrimaryKey
    val uid: Int,
    @ColumnInfo
    val authorUserId: Int?,
    @ColumnInfo
    val recipientUserId: Int?,
    @ColumnInfo
    val massage: String?,
    @ColumnInfo
    val date: Long?
)