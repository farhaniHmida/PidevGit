package esprit.tn.pidev.services;

import java.util.List;



import javax.ejb.Local;

import esprit.tn.pidev.persistence.Course;
import esprit.tn.pidev.persistence.Reservation;


@Local
public interface ReservationServiceLocal {
	
	public boolean reserve(Integer id, int CourseID);
	List<Reservation> findAllCourse();
	List<Reservation> findListCourseReserveByUser(int id );
	List<Course> findListCourseNonReservedByUser(int id );
	List<Course> findAllCourseforReservation();
	List<Course> findAllCourseforReservationtop();
	List<Course> findCourseByPriceRange(int minprice, int maxprice) ;
}
