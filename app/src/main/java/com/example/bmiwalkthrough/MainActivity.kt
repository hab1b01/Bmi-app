package com.example.bmiwalkthrough

import android.icu.text.DecimalFormat
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import com.example.bmiwalkthrough.ui.theme.BMIWalkthroughTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BMIWalkthroughTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Bmi(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}


@Composable
fun Bmi(modifier:Modifier = Modifier){
    var heightInput: String by remember { mutableStateOf("") }
    var weightInput: String by remember { mutableStateOf("") }
    val height = heightInput.toFloatOrNull() ?: 0.0f
    val weight = weightInput.toIntOrNull() ?: 0
    val formatter = DecimalFormat("0.00")
    val bmi = if (weight > 0 && height > 0) formatter.format(weight / (height *height)) else 0.0f
    Column {
        Text(text = "Body Mass Index",
            fontSize = 24.sp,
            color = MaterialTheme.colorScheme.primary,
            textAlign = TextAlign.Center,
            modifier= modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp)

            )
   OutlinedTextField(
       value = heightInput,
       label = { Text(stringResource(R.string.height)) },
       singleLine = true,
       keyboardOptions = KeyboardOptions(
           keyboardType = KeyboardType.Number
       ),
       modifier = Modifier
           .fillMaxWidth()
           .padding(start = 8.dp, end = 8.dp),
       onValueChange = { heightInput = it.replace(',', '.') },
   )

        OutlinedTextField(value = weightInput,
            label = {Text(stringResource(R.string.weight))},
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number),
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 8.dp, end = 8.dp),
            onValueChange = {weightInput = it.replace(',','.')},   )
        Text(
            text = stringResource(R.string.result) +" " +bmi,
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, top = 16.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    BMIWalkthroughTheme {
        Bmi()
    }
}