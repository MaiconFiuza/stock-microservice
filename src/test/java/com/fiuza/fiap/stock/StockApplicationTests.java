package com.fiuza.fiap.stock;

import com.fiuza.fiap.stock.infra.feign.FeignProductClient;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest
@ActiveProfiles("test")
@TestPropertySource(properties = {
		"springdoc.api-docs.enabled=false",
		"springdoc.swagger-ui.enabled=false"
})
class StockApplicationTests {

	@MockBean
	private FeignProductClient  feignProductClient;
	@Test
	void contextLoads() {
	}

}
