package com.rudraksha.school.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.rudraksha.school.models.room.RoomGallery

@Dao
interface GalleryDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertGalleryItem(galleryItem: RoomGallery)

    @Query("SELECT * FROM gallery")
    suspend fun getAllGalleryItems(): List<RoomGallery>

    @Query("SELECT * FROM gallery WHERE id = :id")
    suspend fun getGalleryItemById(id: String): RoomGallery?

    @Query("SELECT * FROM gallery WHERE name LIKE '%' || :query || '%'")
    suspend fun getGalleryItemsByTitle(query: String): List<RoomGallery>

    @Query("DELETE FROM gallery WHERE id = :id")
    suspend fun deleteGalleryItem(id: String): Int

    @Query("DELETE FROM gallery")
    suspend fun deleteAllGalleryItems(): Int

    @Query("UPDATE gallery SET name = :newName, description = :newDescription WHERE id = :id")
    suspend fun updateGalleryItem(id: String, newName: String, newDescription: String): Int
}
