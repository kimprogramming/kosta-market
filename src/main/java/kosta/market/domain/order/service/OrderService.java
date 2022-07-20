package kosta.market.domain.order.service;

public interface OrderService {
	/**
	 * 기능 : 상품 주문 <br>
	 * 설명 : <br>
	 * 상품번호, 수량, 결제방법이 담긴 <b>주문요청 객체</b>를 파라미터로 받고 <br>
	 * Session 에 저장된 user_id 를 이용해 상품을 주문한다. <br>
	 * 단, 주문일자와 결제일자는 자동으로 추가되야 한다. <br>
	 *
	 * @param orderRequestDto 주문요청 정보를 담은 DTO
	 * @return 상품 추가 성공 시 true, 아니면 false
	 */
	boolean addOrder(OrderRequestDto orderRequestDto);

	/**
	 * 기능 : 구매자 주문 리스트 <br>
	 * 설명 : <br>
	 * Session 에 저장된 user_id 를 이용해 <br>
	 * 해당 유저의 구매한 모든 주문 내역을 조회한다.
	 *
	 * @return 조회할 값이 존재할 시 ArrayList&lt;&gt;, 없으면 null
	 */
	List<OrderListDto> listByUserIdOrder();

	/**
	 * 기능 : 판매자 주문 리스트 <br>
	 * 설명 : <br>
	 * Session 에 저장된 user_id 를 이용해 <br>
	 * seller_id 를 조회한 뒤, 해당 판매자의 상품에 대한 <br>
	 * 모든 주문 내역을 조회한다.
	 *
	 * @return 조회할 값이 존재할 시 ArrayList&lt;Object&gt;, 없으면 null
	 */
	List<OrderListDto> listBySellerIdOrder();

	/**
	 * 기능 : 주문 상세 조회 <br>
	 * 설명 : <br>
	 * 주문번호를 받아 해당 주문의 정보를 조회한다. <br>
	 * 단, <b>본인이 주문한 경우</b> 또는 <b>내 판매 상품의 주문 내역</b>만 조회 가능하다.
	 *
	 * @param orderNo 주문번호
	 * @return 조회할 값이 존재할 시 Object, 없으면 null
	 */
	OrderDetailDto detailOrder(int orderNo);

	/**
	 * 기능 : 상품 교환 신청 <br>
	 * 설명 : 구매한 상품의 *<b><u>주문 상태</b></u>를 <b>교환신청</b>으로 변경한다.<br>
	 * <pre><u><b>*주문상태</b></u> { 0:주문완료, 1:상품준비, 2:배송중, 3:교환신청, 4:환불, 5:구매확정 }</pre>
	 *
	 * @param orderNo 주문번호
	 * @return 상품 교환 요청 성공 시 true, 아니면 false
	 */
	boolean exchangeOrder(int orderNo);

	/**
	 * 기능 : 구매 취소<br>
	 * 설명 : 구매한 상품의 *<b><u>주문 상태</b></u>를 <b>취소/환불</b>로 변경한다.<br>
	 * <pre><u><b>*주문상태</b></u> { 0:주문완료, 1:상품준비, 2:배송중, 3:교환신청, 4:취소/환불, 5:구매확정 }</pre>
	 *
	 *
	 * @param orderNo 주문번호
	 * @return 상품 취소 요청 성공 시 true, 아니면 false
	 */
	boolean cancelOrder(int orderNo);

	/**
	 * 기능 : 구매 확정 <br>
	 * 설명 : 구매한 상품의 *<b><u>주문 상태</b></u>를 <b>구매확정</b>으로 변경한다.<br>
	 * <pre><u><b>*주문상태</b></u> { 0:주문완료, 1:상품준비, 2:배송중, 3:교환신청, 4:환불, 5:구매확정 }</pre>
	 *
	 * @param orderNo 주문번호
	 * @return 구매 확정 요청 성공 시 true, 아니면 false
	 */
	boolean confirmOrder(int orderNo);
}
