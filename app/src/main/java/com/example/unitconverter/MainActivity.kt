package com.example.unitconverter

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.unitconverter.ui.theme.UnitConverterTheme
import kotlin.math.roundToInt

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            UnitConverterTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    UnitConverter(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun UnitConverter(modifier: Modifier = Modifier) {
    var inputVal by remember {
        mutableStateOf("")
    }
    var outputVal by remember {
        mutableStateOf("")
    }
    var inputTyp by remember {
        mutableStateOf("Meters")
    }
    var outputTyp by remember {
        mutableStateOf("Meters")
    }
    var iExpanded by remember {
        mutableStateOf(false)
    }
    var oExpanded by remember {
        mutableStateOf(false)
    }
    val inConversionFactor = remember {
        mutableStateOf(1.00)
    }
    val opConversionFactor = remember {
        mutableStateOf(1.00)
    }


    fun CovertUnits(){
        val inputValueDouble = inputVal.toDoubleOrNull() ?: 0.0
        val result = (inputValueDouble * inConversionFactor.value * 100.0/opConversionFactor.value).roundToInt()/100.0
        outputVal = result.toString()
    }

    val context = LocalContext.current
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Unit Converter")
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(value = inputVal, onValueChange = {
            inputVal = it
            CovertUnits()
        },
            label = { Text(text = "Enter Value")})
        Spacer(modifier = Modifier.height(16.dp))
        Row {
            Box {

                Button(onClick = { iExpanded = true}) {
                    Text(text = inputTyp)
                    Icon(
                        Icons.Default.ArrowDropDown,
                        contentDescription = "Arrow Down"
                    )
                }
                DropdownMenu(expanded = iExpanded, onDismissRequest = { iExpanded = false }) {
                    DropdownMenuItem(text = { Text(text = "Centimeters") }, onClick = {
                        iExpanded=false
                        inputTyp="Centimeters"
                        inConversionFactor.value = 0.01
                        CovertUnits()
                    })
                    DropdownMenuItem(text = { Text(text = "Meters") }, onClick = {
                        iExpanded=false
                        inputTyp="Meters"
                        inConversionFactor.value = 1.00
                        CovertUnits()
                    })
                    DropdownMenuItem(text = { Text(text = "Feet") }, onClick = {
                        iExpanded=false
                        inputTyp="Feet"
                        inConversionFactor.value = 0.3048
                        CovertUnits()
                    })
                    DropdownMenuItem(text = { Text(text = "Milimeters") }, onClick = {
                        iExpanded=false
                        inputTyp="Milimeters"
                        inConversionFactor.value = 0.001
                        CovertUnits()
                    })

                }

            }
            Spacer(modifier = Modifier.width(16.dp))
            Box {
                Button(onClick = { oExpanded = true }) {
                    Text(text = outputTyp)
                    Icon(
                        Icons.Default.ArrowDropDown,
                        contentDescription = "Arrow Down"
                    )
                }
                DropdownMenu(expanded = oExpanded, onDismissRequest = { oExpanded = false}) {
                    DropdownMenuItem(text = { Text(text = "Centimeters") }, onClick = {
                        oExpanded=false
                        outputTyp="Centimeters"
                        opConversionFactor.value = 0.01
                        CovertUnits()
                    })
                    DropdownMenuItem(text = { Text(text = "Meters") }, onClick = {
                        oExpanded=false
                        outputTyp="Meters"
                        opConversionFactor.value = 1.00
                        CovertUnits()
                    })
                    DropdownMenuItem(text = { Text(text = "Feet") }, onClick = {
                        oExpanded=false
                        outputTyp="Feet"
                        opConversionFactor.value = 0.3048
                        CovertUnits()
                    })
                    DropdownMenuItem(text = { Text(text = "Milimeters") }, onClick = {
                        oExpanded=false
                        outputTyp="Milimeters"
                        opConversionFactor.value = 0.001
                        CovertUnits()
                    })

                }
            }


        }
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = "Result: ${outputVal+outputTyp}")
    }
}



