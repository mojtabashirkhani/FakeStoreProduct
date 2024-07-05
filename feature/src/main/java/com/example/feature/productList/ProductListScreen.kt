package com.example.feature.productList

import android.content.Context
import android.net.Uri
import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import coil.request.ImageRequest
import coil.size.Scale
import com.example.data.remote.model.ProductResponse
import com.google.gson.Gson


@Composable
fun productList(
    onGoToItem: (String) -> Unit,
    modifier: Modifier = Modifier) {
    val productListVM: ProductListVM = hiltViewModel()
    val productListStateFlow by productListVM.productListStateFlow.collectAsStateWithLifecycle()
    var productList by remember { mutableStateOf(emptyList<ProductResponse>()) } // Store the data
    val context = LocalContext.current

    LaunchedEffect(productListStateFlow) {
        if (productListStateFlow.isLoading()) {
            Log.d("com/example/feature/productList", productListStateFlow.message.toString())

        } else if (productListStateFlow.isSuccess()) {
            productList = productListStateFlow.data ?: emptyList()

        } else if (productListStateFlow.isError()) {
            Log.d("com/example/feature/productList", productListStateFlow.errorObject.toString())

        }
    }

    LazyColumn(modifier) {
        items(productList.size) { item ->
            productItem(
                item = productList[item],
                context,
                onItemClick = {
                    val json = Uri.encode(Gson().toJson(it))
                    onGoToItem(json)
                })

        }
    }


}

@Composable
fun productItem(
    item: ProductResponse,
    context: Context,
    onItemClick: (ProductResponse) -> Unit
) {
    ElevatedCard(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp
        ),
        modifier = Modifier
            .padding(12.dp)
            .clip(RoundedCornerShape(30.dp, 30.dp, 30.dp, 30.dp)),
    ) {


            Card() {

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { onItemClick(item) },
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {

                AsyncImage(
                    model = ImageRequest
                        .Builder(context)
                        .data("${item.image}")
                        .crossfade(true)
                        .scale(Scale.FIT)
                        .build(),
                    contentDescription = "",
                    contentScale = ContentScale.FillBounds,
                    modifier = Modifier.size(
                        (LocalConfiguration.current.screenWidthDp).dp,
                        (LocalConfiguration.current.screenWidthDp).dp
                    ),
                    )

                    Text(
                        text = item.category ?: "",
                        style = MaterialTheme.typography.titleSmall,
                        modifier = Modifier.padding(horizontal = 20.dp, 8.dp),
                        fontSize = 18.sp
                    )

                    Text(
                        text = item.title ?: "",
                        style = MaterialTheme.typography.titleSmall,
                        modifier = Modifier.padding(horizontal = 20.dp),
                        fontSize = 16.sp
                    )

                    Text(
                        text = "Price: " + item.price.toString() + "$",
                        style = MaterialTheme.typography.displaySmall,
                        modifier = Modifier.padding(horizontal = 20.dp, 8.dp),
                        fontSize = 14.sp
                    )

            }






        }

    }
}
