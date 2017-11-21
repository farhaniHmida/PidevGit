package esprit.tn.pidev.persistence;

import java.io.Serializable;


import java.lang.String;
import java.util.List;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Course
 *
 */
@Entity
public class Course implements Serializable {

	   
	
	private int CourseID;
	
	private String title;
	
	private String description;
	
	private String type;
	
	private int numberAttendance;
	
	private float price;
	
	
	
	
	private List<Reservation> reservations;
	
	private static final long serialVersionUID = 1L;

	
	
	public Course() {
		super();
	}   
	
	
	

	public Course(int courseID, String title, String description, int numberAttendance, float price) {
		super();
		CourseID = courseID;
		this.title = title;
		this.description = description;
		this.numberAttendance = numberAttendance;
		this.price = price;
	}




	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int getCourseID() {
		return this.CourseID;
	}

	public void setCourseID(int CourseID) {
		this.CourseID = CourseID;
	}   
	public String getTitle() {
		return this.title;
	}

	public void setTitle(String Title) {
		this.title = Title;
	}   
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String Description) {
		this.description = Description;
	}   
	public int getNumberAttendance() {
		return this.numberAttendance;
	}

	public void setNumberAttendance(int NumberAttendance) {
		this.numberAttendance = NumberAttendance;
	}   
	public float getPrice() {
		return this.price;
	}

	public void setPrice(float price) {
		this.price = price;
	}   
	
	
	

	@OneToMany(mappedBy = "course", cascade = CascadeType.REMOVE )
	public List<Reservation> getReservation() {
		return reservations;
	}

	public void setReservation(List<Reservation> reservation) {
		this.reservations = reservation;
	}




	public String getType() {
		return type;
	}




	public void setType(String type) {
		this.type = type;
	}

	
   
}
