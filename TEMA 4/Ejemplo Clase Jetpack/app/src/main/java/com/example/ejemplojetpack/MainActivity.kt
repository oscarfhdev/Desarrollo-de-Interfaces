package com.example.ejemplojetpack

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.ejemplojetpack.ui.theme.EjemploJetpackTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            EjemploJetpackTheme {

            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun previsualizacion(){
    // crearColumna()
    //crearFila()
    crearMuchasFilas()
}

@Composable
fun crearColumna(){
    Column(modifier = Modifier.padding(100.dp)) {
        mensaje("Hola clase")
        mostrarSuma(8,3)
    }
}

@Composable
fun crearCadenaMensajes(mensajes: List<String>, modifier: Modifier = Modifier){
    Column(modifier = modifier.padding(16.dp)) {
        mensajes.forEach{
            Text(it)
        }
    }
}
@Composable
fun crearFila(modifier: Modifier = Modifier){
    val mensajes = listOf("Hola", "como", "estas")
    Row(
        modifier = modifier.padding(16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
        //verticalAlignment = Alignment.CenterVertically,
        //horizontalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.icono),
            contentDescription = "Imagen 1",
            modifier = Modifier.size(50.dp)
        )
        crearCadenaMensajes(mensajes)
    }
}

@Composable
fun crearMuchasFilas(){
    var estadoScroll = rememberScrollState()
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .verticalScroll(estadoScroll),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        crearFila()
        crearFila()
        crearFila()
        crearFila()
        crearFila()
        crearFila()
        crearFila()
        crearFila()
        crearFila()
        crearFila()
        crearFila()
        crearFila()
        crearFila()
        crearFila()
        crearFila()
        crearFila()
        crearFila()
    }
}

@Composable
fun mensaje(cadena:String){
    Text(cadena)
}

@Composable
fun mostrarSuma(num1: Int, num2: Int){
    Text(sumar(num1, num2).toString())
}

fun sumar(num1: Int, num2: Int): Int {
    return num1 + num2
}