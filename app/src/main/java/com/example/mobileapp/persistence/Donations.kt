package com.example.mobileapp.persistence

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "donations",
    foreignKeys = [  // For the relationships b2in tables and stuff
        ForeignKey(
            entity = Donor::class,
            parentColumns = ["donor_id"],
            childColumns = ["donor_id"]
        ),
        ForeignKey(
            entity = Child::class,
            parentColumns = ["child_id"],
            childColumns = ["child_id"],
            onDelete = ForeignKey.SET_NULL
        )
    ],
    indices = [Index("donor_id"), Index("child_id")] // canr be empty
)
data class Donation(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "donation_id") val donationId: Int = 0,

    @ColumnInfo(name = "donor_id") val donorId: Int,  // These 2 appear in other tables
    @ColumnInfo(name = "child_id") val childId: Int? = null,

    @ColumnInfo(name = "donation_type") val donationType: String,
    @ColumnInfo(name = "amount") val amount: Int = 0,
    @ColumnInfo(name = "donation_date") val donationDate: String,
    @ColumnInfo(name = "purpose") val why: String = ""
)