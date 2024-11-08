package com.rudraksha.school.repo

import com.rudraksha.school.models.firebase.FirebaseGallery
import com.rudraksha.school.models.firebase.FirebaseStudent
import com.rudraksha.school.models.firebase.FirebaseSubject
import com.rudraksha.school.models.firebase.FirebaseTeacher

class LocalDataRepository {
    val quotes: List<String> = listOf(
        "Believe in yourself, achieve greatness.",
        "Learning is the key to unlocking your potential.",
        "Success is not final, failure is not fatal: It is the courage to continue.",
        "Don't watch the clock, do what it does. Keep going.",
        "You don't have to be great to start, but you have to start to be great.",

        "Knowledge is power.",
        "Education is the most powerful weapon.",
        "Learning is not attained by chance, it must be sought for.",
        "The root of education is bitter, but the fruit is sweet.",
        "Education transforms, inspires, and empowers.",

        "Empowering young minds to shape a brighter tomorrow.",
        "Discover, Explore, Learn: The Journey Begins Here.",
        "Unlocking the genius within every student.",
        "Where curiosity meets discovery, and dreams take flight.",
        "Learn with passion, grow with purpose.",

        "Learn, Grow, Achieve.",
        "Empower, Inspire, Educate.",
        "Shape Your Future.",
        "Unlock Your Potential.",
        "Dream Big.",

        "The best way to predict your future is to create it.\n\t\t Abraham Lincoln",
        "Education is not preparation for life; education is life itself.\n\t\t - John Dewey",
        "The whole purpose of education is to turn mirrors into windows.\n\t\t - Sydney J. Harris",
        "Learning is not a spectator sport.\n\t\t - D. Blocher",
        "The highest result of education is tolerance.\n\t\t - Helen Keller",
    )

    val studentList = mutableListOf<FirebaseStudent>().apply {
        // Nursery to Grade U.K.G. (Basic subjects)
        addAll(generateNurseryToGradeUkgStudents("Nursery"))
        addAll(generateNurseryToGradeUkgStudents("L.K.G."))
        addAll(generateNurseryToGradeUkgStudents("U.K.G."))

        // Grade 1 to 5 (Adding more subjects)
        addAll(generateGrade1To5Students("1st"))
        addAll(generateGrade1To5Students("2nd"))
        addAll(generateGrade1To5Students("3rd"))
        addAll(generateGrade1To5Students("4th"))
        addAll(generateGrade1To5Students("5th"))

        // Grade 6 to 9 (Introducing advanced subjects)
        addAll(generateGrade6To9Students("6th"))
        addAll(generateGrade6To9Students("7th"))
        addAll(generateGrade6To9Students("8th"))
        addAll(generateGrade6To9Students("9th"))

        // Grade 10 to 12 (Subject specialization)
        addAll(generateGrade10To12Students("10th"))
        addAll(generateGrade10To12Students("11th"))
        addAll(generateGrade10To12Students("12th"))
    }

