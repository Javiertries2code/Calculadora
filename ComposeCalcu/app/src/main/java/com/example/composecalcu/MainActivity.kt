package com.example.composecalcu


import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val configuration = LocalConfiguration.current

            when (configuration.orientation) {
                Configuration.ORIENTATION_LANDSCAPE -> {
                    LandscapeScreenComposition()
                }
                else -> {
                    VerticalScreenComposition()
                }
        }
    }
}


@Composable
fun OperatorButton(
    operator: String,
    onOperatorClick: (String) -> Unit,
    backgroundColor: Color = colorResource(R.color.default_orange)

) {
    Button(
        onClick = { onOperatorClick(operator) },
        colors = ButtonDefaults.buttonColors(backgroundColor),
        shape = RoundedCornerShape(10.dp),
        modifier = Modifier.padding(8.dp).width(75.dp).height(50.dp),


        ) {
        Text(text = operator, fontSize = 18.sp, color = Color.Black)
    }
}
    @Composable
    fun PowerButton(
        number: String,
        onOperatorClick: (String) -> Unit,

        backgroundColor: Color = Color.Red

    ) {
        Button(
            onClick = { onOperatorClick(number)},
            colors = ButtonDefaults.buttonColors(backgroundColor),
            shape = RoundedCornerShape(12.dp),
            modifier = Modifier.padding(8.dp).width(70.dp).height(50.dp)
        ) {
            Text(text = number, fontSize = 14.sp, color = Color.Black)
        }
    }

@Composable
fun NumberButton(
    number: String,
    onNumberClick: (String) -> Unit,
    backgroundColor: Color = colorResource(R.color.focused_blue),


) {
    Button(
        onClick = { onNumberClick(number) },
        colors = ButtonDefaults.buttonColors(backgroundColor),
        shape = RoundedCornerShape(12.dp),
        modifier = Modifier.padding(8.dp).width(75.dp).height(50.dp)
    ) {
        Text(text = number, fontSize = 18.sp, color = Color.Black)
    }
}

   @Preview(showBackground = true, showSystemUi = true,
   device = "spec:width=411dp,height=891dp,orientation=landscape")
    @Composable
    fun LandscapeScreenComposition() {
        var numberDisplay by rememberSaveable { mutableStateOf("0") }
        var sign: String
        fun operatorButtonClick(operator: String) {

            if (operator == "C" || operator == "ON")
                numberDisplay = "0"
            else if (numberDisplay == "0" && operator !=  "+/-")
                numberDisplay = operator
            else if (operator == "+/-" || operator == "+-") {
                if (numberDisplay == "0")
                    numberDisplay = "0"
                else {
                    if (numberDisplay[0] != '-') {
                        sign = "-$numberDisplay"
                        numberDisplay = sign
                    } else {
                        sign = numberDisplay.substring(1)
                        numberDisplay = sign
                    }
                }
            } else
                numberDisplay += operator

        }

        fun numberButtonClick(number: String) {
            if (numberDisplay == "0")
                numberDisplay = number
            else
                numberDisplay += number
        }
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.DarkGray),
            verticalArrangement = Arrangement.SpaceEvenly
        )
        {

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp)
                    .height(70.dp).padding(top = 5.dp)
            ) {

                    TextField(
                        value = numberDisplay,
                        onValueChange = {},
                        modifier = Modifier.fillMaxWidth().padding(start = 90.dp, end = 90.dp),
                        textStyle = TextStyle(
                            fontSize = 35.sp,
                            color = Color.Black,
                            textAlign = TextAlign.End
                        ),
                    )

            }

            Row(
                horizontalArrangement = Arrangement.SpaceEvenly,
                modifier = Modifier.fillMaxWidth()
            )
            {
                NumberButton(number = "7", onNumberClick = ::numberButtonClick)
                NumberButton(number = "8", onNumberClick = ::numberButtonClick)
                NumberButton(number = "9", onNumberClick = ::numberButtonClick)
                OperatorButton(operator = "+", onOperatorClick = ::operatorButtonClick)
                OperatorButton(operator = "-", onOperatorClick = ::operatorButtonClick)

            }

            Row(
                horizontalArrangement = Arrangement.SpaceEvenly,
                modifier = Modifier.fillMaxWidth()
            )
            {
                NumberButton(number = "4", onNumberClick = ::numberButtonClick)
                NumberButton(number = "5", onNumberClick = ::numberButtonClick)
                NumberButton(number = "6", onNumberClick = ::numberButtonClick)
                OperatorButton(operator = "+-", onOperatorClick = ::operatorButtonClick)
                OperatorButton(operator = "%", onOperatorClick = ::operatorButtonClick)



            }
            Row(
                horizontalArrangement = Arrangement.SpaceEvenly,
                modifier = Modifier.fillMaxWidth()
            )
            {
                NumberButton(number = "1", onNumberClick = ::numberButtonClick)
                NumberButton(number = "2", onNumberClick = ::numberButtonClick)
                NumberButton(number = "3", onNumberClick = ::numberButtonClick)
                OperatorButton(operator = "/", onOperatorClick = ::operatorButtonClick)
                OperatorButton(operator = "X", onOperatorClick = ::operatorButtonClick)
            }

            Row(
                horizontalArrangement = Arrangement.SpaceEvenly,
                modifier = Modifier.fillMaxWidth()
            )
            {
                NumberButton(number = "0", onNumberClick = ::numberButtonClick)
                NumberButton(number = ".", onNumberClick = ::numberButtonClick)
                OperatorButton(operator = "=", onOperatorClick = ::operatorButtonClick)
                OperatorButton(operator = "C", onOperatorClick = ::operatorButtonClick)
                PowerButton(number = "ON", onOperatorClick = ::operatorButtonClick)
            }

        }
    }


