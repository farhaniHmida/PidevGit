package esprit.tn.pidev.persistence;

import java.io.Serializable;

import java.util.Date;

import javax.persistence.*;



/**
 * Entity implementation class for Entity: Reservation
 *
 */
@Entity
public class Reservation implements Serializable {

	
	private static final long serialVersionUID = 1L;
	
	@EmbeddedId
	private ReservationPk reservationID;
	
	
	
	@ManyToOne
	@JoinColumn(name = "userId",referencedColumnName = "id", insertable = false, updatable = false)
	private Member membre;
	
	@ManyToOne
	@JoinColumn(name = "courseID",referencedColumnName = "CourseID", insertable = false, updatable = false)
	private Course course;
	
	private Date reservationDate;
	
	
	

	
	public Reservation() {
		
	}
	
	
	


	public Reservation(Member membre, Course course) {
		this.reservationID = new ReservationPk(course.getCourseID(), membre.getId());
		
	}
	
	
	



	
	public Reservation(Member membre, Course course, Date reservationDate) {
		this.reservationID = new ReservationPk(course.getCourseID(), membre.getId());
		this.reservationDate = reservationDate;
	}





	public ReservationPk getReservationID() {
		if (reservationID == null) {
			reservationID = new ReservationPk();
		}
		return reservationID;
	}



	public void setReservationID(ReservationPk reservationID) {
		this.reservationID = reservationID;
	}


	
	public Member getMembre() {
		return membre;
	}



	public void setMembre(Member membre) {
		this.membre = membre;
	}


	
	public Course getCourse() {
		return course;
	}



	public void setCourse(Course course) {
		this.course = course;
	}





	public Date getReservationDate() {
		return reservationDate;
	}





	public void setReservationDate(Date reservationDate) {
		this.reservationDate = reservationDate;
	}




   
}
