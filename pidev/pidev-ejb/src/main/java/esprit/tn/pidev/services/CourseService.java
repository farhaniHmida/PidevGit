package esprit.tn.pidev.services;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import esprit.tn.pidev.persistence.Course;
import esprit.tn.pidev.persistence.Member;
import esprit.tn.pidev.persistence.Reservation;




/**
 * Session Bean implementation class CourseService
 */
@Stateless
public class CourseService implements CourseServiceLocal, CourseServiceRemote {
	
	
	@PersistenceContext
	private EntityManager em;
    /**
     * Default constructor. 
     */
    public CourseService() {
        // TODO Auto-generated constructor stub
    }

	@Override
	public void addCourse(Course course) {
		em.persist(course);	
		
	}

	@Override
	public void saveOrUpdate(Course course) {
		em.merge(course);	
		
	}

	@Override
	public void deleteCourse(Course course ){
		em.remove(em.merge(course));
		
	}

	@Override
	public List<Course> findAllCourse() {
		return em.createQuery("select c from Course c ORDER BY c.courseID DESC", Course.class)
				.getResultList();
		
		
		
	}

	@Override
	public List<Course> findAllCourseByMemberID(int memberID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Course findCourseById(int courseID) {
		
		return em.find(Course.class, courseID);
		
		
	}

	@Override
	public Course findCourseByIdUser(int userID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Course> findCourseByTitle(String title) {
		String jpql = "SELECT c FROM Course c WHERE c.title like :param";
		Query query = em.createQuery(jpql);
		query.setParameter("param", "%"+title+"%");
		return query.getResultList();
	}

	@Override
	public List<Course> numberCoursesByType(String typecourse) {
		String jpql = "SELECT c FROM Course c WHERE c.type = :param";
		Query query = em.createQuery(jpql);
		query.setParameter("param", typecourse);
		return query.getResultList();
	}

	@Override
	public int numberCoursesByTypes(String typecourse) {
		String jpql = "SELECT c FROM Course c WHERE c.type = :param";
		Query query = em.createQuery(jpql);
		query.setParameter("param", typecourse);
		return query.getResultList().size();
	}

	@Override
	public int numberReservationByMonth(int month) {
		String jpql = "SELECT r FROM Reservation r WHERE month(r.reservationDate) = :param";
		Query query = em.createQuery(jpql);
		query.setParameter("param", month);
		return query.getResultList().size();
	}

	@Override
	public int numberReservationByMonthperCourse(int month, int courseId) {
		String jpql = "SELECT r FROM Reservation r WHERE month(r.reservationDate) = :param And r.reservationID.courseID = :param2";
		Query query = em.createQuery(jpql);
		query.setParameter("param", month);
		query.setParameter("param2", courseId);
		return query.getResultList().size();
	}
	
	@Override
	public int numberReservationByMonthpertype(int month, String typecourse) {
		String jpql = "SELECT r FROM Reservation r WHERE month(r.reservationDate) = :param And r.course.type = :param2";
		Query query = em.createQuery(jpql);
		query.setParameter("param", month);
		query.setParameter("param2", typecourse);
		return query.getResultList().size();
	}

	@Override
	public List<Reservation> findListReservationByCourse(int courseid) {
		String jpql="SELECT r FROM Reservation r join  r.course m where m.courseID = :param ";
		
		Query query = em.createQuery(jpql);
		query.setParameter("param", courseid);
		return query.getResultList();
	}

	@Override
	public int numberReservationByMonthperYUser(int month, int userId) {
		String jpql = "SELECT r FROM Reservation r WHERE month(r.reservationDate) = :param And r.reservationID.userId= :param2";
		Query query = em.createQuery(jpql);
		query.setParameter("param", month);
		query.setParameter("param2", userId);
		return query.getResultList().size();
		
	}

	@Override
	public List<Course> findCourseByPriceRange(int minprice, int maxprice) {
		String jpql = "SELECT c FROM Course c WHERE c.price > :param And c.price < :param2";
		Query query = em.createQuery(jpql);
		query.setParameter("param", minprice);
		query.setParameter("param2", maxprice);
		return query.getResultList();
	}

	


	
}
