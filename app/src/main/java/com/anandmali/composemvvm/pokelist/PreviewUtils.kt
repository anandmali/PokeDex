package com.anandmali.composemvvm.pokelist

import androidx.compose.ui.tooling.preview.PreviewParameterProvider

class NamePreviewParameter : PreviewParameterProvider<String> {
    override val values = sequenceOf("Charmeleon", "Metaphor")
}