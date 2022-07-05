package concert.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

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
@Table(name="concert_singer")
public class ConcertSinger {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "concert_singer_id")
	private int concertSingerId;
	
	@Column(name = "concert_id")
	private int concertId;

	@Column(name = "singer_id")
	private int singerId;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "concert_id", insertable = false, updatable = false)
	private Concert concert;
	
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "singer_id", insertable = false, updatable = false)
	private Singer singer;
	
	
}
