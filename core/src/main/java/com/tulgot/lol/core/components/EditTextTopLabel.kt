package com.tulgot.lol.core.components


import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldColors
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.font.FontWeight.Companion.Normal
import androidx.compose.ui.text.font.FontWeight.Companion.SemiBold
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tulgot.lol.core.R

val GrayLetterHint = Color(0xFFA0A0A0)

@Composable
fun robotoMediumTypo() = FontFamily(
    basicFont(
        "Roboto", R.font.roboto, Normal, FontStyle.Normal
    )
)

@Composable
fun semiBoldTypo() = FontFamily(
    basicFont(
        "Roboto", R.font.roboto, SemiBold, FontStyle.Normal
    )
)

@Composable
fun basicFont(name: String, res: Int, weight: FontWeight, style: FontStyle) = Font(res, weight, style)

@Composable
fun SemiBoldText(
    modifier: Modifier = Modifier,
    text: String,
    letterSpacing: TextUnit = 0.sp,
    color: Color = Color.Black,
    textAlign: TextAlign? = null,
    fontSize: TextUnit = 18.sp,
    textOverflow: TextOverflow = TextOverflow.Clip,
    maxLines: Int = Int.MAX_VALUE,
) {
    Text(
        modifier = modifier,
        text = text,
        fontSize = fontSize,
        color = color,
        letterSpacing = letterSpacing,
        textAlign = textAlign,
        overflow = textOverflow,
        maxLines = maxLines,
        fontFamily = semiBoldTypo()
    )
}

@Composable
fun EditTextTopLabel(
    modifier: Modifier = Modifier,
    placeHolderText: String = "",
    onValueChange: (String) -> Unit,
    isError: Boolean = false,
    value: String,
    errorMessage: String = "",
    keyboardOptions: KeyboardOptions = KeyboardOptions(
        capitalization = KeyboardCapitalization.Words,
        keyboardType = KeyboardType.Text
    ),
    keyboardActions: KeyboardActions = KeyboardActions { },
    enabled: Boolean = true,
    topLabelText: String = "",
    enabledCounter: Boolean = false,
    maxLength: Int = 0,
    singleLine: Boolean = false,
    colors: TextFieldColors = TextFieldDefaults.outlinedTextFieldColors(unfocusedLabelColor = GrayLetterHint),
    isPassword: Boolean = false,
    visualTransformation: VisualTransformation = VisualTransformation.None
) {
    val interactionSource = remember { MutableInteractionSource() }
    var passwordVisibility by remember { mutableStateOf(isPassword) }


    Column(modifier = modifier) {

        Text(
            modifier = Modifier.padding(bottom = 9.dp),
            text = topLabelText,
            color = Color.Black,
            fontSize = 15.sp,
            fontFamily = robotoMediumTypo()
        )

        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth(),
            textStyle = TextStyle(
                fontFamily = semiBoldTypo(),
                color = Color.Black,
                fontWeight = SemiBold,
                fontSize = 15.sp
            ),
            interactionSource = interactionSource,
            value = value,
            onValueChange = {
                if (enabledCounter) {
                    if (it.length <= maxLength) {
                        onValueChange(it)
                    }
                } else {
                    onValueChange(it)
                }
            }, placeholder = {
                SemiBoldText(
                    text = placeHolderText,
                    color = GrayLetterHint,
                    fontSize = 15.sp
                )
            },
            colors = colors,
            isError = isError,
            keyboardOptions = keyboardOptions,
            enabled = enabled,
            singleLine = singleLine,
            keyboardActions = keyboardActions,
            visualTransformation = if (!passwordVisibility) visualTransformation
            else PasswordVisualTransformation(mask = '●')
        )

        AnimatedVisibility(visible = isError && errorMessage.isNotEmpty()) {
            Text(
                text = errorMessage,
                color = MaterialTheme.colors.error,
                fontSize = 15.sp,
                fontFamily = robotoMediumTypo()
            )
        }
    }
}