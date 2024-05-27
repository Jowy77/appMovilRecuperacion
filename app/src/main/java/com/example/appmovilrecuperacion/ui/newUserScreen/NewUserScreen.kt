package com.example.appmovilrecuperacion.ui.newUserScreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.appmovilrecuperacion.R
import com.example.appmovilrecuperacion.ui.routes.Routes

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewUserScreen(navController: NavController?) {
    var user by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var passwordRepeat by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .background(Color.White)
            .fillMaxSize()
            .padding(30.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Logo(size = 200.dp)
        Title(text = "New User Account", fontSize = 28.sp)
        Spacer(modifier = Modifier.height(16.dp))
        EmailInput(email) { email = it }
        Spacer(modifier = Modifier.height(16.dp))
        PasswordInput(password) { password = it }
        Spacer(modifier = Modifier.height(16.dp))
        RepeatPasswordInput(passwordRepeat) { passwordRepeat = it }
        Spacer(modifier = Modifier.height(30.dp))
        CreateAccountButton {
            navController?.navigate(Routes.LoginScreen.ruta)
        }
    }
}

@Composable
fun Logo(size: Dp) {
    Image(
        painter = painterResource(id = R.drawable.logo),
        contentDescription = "Jowys´s Logo",
        modifier = Modifier.size(size)
    )
}

@Composable
fun Title(text: String, fontSize: TextUnit) {
    Text(
        text = text,
        fontSize = fontSize,
        textAlign = TextAlign.Justify,
        color = Color.DarkGray
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EmailInput(value: String, onValueChange: (String) -> Unit) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text("Email", color = Color.DarkGray) },
        placeholder = { Text("Email", color = Color.Gray) },
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp)
            .clip(RoundedCornerShape(5.dp)),
        textStyle = TextStyle(color = Color.DarkGray),
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = Color.DarkGray,
            unfocusedBorderColor = Color.Gray
        )
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PasswordInput(value: String, onValueChange: (String) -> Unit) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text("Password", color = Color.DarkGray) },
        placeholder = { Text("Password", color = Color.Gray) },
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp)
            .clip(RoundedCornerShape(5.dp)),
        textStyle = TextStyle(color = Color.DarkGray),
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = Color.DarkGray,
            unfocusedBorderColor = Color.Gray
        )
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RepeatPasswordInput(value: String, onValueChange: (String) -> Unit) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text("Repeat Password", color = Color.DarkGray) },
        placeholder = { Text("Repeat Password", color = Color.Gray) },
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp)
            .clip(RoundedCornerShape(5.dp)),
        textStyle = TextStyle(color = Color.DarkGray),
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = Color.DarkGray,
            unfocusedBorderColor = Color.Gray
        )
    )
}

@Composable
fun CreateAccountButton(onClick: () -> Unit) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp)
            .clip(RoundedCornerShape(10.dp)),
        shape = RectangleShape,
        colors = ButtonDefaults.buttonColors(Color(0, 0, 139))
    ) {
        Text(text = "Create Account", fontSize = 20.sp, fontWeight = FontWeight.Bold, color = Color.White)
    }
}
@Preview(showSystemUi = true, showBackground = true)
@Composable
fun LoginPreview() {
    NewUserScreen(navController = null)
}