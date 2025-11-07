package com.hitech.pickit.ui
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapp.data.MoviePoster
import com.example.myapp.data.SearchData
import coil.compose.AsyncImage
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreen() {
    var searchText by remember { mutableStateOf("") }

    Scaffold(
        bottomBar = {
            BottomNavigationBar()
        },
        containerColor = _root_ide_package_.com.example.myapp.ui.theme.DarkBackground
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 16.dp, vertical = 8.dp)
        ) {
            SearchBar(searchText = searchText, onTextChange = { searchText = it })
            Spacer(modifier = Modifier.height(24.dp))

            SectionTitle("Recent searches")
            RecentSearchesRow(
                recentSearches = SearchData.recentSearches,
                onItemClick = { searchText = it }
            )
            Spacer(modifier = Modifier.height(24.dp))

            SectionTitle("Genres")
            TagsGrid(
                tags = SearchData.genres,
                onTagClick = { searchText = it }
            )
            ViewMoreButton(label = "View more", onClick = { /* TODO: Genres list */ })
            Spacer(modifier = Modifier.height(24.dp))
            SectionTitle("Languages")
            TagsGrid(
                tags = SearchData.languages,
                onTagClick = { searchText = it }
            )
            ViewMoreButton(label = "View more", onClick = { /* TODO: Languages list */ })
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBar(searchText: String, onTextChange: (String) -> Unit) {
    OutlinedTextField(
        value = searchText,
        onValueChange = onTextChange,
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp)),
        placeholder = { Text("Search movie, shows, genre, etc", color = _root_ide_package_.com.example.myapp.ui.theme.LightText.copy(alpha = 0.7f)) },
        leadingIcon = { Icon(Icons.Default.Search, contentDescription = "Search", tint = _root_ide_package_.com.example.myapp.ui.theme.LightText.copy(alpha = 0.7f)) },
        colors = OutlinedTextFieldDefaults.colors(
            focusedContainerColor = _root_ide_package_.com.example.myapp.ui.theme.DarkSurface,
            unfocusedContainerColor = _root_ide_package_.com.example.myapp.ui.theme.DarkSurface,
            focusedBorderColor = _root_ide_package_.com.example.myapp.ui.theme.OrangePrimary,
            unfocusedBorderColor = _root_ide_package_.com.example.myapp.ui.theme.DarkSurface,
            cursorColor = _root_ide_package_.com.example.myapp.ui.theme.OrangePrimary,
            focusedTextColor = _root_ide_package_.com.example.myapp.ui.theme.LightText,
            unfocusedTextColor = _root_ide_package_.com.example.myapp.ui.theme.LightText,
            focusedLeadingIconColor = _root_ide_package_.com.example.myapp.ui.theme.LightText,
            unfocusedLeadingIconColor = _root_ide_package_.com.example.myapp.ui.theme.LightText,
            focusedPlaceholderColor = _root_ide_package_.com.example.myapp.ui.theme.LightText.copy(alpha = 0.7f),
            unfocusedPlaceholderColor = _root_ide_package_.com.example.myapp.ui.theme.LightText.copy(alpha = 0.7f)
        ),
        singleLine = true
    )
}

@Composable
fun SectionTitle(title: String) {
    Text(
        text = title,
        color = _root_ide_package_.com.example.myapp.ui.theme.LightText,
        fontSize = 18.sp,
        fontWeight = FontWeight.Bold,
        modifier = Modifier.padding(bottom = 12.dp)
    )
}

@Composable
fun RecentSearchesRow(recentSearches: List<MoviePoster>, onItemClick: (String) -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .horizontalScroll(rememberScrollState()),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        recentSearches.forEach { movie ->
            MoviePosterCard(movie) { selectedText ->
                onItemClick(selectedText)
            }
        }
    }
}

