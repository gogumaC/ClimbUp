package com.gogumac.climbup.component.Levels

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.gogumac.climbup.Level

@Composable
fun LevelIcon(
    modifier: Modifier = Modifier,
    level:Level,
    size: Dp =25.dp
){
    Surface(
        modifier = modifier.size(size).aspectRatio(1f),
        color=if(level.color!=null)Color(level.color) else Color.White,
        border = BorderStroke(0.2.dp,color=Color.DarkGray),
        shape = RoundedCornerShape(10.dp),
    ){}

}

@Preview
@Composable
private fun LevelIconPreview(){
        LevelIcon(level = Level.testLevel)

}