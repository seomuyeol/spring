package hello.core.discount;

import hello.core.member.Member;

public interface DiscountPolicy {
	
	/** 
	 * @return 割引対象値段
	 */
	
	int discount(Member member, int price);

}
