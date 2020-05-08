package duc.bui.mycontact.db.dto.contact;

import duc.bui.mycontact.db.entities.contact.Contact;
import lombok.Data;
import org.springframework.beans.BeanUtils;

@Data
public class ContactResponse {
	private Integer id;
	private String name;
	private String email;
	private String phone;

	public ContactResponse(Contact contact) {
		BeanUtils.copyProperties(contact, this);
	}

	public ContactResponse() {
	}
}
