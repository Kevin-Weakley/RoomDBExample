package com.revature.roomdbexample.widgets

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.ExtendedFloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import com.revature.roomdbexample.viewmodels.CustomerViewModel
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.revature.roomdbexample.datamodels.Customer
import java.util.*
import kotlin.random.Random

// #8: Need data from ViewModel obj.
@Composable
fun CustomerList(customerViewModel: CustomerViewModel)
{
    // whenever data changes, ListViews ALWAYS recompose (reason for #12)
    val customerList=customerViewModel.fetchAllCustomers().observeAsState(arrayListOf())

    // Scaffold helps developer w/screen: want logical division, of items
    Scaffold(modifier = Modifier.fillMaxSize(),
        floatingActionButton = {
            ExtendedFloatingActionButton(
                backgroundColor = Color.Red,
                text = {
                    Text(text = "Customer", color = Color.White)
                },
                onClick = {
                    val name = UUID.randomUUID().toString()
                    customerViewModel.insertCustomer(
                        // name will be randomly generated
                        Customer(
                            name = name,
                            gender = "Male",
                            emailId = "xyz@gmail.com"
                        ),
                    )
                }, icon = {
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = "image",
                        tint = Color.White
                    )
                })
        },
        content = {
            // B/C we don't know how many items (i.e., "Lazy Column")
            LazyColumn(content = {
                items(
                    // Just 1 item stored, on #67
                    items = customerList.value,
                    itemContent = {customer->
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 16.dp, vertical = 8.dp), content = {
                                val color =
                                    Color(
                                        Random.nextInt(256),
                                        Random.nextInt(256),
                                        Random.nextInt(256)
                                    )
                                // Box layout: lay elements on top of one another (like a stack)
                                Box(
                                    content = {
                                        Text(
                                            /* If there's a name using a label, use it. . .
                                             . . . if not, empty  */
                                            text = (customer.name ?: "")[0].uppercase(),
                                            fontSize = 24.sp,
                                            color = color
                                        )
                                    }, modifier = Modifier
                                        .size(80.dp)
                                        .border(width = 1.2.dp, color = color, shape = CircleShape),
                                    contentAlignment = Alignment.Center
                                )
                                Spacer(modifier = Modifier.size(16.dp))
                                Column(
                                    modifier = Modifier.weight(2F),
                                    content = {
                                        Spacer(modifier = Modifier.size(8.dp))
                                        Text(
                                            text = customer.name?.uppercase() ?: "",
                                            color = color,
                                            fontSize = 16.sp,
                                            maxLines = 1,
                                            overflow = TextOverflow.Ellipsis
                                        )
                                        Text(
                                            text = "${customer.gender}",
                                            color = Color.Black,
                                            fontSize = 14.6.sp
                                        )
                                        Text(
                                            text = "${customer.emailId}",
                                            color = Color.Gray,
                                            maxLines = 1,
                                            overflow = TextOverflow.Ellipsis
                                        )
                                    })
                                Spacer(modifier = Modifier.size(16.dp))
                                Icon(
                                    imageVector = Icons.Default.Delete,
                                    contentDescription = "image",
                                    tint = Color.Red, modifier = Modifier
                                        .size(30.dp)
                                        .clickable(onClick = {
                                            customerViewModel.deleteCustomerById(customer.id)
                                        })
                                )
                            })
                    })
            })
        })













}