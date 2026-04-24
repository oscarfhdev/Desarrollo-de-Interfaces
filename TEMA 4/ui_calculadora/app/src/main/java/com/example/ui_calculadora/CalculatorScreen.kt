package com.example.ui_calculadora

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun CalculatorScreen(modifier: Modifier = Modifier) {
    val bgColor = Color(0xFF121212)
    val numColor = Color(0xFF2C2C2C)
    val opColor = Color(0xFF00E5FF)
    val topOpColor = Color(0xFF3D3D3D)
    val eqColor = Color(0xFFFF007F)

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(bgColor)
            .padding(16.dp),
        verticalArrangement = Arrangement.Bottom
    ) {
        // Display area
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            contentAlignment = Alignment.BottomEnd
        ) {
            Text(
                text = "0",
                color = Color.White,
                fontSize = 80.sp,
                fontWeight = FontWeight.Light,
                modifier = Modifier.padding(16.dp)
            )
        }

        // Button Grid
        val buttonSpacing = 12.dp

        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(buttonSpacing)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(buttonSpacing)
            ) {
                CalculatorButton(text = "AC", color = topOpColor, modifier = Modifier.weight(1f))
                CalculatorButton(text = "+/-", color = topOpColor, modifier = Modifier.weight(1f))
                CalculatorButton(text = "%", color = topOpColor, modifier = Modifier.weight(1f))
                CalculatorButton(text = "/", color = opColor, textColor = Color.Black, modifier = Modifier.weight(1f))
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(buttonSpacing)
            ) {
                CalculatorButton(text = "7", color = numColor, modifier = Modifier.weight(1f))
                CalculatorButton(text = "8", color = numColor, modifier = Modifier.weight(1f))
                CalculatorButton(text = "9", color = numColor, modifier = Modifier.weight(1f))
                CalculatorButton(text = "x", color = opColor, textColor = Color.Black, modifier = Modifier.weight(1f))
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(buttonSpacing)
            ) {
                CalculatorButton(text = "4", color = numColor, modifier = Modifier.weight(1f))
                CalculatorButton(text = "5", color = numColor, modifier = Modifier.weight(1f))
                CalculatorButton(text = "6", color = numColor, modifier = Modifier.weight(1f))
                CalculatorButton(text = "-", color = opColor, textColor = Color.Black, modifier = Modifier.weight(1f))
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(buttonSpacing)
            ) {
                CalculatorButton(text = "1", color = numColor, modifier = Modifier.weight(1f))
                CalculatorButton(text = "2", color = numColor, modifier = Modifier.weight(1f))
                CalculatorButton(text = "3", color = numColor, modifier = Modifier.weight(1f))
                CalculatorButton(text = "+", color = opColor, textColor = Color.Black, modifier = Modifier.weight(1f))
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(buttonSpacing)
            ) {
                CalculatorButton(text = "0", color = numColor, modifier = Modifier.weight(2f), isWide = true)
                CalculatorButton(text = ".", color = numColor, modifier = Modifier.weight(1f))
                CalculatorButton(text = "=", color = eqColor, modifier = Modifier.weight(1f))
            }
        }
    }
}

@Composable
fun CalculatorButton(
    text: String,
    color: Color,
    modifier: Modifier = Modifier,
    textColor: Color = Color.White,
    isWide: Boolean = false
) {
    Box(
        modifier = modifier
            .aspectRatio(if (isWide) 2.1f else 1f)
            .clip(RoundedCornerShape(24.dp))
            .background(color)
            .clickable { },
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            color = textColor,
            fontSize = 32.sp,
            fontWeight = FontWeight.Medium
        )
    }
}
