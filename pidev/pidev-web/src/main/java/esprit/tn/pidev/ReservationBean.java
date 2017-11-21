package esprit.tn.pidev;

import java.util.ArrayList;


import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import esprit.tn.pidev.persistence.Course;
import esprit.tn.pidev.persistence.Member;
import esprit.tn.pidev.persistence.Reservation;
import esprit.tn.pidev.services.ReservationServiceLocal;

@ManagedBean
@SessionScoped
public class ReservationBean {

	
	@EJB
	private ReservationServiceLocal reservationServiceLocal;
	

	
	private List<Reservation> listReservation =  new ArrayList<Reservation>();
	private List<Reservation> listReservationTest =  new ArrayList<Reservation>();
	private List<Course> listReservationTest1 =  new ArrayList<Course>();
	
	private List<Course>  listCourseNonReserved = new ArrayList<Course>();	
	private List<Course>  listCourseNonreserved = new ArrayList<Course>();
	
	private List<Course>  listCourseRing = new ArrayList<Course>();
	
    private Reservation reserv = new Reservation();
	private Course course = new Course();
	
	private Member membre = new Member();
	
	private int minprice;
	private int maxprice;
	
	
	
	private boolean form ;
	
	
	public ReservationBean() {
		
		// TODO Auto-generated constructor stub
	}
	
	@PostConstruct
	public void init(){		
	
		//listCourseNonReserved = reservationServiceLocal.findAllCourseforReservation();
		//listCourseRing = reservationServiceLocal.findAllCourseforReservationtop();
		//listReservation=reservationServiceLocal.findListCourseReserveByUser(2);
		
		
		
		
		
		
	}
	
	public String doReservation(){
		
		
		reservationServiceLocal.reserve(membre.getId(), course.getCourseID());
		
		listReservation=reservationServiceLocal.findListCourseReserveByUser(membre.getId());
		listCourseNonReserved = reservationServiceLocal.findListCourseNonReservedByUser(membre.getId());
		
	
		
		
		return  "";
	}
	public String ShowListCourseReserved()
	{
		listReservation=reservationServiceLocal.findListCourseReserveByUser(membre.getId());
		
		return "";
	}
	public String doSearch()
	{
		
		listCourseNonReserved=reservationServiceLocal.findCourseByPriceRange(minprice, maxprice);
		return "";
	}
	
	public List<Reservation> getListReservation() {
		return listReservation;
	}
	public void setListReservation(List<Reservation> listReservation) {
		this.listReservation = listReservation;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	public boolean isForm() {
		return form;
	}

	public void setForm(boolean form) {
		this.form = form;
	}
	
	


	public Member getMembre() {
		return membre;
	}

	public void setMembre(Member membre) {
		this.membre = membre;
	}

	public List<Reservation> getListReservationTest() {
		return listReservationTest;
	}

	public void setListReservationTest(List<Reservation> listReservationTest) {
		this.listReservationTest = listReservationTest;
	}

	public List<Course> getListReservationTest1() {
		return listReservationTest1;
	}

	public void setListReservationTest1(List<Course> listReservationTest1) {
		this.listReservationTest1 = listReservationTest1;
	}

	public List<Course> getListCourseNonreserved() {
		return listCourseNonreserved;
	}

	public void setListCourseNonreserved(List<Course> listCourseNonreserved) {
		this.listCourseNonreserved = listCourseNonreserved;
	}

	public List<Course> getListCourseNonReserved() {
		return listCourseNonReserved;
	}

	public void setListCourseNonReserved(List<Course> listCourseNonReserved) {
		this.listCourseNonReserved = listCourseNonReserved;
	}

	public int getMinprice() {
		return minprice;
	}

	public void setMinprice(int minprice) {
		this.minprice = minprice;
	}

	public int getMaxprice() {
		return maxprice;
	}

	public void setMaxprice(int maxprice) {
		this.maxprice = maxprice;
	}

	public Reservation getReserv() {
		return reserv;
	}

	public void setReserv(Reservation reserv) {
		this.reserv = reserv;
	}

	public List<Course> getListCourseRing() {
		return listCourseRing;
	}

	public void setListCourseRing(List<Course> listCourseRing) {
		this.listCourseRing = listCourseRing;
	}

	

	

	

}
