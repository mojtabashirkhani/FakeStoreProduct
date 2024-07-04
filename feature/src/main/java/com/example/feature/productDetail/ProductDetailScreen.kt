package com.example.feature.productDetail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.data.local.model.ProductEntity
import com.example.data.remote.model.ProductResponse
import com.example.util.fromJson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@Composable
fun productDetail(item: String, onGoBack: () -> Unit) {
    val productDetailVM: ProductDetailVM = hiltViewModel()
    val productItem = item.fromJson<ProductResponse>()
    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp),
        verticalArrangement = Arrangement.Center
    ) {

        Button(onClick = {
            coroutineScope.launch {
                withContext(Dispatchers.IO) {
                    productDetailVM.insertProductToFavourite(
                        ProductEntity(
                            productItem?.id ?: 0,
                        )
                    )
                }
            }
        }) {
            Text("Add to Favourite")
        }


        Card() {
            AsyncImage(
                model = ImageRequest
                    .Builder(context)
                    .data("${productItem?.image}")
                    .crossfade(true)
                    .build(),
                contentDescription = "",
                modifier = Modifier.size(
                    (LocalConfiguration.current.screenWidthDp).dp,
                    (LocalConfiguration.current.screenHeightDp).dp
                ),
                contentScale = ContentScale.Crop,

                )
        }

    }
}