package com.example.splashscreen.navigation

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import com.example.splashscreen.BottomNav.BottomScreen
import com.example.splashscreen.R
import com.example.splashscreen.Utils.screens
import com.example.splashscreen.ui.theme.DRAWER_SELECTED
import com.example.splashscreen.ui.theme.DRAWER_SELECTED_BORDER
import com.example.splashscreen.ui.theme.HEADER

@Composable
fun NavigationDrawer(
    currentDestination: NavDestination?,
    onCloseClick: () -> Unit,
    onNavigationDrawerItemClick: (String) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(color = HEADER)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Spacer(modifier = Modifier.padding())
                IconButton(onClick = {
                    onCloseClick()
                }) {
                    Icon(imageVector = Icons.Outlined.Close, contentDescription = "")
                }
            }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
//                verticalArrangement = Arrangement.spacedBy(5.dp)
            ) {
                Image(
                    painterResource(id = R.drawable.avatar),
                    modifier = Modifier
                        .clip(CircleShape)
                        .height(120.dp),
                    contentDescription = ""
                )

                Text(
                    text = "Martin Joseph",
                    style = TextStyle(
                        color = Color.White,
                        fontWeight = FontWeight.ExtraBold,
                        fontSize = 16.sp
                    ),
                    modifier = Modifier.padding(top = 10.dp)
                )

                Text(
                    text = "Entrepreneur",
                    style = TextStyle(
                        color = Color.White,
                        fontSize = 12.sp,
                        fontStyle = FontStyle.Italic
                    )
                )
            }


        }
        LazyColumn(modifier = Modifier.padding(5.dp)) {
            items(screens) { item ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding()
                        .clickable {
                            onNavigationDrawerItemClick(item.title)
                        }
                        .border(
                            width = .5.dp,
                            color = if (currentDestination?.hierarchy?.any { it.route == item.route } == true) DRAWER_SELECTED_BORDER else Color.White,
                            shape = RoundedCornerShape(10.dp))
                        .background(
                          //  shape = RoundedCornerShape(10.dp),
                            color = if (currentDestination?.hierarchy?.any { it.route == item.route } == true) DRAWER_SELECTED else Color.White)
                ) {
                    Text(
                        text = item.title, modifier = Modifier.padding(10.dp)
                    )
                }
            }
        }
    }
}


