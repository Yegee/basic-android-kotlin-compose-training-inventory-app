package com.example.inventory.ui.item

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.inventory.InventoryTopAppBar
import com.example.inventory.R
import com.example.inventory.data.Item
import com.example.inventory.ui.AppViewModelProvider
import com.example.inventory.ui.navigation.NavigationDestination
import com.example.inventory.ui.theme.InventoryTheme





object ItemSearchDestination : NavigationDestination {
    override val route = "item_search"
    override val titleRes = R.string.item_search_title
    const val itemIdArg = "itemId"
    val routeWithArgs = "${ItemSearchDestination.route}/{${ItemSearchDestination.itemIdArg}}"

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ItemSearchScreen(
    navigateBack: () -> Unit,
    onNavigateUp: () -> Unit,
    canNavigateBack: Boolean = true,
    viewModel: ItemSearchScreenViewModel = viewModel(factory = AppViewModelProvider.Factory)
) {
    val coroutineScope = rememberCoroutineScope()

    Scaffold(
        topBar = {
            InventoryTopAppBar(
                title = stringResource(ItemSearchDestination.titleRes),
                canNavigateBack = canNavigateBack,
                navigateUp = onNavigateUp
            )
        }
    ) { innerPadding ->
        ItemSearchBody(
            itemUiState = viewModel.itemUiState,
            onItemClick = { /* Handle item click */ },
            onBackClick = {},
            modifier = Modifier.padding(innerPadding)
        )
    }
}

@Composable
private fun ItemSearchBody(
    onItemClick: (Item) -> Unit,
    onBackClick: (Item) -> Unit,
    modifier: Modifier,
    itemUiState: ItemUiState
)
    {
        var searchText by remember { mutableStateOf("") }
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = modifier
        ){
            TextField(
                value = searchText,
                onValueChange = { searchText = it },
                placeholder = { Text("Search") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = dimensionResource(id = R.dimen.padding_small))
            )
        }
}

//@Composable
//private fun InventoryList(
//    itemList: List<Item>, onItemClick: (Item) -> Unit, modifier: Modifier = Modifier
//) {
//    LazyColumn(modifier = modifier) {
//        items(items = itemList, key = { it.id }) { item ->
//            InventoryItem(item = item,
//                modifier = Modifier
//                    .padding(dimensionResource(id = R.dimen.padding_small))
//                    .clickable { onItemClick(item) })
//        }
//    }
//}
//
//@Composable
//private fun InventoryItem(
//    item: Item, modifier: Modifier = Modifier
//) {
//    Card(
//        modifier = modifier, elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
//    ) {
//        Column(
//            modifier = Modifier.padding(dimensionResource(id = R.dimen.padding_large)),
//            verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.padding_small))
//        ) {
//            Row(
//                modifier = Modifier.fillMaxWidth()
//            ) {
//                Text(
//                    text = item.name,
//                    style = MaterialTheme.typography.titleLarge,
//                )
//                Spacer(Modifier.weight(1f))
//                Text(
//                    text = item.formatedPrice(),
//                    style = MaterialTheme.typography.titleMedium
//                )
//            }
//            Text(
//                text = stringResource(R.string.in_stock, item.quantity),
//                style = MaterialTheme.typography.titleMedium
//            )
//        }
//    }
//}

@Preview(showBackground = true)
@Composable
private fun ItemSearchPreview() {
    InventoryTheme {
        ItemSearchScreen(
            navigateBack ={},
        onNavigateUp = {},
        canNavigateBack = true,
        )
    }

}

