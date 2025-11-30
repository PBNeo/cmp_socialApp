package com.neosoft.designsystem.components.CountrySelector
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.snapping.rememberSnapFlingBehavior
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.neosoft.designsystem.components.CountrySelector.data.sampleCountries
import com.neosoft.designsystem.components.CountrySelector.helper.isoToEmoji
import com.neosoft.designsystem.components.CountrySelector.helper.models.Country
import com.neosoft.designsystem.utils.AppColors.primary
import kotlinx.coroutines.launch

@Composable
fun CountryWheelPicker(
    countries: List<Country> = sampleCountries,
    selectedIndex: Int,
    onSelectedIndexChange: (Int, Country) -> Unit,
    modifier: Modifier = Modifier,
    visibleItemsCount: Int = 5
) {
    val itemHeight = 48.dp
    val listState = rememberLazyListState(initialFirstVisibleItemIndex = selectedIndex)
    val coroutineScope = rememberCoroutineScope()

    val flingBehavior = rememberSnapFlingBehavior(lazyListState = listState)

    Box(
        modifier = modifier
            .height(itemHeight * visibleItemsCount)
            .fillMaxWidth(),
        contentAlignment = Alignment.Center
    ) {
        // Center highlight box
        Box(
            modifier = Modifier
                .align(Alignment.Center)
                .fillMaxWidth()
                .height(itemHeight)
                .clip(RoundedCornerShape(12.dp))
                .background(primary.copy(alpha = 0.2f)) // subtle highlight
        )

        LazyColumn(
            state = listState,
            flingBehavior = flingBehavior,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(vertical = (itemHeight * visibleItemsCount / 2) - (itemHeight / 2))
        ) {
            itemsIndexed(countries) { index, country ->
                val isSelected = index == selectedIndex
                Row(
                    modifier = Modifier
                        .height(itemHeight)
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "${isoToEmoji(country.iso2)}  ${country.name} ${country.callingCode}",
                        textAlign = TextAlign.Center,
                        color = if (isSelected) primary else Color.Gray,
                        fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal,
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }
        }
    }

    // Track the centered item
    LaunchedEffect(listState.firstVisibleItemIndex, listState.firstVisibleItemScrollOffset) {
        val centerItemIndex = listState.firstVisibleItemIndex
        if (centerItemIndex in countries.indices && centerItemIndex != selectedIndex) {
            onSelectedIndexChange(centerItemIndex, countries[centerItemIndex])
            // snap exactly to center
            coroutineScope.launch {
                listState.animateScrollToItem(centerItemIndex)
            }
        }
    }
}


