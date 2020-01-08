package com.ufrn.highlighter;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootTest
class HighlighterApplicationTests {

	@Test
	void contextLoads() {
	}

	@Test
	public void test(){
		System.out.println(new BCryptPasswordEncoder().encode("12345678"));
	}

}
