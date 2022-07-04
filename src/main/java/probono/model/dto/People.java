package probono.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class People {

	private String id;
	private String name;
	private String password;

	public People() {
		super();
	}

	public People(String id, String name, String password) {
		super();
		this.id = id;
		this.name = name;
		this.password = password;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("1. 재능 기부자 id : ");
		builder.append(id);
		builder.append(" 2. 이름 : ");
		builder.append(name);
		builder.append(" 3. 비밀번호 : ");
		builder.append(password);
		return builder.toString();
	}

}
