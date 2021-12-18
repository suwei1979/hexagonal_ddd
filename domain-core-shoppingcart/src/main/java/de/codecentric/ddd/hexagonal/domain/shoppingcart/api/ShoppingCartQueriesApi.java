package de.codecentric.ddd.hexagonal.domain.shoppingcart.api;

import de.codecentric.ddd.hexagonal.domain.shoppingcart.impl.ShoppingCartItemsInfo;
import de.codecentric.ddd.hexagonal.domain.shoppingcart.impl.ShoppingCartListRow;

import java.util.List;
import java.util.UUID;

public interface ShoppingCartQueriesApi {
  List<ShoppingCartListRow> getShoppingCarts();
  ShoppingCartItemsInfo getShoppingCartItems( final UUID cartId );
}