package com.revature.roomdbexample.datamodels

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

// Creating keys; then, database table's created
@Entity(tableName = "customer")
data class Customer(

    @PrimaryKey(autoGenerate = true)
    val id:Int=0,

    @ColumnInfo(name="name")
    val name:String?=null,

    @ColumnInfo(name="gender")
    val gender:String?=null,

    @ColumnInfo(name="email_id")
    val emailId:String?=null,

)
