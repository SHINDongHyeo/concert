package concert.model.dto;

import concert.model.entity.Orders;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder

public class OrdersDTO {
	private int orderId;
	private String customerName;
	private String customerEmail;
	private int concertId;
	private int amount;
	
	public Orders toEntity() {
		return Orders.builder().customerName(customerName).customerEmail(customerEmail).concert_id(concertId).amount(amount).build();
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("OrdersDTO [orderId=");
		builder.append(orderId);
		builder.append(", customerName=");
		builder.append(customerName);
		builder.append(", customerEmail=");
		builder.append(customerEmail);
		builder.append(", concertId=");
		builder.append(concertId);
		builder.append(", amount=");
		builder.append(amount);
		builder.append("]");
		return builder.toString();
	}
	
}