@Composable
fun MoviePosterCard(movie: MoviePoster, onClick: (String) -> Unit) {
    Column(
        modifier = Modifier
            .width(120.dp)
            .clip(RoundedCornerShape(8.dp))
            .background(_root_ide_package_.com.example.myapp.ui.theme.DarkSurface)
            .clickable { onClick(movie.title) }
    ) {
        AsyncImage(
            model = movie.imageUrl,
            contentDescription = movie.title,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .height(160.dp)
                .clip(RoundedCornerShape(topStart = 8.dp, topEnd = 8.dp))
        )
        Text(
            text = movie.title,
            color = _root_ide_package_.com.example.myapp.ui.theme.LightText,
            fontSize = 14.sp,
            fontWeight = FontWeight.Medium,
            modifier = Modifier.padding(horizontal = 8.dp, vertical = 6.dp),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun TagsGrid(tags: List<String>, onTagClick: (String) -> Unit) {
    FlowRow(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        tags.forEach { tag ->
            FilterChip(
                selected = false,
                onClick = { onTagClick(tag) },
                label = { Text(tag, color = _root_ide_package_.com.example.myapp.ui.theme.LightText) },
                colors = FilterChipDefaults.filterChipColors(
                    containerColor = _root_ide_package_.com.example.myapp.ui.theme.DarkSurface,
                    labelColor = _root_ide_package_.com.example.myapp.ui.theme.LightText,
                    selectedContainerColor = _root_ide_package_.com.example.myapp.ui.theme.OrangePrimary,
                    selectedLabelColor = Color.White
                ),
                border = null
            )
        }
    }
}

@Composable
fun ViewMoreButton(label: String, onClick: () -> Unit) {
    TextButton(onClick = onClick) {
        Text(
            text = label,
            color = _root_ide_package_.com.example.myapp.ui.theme.OrangePrimary,
            fontSize = 14.sp
        )
        Icon(
            imageVector = Icons.Default.KeyboardArrowDown,
            contentDescription = label,
            tint = _root_ide_package_.com.example.myapp.ui.theme.OrangePrimary,
            modifier = Modifier.size(20.dp)
        )
    }
}

@Composable
fun BottomNavigationBar() {
    NavigationBar(
        containerColor = _root_ide_package_.com.example.myapp.ui.theme.DarkSurface,
        contentColor = _root_ide_package_.com.example.myapp.ui.theme.LightText
    ) {
        NavigationBarItem(
            selected = false, onClick = { },
            icon = { Icon(Icons.Default.Home, contentDescription = "Home", tint = _root_ide_package_.com.example.myapp.ui.theme.LightText) },
            colors = NavigationBarItemDefaults.colors(indicatorColor = _root_ide_package_.com.example.myapp.ui.theme.OrangeTransparent)
        )
        NavigationBarItem(
            selected = true, onClick = { },
            icon = { Icon(Icons.Default.Search, contentDescription = "Search", tint = _root_ide_package_.com.example.myapp.ui.theme.OrangePrimary) },
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = _root_ide_package_.com.example.myapp.ui.theme.OrangePrimary,
                selectedTextColor = _root_ide_package_.com.example.myapp.ui.theme.OrangePrimary,
                indicatorColor = _root_ide_package_.com.example.myapp.ui.theme.OrangeTransparent
            )
        )
        NavigationBarItem(
            selected = false, onClick = { },
            icon = { Icon(Icons.Default.Favorite, contentDescription = "Favorites", tint = _root_ide_package_.com.example.myapp.ui.theme.LightText) },
            colors = NavigationBarItemDefaults.colors(indicatorColor = _root_ide_package_.com.example.myapp.ui.theme.OrangeTransparent)
        )
        NavigationBarItem(
            selected = false, onClick = { },
            icon = { Icon(Icons.Default.Person, contentDescription = "Profile", tint = _root_ide_package_.com.example.myapp.ui.theme.LightText) },
            colors = NavigationBarItemDefaults.colors(indicatorColor = _root_ide_package_.com.example.myapp.ui.theme.OrangeTransparent)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewSearchScreen() {
    _root_ide_package_.com.example.myapp.ui.theme.MovieSearchTheme {
        Surface(color = MaterialTheme.colorScheme.background) {
            SearchScreen()
        }
    }
}