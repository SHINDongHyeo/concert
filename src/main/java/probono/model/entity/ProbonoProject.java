package probono.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
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
public class ProbonoProject {
	
	@Id
	@Column(name="probonoProjectId")
	private int probonoProjectId;
	
	private String probonoProjectName;
	
	@ManyToOne(targetEntity = Probono.class)
	@JoinColumn(name="probonoId")
	private String probonoId;
	
	@ManyToOne(targetEntity = Activist.class)
	@JoinColumn(name="activistId")
	private String activistId; 
	
	@ManyToOne(targetEntity = Recipient.class)
	@JoinColumn(name="receiveId")
	private String receiveId;
	
	private String projectContent;
	

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("1. 프로젝트 id : ");
		builder.append(probonoProjectId);
		builder.append("2. 프로보노 프로젝트명 : ");
		builder.append(probonoProjectName);
		builder.append("3. 프로보노 정보 : ");
		builder.append(probonoId);
		builder.append("4. 재능 기부자 정보 : ");
		builder.append(activistId);
		builder.append("5. 수해자 정보 : ");
		builder.append(receiveId);
		builder.append("6. 프로젝트 제공내용 : ");
		builder.append(projectContent);
		return builder.toString();
	}
}
