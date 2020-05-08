package duc.bui.mycontact.business.contact;

import duc.bui.mycontact.business.common.MyContactBaseException;
import duc.bui.mycontact.db.dto.contact.ContactRequest;
import duc.bui.mycontact.db.dto.contact.ContactResponse;

import java.util.List;

public interface ContactService {
	List<ContactResponse> getAll();

	List<ContactResponse> search(String name);

	ContactResponse create (ContactRequest request) throws MyContactBaseException;

	ContactResponse update (ContactRequest request) throws MyContactBaseException;

	void delete(Integer id) throws MyContactBaseException;

	ContactResponse findOne(Integer id) throws MyContactBaseException;
}
