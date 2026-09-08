package com.uday.notesapp.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.uday.notesapp.roomdb.Note

import androidx.compose.foundation.layout.Box
import androidx.compose.ui.Alignment
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Composable
fun NoteListItem(note : Note, onClick: () -> Unit){
    val sdf = remember { SimpleDateFormat("dd MMM, hh:mm a", Locale.getDefault()) }
    val dateString = sdf.format(Date(note.timestamp))

    Card(
        elevation = CardDefaults.cardElevation(4.dp),
        colors = CardDefaults.cardColors(containerColor = Color(note.color)),
        border = BorderStroke(0.5.dp, Color.Black.copy(alpha = 0.1f)),
        modifier = Modifier
            .padding(6.dp)
            .clickable { onClick() }
    ) {
        Column(modifier = Modifier.fillMaxWidth().padding(12.dp)) {
            Text(text = note.title,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                maxLines = 2)
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = note.description,
                fontSize = 14.sp,
                maxLines = 8)
            Spacer(modifier = Modifier.height(8.dp))
            Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.BottomEnd) {
                Text(
                    text = dateString,
                    fontSize = 10.sp,
                    color = Color.Black.copy(alpha = 0.5f)
                )
            }
        }
    }
}