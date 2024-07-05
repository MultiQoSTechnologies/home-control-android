package com.app.homecontrol.core.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.app.homecontrol.core.utils.toBoolean
import com.app.homecontrol.domain.data.Device
import com.smarttoolfactory.slider.ColorfulSlider
import com.smarttoolfactory.slider.MaterialSliderDefaults
import com.smarttoolfactory.slider.SliderBrushColor
import java.util.Locale
import kotlin.math.roundToInt

@Composable
fun Fan(
    modifier: Modifier = Modifier,
    device: Device,
    fanValueUpdate: (Int) -> Unit,
    statusUpdate: (Boolean) -> Unit
) {

    var fanValue by remember {
        mutableStateOf(device.fanLevel)
    }
    Card(
        modifier = modifier
            .padding(horizontal = 16.dp, vertical = 4.dp)
            .clickable {

            },
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer
        )
    ) {
        Column(
            modifier = modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
                .padding(top = 20.dp, bottom = 10.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {

                Text(
                    text = device.deviceName ?: "",
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onBackground,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )

                Switch(
                    checked = device.status.toBoolean(),
                    modifier = Modifier
                        .height(24.dp)
                        .width(43.dp)
                        .padding(end = 16.dp),
                    onCheckedChange = {
                        statusUpdate.invoke(it)
                    }
                )
            }

            SpacerVertical(height = 8.dp)

            ColorfulSlider(
                value = fanValue?.toFloat() ?: 0f,
                valueRange = 0f..5f,
                onValueChange = { value, _ ->
                    fanValue = value.roundToInt()
                },
                trackHeight = 7.dp,
                colors = MaterialSliderDefaults.materialColors(
                    activeTrackColor = SliderBrushColor(
                        color = SliderDefaults.colors().activeTrackColor
                    ),
                    inactiveTrackColor = SliderBrushColor(
                        color = SliderDefaults.colors().inactiveTrackColor
                    ),
                    thumbColor = SliderBrushColor(
                        color = SliderDefaults.colors().thumbColor
                    )
                ),
                onValueChangeFinished = {
                    fanValueUpdate.invoke(fanValue ?: 0)
                }
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.End
            ) {
                Text(
                    text = "Speed: ${String.format(Locale.US, "%02d", device.fanLevel)}",
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onBackground,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }
}