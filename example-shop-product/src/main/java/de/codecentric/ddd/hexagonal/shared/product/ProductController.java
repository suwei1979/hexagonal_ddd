package de.codecentric.ddd.hexagonal.shared.product;

import de.codecentric.ddd.hexagonal.domain.product.api.*;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;

@CrossOrigin( origins = { "http://localhost:3000", "http://localhost:3001", "http://localhost" } )
@RestController
public class ProductController {
  private final ProductsApi productApi;

  public ProductController( final ProductsApi productApi ) {
    this.productApi = productApi;
  }

  @GetMapping( "/api/product" )
  public List<ProductListRow> getProducts() {
    return productApi.getProductList();
  }

  @GetMapping( path = "/api/product/shopping" )
  public List<ProductShoppingListRow> getShoppingProducts() {
    return productApi.getProductShoppingList();
  }


  @GetMapping( value = "/api/product", params = "label" )
  public void validateProductByLabel( @RequestParam final String label ) {
    try {
      productApi.validateProduct( label );
    } catch( ProductNotFoundException e ) {
      throw new ResponseStatusException( NOT_FOUND );
    }
  }


  @PostMapping( "/api/product" )
  public void addProduct( @RequestBody final Product product ) {
    final Product p =
      new Product( product.getId() != null ? product.getId() : UUID.randomUUID(),
                   product.getName(),
                   product.getPackagingType(),
                   product.getPrice(),
                   product.getAmount() );
    productApi.addProduct( p );
  }

  @DeleteMapping( "/api/product" )
  public void deleteProduct( final String id ) {
    productApi.removeProduct( UUID.fromString( id ) );
  }
}
