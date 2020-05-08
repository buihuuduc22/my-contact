package duc.bui.mycontact.db.entities.contact;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "contact")
@Data
public class Contact {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private Integer id;

	@Column(name = "name", nullable = false)
	private String name;

	@Column(name = "email")
	private String email;

	@Column(name = "phone")
	private String phone;

	public Contact(String name, String email, String phone) {
		this.name = name;
		this.email = email;
		this.phone = phone;
	}

	public Contact(Integer id) {
		this.id = id;
	}

	public Contact() {
	}
}
