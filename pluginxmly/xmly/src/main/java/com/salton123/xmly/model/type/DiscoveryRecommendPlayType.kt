package com.salton123.xmly.model.type

import com.ximalaya.ting.android.opensdk.model.album.DiscoveryRecommendAlbumsList

/**
 * User: newSalton@outlook.com
 * Date: 2018/5/25 下午8:20
 * ModifyTime: 下午8:20
 * Description:
 */
data class DiscoveryRecommendPlayType(var title: String, var data: DiscoveryRecommendAlbumsList) : BasePlayType()