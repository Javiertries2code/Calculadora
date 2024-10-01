package com.example.composecalcu

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import java.math.*


import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.composecalcu.ui.theme.ComposeCalcuTheme
import com.example.composecalcu.ui.theme.White

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
           my_calcu()
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
        modifier = Modifier.padding(8.dp),


    ) {
        Text(text = operator, fontSize = 24.sp, color = Color.Black)
    }
}

@Composable
fun NumberButton(
    number: String,
    onNumberClick: (String) -> Unit,
    backgroundColor: Color = colorResource(R.color.focused_blue)

) {
    Button(
        onClick = { onNumberClick(number) },
        colors = ButtonDefaults.buttonColors(backgroundColor),
        shape = RoundedCornerShape(12.dp),
        modifier = Modifier.padding(8.dp)
    ) {
        Text(text = number.toString(), fontSize = 24.sp, color = Color.Black)
    }
}




@Preview(showBackground = true)
@Composable
fun my_calcu() {
    var numberDisplay by rememberSaveable { mutableStateOf("0") }
 var sign: String
    fun OperatorButtonClick(operator: String) {
        if (operator == "C")
            numberDisplay = "0"
        else if (numberDisplay == "0")
            numberDisplay=operator
        else if(operator == "+/-")
        {
            if (numberDisplay[0] != '-') {
                sign = "-" + numberDisplay
                numberDisplay = sign
            }
            else {
                sign = numberDisplay.substring(1)
                numberDisplay = sign
            }
        }
        else
            numberDisplay+=operator

    }
    fun NumberButtonClick(number: String) {
        if (numberDisplay == "0")
            numberDisplay=number
        else
            numberDisplay+=number
    }
  Column(modifier = Modifier
      .fillMaxSize()
      .background(Color.DarkGray),
      verticalArrangement = Arrangement.SpaceEvenly
  )
  {

      Row(modifier = Modifier
          .fillMaxWidth()
          .padding(10.dp)
          .height(120.dp)){
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
      //LineaOp("C", "%","/", "X")
      Row( horizontalArrangement = Arrangement.SpaceEvenly,
          modifier = Modifier.fillMaxWidth())
      {
          OperatorButton(operator = "C", onOperatorClick = ::OperatorButtonClick)
          OperatorButton(operator = "%", onOperatorClick = ::OperatorButtonClick)
          OperatorButton(operator = "/", onOperatorClick = ::OperatorButtonClick)
          OperatorButton(operator = "X", onOperatorClick = ::OperatorButtonClick)

      }

   //   LineaNum(7,8,9, "+")
      Row( horizontalArrangement = Arrangement.SpaceEvenly,
          modifier = Modifier.fillMaxWidth())
      {
          NumberButton(number = "7", onNumberClick = ::NumberButtonClick)
          NumberButton(number = "8", onNumberClick = ::NumberButtonClick)
          NumberButton(number = "9", onNumberClick = ::NumberButtonClick)
          OperatorButton(operator = "+", onOperatorClick = ::OperatorButtonClick)

      }

     // LineaNum(4,5,6, "-")
      Row( horizontalArrangement = Arrangement.SpaceEvenly,
          modifier = Modifier.fillMaxWidth())
      {
          NumberButton(number = "4", onNumberClick = ::NumberButtonClick)
          NumberButton(number = "5", onNumberClick = ::NumberButtonClick)
          NumberButton(number = "6", onNumberClick = ::NumberButtonClick)
          OperatorButton(operator = "-", onOperatorClick = ::OperatorButtonClick)

      }
     // LineaNum(1,2,3, "/")
      Row( horizontalArrangement = Arrangement.SpaceEvenly,
          modifier = Modifier.fillMaxWidth())
      {
          NumberButton(number = "1", onNumberClick = ::NumberButtonClick)
          NumberButton(number = "2", onNumberClick = ::NumberButtonClick)
          NumberButton(number = "3", onNumberClick = ::NumberButtonClick)
          OperatorButton(operator = "/", onOperatorClick = ::OperatorButtonClick)

      }

     Row( horizontalArrangement = Arrangement.SpaceEvenly,
         modifier = Modifier.fillMaxWidth())
     {
         NumberButton(number = "0", onNumberClick = ::NumberButtonClick)
         NumberButton(number = ".", onNumberClick = ::NumberButtonClick)
         OperatorButton(operator = "+/-", onOperatorClick = ::OperatorButtonClick)
         OperatorButton(operator = "=", onOperatorClick = ::OperatorButtonClick)

     }
  }


}


