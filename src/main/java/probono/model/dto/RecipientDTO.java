/*CREATE TABLE recipient (
       recipient_id         VARCHAR2(20) PRIMARY KEY,
       name                 VARCHAR2(20) NULL,
       password             VARCHAR2(20) NULL,
       receiveHopeContent   VARCHAR2(50) NULL
); */

package probono.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import probono.model.entity.Recipient;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder

public class RecipientDTO {

	private String id;
	private String name;
	private String password;
	private String receiveHopeContent;

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(" 4. 제공받는 대상자가 제공받는 서비스 : ");
		builder.append(receiveHopeContent);
		return builder.toString();
	}

	public Recipient toEntity() {
		return Recipient.builder().id(id).name(name).password(password).receiveHopeContent(receiveHopeContent).build();
	}
}
