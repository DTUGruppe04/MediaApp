package com.example.mediaapp.ui

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.runtime.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Divider
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.wear.compose.material.ContentAlpha
import com.example.mediaapp.R


class TabsAndFilters(
    private val tabs: List<String>,
    private val filters: List<FilterOption>
) {
    data class FilterOption(val label: String, val options: List<String>)
    @Composable
    fun Render() {
        var selectedTab by remember { mutableStateOf("All") }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFF141218)),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TabsRow(tabs, selectedTab) { tab ->
                selectedTab = tab
            }
            Divider(color = Color.Gray, thickness = 0.5.dp,)
            FiltersRow(filters)
        }
    }
    @Composable
    private fun TabsRow(tabs: List<String>, selectedTab: String, onTabSelected: (String) -> Unit) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            tabs.forEach { tab ->
                Box(
                    modifier = Modifier.weight(1f),
                    contentAlignment = Alignment.Center,
                ) {
                    TabButton(tab, selectedTab == tab) {
                        onTabSelected(tab)
                    }
                }
            }
        }
    }
    @Composable
    private fun FiltersRow(filters: List<FilterOption>) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .horizontalScroll(rememberScrollState())
                .padding(8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            filters.forEach { filter ->
                FilterDropdown(filter.label , filter.options)
            }
        }
    }
    @Composable
    fun TabButton(label: String, isSelected: Boolean, onClick: () -> Unit) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.clickable { if (!isSelected) onClick() }
        ) {
            TabIcon(isSelected = isSelected)
            TabText(label = label, isSelected = isSelected)
            TabIndicator(isSelected = isSelected)
        }
    }
    @Composable
    fun TabIcon(isSelected: Boolean) {
        val imageAlpha = if (isSelected) 1f else ContentAlpha.medium
        Image(
            painter = painterResource(id = R.drawable.icon),
            contentDescription = "Tab icon",
            modifier = Modifier
                .padding(1.dp)
                .width(24.dp)
                .height(24.dp)
                .alpha(imageAlpha),
            contentScale = ContentScale.Fit
        )
    }
    @Composable
    fun TabText(label: String, isSelected: Boolean) {
        Text(
            text = label,
            modifier = Modifier
                .width(96.dp)
                .height(20.dp),
            style = TextStyle(
                fontSize = 14.sp,
                lineHeight = 20.sp,
                fontWeight = FontWeight(500),
                color = Color(0xFFF5F5F5),
                textAlign = TextAlign.Center,
                letterSpacing = 0.1.sp,
            )
        )
    }
    @Composable
    fun TabIndicator(isSelected: Boolean) {
            Box (
                modifier =
                if (isSelected) Modifier
                    .padding(start = 5.dp, end = 5.dp, top = 4.dp)
                    .width(92.dp)
                    .height(3.dp)
                    .background(
                        color = Color(0xFFF5F5F5),
                        shape = RoundedCornerShape(
                            topStart = 100.dp,
                            topEnd = 100.dp,
                        ))
                else
                    Modifier
                        .padding(start = 5.dp, end = 5.dp, top = 4.dp)
                        .width(92.dp)
                        .height(3.dp)
                        .background(color = Color.Transparent)
            )
    }

    @Composable
    fun FilterDropdown(label: String, options: List<String>){
        var selectedOption by remember { mutableStateOf(label) }
        var expanded by remember { mutableStateOf(false)}
        var clicked by remember { mutableStateOf(false)}
        val (backgroundColor, textColor) = getDropdownColors(expanded)
        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(8.dp))
                .background(backgroundColor),
        ) {
            DropdownMenuToggle(
                label = selectedOption,
                expanded = expanded,
                onToggle = { expanded = !expanded },
                modifier = Modifier
                    .fillMaxWidth(),
                textColor = textColor
            )
            DropdownMenu(
                expanded = expanded,
                onDismissRequest = {expanded = false}
            ) {
                options.forEach{
                        option -> DropdownMenuItem(text = { Text(text = option) }, onClick = {
                    expanded = false
                    selectedOption = option
                    clicked = true
                })
                }
            }
        }
    }

    @Composable
    fun DropdownMenuToggle(
        label: String,
        expanded: Boolean,
        onToggle: (Boolean) -> Unit,
        modifier: Modifier = Modifier,
        textColor: Color
    ) {
        Row(
            modifier = modifier
                .padding(start = 16.dp, top = 6.dp, end = 8.dp, bottom = 6.dp)
                .clickable { onToggle(!expanded) },
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = label,
                style = TextStyle(
                    fontSize = 14.sp,
                    lineHeight = 20.sp,
                    fontWeight = FontWeight(500),
                    color = textColor,
                    textAlign = TextAlign.Center,
                    letterSpacing = 0.1.sp,
                )
            )
            Icon(
                Icons.Filled.ArrowDropDown,
                contentDescription = null,
                tint = textColor,
                modifier = Modifier
                    .padding(1.dp)
                    .width(18.dp)
                    .height(18.dp)
                    .rotate(if (expanded) 180f else 0f))
        }
    }
    @Composable
    private fun getDropdownColors(expanded: Boolean): Pair<Color, Color> {
        val backgroundColor = animateColorAsState(if (expanded) Color(0xFF4A4458) else Color(0x29CAC4D0)).value
        val textColor = animateColorAsState(if (expanded) Color(0xFFE8DEF8) else Color(0xFFCAC4D0)).value
        return Pair(backgroundColor, textColor)
    }
}

