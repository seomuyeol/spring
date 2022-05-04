package hello.core.singleton;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import hello.core.AppConfig;
import hello.core.member.MemberService;

public class SingletonTest {

	@Test
	@DisplayName("spring 無しのDIコンテナー")
	void pureContainer() {
		AppConfig appConfig = new AppConfig();
		//1. 検索　オブジェクト作り
		MemberService memberService1 = appConfig.memberService();
		
		//2. 検索　オブジェクト作り
		MemberService memberService2 = appConfig.memberService();
		
		//参照値異なる
		System.out.println("memberService1 = " + memberService1);
		System.out.println("memberService2 = " + memberService2);
		
		Assertions.assertThat(memberService1).isNotSameAs(memberService2);
		
	}
	
	@Test
	@DisplayName("singleton patton オブジェクト使用")
	void singletonServiceTest() {
		SingletonService singletonService1 = SingletonService.getInstance();
		SingletonService singletonService2 = SingletonService.getInstance();
		
		System.out.println("singletonService1 = " + singletonService1);
		System.out.println("singletonService2 = " + singletonService2);
		
		Assertions.assertThat(singletonService1).isSameAs(singletonService2);
		
		//same ==　参照値比較
		//equal は値を比較
	}
	
	@Test
	@DisplayName("Spring container & singleton")
	void springContainer() {
		
		ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
		
		//1. 検索　オブジェクト作り
		MemberService memberService1 = ac.getBean("memberService", MemberService.class);
		//2. 検索　オブジェクト作り
		MemberService memberService2 = ac.getBean("memberService", MemberService.class);
		
		//参照値異なる
		System.out.println("memberService1 = " + memberService1);
		System.out.println("memberService2 = " + memberService2);
		
		Assertions.assertThat(memberService1).isSameAs(memberService2);
		
	}
}
