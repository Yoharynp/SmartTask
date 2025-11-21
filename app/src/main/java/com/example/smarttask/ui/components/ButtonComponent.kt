package com.example.smarttask.ui.components

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.smarttask.ui.theme.SmartTaskTheme

@Composable
fun ButtonComponent(text: String, onClick: () -> Unit){
    val interactionSource = remember {MutableInteractionSource()}
    val isPressed by interactionSource.collectIsPressedAsState()
    val scale by animateFloatAsState(
        targetValue = if (isPressed) 0.8f else 1f,
        label = "ButtonScaleAnimation"
    )
    Button(
        onClick = onClick,
        interactionSource = interactionSource,
        modifier = Modifier
            .fillMaxWidth()
            .graphicsLayer(
                scaleX = scale,
                scaleY = scale
            )
    ) {
        Text(
            text = text,
            fontSize = LocalConfiguration.current.screenWidthDp.sp * 0.045f,
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.background,
            modifier = Modifier.padding(vertical = 5.dp)
        )
    }
}

@Preview(showBackground = true, showSystemUi = true, uiMode = 32)
@Composable
fun ButtonComponentPreview() {
    SmartTaskTheme {
        Surface(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Column(
                modifier = Modifier.padding(22.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                ButtonComponent(text = "Log In", onClick = {})
            }
        }
    }
}