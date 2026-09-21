package com.example.loginapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.loginapplication.ui.theme.LoginApplicationTheme
import androidx.compose.ui.graphics.Color
import androidx.compose.foundation.Image
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.painterResource
import androidx.compose.foundation.Image
import androidx.compose.ui.res.painterResource



var savedFirstName = ""
var savedLastName = ""
var savedUsername = ""
var savedEmail = ""
var savedPassword = ""
var savedPhone = ""
var savedAddress = ""
var savedBirthDate = ""


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LoginApplicationTheme {
                AuthApp()
            }
        }
    }
}

@Composable
fun AuthApp() {
    var screen by remember { mutableStateOf("register") }

    when (screen) {
        "register" -> RegisterPage(
            onSaved = { screen = "login" },
            onGoToLogin = { screen = "login" }
        )
        "login" -> LoginPage(
            onLoginSuccess = { screen = "profile" },
            onGoToRegister = { screen = "register" }
        )
        "profile" -> ProfilePage(
            onChangeAvatar = { screen = "avatar" },
            onLogout = { screen = "login" }
        )
        "avatar" -> AvatarPage(
            onBack = { screen = "profile" }

        )
    }
}



@Composable
fun RegisterPage(onSaved: () -> Unit, onGoToLogin: () -> Unit) {
    var firstName by remember { mutableStateOf("") }
    var lastName by remember { mutableStateOf("") }
    var username by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var phone by remember { mutableStateOf("") }
    var address by remember { mutableStateOf("") }
    var birthDate by remember { mutableStateOf("") }
    var message by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFA80000))
            .verticalScroll(rememberScrollState())
            .padding(24.dp)
    ) {
        Text("Welcome to Andrii Njak App!", fontSize = 30.sp, color = Color.White)
        Text("Registration", fontSize = 28.sp, color = Color.White)

        OutlinedTextField(value = firstName, onValueChange = { firstName = it },
            label = { Text("First Name", color = Color.Gray) },shape = RoundedCornerShape(16.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedContainerColor = Color(0xFFFFFFFF),
                unfocusedContainerColor = Color(0xFFFFFFFF)
            ),
            modifier = Modifier.fillMaxWidth())

        OutlinedTextField(value = lastName, onValueChange = { lastName = it },
            label = { Text("Last Name", color = Color.Gray) },shape = RoundedCornerShape(16.dp),colors = OutlinedTextFieldDefaults.colors(
                focusedContainerColor = Color(0xFFFFFFFF),
                unfocusedContainerColor = Color(0xFFFFFFFF)
            ), modifier = Modifier
                .fillMaxWidth()
        )
        OutlinedTextField(value = username, onValueChange = { username = it },
            label = { Text("Username", color = Color.Gray) }, shape = RoundedCornerShape(16.dp),colors = OutlinedTextFieldDefaults.colors(
                focusedContainerColor = Color(0xFFFFFFFF),
                unfocusedContainerColor = Color(0xFFFFFFFF)
            ),modifier = Modifier
                .fillMaxWidth())


        OutlinedTextField(value = email, onValueChange = { email = it },
            label = { Text("Email", color = Color.Gray) },shape = RoundedCornerShape(16.dp),colors = OutlinedTextFieldDefaults.colors(
                focusedContainerColor = Color(0xFFFFFFFF),
                unfocusedContainerColor = Color(0xFFFFFFFF)
            ), modifier = Modifier
                .fillMaxWidth())

        OutlinedTextField(value = password, onValueChange = { password = it },
            label = { Text("Password", color = Color.Gray) },shape = RoundedCornerShape(16.dp),colors = OutlinedTextFieldDefaults.colors(
                focusedContainerColor = Color(0xFFFFFFFF),
                unfocusedContainerColor = Color(0xFFFFFFFF)
                ), modifier = Modifier
                .fillMaxWidth(),

            visualTransformation = PasswordVisualTransformation())
        OutlinedTextField(value = phone, onValueChange = { phone = it },
            label = { Text("Phone", color = Color.Gray) }, shape = RoundedCornerShape(16.dp),colors = OutlinedTextFieldDefaults.colors(
                focusedContainerColor = Color(0xFFFFFFFF),
                unfocusedContainerColor = Color(0xFFFFFFFF)
            ),modifier = Modifier
                .fillMaxWidth())

        OutlinedTextField(value = address, onValueChange = { address = it },
            label = { Text("Address", color = Color.Gray) },shape = RoundedCornerShape(16.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedContainerColor = Color(0xFFFFFFFF),
                unfocusedContainerColor = Color(0xFFFFFFFF)
            ), modifier = Modifier
                .fillMaxWidth())

        OutlinedTextField(value = birthDate, onValueChange = { birthDate = it },
            label = { Text("Birth Date", color = Color.Gray) }, shape = RoundedCornerShape(16.dp),colors = OutlinedTextFieldDefaults.colors(
                focusedContainerColor = Color(0xFFFFFFFF),
                unfocusedContainerColor = Color(0xFFFFFFFF)
            ), modifier = Modifier
                .fillMaxWidth())

        Text(message, color = MaterialTheme.colorScheme.error)

        Button(
            onClick = {
                if (username.isBlank() || password.isBlank()) {
                    message = "Username and password are required."
                } else {
                    savedFirstName = firstName
                    savedLastName = lastName
                    savedUsername = username
                    savedEmail = email
                    savedPassword = password
                    savedPhone = phone
                    savedAddress = address
                    savedBirthDate = birthDate
                    onSaved()
                }
            },
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFFFFFF)),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Save",color = Color.Red)
        }