@Preview(showBackground = true)
@Composable
fun VerticalScreenComposition() {
    var numberDisplay by rememberSaveable { mutableStateOf("0") }
    var sign: String
    fun operatorButtonClick(operator: String) {
        if (operator == "ON")
            numberDisplay = "0"
        else if (operator == "CE")
            numberDisplay = "0"
        else if (operator == "C" )
            numberDisplay = "0"
        else if (numberDisplay == "0" && operator !=  "+/-")
            numberDisplay = operator
        else if (operator == "+/-") {
            if (numberDisplay == "0")
                numberDisplay = "0"
            else {
                if (numberDisplay[0] != '-') {
                    sign = "-" + numberDisplay
                    numberDisplay = sign
                } else {
                    sign = numberDisplay.substring(1)
                    numberDisplay = sign
                }
            }
        } else
            numberDisplay += operator

    }

    fun numberButtonClick(number: String) {
        if (numberDisplay == "0")
            numberDisplay = number
        else
            numberDisplay += number
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.DarkGray),
        verticalArrangement = Arrangement.SpaceEvenly
    )
    {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
                .height(120.dp)
        ) {
            TextField(
                value = numberDisplay,
                onValueChange = {},
                modifier = Modifier.fillMaxWidth(),
                textStyle = TextStyle(
                    fontSize = 75.sp,
                    color = Color.Black,
                    textAlign = TextAlign.End
                ),
            )
        }

        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier.fillMaxWidth()
        )
        {
            PowerButton(number = "ON", onOperatorClick = ::operatorButtonClick)
            OperatorButton(operator = "C", onOperatorClick = ::operatorButtonClick)
            OperatorButton(operator = "/", onOperatorClick = ::operatorButtonClick)
            OperatorButton(operator = "X", onOperatorClick = ::operatorButtonClick)

        }


        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier.fillMaxWidth()
        )
        {
            NumberButton(number = "7", onNumberClick = ::numberButtonClick)
            NumberButton(number = "8", onNumberClick = ::numberButtonClick)
            NumberButton(number = "9", onNumberClick = ::numberButtonClick)
            OperatorButton(operator = "+", onOperatorClick = ::operatorButtonClick)

        }


        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier.fillMaxWidth()
        )
        {
            NumberButton(number = "4", onNumberClick = ::numberButtonClick)
            NumberButton(number = "5", onNumberClick = ::numberButtonClick)
            NumberButton(number = "6", onNumberClick = ::numberButtonClick)
            OperatorButton(operator = "-", onOperatorClick = ::operatorButtonClick)

        }

        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier.fillMaxWidth()
        )
        {
            NumberButton(number = "1", onNumberClick = ::numberButtonClick)
            NumberButton(number = "2", onNumberClick = ::numberButtonClick)
            NumberButton(number = "3", onNumberClick = ::numberButtonClick)
            OperatorButton(operator = "%", onOperatorClick = ::operatorButtonClick)

        }

        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier.fillMaxWidth()
        )
        {
            NumberButton(number = "0", onNumberClick = ::numberButtonClick)
            NumberButton(number = ".", onNumberClick = ::numberButtonClick)
            OperatorButton(operator = "+-", onOperatorClick = ::operatorButtonClick)
            OperatorButton(operator = "=", onOperatorClick = ::operatorButtonClick)

        }
    }
}}


