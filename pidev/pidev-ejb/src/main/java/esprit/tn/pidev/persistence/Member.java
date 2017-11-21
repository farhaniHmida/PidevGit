package esprit.tn.pidev.persistence;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;



/**
 * Entity implementation class for Entity: Member
 *
 */
@Entity

public class Member implements Serializable {

	
	private static final long serialVersionUID = 1L;

	private Integer id;
	

	
	private String login;
	
	private String password;
	
	private String email;
	
	

	public Member(Integer id, String login, String password, String email) {
		
		this.id = id;
		this.login = login;
		this.password = password;
		this.email = email;
	}

	private List<Reservation> reservations;

	public Member() {
	}

	public Member(String login, String password, String email) {
		this.login = login;
		this.password = password;
		this.email = email;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(unique = true)
	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	

	


	@OneToMany(mappedBy = "membre", cascade = CascadeType.REMOVE )
	public List<Reservation> getReservation() {
		return reservations;
	}

	public void setReservation(List<Reservation> reservation) {
		this.reservations = reservation;
	}
}
