package com.revature.roomdbexample.repository

import android.app.Application
import androidx.lifecycle.LiveData
import com.revature.roomdbexample.dao.CustomerData
import com.revature.roomdbexample.datamodels.AppDatabase
import com.revature.roomdbexample.datamodels.Customer

class CustomerRepository(application: Application) {

/* Follows design pattern (best practice);
     Initialize #12, later on  */
    private lateinit var customerDao:CustomerData

    init {

        // 1 repository, per appl.
        var database=AppDatabase.getDatabase(application)
        customerDao=database.customerDao()

    }

    val readAllCustomers: LiveData<List<Customer>> =customerDao.fetchAllCustomer()

    // database's initialized only 1x
    suspend fun deleteCustomerById(id:Int)
    {
        customerDao.deleteCustomerById(id)
    }

    suspend fun insertCustomer(customer: Customer)
    {
        customerDao.insertCustomer(customer)
    }
}