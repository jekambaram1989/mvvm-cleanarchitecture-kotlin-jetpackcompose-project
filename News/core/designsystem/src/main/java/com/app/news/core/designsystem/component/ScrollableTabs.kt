package com.app.news.core.designsystem.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.TabPosition
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.app.news.core.designsystem.resources.Dimens.TAB_ITEM_ROUNDED_CORNER
import com.app.news.core.designsystem.resources.Dimens.smallPadding
import com.app.news.core.designsystem.resources.Dimens.widgetSpaceMedium
import com.app.news.core.designsystem.resources.Dimens.zeroPadding
import com.app.news.core.designsystem.theme.appColor

@Composable
fun NewsScrollableTabRow(
    selectedIndex: Int,
    tabs: @Composable () -> Unit
) {
    ScrollableTabRow(
        containerColor = Color.White,
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = widgetSpaceMedium),
        selectedTabIndex = selectedIndex,
        edgePadding = zeroPadding,
        divider = {},
        indicator = { tabPositions ->
            TabIndicator(tabPositions, selectedIndex)
        }, tabs = tabs
    )
}

@Composable
fun TabIndicator(
    tabPositions: List<TabPosition>,
    selectedIndex: Int,
) {
    Box(
        modifier = Modifier
            .tabIndicatorOffset(tabPositions[selectedIndex])
            .fillMaxSize()
            .padding(smallPadding)
            .background(
                appColor,
                RoundedCornerShape(TAB_ITEM_ROUNDED_CORNER),
            )
    )
}