    val teacherList = listOf(
        FirebaseTeacher(
            id = "T1",
            name = "Teacher 1",
            imageUrl = "https://th.bing.com/th/id/OIP.3BPhNtWz9Kz7ov65XEn99gHaHa?w=512&h=512&rs=1&pid=ImgDetMain",
            description = "Description for Teacher 1",
            isClassTeacher = true,
            standard = "Nursery"
        ),
        FirebaseTeacher(
            id = "T2",
            name = "Teacher 2",
            imageUrl = "https://img.freepik.com/premium-photo/male-female-profile-avatar-user-avatars-gender-icons_1020867-74822.jpg",
            description = "Description for Teacher 2",
            isClassTeacher = true,
            standard = "L.K.G."
        ),
        FirebaseTeacher(
            id = "T3",
            name = "Teacher 3",
            imageUrl = "https://png.pngtree.com/png-vector/20191103/ourmid/pngtree-handsome-young-guy-avatar-cartoon-style-png-image_1947775.jpg",
            description = "Description for Teacher 3",
            isClassTeacher = true,
            standard = "U.K.G."
        ),
        FirebaseTeacher(
            id = "T4",
            name = "Teacher 4",
            imageUrl = "https://cdn-icons-png.freepik.com/512/15825/15825382.png",
            description = "Description for Teacher 4",
            isClassTeacher = true,
            standard = "4th"
        ),
        FirebaseTeacher(
            id = "T5",
            name = "Teacher 5",
            imageUrl = "https://www.pngitem.com/pimgs/m/78-786293_1240-x-1240-0-avatar-profile-icon-png.png",
            description = "Description for Teacher 5",
            isClassTeacher = true,
            standard = "5th"
        ),
        FirebaseTeacher(
            id = "T6",
            name = "Teacher 6",
            imageUrl = "https://thumbs.dreamstime.com/b/avatar-men-profile-simple-method-your-work-i-hope-you-like-268947386.jpg",
            description = "Description for Teacher 6",
        ),
        FirebaseTeacher(
            id = "T7",
            name = "Teacher 7",
            imageUrl = "https://media-public.canva.com/MADrs1-ByK4/2/thumbnail_large.png",
            description = "Description for Teacher 7",
            isClassTeacher = true,
            standard = "1st"
        ),
        FirebaseTeacher(
            id = "T8",
            name = "Teacher 8",
            imageUrl = "https://th.bing.com/th/id/OIP.3BPhNtWz9Kz7ov65XEn99gHaHa?w=512&h=512&rs=1&pid=ImgDetMain",
            description = "Description for Teacher 8",
            isClassTeacher = true,
            standard = "2nd"
        ),
        FirebaseTeacher(
            id = "T9",
            name = "Teacher 9",
            imageUrl = "https://img.freepik.com/premium-photo/male-female-profile-avatar-user-avatars-gender-icons_1020867-74822.jpg",
            description = "Description for Teacher 9",
            isClassTeacher = true,
            standard = "3rd"
        ),
        FirebaseTeacher(
            id = "T10",
            name = "Teacher 10",
            imageUrl = "https://png.pngtree.com/png-vector/20191103/ourmid/pngtree-handsome-young-guy-avatar-cartoon-style-png-image_1947775.jpg",
            description = "Description for Teacher 10",
        ),
        FirebaseTeacher(
            id = "T11",
            name = "Teacher 11",
            imageUrl = "https://cdn-icons-png.freepik.com/512/15825/15825382.png",
            description = "Description for Teacher 11",
            isClassTeacher = true,
            standard = "6th"
        ),
        FirebaseTeacher(
            id = "T12",
            name = "Teacher 12",
            imageUrl = "https://www.pngitem.com/pimgs/m/78-786293_1240-x-1240-0-avatar-profile-icon-png.png",
            description = "Description for Teacher 12",
            isClassTeacher = true,
            standard = "7th"
        ),
        FirebaseTeacher(
            id = "T13",
            name = "Teacher 13",
            imageUrl = "https://thumbs.dreamstime.com/b/avatar-men-profile-simple-method-your-work-i-hope-you-like-268947386.jpg",
            description = "Description for Teacher 13",
            isClassTeacher = true,
            standard = "12th"
        ),
        FirebaseTeacher(
            id = "T14",
            name = "Teacher 14",
            imageUrl = "https://media-public.canva.com/MADrs1-ByK4/2/thumbnail_large.png",
            description = "Description for Teacher 14",
            isClassTeacher = true,
            standard = "10th"
        ),
        FirebaseTeacher(
            id = "T15",
            name = "Teacher 15",
            imageUrl = "https://www.pngitem.com/pimgs/m/78-786293_1240-x-1240-0-avatar-profile-icon-png.png",
            description = "Description for Teacher 15",
            isClassTeacher = true,
            standard = "9th"
        ),
        FirebaseTeacher(
            id = "T16",
            name = "Teacher 16",
            imageUrl = "https://thumbs.dreamstime.com/b/avatar-men-profile-simple-method-your-work-i-hope-you-like-268947386.jpg",
            description = "Description for Teacher 16",
            isClassTeacher = true,
            standard = "8th"
        ),
        FirebaseTeacher(
            id = "T17",
            name = "Teacher 17",
            imageUrl = "https://media-public.canva.com/MADrs1-ByK4/2/thumbnail_large.png",
            description = "Description for Teacher 74",
            isClassTeacher = true,
            standard = "11th"
        ),
    )

