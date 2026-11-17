package com.example.mobileapp.persistence

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "children")
data class Child(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "child_id") val childId: Int = 0,

    @ColumnInfo(name = "child_name") val childName: String,
    @ColumnInfo(name = "dob") val dob: String,
    @ColumnInfo(name = "admission_date") val admissionDate: String,
    @ColumnInfo(name = "description") val description: String = ""
)