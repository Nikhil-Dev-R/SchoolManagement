package com.rudraksha.school.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.rudraksha.school.converters.Converters
import com.rudraksha.school.database.dao.GalleryDao
import com.rudraksha.school.database.dao.StudentDao
import com.rudraksha.school.database.dao.TeacherDao
import com.rudraksha.school.database.dao.SubjectDao
import com.rudraksha.school.database.dao.AppUserDao
import com.rudraksha.school.models.room.RoomGallery
import com.rudraksha.school.models.room.RoomStudent
import com.rudraksha.school.models.room.RoomSubject
import com.rudraksha.school.models.room.RoomTeacher
import com.rudraksha.school.models.room.RoomAppUser

@Database(
    entities = [
        RoomStudent::class,
        RoomSubject::class,
        RoomTeacher::class,
        RoomGallery::class,
        RoomAppUser::class
    ],
    version = 3, // Updated version number
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class SchoolDatabase : RoomDatabase() {
    abstract fun studentDao(): StudentDao
    abstract fun subjectDao(): SubjectDao
    abstract fun teacherDao(): TeacherDao
    abstract fun galleryDao(): GalleryDao
    abstract fun appUserDao(): AppUserDao

    companion object {
        @Volatile private var INSTANCE: SchoolDatabase? = null

        fun getDatabase(context: Context): SchoolDatabase =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: Room.databaseBuilder(
                    context.applicationContext,
                    SchoolDatabase::class.java,
                    "school_database"
                )
                .fallbackToDestructiveMigration() // Optional: Clears the database if migration is not provided
                .build().also { INSTANCE = it }
            }
    }
}
