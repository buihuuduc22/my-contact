package duc.bui.mycontact.rest.contact;

import duc.bui.mycontact.business.common.MyContactBaseException;
import duc.bui.mycontact.business.contact.ContactService;
import duc.bui.mycontact.db.dto.ResponseObject;
import duc.bui.mycontact.db.dto.contact.ContactRequest;
import javafx.beans.binding.ObjectBinding;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/contact")
public class ContactController {
	@Autowired
	private ContactService contactSv;

	@GetMapping
	public ResponseEntity<Object> list() {
		ResponseObject<Object> response = new ResponseObject<>();
		response.setResponseData(contactSv.getAll());
		return new ResponseEntity<>(response, new HttpHeaders(), HttpStatus.OK);
	}

	@PostMapping
	@ResponseBody
	public ResponseEntity<Object> create(@RequestBody ContactRequest request) {
		ResponseObject<Object> response = new ResponseObject<>();
		try {
			response.setResponseData(contactSv.create(request));
			response.setSuccessMessage("Create contact successfully");
		} catch (MyContactBaseException e) {
			response.setError(e.getError());
		}
		return new ResponseEntity<>(response, new HttpHeaders(), HttpStatus.OK);
	}

	@PutMapping
	@ResponseBody
	public ResponseEntity<Object> update(@RequestBody ContactRequest request) {
		ResponseObject<Object> response = new ResponseObject<>();
		try {
			response.setResponseData(contactSv.update(request));
			response.setSuccessMessage("Update contact successfully");
		} catch (MyContactBaseException e) {
			response.setError(e.getError());
		}
		return new ResponseEntity<>(response, new HttpHeaders(), HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Object> delete(@PathVariable("id") Integer id) {
		ResponseObject<Object> response = new ResponseObject<>();
		try {
			contactSv.delete(id);
			response.setResponseData(true);
		} catch (MyContactBaseException e) {
			e.setError(e.getError());
		}
		return new ResponseEntity<>(response, new HttpHeaders(), HttpStatus.OK);
	}

	@GetMapping("/search")
	public ResponseEntity<Object> search(String name) {
		ResponseObject<Object> response = new ResponseObject<>();
		response.setResponseData(contactSv.search(name));
		return new ResponseEntity<>(response, new HttpHeaders(), HttpStatus.OK);
	}
}
