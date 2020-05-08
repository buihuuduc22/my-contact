package duc.bui.mycontact.db.dto.contact;

import lombok.Data;

@Data
public class ContactRequest {
	private Integer id;
	private String name;
	private String email;
	private String phone;
}
