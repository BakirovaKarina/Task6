package com.example.drawapp

import android.graphics.drawable.Icon
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.input.pointer.PointerIcon.Companion.Text
import androidx.compose.ui.modifier.ModifierLocal
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import org.intellij.lang.annotations.JdkConstants.HorizontalAlignment

@Composable
fun BottomPanel(
    onClick: (Color)-> Unit,
    onLineWidthChange: (Float)-> Unit,
    onBackClick: () -> Unit,
    onCapClick: (StrokeCap) -> Unit,){
    Column(
        //элементы во всю ширину
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.LightGray),

        //элементы в середине по горизонтали
        horizontalAlignment = Alignment.CenterHorizontally
    )
    {
        //выбор цвета линии, которую мы рисуем
        ColorList{color ->
            onClick(color)
        }
        //Запуск слайдера
        CustomSlider{lineWidth ->
            //передаем ширину
            onLineWidthChange(lineWidth)
        }
        ButtonPanel ({
            onBackClick()
        }){cap ->

            onCapClick(cap)
        }
        Spacer(modifier = Modifier.height(5.dp))
    }
}
//ColorList отображает список цветов в горизонтальном порядке.
//Каждый цвет представлен в виде круглого блока с соответствующим цветом.
@Composable
fun ColorList(onClick: (Color)-> Unit){
    val colors = listOf(
        Color.Red,
        Color.Black,
        Color.Yellow,
        Color.Green,
        Color.Cyan,
        Color.Blue,
        Color.Gray,
        Color.Magenta

    )
    LazyRow(modifier = Modifier.padding(10.dp)){
        items(colors){color ->
            Box(
                modifier = Modifier
                    //отступ только справа
                    .padding(end = 5.dp)
                    .clickable {
                        //вызов метода изменеия цвета по нажатию
                        onClick(color)
                    }
                    .size(40.dp)
                    //круглой формы
                    .background(color, CircleShape)
            )


        }

    }
}
//CustomSlider отображает слайдер для выбора толщины линии.
//Текущее значение толщины отображается в виде числа под слайдером.
@Composable
fun CustomSlider(onChange: (Float)-> Unit){
    var position by remember {
        //стартовое значение по умолчанию
        mutableStateOf(0.05f)
    }
    Column(horizontalAlignment = Alignment.CenterHorizontally){
        Text("Толщина линии ${(position*100).toInt()}") //умножаем на 100 и добавляем Int,
        // чтобы числа были целыми от 1 до 100
        Slider(
            value = position,
            onValueChange = {
                //чтобы пользователь не выбрал значение <1
                val tempPos = if(it > 0)
                    it
                else
                    0.01f
                position = tempPos
                onChange(tempPos*100)
            }

        )

    }
}

//кнопка отмены последнего действия
@Composable
fun ButtonPanel(onClick: () -> Unit, onCapClick: (StrokeCap) -> Unit) {
    Row(Modifier.fillMaxWidth()) {
        Row(
            //размещение кнопки
            modifier = Modifier
                .fillMaxWidth(0.5f)
                .padding(start = 10.dp, end = 10.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        )
        {
            IconButton(
                //белая круглая кнопка
                modifier = Modifier
                    .clip(CircleShape)
                    .background(Color.White),
                onClick = {
                    onClick()
                }) {

                Icon(
                    Icons.Default.ArrowBack,
                    contentDescription = null
                )

            }

        }


        //еще одна кнопка
        Row(
            //размещение кнопки
            modifier = Modifier
                .fillMaxWidth(0.5f)
                .padding(end = 10.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        )
        {
            IconButton(
                //белая круглая кнопка
                modifier = Modifier
                    .clip(CircleShape)
                    .background(Color.White),
                onClick = {
                    onCapClick(StrokeCap.Butt) //стиль линий полоски
                })
            {

                Icon(
                    painter = painterResource(id = R.drawable.cap_1),
                    contentDescription = null
                )
            }


            //2 кнопка
            IconButton(

                modifier = Modifier
                    .clip(CircleShape)
                    .background(Color.White),
                onClick = {
                    onCapClick(StrokeCap.Square) //стиль линий квадраты
                })
            {

                Icon(
                    painter = painterResource(id = R.drawable.cap_2),
                    contentDescription = null
                )
            }




        }
    }
}





