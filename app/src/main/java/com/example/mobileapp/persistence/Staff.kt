package com.example.mobileapp.persistence

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "staff",
    indices = [Index(value = ["username"], unique = true)]
)
data class Staff(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "staff_id") val staffId: Int = 0,

    @ColumnInfo(name = "full_name") val fullName: String,
    @ColumnInfo(name = "username") val username: String,
    @ColumnInfo(name = "password") val password: String,
    @ColumnInfo(name = "role") val role: String = "Staff"
)