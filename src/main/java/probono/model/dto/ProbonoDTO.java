/*
CREATE TABLE probono (
       probono_id          	VARCHAR2(50) PRIMARY KEY,
       probono_name      VARCHAR2(50) NOT NULL,
       probono_purpose  	VARCHAR2(200) NOT NULL
);  */
package probono.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import probono.model.entity.Probono;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class ProbonoDTO {
	
	private String probonoId;
	private String probonoName;
	private String probonoPurpose;
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("프로보노 정보 1. 프로보노 아이디 = ");
		builder.append(probonoId);
		builder.append("2. 프로보노 이름 : ");
		builder.append(probonoName);
		builder.append("3. 프로보노 목적 : ");
		builder.append(probonoPurpose);
		return builder.toString();
	}
	
	public Probono toEntity() {
		return Probono.builder().probonoId(probonoId).probonoName(probonoName).probonoPurpose(probonoPurpose).build();
	}
}
