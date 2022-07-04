/**
CREATE TABLE activist (
       activist_id          	VARCHAR2(20)  PRIMARY KEY,
       name               	VARCHAR2(20) NOT NULL,
       password         	VARCHAR2(20) NOT NULL,
       major                	VARCHAR2(50) NOT NULL
); */
package probono.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import probono.model.entity.Activist;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class ActivistDTO {

	private String id;
	private String name;
	private String password;
	private String major;

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(super.toString());
		builder.append(" 4. 재능 기부 분야 : ");
		builder.append(major);
		return builder.toString();
	}

	public Activist toEntity() {
		return Activist.builder().id(id).name(name).password(password).major(major).build();
	}

}
