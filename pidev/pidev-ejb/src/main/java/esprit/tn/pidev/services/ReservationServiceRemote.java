package esprit.tn.pidev.services;

import java.util.List;


import javax.ejb.Remote;

import esprit.tn.pidev.persistence.Course;
import esprit.tn.pidev.persistence.Reservation;


@Remote
public interface ReservationServiceRemote {
	
	public boolean reserve(Integer id, int CourseID);
	List<Reservation> findAllCourse();
	List<Reservation> findListCourseReserveByUser(int id );
	List<Course> findListCourseNonReservedByUser(int id );
}
