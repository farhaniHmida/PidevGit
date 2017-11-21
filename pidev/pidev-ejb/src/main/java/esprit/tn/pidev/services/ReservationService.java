package esprit.tn.pidev.services;



import java.util.Calendar;

import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import esprit.tn.pidev.persistence.Course;
import esprit.tn.pidev.persistence.Member;
import esprit.tn.pidev.persistence.Reservation;


/**
 * Session Bean implementation class ReservationService
 */
@Stateless
public class ReservationService implements ReservationServiceRemote, ReservationServiceLocal {
	
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@EJB
	private CourseServiceLocal courseServiceLocal;
	
	@EJB
	private MemberServiceLocal memeberServiceLocal;
	
	
	
    /**
     * Default constructor. 
     */
    public ReservationService() {
        // TODO Auto-generated constructor stub
    }
	
	
	@Override
	public List<Reservation> findAllCourse() {
		return entityManager.createQuery("select c from Reservation c", Reservation.class)
				.getResultList();
		
	}
	
	@Override
	public List<Course> findAllCourseforReservation() {
		return entityManager.createQuery("select c from Course c ORDER BY c.courseID DESC", Course.class)
				.getResultList();
		
		
		
	}


	@Override
	public boolean reserve(Integer id, int CourseID) {
		
		Member member = memeberServiceLocal.findUserByID(id) ;
		Course course = courseServiceLocal.findCourseById(CourseID);
		 
		 
		  Date d1 = new Date();
	      Calendar cl = Calendar. getInstance();
	      cl.setTime(d1);
	      
	      
	      Reservation reservation = new Reservation(member, course,d1);
	      entityManager.merge(reservation);
	      
	      course.setNumberAttendance(course.getNumberAttendance()-1);
	      entityManager.persist(course);
		return true;
	}


	@Override
	public List<Reservation> findListCourseReserveByUser(int id) {
		
		
		String jpql="SELECT r FROM Reservation r where r.reservationID.userId = :param ";
		
		Query query = entityManager.createQuery(jpql);
		query.setParameter("param", id);
		return query.getResultList();
	
		
	}


	@Override
	public List<Course> findListCourseNonReservedByUser(int id) {
		
		String jpql="SELECT c FROM Course c WHERE c.courseID NOT IN(SELECT r.reservationID.courseID FROM Reservation r where r.reservationID.userId = :param)  ";
		Query query = entityManager.createQuery(jpql);
		query.setParameter("param", id);
		return query.getResultList();

		
	}
	
	@Override
	public List<Course> findCourseByPriceRange(int minprice, int maxprice) {
		String jpql = "SELECT c FROM Course c WHERE c.price > :param And c.price < :param2";
		Query query = entityManager.createQuery(jpql);
		query.setParameter("param", minprice);
		query.setParameter("param2", maxprice);
		return query.getResultList();
	}


	@Override
	public List<Course> findAllCourseforReservationtop() {
		return entityManager.createQuery("select  c from Course c ORDER BY c.courseID DESC", Course.class)
				.setMaxResults(3).getResultList();
	}

	

	

}