    val galleryList = listOf(
        FirebaseGallery(
            id = "F1",
            name = "Festival 1",
            imageUrl = "https://i.postimg.cc/2jZqd3xb/2609e729-9cb4-44c2-9361-f663d02ea415.jpg",
            description = "Desc F1",
        ),
        FirebaseGallery(
            id = "F2",
            name = "Festival 2",
            imageUrl = "https://i.postimg.cc/2jZqd3xb/2609e729-9cb4-44c2-9361-f663d02ea415.jpg",
            description = "Desc F2",
        ),
        FirebaseGallery(
            id = "F3",
            name = "Festival 3",
            imageUrl = "https://i.postimg.cc/2jZqd3xb/2609e729-9cb4-44c2-9361-f663d02ea415.jpg",
            description = "Desc F3",
        ),
        FirebaseGallery(
            id = "F4",
            name = "Festival 4",
            imageUrl = "https://i.postimg.cc/2jZqd3xb/2609e729-9cb4-44c2-9361-f663d02ea415.jpg",
            description = "Desc F4",
        ),
        FirebaseGallery(
            id = "F5",
            name = "Festival 5",
            imageUrl = "https://i.postimg.cc/2jZqd3xb/2609e729-9cb4-44c2-9361-f663d02ea415.jpg",
            description = "Desc F5",
        ),
        FirebaseGallery(
            id = "F6",
            name = "Festival 6",
            imageUrl = "https://i.postimg.cc/2jZqd3xb/2609e729-9cb4-44c2-9361-f663d02ea415.jpg",
            description = "Desc F6",
        ),
        FirebaseGallery(
            id = "F7",
            name = "Festival 7",
            imageUrl = "https://i.postimg.cc/2jZqd3xb/2609e729-9cb4-44c2-9361-f663d02ea415.jpg",
            description = "Desc F7",
        ),
        FirebaseGallery(
            id = "F8",
            name = "Festival 8",
            imageUrl = "https://i.postimg.cc/2jZqd3xb/2609e729-9cb4-44c2-9361-f663d02ea415.jpg",
            description = "Desc F8",
        ),
    )

    private fun generateNurseryToGradeUkgStudents(gradeLevel: String): List<FirebaseStudent> {
        return List(30) { i ->
            FirebaseStudent(
                id = "N${gradeLevel[0]}${gradeLevel[2].uppercase()}${i + 1}",
                rollNumber = String.format("%03d", i + 1),
                name = "Student${i + 1}",
                standard = gradeLevel,  // Nursery 1, 2, or 3
                section = if ((0..1).random() == 0) "A" else "B",
                presentDays = (0..25).random(),
                subjectMarks = listOf(
                    FirebaseSubject("H", "Hindi", (70..100).random()),
                    FirebaseSubject("E", "English", (70..100).random()),
                    FirebaseSubject("M", "Math", (70..100).random())
                )
            )
        }
    }

    private fun generateGrade1To5Students(gradeLevel: String): List<FirebaseStudent> {
        return List(35) { i ->
            FirebaseStudent(
                id = "G${gradeLevel[0]}${gradeLevel[1].uppercase()}${i + 1}",
                rollNumber = String.format("%03d", i + 1),
                name = "Student${i + 1}",
                standard = gradeLevel,
                section = if ((0..1).random() == 0) "A" else "B",
                presentDays = (0..25).random(),
                subjectMarks = listOf(
                    FirebaseSubject("H", "Hindi", (70..100).random()),
                    FirebaseSubject("E", "English", (60..95).random()),
                    FirebaseSubject("M", "Math", (60..95).random()),
                    FirebaseSubject("S", "Science", (60..95).random())
                )
            )
        }
    }

    private fun generateGrade6To9Students(gradeLevel: String): List<FirebaseStudent> {
        return List(40) { i ->
            FirebaseStudent(
                id = "G${gradeLevel[0]}${gradeLevel[1].uppercase()}${i + 1}",
                rollNumber = String.format("%03d", i + 1),
                name = "Student${i + 1}",
                standard = gradeLevel,
                section = if ((0..1).random() == 0) "A" else "B",
                presentDays = (0..25).random(),
                subjectMarks = listOf(
                    FirebaseSubject("H", "Hindi", (70..100).random()),
                    FirebaseSubject("E", "English", (55..100).random()),
                    FirebaseSubject("M", "Math", (55..100).random()),
                    FirebaseSubject("S", "Science", (55..100).random()),
                    FirebaseSubject("PS", "Political Science", (55..100).random()),
                    FirebaseSubject("G", "Geography", (55..100).random())
                )
            )
        }
    }

    private fun generateGrade10To12Students(gradeLevel: String): List<FirebaseStudent> {
        return List(50) { i ->
            FirebaseStudent(
                id = "G${gradeLevel[0]}${gradeLevel[1]}${i + 1}",
                rollNumber = String.format("%03d", i + 1),
                name = "Student${i + 1}",
                standard = gradeLevel,
                section = if ((0..1).random() == 0) "A" else "B",
                presentDays = (0..25).random(),
                subjectMarks = listOf(
                    FirebaseSubject("H", "Hindi", (50..100).random()),
                    FirebaseSubject("E", "English", (50..100).random()),
                    FirebaseSubject("M", "Math", (50..100).random()),
                    FirebaseSubject("F", "French", (70..100).random()),
                    FirebaseSubject("S", "Science", (50..100).random()),
                    FirebaseSubject("C", "Chemistry", (50..100).random()),
                    FirebaseSubject("B", "Biology", (50..100).random()),
                    FirebaseSubject("P", "Physics", (50..100).random())
                )
            )
        }
    }
}
