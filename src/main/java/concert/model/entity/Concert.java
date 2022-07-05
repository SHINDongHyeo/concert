package concert.model.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

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
public class Concert {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "concert_id")
	private int concertId;

	@Column(name = "concert_name")
	private String concertName;
	
	private String location;

	@Temporal(TemporalType.DATE)
	private java.util.Date date;

	@Column(name = "max_seats")
	private int maxSeats;
	
	private String contents;

	@OneToMany(mappedBy = "concert")
	private List<Orders> order;
	
	@OneToMany(mappedBy = "concert")
	private List<ConcertSinger> concertSinger;
	
}
