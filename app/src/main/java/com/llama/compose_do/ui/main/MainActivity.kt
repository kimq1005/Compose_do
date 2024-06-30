package com.llama.compose_do.ui.main

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.Stable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.key
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.llama.compose_do.Global.MainGlobal
import com.llama.compose_do.solid.Circle
import com.llama.compose_do.solid.Square

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val viewModel: MainViewModel = viewModel()
            val navController = rememberNavController()

            NavHost(
                navController = navController,
                startDestination = MainGlobal.MAIN_SCREEN
            ) {
                composable(MainGlobal.MAIN_SCREEN) {
                    MainScreen(viewModel = viewModel, navController = navController)
                }

                composable(MainGlobal.SUB_SCREEN) {
                    SubScreen(viewModel = viewModel, navController = navController)
                }
            }
        }
    }
}

@Composable
fun MainScreen(
    viewModel: MainViewModel,
    navController: NavController
) {
    TitleLayout(
        title = viewModel.titleText.value,
        onSetTitle = { titleText ->
            Circle().draw()
            viewModel.setTitleText(titleText)
        },
        onNextPage = {
            Square().draw()
            navController.navigate(MainGlobal.SUB_SCREEN)
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TitleLayout(
    title: String,
    onSetTitle: (String) -> Unit,
    onNextPage: () -> Unit
) {
    DisposableEffect(Unit) {
        Log.d("Recomposition", "RecompositionButtonLayout")
        onDispose { }
    }

    val titleRememberText = remember {
        mutableStateOf("")
    }

    val checkValidationYeah = remember {
        derivedStateOf() {
            checkValidation(titleRememberText.value)
        }
    }

    Log.d("TAG", "TitleLayout: ${checkValidation(titleRememberText.value)} // $checkValidationYeah")

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(horizontal = 20.dp),
    ) {
        Text(
            text = title,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center,
            color = Color.Black,
            fontSize = 30.sp
        )

        Spacer(modifier = Modifier.height(20.dp))

        TextField(
            value = titleRememberText.value,
            onValueChange = {
                titleRememberText.value = it
            },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(20.dp))

        for(i in 0..1) {
            key(i) {
                KeyLayOut(string = i.toString())
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        Button(
            onClick = {
                onSetTitle(
                    titleRememberText.value
                )
            },
            modifier = Modifier
                .fillMaxWidth()
                .background(if (!checkValidationYeah.value) Color.Gray else Color.White)
                .height(70.dp)
        ) {
            Text(text = "set Title")
        }

        Spacer(modifier = Modifier.weight(1f))

        RecompositionButtonLayout()

        Spacer(modifier = Modifier.height(20.dp))

        ResultButtonLayout(
            onNextPage = {
                onNextPage()
            }
        )
    }
}

@Composable
fun SubScreen(viewModel: MainViewModel, navController: NavController) {
    SubTitleLayout(
        title = viewModel.titleText.value,
        onBack = {
            navController.popBackStack()
        })
}

@Composable
fun SubTitleLayout(title: String, onBack:()-> Unit) {
    Column(modifier = Modifier
        .fillMaxSize()
        .background(Color.White),
    ) {
        Text(
            text = title,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center,
            color = Color.Black,
            fontSize = 30.sp,
        )

        Spacer(
            modifier = Modifier
                .weight(1f),
        )

        Button(
            onClick = { onBack() },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
                .padding(bottom = 20.dp)
                .height(70.dp),
        ) {
            Text(text = "Back")
        }
    }
}

@Composable
fun RecompositionButtonLayout() {

    Button(
        onClick = {

        },
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 20.dp)
            .height(70.dp)
    ) {
        Text(text = "RecompositionCheck")
    }
}
@Composable
@Stable
fun ResultButtonLayout(
    onNextPage: () -> Unit
) {
    Log.d("Recomposition", "ResultButtonLayout")

    Button(
        onClick = {
            onNextPage()
        },
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 20.dp)
            .height(70.dp)
    ) {
        Text(text = "NextPage")
    }
}

@Composable
fun KeyLayOut(string: String) {
    Column(
        modifier = Modifier
            .fillMaxWidth(),
    ) {
        Text(
            text = string,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth(),
        )
    }
}

private fun checkValidation(title: String) : Boolean {
    return title.length > 5
}

@Preview
@Composable
fun PreviewTitleLayout() {
    TitleLayout(
        title = "히힛",
        onSetTitle = {},
        onNextPage = {},
    )
}

@Preview
@Composable
fun PreviewSubScreen() {
    SubTitleLayout(
        title = "히힛",
        onBack = {}
    )
}

abstract class Animal(val name: String) {
    abstract fun speak()
    abstract fun thrTow()
}

class Dog: Animal("개") {
    override fun speak() {
        //
    }

    override fun thrTow() {
        //
    }
}

