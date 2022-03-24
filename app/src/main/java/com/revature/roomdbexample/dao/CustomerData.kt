package com.revature.roomdbexample.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.revature.roomdbexample.datamodels.Customer

/* Line 13: defining 'interface' helps: it's usable by other classes, &
eliminates hard-coding */
@Dao
interface CustomerData {

    @Query("SELECT * FROM customer")
    fun fetchAllCustomer():LiveData<List<Customer>>

    // 'REPLACE' will replace, w/new one
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCustomer(customer:Customer)

    // No '*' on 21, b/c w/o Id, NO table exists; only focused on recs w/matching ID
    @Query("DELETE FROM customer where id=:id")
    suspend fun deleteCustomerById(id:Int)


}