package com.example.springreadreplica;

import org.junit.jupiter.api.Test;

import java.util.function.*;

class SpringReadReplicaApplicationTests {

  @Test
  void contextLoads() {
  }

	static int invokeAGod(int a, int b, int c) {
		throw new StackOverflowError("XXXXXX");
	}
	static int a, b, c;

	@Test
  public void hello() {
		try {
			invokeAGod(a = 1, b = 2, c = 3);
		}catch(Exception e){
			System.out.println("Handled error");
		}
	}

}
