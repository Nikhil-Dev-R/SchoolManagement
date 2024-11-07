package com.rudraksha.school.ui.utils

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.os.Environment
import androidx.core.content.FileProvider
import com.rudraksha.school.models.firebase.FirebaseSubject
import com.rudraksha.school.models.room.RoomSubject
import com.rudraksha.school.ui.screen.ReportCard
import com.rudraksha.school.ui.utils.captureComposableAsBitmap
import java.io.File
import java.io.FileOutputStream

val studentName = "John Doe"

fun createReportCardBitmap(context: Context): Bitmap {
    // Capture the report card as an image
    val subjects = listOf(
        RoomSubject("M", "Mathematics", 90),
        RoomSubject("S", "Science", 85),
        RoomSubject("Hi", "History", 97),
        RoomSubject("A", "Art", 89),
        RoomSubject("PE", "Physical Education", 87)
    )
    return captureComposableAsBitmap(context) {
        ReportCard(
            studentName = studentName,
            studentId = "123456",
            gradeLevel = "9th",
            section = "B",
            schoolName = "Springfield High School",
            academicYear = "2022-2023",
            subjects = subjects,
            overallGrade = "A-",
            teacherComment = "John has shown excellent performance in all subjects. Keep up the good work!"
        )
    }
}

fun saveBitmapToFile(context: Context, bitmap: Bitmap, studentName: String): File {
    val file = File(context.getExternalFilesDir(Environment.DIRECTORY_PICTURES),
        "report_card_${studentName}.png")
    FileOutputStream(file).use { out ->
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, out)
    }
    return file
}

fun shareImageBitmap(context: Context, file: File) {
    val uri = FileProvider.getUriForFile(context, "${context.packageName}.fileprovider", file)
    val shareIntent = Intent().apply {
        action = Intent.ACTION_SEND
        putExtra(Intent.EXTRA_STREAM, uri)
        type = "image/png"
        addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
    }
    context.startActivity(Intent.createChooser(shareIntent, "Share Report Card"))
}

fun saveImage(context: Context) {
    val bitmap = createReportCardBitmap(context)
    saveBitmapToFile(context, bitmap, studentName)
}

fun shareImage(context: Context) {
    val bitmap = createReportCardBitmap(context)
    val file = saveBitmapToFile(context, bitmap, studentName)
    shareImageBitmap(context, file)
}
