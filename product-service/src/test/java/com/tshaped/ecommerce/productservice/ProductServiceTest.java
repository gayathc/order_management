package com.tshaped.ecommerce.product;

import com.tshaped.ecommerce.product.dto.ProductRequest;
import com.tshaped.ecommerce.product.dto.ProductResponse;
import com.tshaped.ecommerce.product.mapper.ProductMapper;
import com.tshaped.ecommerce.product.model.Product;
import com.tshaped.ecommerce.product.repository.ProductRepository;
import com.tshaped.ecommerce.product.service.ProductService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

//@SpringBootTest
@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    /*
    MapStruct mappers are Spring beans, not Mockito mocks.
    But in a pure unit test, Spring is NOT running.
    So Mockito cannot auto-create ProductMapper for you.
    Therefore, ProductMapper needs to mock in the test class
     */
    @Mock
    private ProductMapper productMapper;

    @InjectMocks
    private ProductService productService;

	@Test
	void createProduct() {
        ProductRequest request = ProductRequest.builder()
                .name("iPhone")
                .description("iPhone 17 Pro Max")
                .price(new BigDecimal("1750"))
                .build();

        Product product  = Product.builder()
                .name("iPhone")
                .description("iPhone 17 Pro Max")
                .price(new BigDecimal("1750"))
                .build();

        Mockito.when(productMapper.toEntity(Mockito.any(ProductRequest.class)))
                .thenReturn(product);

        Mockito.when(productRepository.save(Mockito.any(Product.class)))
                .thenReturn(product);

        ProductResponse response = productService.createProduct(request);

        //ProductResponse result = productService.createProduct(request);

        assertNotNull(response);
        assertEquals("iPhone", response.name());
        assertEquals(BigDecimal.valueOf(1750), response.price());

        Mockito.verify(productMapper).toEntity(Mockito.any(ProductRequest.class)) ;
        Mockito.verify(productRepository).save(Mockito.any(Product.class));
	}

}
