package com.linhcn.simplenoteapp.domain.note

import com.linhcn.simplenoteapp.CommonParcelable
import com.linhcn.simplenoteapp.CommonParcelize
import com.linhcn.simplenoteapp.CommonTypeParceler
import com.linhcn.simplenoteapp.LocalDateTimeParceler
import com.linhcn.simplenoteapp.presentation.GreenLightHex
import com.linhcn.simplenoteapp.presentation.PinkSalmonLightHex
import com.linhcn.simplenoteapp.presentation.RoseLavenderHex
import com.linhcn.simplenoteapp.presentation.WaterSpoutHex
import com.linhcn.simplenoteapp.presentation.YellowPastelHex
import kotlinx.datetime.LocalDateTime
import kotlinx.serialization.Serializable
import kotlinx.datetime.serializers.LocalDateTimeIso8601Serializer

@Serializable
@CommonParcelize
data class Note(
    val id: Long?,
    val title: String,
    val content: String,
    val colorHex: Long,

    @Serializable(with = LocalDateTimeIso8601Serializer::class)
    @CommonTypeParceler<LocalDateTime, LocalDateTimeParceler>()
    val createDate: LocalDateTime
): CommonParcelable {
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