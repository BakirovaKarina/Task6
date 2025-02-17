package com.example.drawapp

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
//PathData представляет данные о рисуемом пути.
//Включает в себя сам путь (path), цвет (color), толщину линии (lineWidth) и стиль линии (cap).
//сохранять пути и цвета

data class PathData(
    val path: Path = Path(),
    //цвет по умолчанию
    val color: Color = Color.Blue,
    //ширина по умолчанию
    val lineWidth: Float = 5f,
    val cap: StrokeCap = StrokeCap.Round
)
