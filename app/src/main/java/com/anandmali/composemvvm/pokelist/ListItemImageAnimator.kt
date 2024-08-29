package com.anandmali.composemvvm.pokelist

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale


@Composable
fun ListItemImageAnimator(
    modifier: Modifier,
    child: @Composable () -> Unit,
) {
    val scale = remember { Animatable(0.3f) }

    LaunchedEffect(Unit) {
        scale.animateTo(
            targetValue = 1f,
            animationSpec = spring(
                dampingRatio = Spring.DampingRatioHighBouncy,
                stiffness = Spring.StiffnessLow
            )
        )
    }

    Box(
        modifier = modifier
    ) {
        Box(
            modifier = Modifier
                .wrapContentSize()
                .scale(scale.value)
        ) {
            child()
        }
    }
}
