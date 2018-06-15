package com.salton123.xmly.fm

import com.salton123.xmly.R
import kotlinx.android.synthetic.main.xmly_cp_music_player.*

class MusicPlayerComponent : BasicPlayerComponent() {

    override fun getLayout(): Int = R.layout.xmly_cp_music_player
    override fun initViewAndData() {
        super.initViewAndData()
        ivCover.startRotateAnimation()
    }
}
