package com.godieboy;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import com.godieboy.config.TransactionServiceConfig;

@SpringBootTest
@ContextConfiguration(classes = TransactionServiceConfig.class)

class ClipApplicationTests {

	@Test
	void contextLoads() {
	}

}
