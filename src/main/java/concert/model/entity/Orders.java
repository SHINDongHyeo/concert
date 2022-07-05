package concert.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder

@Entity
public class Orders {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "order_id")
	private int orderId;

	@Column(name = "customer_name")
	private String customerName;

	@Column(name = "customer_email")
	private String customerEmail;

	private int amount;

	private int concert_id;
	

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "concert_id", insertable = false, updatable = false)
	private Concert concert;


	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Orders [orderId=");
		builder.append(orderId);
		builder.append(", customerName=");
		builder.append(customerName);
		builder.append(", customerEmail=");
		builder.append(customerEmail);
		builder.append(", amount=");
		builder.append(amount);
		builder.append(", concert_id=");
		builder.append(concert_id);
		builder.append("]");
		return builder.toString();
	}

	
	
}
