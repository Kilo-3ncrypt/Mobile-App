package com.example.mobileapp.persistence

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface DonationDao {
    @Insert
    suspend fun insertDonation(donation: Donation)

    @Update
    suspend fun updateDonation(donation: Donation)

    @Delete
    suspend fun deleteDonation(donation: Donation)

    @Query("SELECT * FROM donations WHERE donation_id = :id")
    suspend fun getDonationById(id: Int): Donation?

    @Query("SELECT * FROM donations")
    suspend fun getAllDonations(): List<Donation>

    @Query("SELECT * FROM donations WHERE donor_id = :donorId")
    suspend fun getDonationsByDonorId(donorId: Int): List<Donation>

    @Query("SELECT * FROM donations WHERE child_id = :childId")
    suspend fun getDonationsByChildId(childId: Int): List<Donation>
}