package com.example.mobileapp.persistence

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface DonorDao {
    @Insert
    suspend fun insertDonor(donor: Donor)

    @Update
    suspend fun updateDonor(donor: Donor)

    @Delete
    suspend fun deleteDonor(donor: Donor)

    @Query("SELECT * FROM donors WHERE donor_id = :id")
    suspend fun getDonorById(id: Int): Donor?

    @Query("SELECT * FROM donors")
    suspend fun getAllDonors(): List<Donor>
}