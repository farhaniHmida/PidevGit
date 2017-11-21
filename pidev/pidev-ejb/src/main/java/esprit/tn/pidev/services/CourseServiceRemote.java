package esprit.tn.pidev.services;

import java.util.List;

import javax.ejb.Remote;

import esprit.tn.pidev.persistence.Course;

@Remote
public interface CourseServiceRemote {
	
	void addCourse(Course course);
	
	void saveOrUpdate(Course course);
	
	void deleteCourse(Course course);
	
	List<Course> findAllCourse();
	
	List<Course> findAllCourseByMemberID(int memberID);
	
	List<Course> findCourseByTitle(String title);
	
	Course findCourseById(int courseID);
	 
	Course findCourseByIdUser(int userID);

}
