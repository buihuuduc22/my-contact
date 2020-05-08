package duc.bui.mycontact.business.contact;

import duc.bui.mycontact.business.common.ErrorInfo;
import duc.bui.mycontact.business.common.MyContactBaseException;
import duc.bui.mycontact.db.dto.contact.ContactRequest;
import duc.bui.mycontact.db.dto.contact.ContactResponse;
import duc.bui.mycontact.db.repos.ContactRepository;
import duc.bui.mycontact.db.entities.contact.Contact;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ContactServiceImpl implements ContactService {
	@Autowired
	private ContactRepository contactRepo;

	@Override
	public List<ContactResponse> getAll() {
		List<Contact> contactList = contactRepo.findAll();

		return contactList.stream().map(ContactResponse::new).collect(Collectors.toList());
	}

	@Override
	public List<ContactResponse> search(String name) {
		List<Contact> contactList = contactRepo.findByNameContaining(name);
		return contactList.stream().map(ContactResponse::new).collect(Collectors.toList());
	}

	@Override
	public ContactResponse create(ContactRequest request) throws MyContactBaseException {
		String name = request.getName();

		Contact contact = contactRepo.findFirstByName(name);

		if (contact != null) throw new MyContactBaseException(ErrorInfo.CONTACT_EXISTED_ERROR);

		contact = new Contact();

		BeanUtils.copyProperties(request, contact, "id");

		contact = contactRepo.save(contact);

		return new ContactResponse(contact);
	}

	@Override
	public ContactResponse update(ContactRequest request) throws MyContactBaseException {
		String name = request.getName();

		Integer id = request.getId();

		if (id == null) throw new MyContactBaseException(ErrorInfo.BAD_REQUEST_ERROR);

		Contact contact = contactRepo.findFirstById(id);

		if (contact == null) throw new MyContactBaseException(ErrorInfo.CONTACT_NOT_FOUND_ERROR);

		if (!contact.getName().equals(name)) {
			Contact existed = contactRepo.findFirstByName(name);
			if (existed != null) throw new MyContactBaseException(ErrorInfo.CONTACT_EXISTED_ERROR);
		}
		BeanUtils.copyProperties(request, contact, "id");
		contact = contactRepo.save(contact);
		return new ContactResponse(contact);
	}

	@Override
	public void delete(Integer id) throws MyContactBaseException {
		Contact contact = contactRepo.findFirstById(id);
		if (contact == null) throw new MyContactBaseException(ErrorInfo.CONTACT_NOT_FOUND_ERROR);
		contactRepo.delete(contact);
	}

	@Override
	public ContactResponse findOne(Integer id) throws MyContactBaseException {
		Contact contact = contactRepo.findFirstById(id);
		if (contact == null) throw new MyContactBaseException((ErrorInfo.CONTACT_NOT_FOUND_ERROR));
		return new ContactResponse(contact);
	}
}

