package com.salton123.storeplugin.bean

/**
 * User: newSalton@outlook.com
 * Date: 2018/11/2 6:50 PM
 * ModifyTime: 6:50 PM
 * Description:
 */

data class SearchResult(
    val result: String,
    val code: Int,
    val data: List<Data>
)

data class Data(
    val id: String,
    val name: String,
    val singer: String,
    val url: String,
    val pic: String,
    val lrc: String
)