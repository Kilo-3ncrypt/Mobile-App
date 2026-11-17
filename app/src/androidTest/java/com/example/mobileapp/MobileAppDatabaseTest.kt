package com.example.mobileapp

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.mobileapp.persistence.*
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MobileAppDatabaseTest {

    private lateinit var db: MobileAppDatabase

    private val staff get() = db.staffDao()
    private val child get() = db.childDao()
    private val donor get() = db.donorDao()
    private val donation get() = db.donationDao()

    @Before
    fun createDb() {
        db = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            MobileAppDatabase::class.java
        ).allowMainThreadQueries().build()
    }

    @After
    fun closeDb() {
        db.close()
    }

    // Sample data
    private val sampleStaff = Staff(
        1, "Grace Wanjiru", "grace", "pass123", "Admin"
    )

    private val sampleChild = Child(
        1, "Amina", "2015-03-12", "2024-01-10", "Likes drawing"
    )

    private val sampleDonor = Donor(
        1, "John Kamau", "0700000000", "john@example.com"
    )

    private val sampleDonation = Donation(
        1, 1, 1, "Cash", 5000, "2026-11-01", "School fees Term 1"
    )

    private suspend fun insertParents() {
        donor.insertDonor(sampleDonor)
        child.insertChild(sampleChild)
    }

    // Staff tests
    @Test
    fun staff_insertAndGet() = runBlocking {
        staff.insertStaff(sampleStaff)
        assertEquals(sampleStaff, staff.getStaffById(1))
    }

    @Test
    fun staff_update() = runBlocking {
        staff.insertStaff(sampleStaff)
        staff.updateStaff(sampleStaff.copy(role = "Staff"))
        assertEquals("Staff", staff.getStaffById(1)?.role)
    }

    @Test
    fun staff_delete() = runBlocking {
        staff.insertStaff(sampleStaff)
        staff.deleteStaff(sampleStaff)
        assertTrue(staff.getAllStaff().isEmpty())
    }

    @Test
    fun staff_login() = runBlocking {
        staff.insertStaff(sampleStaff)
        assertNotNull(staff.login("grace", "pass123"))
        assertNull(staff.login("grace", "wrong"))
    }

    // Child tests
    @Test
    fun child_insertAndGet() = runBlocking {
        child.insertChild(sampleChild)
        assertEquals(sampleChild, child.getChildById(1))
    }

    @Test
    fun child_update() = runBlocking {
        child.insertChild(sampleChild)
        child.updateChild(sampleChild.copy(description = "Moved to Class 4"))
        assertEquals("Moved to Class 4", child.getChildById(1)?.description)
    }

    @Test
    fun child_delete() = runBlocking {
        child.insertChild(sampleChild)
        child.deleteChild(sampleChild)
        assertTrue(child.getAllChildren().isEmpty())
    }

    // Donor tests
    @Test
    fun donor_insertAndGet() = runBlocking {
        donor.insertDonor(sampleDonor)
        assertEquals(sampleDonor, donor.getDonorById(1))
    }

    @Test
    fun donor_update() = runBlocking {
        donor.insertDonor(sampleDonor)
        donor.updateDonor(sampleDonor.copy(phone = "0711111111"))
        assertEquals("0711111111", donor.getDonorById(1)?.phone)
    }

    @Test
    fun donor_delete() = runBlocking {
        donor.insertDonor(sampleDonor)
        donor.deleteDonor(sampleDonor)
        assertTrue(donor.getAllDonors().isEmpty())
    }

    // Donation tests
    @Test
    fun donation_insertAndGet() = runBlocking {
        insertParents()
        donation.insertDonation(sampleDonation)
        assertEquals(sampleDonation, donation.getDonationById(1))
    }

    @Test
    fun donation_update() = runBlocking {
        insertParents()
        donation.insertDonation(sampleDonation)
        donation.updateDonation(sampleDonation.copy(why = "Uniform"))
        assertEquals("Uniform", donation.getDonationById(1)?.why)
    }

    @Test
    fun donation_delete() = runBlocking {
        insertParents()
        donation.insertDonation(sampleDonation)
        donation.deleteDonation(sampleDonation)
        assertTrue(donation.getAllDonations().isEmpty())
    }

    @Test
    fun donation_getByDonorAndChild() = runBlocking {
        insertParents()
        donation.insertDonation(sampleDonation)

        assertEquals(1, donation.getDonationsByDonorId(1).size)
        assertEquals(1, donation.getDonationsByChildId(1).size)
        assertTrue(donation.getDonationsByDonorId(99).isEmpty())
    }

    @Test
    fun donation_survivesChildDeletion() = runBlocking {
        insertParents()
        donation.insertDonation(sampleDonation)
        child.deleteChild(sampleChild)

        assertNull(donation.getDonationById(1)?.childId)
    }
}