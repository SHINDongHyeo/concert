package concert.model.dto;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;

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
@ToString
public class ConcertDTO {
	private int concertId;
	private String concertName;
	private String location;
	@Temporal(TemporalType.DATE)
	private java.util.Date date;
	private int maxSeats;
	private String contents;
}
