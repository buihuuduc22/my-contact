package duc.bui.mycontact.db.repos;

import duc.bui.mycontact.db.entities.contact.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContactRepository extends JpaRepository<Contact, Integer> {
	List<Contact> findByNameContaining(String name);

	Contact findFirstByName(String name);

	Contact findFirstById(Integer id);
}
