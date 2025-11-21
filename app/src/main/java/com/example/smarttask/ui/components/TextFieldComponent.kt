package com.example.smarttask.ui.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.smarttask.R
import com.example.smarttask.ui.theme.SmartTaskTheme
import com.example.smarttask.ui.theme.customColors

@Composable
fun TextFieldComponent(
    upperText: String,
    label: String,
    icon: Painter,
    isPassword: Boolean = false
) {
    var text by remember { mutableStateOf("") }
    var isFocused by remember { mutableStateOf(false) }
    var isViewPassword by remember {mutableStateOf(false)}
    val animatedBorderWith by animateDpAsState(
        targetValue = if (isFocused) 2.dp else 0.5.dp,
        label = "BorderWidthAnimation",
        animationSpec = tween(
            durationMillis = 200,
            easing = LinearOutSlowInEasing
        )
    )
    val animatedBorderColor by animateColorAsState(
        targetValue = if (isFocused) customColors.primaryColor else customColors.borderColor,
        label = "BorderColorAnimation",
        animationSpec = tween(
            durationMillis = 200,
            easing = LinearOutSlowInEasing
        )
    )
    Column(
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        Text(
            text = upperText,
            style = MaterialTheme.typography.bodyMedium,
            fontSize = LocalConfiguration.current.screenWidthDp.sp * 0.045f,
        )
        TextField(
            value = text,
            onValueChange = {text = it},
            placeholder = {
                Text(text = label, color = customColors.textSecondaryColor)
            },

            textStyle = TextStyle(
                textAlign = TextAlign.Start,
                fontSize = LocalConfiguration.current.screenWidthDp.sp * 0.045f,
            ),
            shape = RoundedCornerShape(20.dp),
            singleLine = true,
            modifier = Modifier
                .fillMaxWidth()
                .onFocusChanged { focusState ->
                    isFocused = focusState.isFocused
                }
                .border(
                    width = animatedBorderWith,
                    color = animatedBorderColor,
                    shape = RoundedCornerShape(20.dp)
                ),
            prefix = {
                Icon(
                    painter = icon,
                    contentDescription = null,
                    tint = customColors.primaryColor,
                    modifier = Modifier
                        .size(LocalConfiguration.current.screenWidthDp.dp * 0.07f)
                        .padding(end = 10.dp)
                )
            },
            suffix = {
                if(isPassword){
                    val visibilityIcon = if (isViewPassword) {
                        painterResource(R.drawable.eye_svgrepo)
                    } else {
                        painterResource(R.drawable.eye_closed)
                    }
                    Icon(
                        painter = visibilityIcon,
                        contentDescription = null,
                        tint = customColors.primaryColor,
                        modifier = Modifier
                            .size(LocalConfiguration.current.screenWidthDp.dp * 0.07f)
                            .padding(start = 10.dp)
                            .clickable(
                                interactionSource = remember { MutableInteractionSource() },
                                indication = null,
                                onClick = {
                                    isViewPassword = !isViewPassword
                                }
                            )
                    )
                }
            },
            visualTransformation = if (isPassword && !isViewPassword) PasswordVisualTransformation() else VisualTransformation.None,
            colors = TextFieldDefaults.colors(
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent,

                focusedContainerColor = customColors.backgroundTextFieldColor,
                unfocusedContainerColor = customColors.backgroundTextFieldColor,
                disabledContainerColor = customColors.backgroundTextFieldColor,

                focusedTextColor = customColors.textSecondaryColor,
                unfocusedTextColor = customColors.textSecondaryColor,
                disabledTextColor = customColors.textSecondaryColor,

            )
        )
    }
}

@Preview(showBackground = true, showSystemUi = true, uiMode = 32)
@Composable
fun TextFieldComponentPreview() {
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
                TextFieldComponent(
                    upperText = "Correo Electrónico",
                    label = "Ingresa tu correo",
                    icon = painterResource(R.drawable.user_hands_svgrepo_com),
                    isPassword =true
                )
            }
        }
    }
}