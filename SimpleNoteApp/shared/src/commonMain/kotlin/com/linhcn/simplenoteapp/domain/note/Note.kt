package com.linhcn.simplenoteapp.domain.note

import com.linhcn.simplenoteapp.presentation.GreenLightHex
import com.linhcn.simplenoteapp.presentation.PinkSalmonLightHex
import com.linhcn.simplenoteapp.presentation.RoseLavenderHex
import com.linhcn.simplenoteapp.presentation.WaterSpoutHex
import com.linhcn.simplenoteapp.presentation.YellowPastelHex
import kotlinx.datetime.LocalDateTime

data class Note(
    val id: Long?,
    val title: String,
    val content: String,
    val colorHex: Long,
    val createDate: LocalDateTime
) {
    companion object {

        private val colors = listOf(
            GreenLightHex,
            PinkSalmonLightHex,
            YellowPastelHex,
            WaterSpoutHex,
            RoseLavenderHex
        )

        fun generateRandomColor() = colors.random()
    }
}