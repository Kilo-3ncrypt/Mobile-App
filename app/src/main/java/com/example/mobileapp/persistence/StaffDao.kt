package com.example.mobileapp.persistence

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface StaffDao {
    @Insert
    suspend fun insertStaff(staff: Staff)

    @Update
    suspend fun updateStaff(staff: Staff)

    @Delete
    suspend fun deleteStaff(staff: Staff)

    @Query("SELECT * FROM staff WHERE staff_id = :id")
    suspend fun getStaffById(id: Int): Staff?

    @Query("SELECT * FROM staff")
    suspend fun getAllStaff(): List<Staff>

    @Query("SELECT * FROM staff WHERE username = :username AND password = :password")
    suspend fun login(username: String, password: String): Staff?
}