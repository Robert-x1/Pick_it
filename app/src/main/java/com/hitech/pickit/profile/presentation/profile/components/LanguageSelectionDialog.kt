package com.hitech.pickit.profile.presentation.profile.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.hitech.pickit.R
import com.hitech.pickit.profile.util.Language
import com.hitech.pickit.ui.theme.PickItTheme

@Composable
fun LanguageDialog(
    openDialog: Boolean,
    currentLanguage: Language,
    onLanguageSelected: (Language) -> Unit,
    onDismiss: () -> Unit,
    modifier: Modifier = Modifier
) {
    val languages = Language.entries.toTypedArray()

    if(openDialog) {
        AlertDialog(
            onDismissRequest = onDismiss,
            confirmButton = { },
            dismissButton = {
                TextButton(onClick = onDismiss) {
                    Text("Cancel")
                }
            },
            icon = {
                Icon(
                    painter = painterResource(R.drawable.language_icon),
                    tint = MaterialTheme.colorScheme.primary,
                    contentDescription = null
                )
            },
            title = {
                Text("Choose Language")
            },
            text = {
                Column(Modifier.selectableGroup()) {
                    languages.forEach { language ->
                        LanguageRow(
                            language = language,
                            isSelected = (currentLanguage == language),
                            onSelected = {
                                onLanguageSelected(language)
                                onDismiss()
                            }
                        )
                    }
                }
            },
        )
    }
}

@Composable
private fun LanguageRow(
    language: Language,
    isSelected: Boolean,
    onSelected: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .selectable(
                selected = isSelected,
                onClick = onSelected,
                role = Role.RadioButton
            )
            .padding(vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        RadioButton(
            selected = isSelected,
            onClick = null
        )
        Spacer(
            modifier = Modifier
                .width(16.dp)
        )
        Text(
            text = language.displayName,
            style = MaterialTheme.typography.bodyLarge
        )
    }
}

@Preview(name = "Language Dialog")
@Composable
fun LanguageDialogPreview() {
    PickItTheme {
        LanguageDialog(
            currentLanguage = Language.ENGLISH,
            onLanguageSelected = {},
            onDismiss = {},
            openDialog = true
        )
    }
}