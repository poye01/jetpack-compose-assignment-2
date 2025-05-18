package com.example.jetpack_compose_assignment_2.ui.screen



import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults

@Composable
fun OnboardingScreen(onContinueClicked: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Welcome to ToDo list App!📝",
            style = MaterialTheme.typography.headlineSmall.copy(
                color = MaterialTheme.colorScheme.onPrimary
            ))
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = onContinueClicked,
            shape = RoundedCornerShape(24.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.onPrimary,
                contentColor = MaterialTheme.colorScheme.primary
            )
        ) {
            Text("Continue")
        }
    }
}