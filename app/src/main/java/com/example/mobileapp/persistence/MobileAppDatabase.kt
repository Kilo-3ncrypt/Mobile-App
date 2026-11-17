package com.example.mobileapp.persistence

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [Staff::class, Child::class, Donor::class, Donation::class],
    version = 1
)
abstract class MobileAppDatabase : RoomDatabase() {

    // the DBs DAOs
    abstract fun staffDao(): StaffDao
    abstract fun childDao(): ChildDao
    abstract fun donorDao(): DonorDao
    abstract fun donationDao(): DonationDao

    companion object {
        @Volatile
        private var INSTANCE: MobileAppDatabase? = null

        fun getDatabase(context: Context): MobileAppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    MobileAppDatabase::class.java,
                    "mobileapp_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}