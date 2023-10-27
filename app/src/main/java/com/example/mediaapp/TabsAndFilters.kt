package com.example.mediaapp

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.runtime.*
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mediaapp.ui.theme.MediaAppTheme


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
                .background(Color(0xFF141218))
                .padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Top Tabs
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
                            selectedTab = tab
                        }
                    }
                }
            }

            Divider(
                color = Color.Gray,
                thickness = 0.5.dp,
            )
            // Bottom filters
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .horizontalScroll(rememberScrollState())
                    .padding(bottom = 8.dp, top = 8.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                filters.forEach { filter ->
                    FilterDropdown(filter.label , filter.options)
                }
            }
        }
    }

    @Composable
    fun TabButton(label: String, isSelected: Boolean, onClick: () -> Unit) {
        val textColor = if (isSelected) Color(0xFFD0BCFF) else Color.LightGray
        val buttonColor = if (isSelected) Color(0xFFD0BCFF) else Color.LightGray
        val lineColor = if (isSelected) Color(0xFFD0BCFF) else Color.Transparent

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.clickable { if(!isSelected) onClick() }
        ) {
            Box(
                modifier = Modifier
                    .size(10.dp)
                    .clip(CircleShape)
                    .background(buttonColor)
                    .align(Alignment.CenterHorizontally)
                    .padding(top = 8.dp),
            )
            Text(
                text = label,
                fontSize = 14.sp,
                color = textColor,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.padding(8.dp),
            )
            Box(
                modifier = Modifier
                    .height(2.dp)
                    .width(50.dp)
                    .clip(CircleShape)
                    .background(lineColor)
            )
        }
    }

    @Composable
    fun FilterDropdown(label: String, options: List<String>){
        var selectedOption by remember { mutableStateOf(label) }
        var expanded by remember { mutableStateOf(false)}
        var clicked by remember { mutableStateOf(false)}
        val backgroundColor by animateColorAsState(if (clicked) Color(0xFF4A4458) else Color.DarkGray)
        val textColor by animateColorAsState(if (clicked) Color(0xFFE8DEF8) else Color(0xFFCAC4D0))
        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(10.dp))
                .background(backgroundColor),
        ) {
            DropdownMenuToggle(
                label = selectedOption,
                expanded = expanded,
                onToggle = { expanded = !expanded },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 4.dp, bottom = 4.dp, start = 16.dp, end = 6.dp),
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
            modifier = modifier.clickable { onToggle(!expanded) },
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = label,
                fontSize = 14.sp,
                color = textColor,
                fontWeight = FontWeight.Normal)
            Image(
                painter = painterResource(R.drawable.arrow_dropdown),
                contentDescription = null,
                modifier = Modifier
                    .height(25.dp)
                    .width(25.dp)
                    .padding(start = 4.dp)
                    .rotate(if (expanded) 180f else 0f)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewCustomUI() {
    MediaAppTheme {
        FollowingListPage()
    }
}

