package com.example.feature.productDetail

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.common.fromJson
import com.example.database.model.ProductEntity
import com.example.feature.R
import com.example.network.model.ProductResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun productDetail(productItem: String, onGoBack: () -> Unit) {
    val productDetailVM: ProductDetailVM = hiltViewModel()
    val item = productItem.fromJson<ProductResponse>()
    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()
    val productListStateFlow by productDetailVM.productStateFlow.collectAsStateWithLifecycle()
    var productFavorite by remember { mutableStateOf(ProductEntity()) }
    var isFavorite by remember { mutableStateOf(-1L) }

    coroutineScope.launch(Dispatchers.IO) {
        productDetailVM.getProductFavourite(item?.id ?: 0L)
    }

    LaunchedEffect(productListStateFlow) {
        productFavorite = productListStateFlow
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp),
        verticalArrangement = Arrangement.Center
    ) {


        /*if (productFavorite.id != 0L || isFavorite != -1L) {
            Text(
                text = "is favorite",
                style = MaterialTheme.typography.displaySmall,
                modifier = Modifier.padding(horizontal = 20.dp)
            )
        }*/


        Card(modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()) {

            Column(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {

                AsyncImage(
                    model = ImageRequest
                        .Builder(context)
                        .data("${item?.image}")
                        .crossfade(true)
                        .build(),
                    contentDescription = "",
                    modifier = Modifier.size(
                        (LocalConfiguration.current.screenWidthDp).dp,
                        (LocalConfiguration.current.screenWidthDp).dp
                    ),
                    contentScale = ContentScale.Crop,

                    )

                Text(
                    text = item?.category ?: "",
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.padding(horizontal = 20.dp, 8.dp),
                    fontSize = 18.sp
                )

                Text(
                    text = item?.description ?: "",
                    style = MaterialTheme.typography.titleSmall,
                    modifier = Modifier.padding(horizontal = 20.dp),
                    fontSize = 16.sp
                )

                Text(
                    text = "Price: " + item?.price.toString() + "$",
                    style = MaterialTheme.typography.displaySmall,
                    modifier = Modifier.padding(horizontal = 20.dp, 8.dp),
                    fontSize = 14.sp
                )

                Button(onClick = {
                    coroutineScope.launch {
                        withContext(Dispatchers.IO) {
                            isFavorite = productDetailVM.insertProductToFavourite(
                                ProductEntity(
                                    item?.id ?: 0L,
                                )
                            )
                        }
                    }
                }) {
                    if (productFavorite.id != 0L || isFavorite != -1L) {
                        Image(
                            painter = painterResource(id = R.drawable.icons8_star_filled_48),
                            contentDescription = null
                        )
                    } else {
                        Image(
                            painter = painterResource(id = R.drawable.icons8_star_50),
                            contentDescription = null
                        )
                    }

                }


            }
        }
    }
}