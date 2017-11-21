package esprit.tn.pidev.services;

import java.util.List;


import javax.ejb.Local;

import esprit.tn.pidev.persistence.Course;
import esprit.tn.pidev.persistence.Reservation;




@Local
public interface CourseServiceLocal {
	
	void addCourse(Course course);
	
	void saveOrUpdate(Course course);
	
	void deleteCourse(Course course);
	
	List<Course> findAllCourse();
	
	List<Course> findAllCourseByMemberID(int memberID);
	
	List<Course> findCourseByTitle(String title);
	
	Course findCourseById(int courseID);
	 
	Course findCourseByIdUser(int userID);
	
	List<Course> numberCoursesByType(String typecourse);
	
	int numberCoursesByTypes(String typecourse);
	
	int numberReservationByMonth(int month);
	
	int numberReservationByMonthperCourse(int month , int courseId);
	
	int numberReservationByMonthpertype(int month, String typecourse);
	
	int numberReservationByMonthperYUser(int month, int userId ) ;
	
	List<Reservation> findListReservationByCourse(int courseid);
	
	List<Course> findCourseByPriceRange(int minprice,int maxprice) ;
	
	

}
