package com.example.ejemplojetpack

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
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
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.TextField
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue

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
    //crearMuchasFilas()
    //val mensaje = textos("Pepe", "Jetpack")
    //mensajeClase(mensaje)
    //crearFilaClase(mensaje)
    /*val arrayMensajes: List<textos> = listOf<textos>(
        textos("Pepe", "Jetpack"),
        textos("Juan", "Jetpack"),
        textos("Pedro", "Jetpack"),
        textos("Maria", "Jetpack"),
        textos("Jose", "Jetpack"),
        textos("Ana", "Jetpack"),
        textos("Luis", "Jetpack")
    )*/

    //crearColumnaClase(arrayMensajes)
    extraerVariable()

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

/*@Composable
fun mensajeClase(mensaje: textos){
    Text("Hola " + mensaje.texto1 + " bienvenido a " + mensaje.texto2, modifier = Modifier.padding(30.dp))
}*/

@Composable
fun crearFilaClase(mensaje: textos){
    var estadoLineas by remember { mutableStateOf(false) }

    Row(modifier = Modifier.padding(36.dp).clickable{
        estadoLineas = !estadoLineas
    }) {

        Image(
            painter = painterResource(id = R.drawable.icono),
            contentDescription = "Imagen 1",
            modifier = Modifier.size(50.dp)
        )
        mensajeClase(mensaje, estadoLineas)
    }
}

@Composable
fun crearColumnaClase(mensaje: List<textos>){
    LazyColumn() {
        items(mensaje.size){
            crearFilaClase(mensaje[it])
        }
    }
}

@Composable
fun mensajeClase(mensaje: textos, estado: Boolean) {
    Text("Hola " + mensaje.texto1 + " bienvenido a " + mensaje.texto2 + " aaaaaaa aaaaaaaaaaaaaaaaaaaaaaa aaaaaa aaaaaaaaaaa", modifier = Modifier.padding(30.dp), maxLines = if (estado) Int.MAX_VALUE else 1)
}

@Composable
fun extraerVariable(){
    var numero by remember { mutableStateOf(0) }

    Column(modifier = Modifier.padding(30.dp),
        horizontalAlignment = Alignment.CenterHorizontally) {

        Text("Número 1")
        OutlinedTextField(value = numero.toString(),
            onValueChange = {newValue -> if (newValue.all { it.isDigit() }) numero = newValue.toInt()
            }
        )
        Text("el número es: $numero" )
    }

}