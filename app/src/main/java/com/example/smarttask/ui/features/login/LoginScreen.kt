package com.example.smarttask.ui.features.login

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.ime
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.smarttask.R
import com.example.smarttask.ui.components.ButtonComponent
import com.example.smarttask.ui.components.TextFieldComponent
import com.example.smarttask.ui.theme.SmartTaskTheme
import com.example.smarttask.ui.theme.customColors

@Composable
fun LoginScreen(
    onNavigateHome: () -> Unit,
    onNavigateRegister : () -> Unit
) {
    val focusManager = LocalFocusManager.current
    val density = LocalDensity.current

    val ime = WindowInsets.ime

    val isKeyboardOpen by remember {
        derivedStateOf {
            ime.getBottom(density) > 0
        }
    }

    LaunchedEffect(isKeyboardOpen) {
        if (!isKeyboardOpen) {
            focusManager.clearFocus()
        }
    }

    Surface(
        modifier = Modifier
            .fillMaxSize()
            .clickable(
                indication = null,
                interactionSource = remember { MutableInteractionSource() }
            ) {
                focusManager.clearFocus()
            }
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(
                space = LocalConfiguration.current.screenWidthDp.dp * 0.05f,
                alignment = Alignment.CenterVertically
            ),
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(LocalConfiguration.current.screenWidthDp.dp * 0.04f)
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Icon(
                    painter = painterResource(R.drawable.check_circle),
                    contentDescription = "App Icon",
                    tint = MaterialTheme.colorScheme.primary,
                    modifier = Modifier
                        .size(LocalConfiguration.current.screenWidthDp.dp * 0.2f)
                        .background(color = customColors.iconBackground, shape = CircleShape)
                        .padding(LocalConfiguration.current.screenWidthDp.dp * 0.03f)
                )
                Text(
                    text = "Bienvenido de nuevo",
                    style = MaterialTheme.typography.titleLarge,
                    fontSize = (LocalConfiguration.current.screenWidthDp.sp * 0.08f),
                    modifier = Modifier.padding(vertical = 5.dp)
                )
                Text(
                    text = "Inicia sesión para continuar",
                    style = MaterialTheme.typography.bodySmall,
                    fontSize = LocalConfiguration.current.screenWidthDp.sp * 0.05f,
                    color = customColors.textSecondaryColor,
                    modifier = Modifier.padding(vertical = LocalConfiguration.current.screenWidthDp.dp * 0.01f)
                )
            }
            TextFieldComponent(
                upperText = "Correo Electrónico",
                label = "Ingresa tu correo electrónico",
                icon = painterResource(R.drawable.user_hands_svgrepo_com),
            )
            TextFieldComponent(
                upperText = "Contraseña",
                label = "Ingresa tu contraseña",
                icon = painterResource(R.drawable.lock_password_svgrepo_com),
                isPassword = true
            )
            ButtonComponent(
                text = "Log In",
                onClick = onNavigateHome
            )
            Row(
                horizontalArrangement = Arrangement.spacedBy(
                    space = LocalConfiguration.current.screenWidthDp.dp * 0.02f,
                    alignment = Alignment.CenterHorizontally
                ),
                verticalAlignment = Alignment.CenterVertically
            ) {
                HorizontalDivider(modifier = Modifier.weight(1f))
                Text(
                    text = "O",
                    style = MaterialTheme.typography.bodyMedium,
                    fontSize = (LocalConfiguration.current.screenWidthDp.sp * 0.04f),
                    color = customColors.textSecondaryColor
                )
                HorizontalDivider(modifier = Modifier.weight(1f))
            }
            ContainerLogInWith(
                icon = painterResource(R.drawable.google_icon),
                text = "Continuar con Google"
            )
            ContainerLogInWith(
                icon = painterResource(R.drawable.apple_icon),
                text = "Continuar con Apple"
            )
            Row() {
                Text(
                    text = "¿No tienes una cuenta?",
                    style = MaterialTheme.typography.bodyMedium,
                    fontSize = LocalConfiguration.current.screenWidthDp.sp * 0.04f,
                    color = customColors.textSecondaryColor,
                )
                Text(
                    text = " Regístrate",
                    style = MaterialTheme.typography.titleLarge,
                    fontSize = LocalConfiguration.current.screenWidthDp.sp * 0.04f,
                    textDecoration = TextDecoration.Underline,
                    color = MaterialTheme.colorScheme.primary,
                    modifier = Modifier
                        .clickable(
                            indication = null,
                            interactionSource = remember { MutableInteractionSource() }
                        ) {
                            onNavigateRegister()
                        }
                )
            }

        }
    }
}

@Composable
private fun ContainerLogInWith(icon: Painter, text: String) {
    Box(
    modifier = Modifier
        .fillMaxWidth()
        .background(
            color = customColors.textFieldBackgroundColor,
            shape = MaterialTheme.shapes.extraLarge
        )
        .border(
            width = 0.5.dp,
            color = customColors.borderColor,
            shape = MaterialTheme.shapes.extraLarge
        )
    ){
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(
                space = LocalConfiguration.current.screenWidthDp.dp * 0.03f,
                alignment = Alignment.CenterHorizontally
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    vertical = LocalConfiguration.current.screenWidthDp.dp * 0.03f,
                    horizontal = LocalConfiguration.current.screenWidthDp.dp * 0.04f
                )

        ) {
            Icon(
                painter = icon,
                contentDescription = null,
                tint = Color.Unspecified,
                modifier = Modifier
                    .size(LocalConfiguration.current.screenWidthDp.dp * 0.07f)
            )
            Text(
                text = text,
                style = MaterialTheme.typography.titleMedium,
                fontSize = LocalConfiguration.current.screenWidthDp.sp * 0.040f,
                color = MaterialTheme.colorScheme.onSurface,
                modifier = Modifier.padding(start = LocalConfiguration.current.screenWidthDp.dp * 0.03f)
            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun LoginScreenPreview() {
    SmartTaskTheme {
        LoginScreen(
            onNavigateHome = {},
            onNavigateRegister = {}
        )
    }
}
