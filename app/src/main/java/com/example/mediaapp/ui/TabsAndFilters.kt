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
import androidx.compose.material3.MaterialTheme
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
import com.example.mediaapp.ui.theme.MediaAppTheme


class TabsAndFilters(
    private val tabs: List<String>,
    private val filters: List<FilterOption>
) {

    data class FilterOption(val label: String, val options: List<String>)
    @Composable
    fun Render() {
        MediaAppTheme{
            var selectedTab by remember { mutableStateOf("All") }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(MaterialTheme.colorScheme.background),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                TabsRow(tabs, selectedTab) { tab ->
                    selectedTab = tab
                }
                Divider(color = MaterialTheme.colorScheme.outline, thickness = 0.5.dp,)
                FiltersRow(filters)
            }
        }
    }
    @Composable
    private fun TabsRow(tabs: List<String>, selectedTab: String, onTabSelected: (String) -> Unit) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp, bottom = 8.dp),
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
            TabText(label = label, isSelected = isSelected)
            TabIndicator(isSelected = isSelected)
        }
    }
    @Composable
    fun TabText(label: String, isSelected: Boolean) {
        Text(
            text = label,
            modifier = Modifier
                .width(96.dp)
                .height(20.dp),
            style = MaterialTheme.typography.titleSmall.copy(textAlign = TextAlign.Center)

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
                        color = MaterialTheme.colorScheme.onSurface,
                        shape = RoundedCornerShape(
                            topStart = 100.dp,
                            topEnd = 100.dp,
                        )
                    )
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
        val itemHeight = 48.dp
        val dropdownHeight = itemHeight * (if (options.size < 8) options.size else 8)
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
                onDismissRequest = {expanded = false},
                modifier = Modifier.heightIn(max = dropdownHeight)
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
                style = MaterialTheme.typography.labelLarge
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
        val backgroundColor = animateColorAsState(if (expanded) MaterialTheme.colorScheme.secondaryContainer else MaterialTheme.colorScheme.surfaceVariant,
            label = ""
        ).value
        val textColor = animateColorAsState(if (expanded) MaterialTheme.colorScheme.onSecondaryContainer else MaterialTheme.colorScheme.onSurfaceVariant,
            label = ""
        ).value
        return Pair(backgroundColor, textColor)
    }
}

