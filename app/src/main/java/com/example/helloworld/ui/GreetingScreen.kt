package com.example.helloworld.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AssignmentInd
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.helloworld.ui.theme.HelloWorldTheme

/**
 * MINGGU 1 — Pemrograman Mobile Android
 * Form Sederhana dengan Validasi
 */
@Composable
fun GreetingScreen() {

    // ==========================================
    // STATE MANAGEMENT
    // ==========================================
    var name by remember { mutableStateOf("") }
    var idNumber by remember { mutableStateOf("") }
    
    // State untuk data yang sudah di-submit
    var submittedName by remember { mutableStateOf("") }
    var submittedIdNumber by remember { mutableStateOf("") }
    var isSubmitted by remember { mutableStateOf(false) }

    // State untuk validasi error
    var nameError by remember { mutableStateOf(false) }
    var idError by remember { mutableStateOf(false) }

    // ==========================================
    // UI LAYOUT
    // ==========================================
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        // ── Icon ──
        Icon(
            imageVector = Icons.Default.AssignmentInd,
            contentDescription = "Form Icon",
            modifier = Modifier.size(72.dp),
            tint = MaterialTheme.colorScheme.primary
        )

        Spacer(modifier = Modifier.height(16.dp))

        // ── Judul ──
        Text(
            text = "Form Data Diri",
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary,
            textAlign = TextAlign.Center
        )

        Text(
            text = "Silakan lengkapi data di bawah ini",
            fontSize = 14.sp,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(top = 4.dp, bottom = 32.dp)
        )

        // ── Card Input ──
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(16.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
        ) {
            Column(
                modifier = Modifier.padding(20.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Input Nama Lengkap
                OutlinedTextField(
                    value = name,
                    onValueChange = { 
                        name = it
                        nameError = false
                        isSubmitted = false 
                    },
                    label = { Text("Nama Lengkap") },
                    placeholder = { Text("Contoh: Budi Santoso") },
                    isError = nameError,
                    supportingText = {
                        if (nameError) {
                            Text("Nama tidak boleh kosong", color = MaterialTheme.colorScheme.error)
                        }
                    },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp)
                )

                Spacer(modifier = Modifier.height(8.dp))

                // Input NIM/NIP
                OutlinedTextField(
                    value = idNumber,
                    onValueChange = { 
                        // Hanya terima input angka
                        if (it.all { char -> char.isDigit() }) {
                            idNumber = it
                            idError = false
                            isSubmitted = false
                        }
                    },
                    label = { Text("NIM/NIP") },
                    placeholder = { Text("Contoh: 123456789") },
                    isError = idError,
                    supportingText = {
                        if (idError) {
                            Text("NIM/NIP harus berupa angka dan tidak boleh kosong", color = MaterialTheme.colorScheme.error)
                        }
                    },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp)
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Button Submit
                Button(
                    onClick = { 
                        // Validasi sederhana
                        nameError = name.isBlank()
                        idError = idNumber.isBlank()

                        if (!nameError && !idError) {
                            submittedName = name
                            submittedIdNumber = idNumber
                            isSubmitted = true
                        }
                    },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Text(
                        text = "Submit Data",
                        fontSize = 16.sp,
                        modifier = Modifier.padding(vertical = 4.dp)
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        // ── Result Display ──
        AnimatedVisibility(
            visible = isSubmitted,
            enter = fadeIn() + slideInVertically()
        ) {
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer
                )
            ) {
                Column(
                    modifier = Modifier.padding(20.dp),
                    horizontalAlignment = Alignment.Start
                ) {
                    Text(
                        text = "Data Terkirim:",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onPrimaryContainer
                    )
                    Divider(
                        modifier = Modifier.padding(vertical = 8.dp),
                        color = MaterialTheme.colorScheme.onPrimaryContainer.copy(alpha = 0.2f)
                    )
                    Text(
                        text = "Nama: $submittedName",
                        fontSize = 16.sp,
                        color = MaterialTheme.colorScheme.onPrimaryContainer
                    )
                    Text(
                        text = "NIM/NIP: $submittedIdNumber",
                        fontSize = 16.sp,
                        color = MaterialTheme.colorScheme.onPrimaryContainer,
                        modifier = Modifier.padding(top = 4.dp)
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true, name = "Form Screen Preview")
@Composable
fun GreetingScreenPreview() {
    HelloWorldTheme {
        GreetingScreen()
    }
}
