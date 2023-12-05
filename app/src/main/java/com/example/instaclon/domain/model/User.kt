package com.example.instaclon.domain.model

data class User(
    var name: String ="",
    var userName: String ="",
    var userid: String ="",
    var email : String ="",
    var password : String="",
    var imageUrl: String ="",
    var following : List<String> = emptyList(),
    var followers : List<String> = emptyList(),
    var totalPost : String ="",
    var bio : String ="",
    var url : String =""


)
