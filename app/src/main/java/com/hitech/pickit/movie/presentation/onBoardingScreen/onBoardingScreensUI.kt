package com.hitech.pickit.movie.presentation.onBoardingScreen

import androidx.annotation.DrawableRes
import com.hitech.pickit.movie.domain.OnBoardingPage
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.vectorResource
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.hapticfeedback.HapticFeedbackType
import androidx.compose.ui.platform.LocalHapticFeedback
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.hitech.pickit.R
import com.hitech.pickit.ui.theme.PickItTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch





@OptIn(ExperimentalFoundationApi::class)
@Composable
fun OnboardingScreen(onFinish: () -> Unit) {
    val pages = listOf(
        OnBoardingPage(
            "Welcome",
            "Welcome to PickIt! Your new companion for... well, picking it!",
            R.drawable.pickit_logo_foreground
        ),
        OnBoardingPage(
            "Features",
            "Discover awesome features that will make your life easier.",
            R.drawable.pickit_logo_foreground
        ),
        OnBoardingPage(
            "Get Started",
            "Let's Pick it! Tap the button below to begin your journey.",
            R.drawable.pickit_logo_foreground

        )
    )

    val pagerState = rememberPagerState { pages.size }
    val scope = rememberCoroutineScope()


    val haptic = LocalHapticFeedback.current
    LaunchedEffect(pagerState.currentPage) {
        haptic.performHapticFeedback(HapticFeedbackType.LongPress)
    }


    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.surface)
    ) {

        HorizontalPager(
            state = pagerState,
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
        ) { page ->
            OnboardingPageContent(pageData = pages[page])
        }


        OnboardingControls(
            pagerState = pagerState,
            scope = scope,
            pageCount = pages.size,
            onFinish = onFinish
        )
    }
}

@Composable
fun OnboardingPageContent(pageData: OnBoardingPage) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Icon(
            imageVector = ImageVector.vectorResource(R.drawable.star),
            contentDescription = null,
            modifier = Modifier.size(250.dp),
            tint = MaterialTheme.colorScheme.primary
        )

        Image(
            imageVector = ImageVector.vectorResource(pageData.imageVector),
            contentDescription = null,
            modifier = Modifier.size(250.dp)
        )

        Spacer(modifier = Modifier.height(40.dp))

        Text(
            text = pageData.title,
            style = MaterialTheme.typography.headlineLarge,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onBackground,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = pageData.description,
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            textAlign = TextAlign.Center,
            lineHeight = 24.sp
        )
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun OnboardingControls(
    pagerState: PagerState,
    scope: CoroutineScope,
    pageCount: Int,
    onFinish: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 40.dp, start = 32.dp, end = 32.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        DotsIndicator(
            totalDots = pageCount,
            selectedIndex = pagerState.currentPage,
            onDotClick = { index ->
                scope.launch {
                    pagerState.animateScrollToPage(index)
                }
            }
        )
        Spacer(modifier = Modifier.height(32.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            TextButton(onClick = onFinish) {
                Text("Skip", style = MaterialTheme.typography.labelLarge)
            }

            val isLastPage = pagerState.currentPage == pageCount - 1
            Button(
                onClick = {
                    if (isLastPage) {
                        onFinish()
                    } else {
                        scope.launch {
                            pagerState.animateScrollToPage(pagerState.currentPage + 1)
                        }
                    }
                },
                contentPadding = PaddingValues(horizontal = 24.dp, vertical = 12.dp),
                colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primaryContainer)
            ) {
                Text(
                    text = if (isLastPage) "Let's pick it" else "Next",
                    color = Color.White
                )
                Spacer(modifier = Modifier.width(8.dp))
                Icon(
                    imageVector = if (isLastPage) Icons.Default.Done else Icons.AutoMirrored.Filled.ArrowForward,
                    contentDescription = null,
                    tint = Color.White
                )
            }
        }
    }
}


@Composable
fun DotsIndicator(
    totalDots: Int,
    selectedIndex: Int,
    onDotClick: (Int) -> Unit
) {
    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        repeat(totalDots) { index ->
            val isSelected = index == selectedIndex

            val color: Color by animateColorAsState(
                targetValue = if (isSelected) MaterialTheme.colorScheme.primary
                else MaterialTheme.colorScheme.onSurface.copy(alpha = 0.2f),
                animationSpec = tween(300),
                label = "DotColor"
            )

            val size by animateDpAsState(
                targetValue = if (isSelected) 12.dp else 8.dp,
                animationSpec = tween(300),
                label = "DotSize"
            )

            Box(
                modifier = Modifier
                    .padding(4.dp)
                    .size(size)
                    .clip(CircleShape)
                    .background(color)
                    .clickable { onDotClick(index) }
            )
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@PreviewLightDark
@Composable
fun OnboardingScreenPreview() {
    PickItTheme {
        OnboardingScreen(onFinish = {})
    }
}

