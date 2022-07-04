/*CREATE TABLE recipient (
       recipient_id         VARCHAR2(20) PRIMARY KEY,
       name                 VARCHAR2(20) NULL,
       password             VARCHAR2(20) NULL,
       receiveHopeContent   VARCHAR2(50) NULL
); */

package probono.model.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

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
public class Recipient {

	@Id
	@Column(name="recipient_id")
	private String id;

	private String name;

	private String password;

	private String receiveHopeContent;

	@OneToMany(mappedBy = "receiveId")
	private List<ProbonoProject> ProbonoProjects;

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(" 4. 제공받는 대상자가 제공받는 서비스 : ");
		builder.append(receiveHopeContent);
		return builder.toString();
	}
}
