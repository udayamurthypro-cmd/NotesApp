package com.uday.notesapp.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.core.graphics.toColorInt

@Composable
fun MyColorPicker(
    selectedColor: Color,
    onColorSelected: (Color) -> Unit
) {


    //Colors List
    val colorsList = listOf(
        Color("#f59697".toColorInt()),
        Color("#f39697".toColorInt()),
        Color("#f49497".toColorInt()),
        Color("#f49597".toColorInt()),
        Color("#f69597".toColorInt()),
        Color("#f79297".toColorInt()),
        Color("#f79997".toColorInt()),
        Color("#f88007".toColorInt()),
        Color("#f66987".toColorInt()),
        Color("#f58807".toColorInt()),
    )

    LazyRow(
        horizontalArrangement = Arrangement.SpaceEvenly,
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        items(colorsList) {
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .padding(4.dp)
                    .clip(CircleShape)
                    .background(color = it)
                    .border(
                        width = if (it == selectedColor) 4.dp else 0.dp,
                        color = if (it == selectedColor) Color.Black else Color.Transparent,
                        shape = CircleShape
                    )
                    .clickable {
                        onColorSelected(it)
                    })
        }
    }
}
