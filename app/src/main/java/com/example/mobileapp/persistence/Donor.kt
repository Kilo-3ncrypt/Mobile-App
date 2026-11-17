package com.example.mobileapp.persistence

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "donors")
data class Donor(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "donor_id") val donorId: Int = 0,

    @ColumnInfo(name = "donor_name") val donorName: String,
    @ColumnInfo(name = "phone") val phone: String = "",
    @ColumnInfo(name = "email") val email: String = ""
)