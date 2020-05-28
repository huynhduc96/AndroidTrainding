package com.example.android.marsrealestate.network

import com.example.android.marsrealestate.database.DatabaseImage


fun List<DatabaseImage>.asImageModel(): List<MarsProperty> {
  return map {
    MarsProperty(
        id = it.id,
        imgSrcUrl = it.imgSrcUrl,
        type = it.type,
        price = it.price
    )
  }
}

fun List<MarsProperty>.asDatabaseModel(): List<DatabaseImage> {
  return map {
    DatabaseImage(
        id = it.id,
        imgSrcUrl = it.imgSrcUrl,
        type = it.type,
        price = it.price
    )
  }
}