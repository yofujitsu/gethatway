package com.example.merchstore;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = {CartServiceTest.class, CustomUserDetailsServiceTest.class, ProductServiceTest.class, UserServiceTest.class})
class MerchshopApplicationTests {

	@Test
	void contextLoads() {
	}

}
