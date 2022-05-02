package hello.core.singleton;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

class StatefulServiceTest {

	@Test
	void statefulServiceSingleton() {
		ApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);
		StatefulService statefulService1 = ac.getBean(StatefulService.class);
		StatefulService statefulService2 = ac.getBean(StatefulService.class);
		
		//ThreadA 10000注文
		statefulService1.order("userA", 10000);
		//ThreadA 20000注文
		statefulService1.order("userB", 20000);
		
		//ThreadA A注文確認
		int price = statefulService1.getPrice();
		System.out.println("price = " + price);
		
		Assertions.assertThat(statefulService1.getPrice());
		
	}
	
	static class TestConfig  {
		
		@Bean
		public StatefulService statuefulService() {
			return new StatefulService();
		}
		
	}

}