//        Button(onClick = {}, modifier = Modifier.fillMaxWidth()){ Text("Hai")}

        TextButton(onClick = onGoToLogin) {
            Text("Already have an account? Login")
        }
    }
}



@Composable
fun LoginPage(onLoginSuccess: () -> Unit, onGoToRegister: () -> Unit) {
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var message by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFA80000))
            .padding(24.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Text("Login", fontSize = 28.sp, color = Color.White)

        OutlinedTextField(value = username, onValueChange = { username = it },
            label = { Text("Username", color = Color.Gray) }, shape = RoundedCornerShape(16.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedContainerColor = Color(0xFFFFFFFF),
                unfocusedContainerColor = Color(0xFFFFFFFF)
            ),
            modifier = Modifier.fillMaxWidth())

        OutlinedTextField(value = password, onValueChange = { password = it },
            label = { Text("Password", color = Color.Gray) }, shape = RoundedCornerShape(16.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedContainerColor = Color(0xFFFFFFFF),
                unfocusedContainerColor = Color(0xFFFFFFFF)
            ),modifier = Modifier.fillMaxWidth(),
            visualTransformation = PasswordVisualTransformation())

        Text(message, color = MaterialTheme.colorScheme.error)

        Button(
            onClick = {
                if (username == savedUsername && password == savedPassword && savedUsername != "") {
                    onLoginSuccess()
                } else {
                    message = "Wrong username or password."
                }
            },
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFFFFFF)),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Login", color = Color.Red)
        }

        TextButton(onClick = { message = "Reset password is not available yet." }) {
            Text("Forgot Password", color = Color.White)
        }

        TextButton(onClick = onGoToRegister) {
            Text("Do not have an account? Register", color = Color.White)
        }
    }
}



@Composable
fun ProfilePage(onChangeAvatar: () -> Unit, onLogout: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFA80000))
            .verticalScroll(rememberScrollState())
            .padding(24.dp)
    ) {
        Text("Profile", fontSize = 28.sp, color = Color.White)
        Image(
            painter = painterResource(R.drawable.visage),
            contentDescription = "Avatar",
            modifier = Modifier.size(120.dp)
        )

        Text("First Name: $savedFirstName", color = Color.White)
        Text("Last Name: $savedLastName", color = Color.White)
        Text("Username: $savedUsername", color = Color.White)
        Text("Email: $savedEmail", color = Color.White)
        Text("Phone: $savedPhone", color = Color.White)
        Text("Address: $savedAddress", color = Color.White)
        Text("Birth Date: $savedBirthDate", color = Color.White)

        Button(onClick = onChangeAvatar,
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFFFFFF)),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Change Avatar", color = Color.Red)
        }
        Button(onClick = onLogout,
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFFFFFF)),
            modifier = Modifier.fillMaxWidth()) {
            Text("Logout", color = Color.Red)
        }
    }
}


@Composable
fun AvatarPage(onBack: () -> Unit) {
    var showEyebrow by remember { mutableStateOf(true) }
    var showEyes by remember { mutableStateOf(true) }
    var showNose by remember { mutableStateOf(true) }
    var showMouth by remember { mutableStateOf(true) }


    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFA80000))
            .verticalScroll(rememberScrollState())
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Your Avatar", fontSize = 28.sp, color = Color.White)

        Spacer(modifier = Modifier.height(16.dp))

        Box(modifier = Modifier.width(300.dp).height(425.dp)) {


            Image(
                painter = painterResource(R.drawable.face),
                contentDescription = "Face",
                modifier = Modifier.fillMaxSize()
            )


            if (showNose) {
                Image(
                    painter = painterResource(R.drawable.nose),
                    contentDescription = "Nose",
                    modifier = Modifier
                        .width(70.dp)
                        .align(Alignment.TopCenter)
                        .offset(y = 235.dp)
                )
            }


            if (showMouth) {
                Image(
                    painter = painterResource(R.drawable.mouth),
                    contentDescription = "Mouth",
                    modifier = Modifier
                        .width(75.dp)
                        .align(Alignment.TopCenter)
                        .offset(y = 280.dp)
                )
            }


            if (showEyes) {
                Image(
                    painter = painterResource(R.drawable.eyes),
                    contentDescription = "Eyes",
                    modifier = Modifier
                        .width(198.dp)
                        .align(Alignment.TopCenter)
                        .offset(y = 184.dp)
                )
            }

            if (showEyebrow) {
                Image(
                    painter = painterResource(R.drawable.eyebrow),
                    contentDescription = "Eyebrows",
                    modifier = Modifier
                        .width(198.dp)
                        .align(Alignment.TopCenter)
                        .offset(y = 155.dp)
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))


        FaceCheckbox("Eyebrows", showEyebrow) { showEyebrow = it }
        FaceCheckbox("Eyes", showEyes) { showEyes = it }
        FaceCheckbox("Nose", showNose) { showNose = it }
        FaceCheckbox("Mouth", showMouth) { showMouth = it }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = onBack,
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFFFFFF)),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Back to Profile", color = Color.Red)
        }
    }
}


@Composable
fun FaceCheckbox(label: String, checked: Boolean, onCheckedChange: (Boolean) -> Unit) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Checkbox(
            checked = checked,
            onCheckedChange = onCheckedChange,
            colors = CheckboxDefaults.colors(
                checkedColor = Color.White,
                uncheckedColor = Color.White,
                checkmarkColor = Color.Red
            )
        )
        Text(label, color = Color.White)
    }
}