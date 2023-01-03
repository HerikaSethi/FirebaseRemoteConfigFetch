package com.demoproject.room.relationships

import androidx.room.Embedded
import androidx.room.Relation
import com.demoproject.room.entities.Director
import com.demoproject.room.entities.School


/***1:1 relation
1 school can have one director and one director can be of one school***/
data class SchoolAndDirector(
//    School table fields will be automatically added
   @Embedded val school: School,
   @Relation(
       parentColumn = "SchoolName",
       entityColumn = "SchoolName"
   )
   val Director: Director
)
