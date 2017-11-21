package esprit.tn.pidev;

import java.util.ArrayList;







import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.LineChartModel;
import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.chart.DateAxis;
import org.primefaces.model.chart.LegendPlacement;
import org.primefaces.model.chart.LineChartSeries;
import esprit.tn.pidev.persistence.Course;
import esprit.tn.pidev.persistence.Member;
import esprit.tn.pidev.persistence.Reservation;
import esprit.tn.pidev.services.CourseServiceLocal;
import esprit.tn.pidev.services.ReservationServiceLocal;

@ManagedBean
@SessionScoped
public class CourseBean {
	
	@EJB
	private CourseServiceLocal courseServiceLocal;
	
	@EJB
	private ReservationServiceLocal reservationServiceLocal;
	
	
	private Course course = new Course();
	
	private List<Course> listCourse =  new ArrayList<Course>();
	
	private List<Reservation> listReservationForCourse =  new ArrayList<Reservation>();
	
	private String valuesearch;
	
	private String Titleforstat;
	
	private BarChartModel animatedModeForCoursePerType;
	

    private LineChartModel dateModelForReservationPerMonth;
	
    private LineChartModel dateModelForReservationPerMonthPerCourse;
	
	private int occurence ;
	
	
	
	private boolean form ;
	private boolean listform ;
	private boolean listformreservation ;
	public CourseBean() {
		
	}
	
	@PostConstruct
	public void init(){
		listCourse = courseServiceLocal.findAllCourse();
		
		createAnimatedModels();
		createDateModel();
		
		
		
		form = false;
		setListform(true);
		listformreservation = false;
		listforreservation = false;
		listCourseNonReserved = reservationServiceLocal.findAllCourseforReservation();
		listCourseRing = reservationServiceLocal.findAllCourseforReservationtop();
	
		
		
		
		
		
		
	}
	
	public String doSaveOrUpdate(){
		
		courseServiceLocal.saveOrUpdate(course);
		listCourse = courseServiceLocal.findAllCourse();
		
		listCourseNonReserved = reservationServiceLocal.findAllCourseforReservation();
		listCourseRing = reservationServiceLocal.findAllCourseforReservationtop();
		
		
		course = null;
		form = false;
		listform = true;
		return  "";
	}
	
	public String ShowListReservation()
	{	
		listReservationForCourse=courseServiceLocal.findListReservationByCourse(course.getCourseID());
		createDateModelPerCourse(course.getCourseID());
		return "";
	}
	
		public String doCancelCourse(){
		
		courseServiceLocal.deleteCourse(course);;
		listCourse = courseServiceLocal.findAllCourse();
		course = null;
	    
		
		
		
		return  "";
	}
		
		public String doSerach()
		{	
			if(valuesearch.equalsIgnoreCase(""))
			{
				listCourse=courseServiceLocal.findAllCourse();
			}else
			{
			listCourse=courseServiceLocal.findCourseByTitle(valuesearch);
			}
			return "";
		}
		
		
		public String doReturnCourse(){
			
			
			listCourse = courseServiceLocal.findAllCourse();
			form = false;
			setListform(true);
			
		    course = null;
			
			
			
			return  "";
		}
		
			public String doReturnCourseList(){
			
			
			listCourse = courseServiceLocal.findAllCourse();
			form = false;
			listformreservation = false;
			setListform(true);
			
		    course = null;
			
			
			
			return  "";
		}
		
		
		////********************************************Statisque**************************************///
		
		 private void createAnimatedModels() {
		       
		         
			 animatedModeForCoursePerType = initBarModel();
			 animatedModeForCoursePerType.setTitle("Course Per Type Charts");
			 animatedModeForCoursePerType.setAnimate(true);
			 animatedModeForCoursePerType.setLegendPosition("e");		 
			 animatedModeForCoursePerType.setLegendPlacement(LegendPlacement.OUTSIDEGRID);
			 Axis yAxis = animatedModeForCoursePerType.getAxis(AxisType.Y);
		     yAxis.setMin(0);
		     yAxis.setMax(20);
		    }
		 
		 
		 private BarChartModel initBarModel() {
			 
		        int plasticArtOccu = courseServiceLocal.numberCoursesByTypes("PlaticArt");
		        int paintingOccu = courseServiceLocal.numberCoursesByTypes("Painting");
		        int sculptureOccu = courseServiceLocal.numberCoursesByTypes("Sculpture");
		        int designOccu  = courseServiceLocal.numberCoursesByTypes("Design");
			 
			 	BarChartModel model = new BarChartModel();
		 
		        ChartSeries coursePerPlastic = new ChartSeries();
		        coursePerPlastic.setLabel("Plastic Art Courses ");
		        coursePerPlastic.set("Platic Art", plasticArtOccu);
		     
		        
		        ChartSeries coursePerDesign = new ChartSeries();
		        coursePerDesign.setLabel("Design Courses ");
		        coursePerDesign.set("Design", designOccu);
		        
		        ChartSeries coursePerPainting= new ChartSeries();
		        coursePerPainting.setLabel("Painting Courses ");
		        coursePerPainting.set("Painting", paintingOccu);
		       
		        
		        ChartSeries courseSculpture = new ChartSeries();
		        courseSculpture.setLabel("Sculpture Courses ");
		        courseSculpture.set("Sculpture", sculptureOccu);
		        
		        
		        
		       
		 
		        model.addSeries(coursePerPlastic);
		        model.addSeries(coursePerDesign);
		        model.addSeries(coursePerPainting);
		        model.addSeries(courseSculpture);
		        
		         
		        return model;
		    }
		 
		 private void createDateModel() {
			 
			 	int JanuaryOcc = courseServiceLocal.numberReservationByMonth(1);
			 	int FebruaryOcc = courseServiceLocal.numberReservationByMonth(2);
			 	int MarsOcc = courseServiceLocal.numberReservationByMonth(3);
			 	int AvrilOcc = courseServiceLocal.numberReservationByMonth(4);
			 	int MaiOcc = courseServiceLocal.numberReservationByMonth(5);
			 	int JuinOcc = courseServiceLocal.numberReservationByMonth(6);
			 	int juilletOcc  = courseServiceLocal.numberReservationByMonth(7);
			 	int AoutOcc  = courseServiceLocal.numberReservationByMonth(8);
			 	int SeptembreOcc = courseServiceLocal.numberReservationByMonth(9);
			 	int OctobreOcc = courseServiceLocal.numberReservationByMonth(10);
			 	int NovembreOcc = courseServiceLocal.numberReservationByMonth(11);	
			 	int DecembreOcc = courseServiceLocal.numberReservationByMonth(12);
			 
			 
			 
			    dateModelForReservationPerMonth = new LineChartModel();
		        LineChartSeries series1 = new LineChartSeries();
		        series1.setLabel("Reservation Per Month");
		 
		        series1.set("01",JanuaryOcc );
		        series1.set("02",FebruaryOcc );
		        series1.set("03",MarsOcc );
		        series1.set("04",AvrilOcc );
		        series1.set("05",MaiOcc );
		        series1.set("06",JuinOcc );
		        series1.set("07",juilletOcc);
		        series1.set("08",AoutOcc );
		        series1.set("09",SeptembreOcc );
		        series1.set("10",OctobreOcc);
		        series1.set("11",NovembreOcc);
		        series1.set("12",DecembreOcc );
		        
		        dateModelForReservationPerMonth.addSeries(series1);
		        dateModelForReservationPerMonth.addSeries(initLineModel("Sculpture"));
		        dateModelForReservationPerMonth.addSeries(initLineModel("Design"));
		        dateModelForReservationPerMonth.addSeries(initLineModel("PlaticArt"));
		        dateModelForReservationPerMonth.addSeries(initLineModel("Painting"));
		        
		        dateModelForReservationPerMonth.setTitle("Reservation Per Month and Per Type");
		        dateModelForReservationPerMonth.setZoom(true);
		        dateModelForReservationPerMonth.setLegendPosition("e");
		        dateModelForReservationPerMonth.setLegendPlacement(LegendPlacement.OUTSIDEGRID);
		        dateModelForReservationPerMonth.getAxis(AxisType.Y).setLabel("Reservation");
		        DateAxis axis = new DateAxis("Dates");
		        
		        axis.setTickFormat("%b");
		         
		        dateModelForReservationPerMonth.getAxes().put(AxisType.X, axis);
		    }
		 	
		 
		 public LineChartSeries initLineModel(String typecourse)
		 {
			 LineChartSeries series1 = new LineChartSeries();
			 
			 	int JanuaryOcc = courseServiceLocal.numberReservationByMonthpertype(1, typecourse);
			 	int FebruaryOcc = courseServiceLocal.numberReservationByMonthpertype(2, typecourse);
			 	int MarsOcc = courseServiceLocal.numberReservationByMonthpertype(3, typecourse);
			 	int AvrilOcc = courseServiceLocal.numberReservationByMonthpertype(4, typecourse);
			 	int MaiOcc = courseServiceLocal.numberReservationByMonthpertype(5, typecourse);
			 	int JuinOcc = courseServiceLocal.numberReservationByMonthpertype(6, typecourse);
			 	int juilletOcc  = courseServiceLocal.numberReservationByMonthpertype(7, typecourse);
			 	int AoutOcc  = courseServiceLocal.numberReservationByMonthpertype(8, typecourse);
			 	int SeptembreOcc = courseServiceLocal.numberReservationByMonthpertype(9, typecourse);
			 	int OctobreOcc = courseServiceLocal.numberReservationByMonthpertype(10, typecourse);
			 	int NovembreOcc = courseServiceLocal.numberReservationByMonthpertype(11, typecourse);	
			 	int DecembreOcc = courseServiceLocal.numberReservationByMonthpertype(12, typecourse);
			 	
			 	 series1.setLabel(typecourse);
				 
			        series1.set("01",JanuaryOcc );
			        series1.set("02",FebruaryOcc );
			        series1.set("03",MarsOcc );
			        series1.set("04",AvrilOcc );
			        series1.set("05",MaiOcc );
			        series1.set("06",JuinOcc );
			        series1.set("07",juilletOcc);
			        series1.set("08",AoutOcc );
			        series1.set("09",SeptembreOcc );
			        series1.set("10",OctobreOcc);
			        series1.set("11",NovembreOcc);
			        series1.set("12",DecembreOcc );
			 
			 
			 return series1;
		 }
		 
		 
		 
		   
		 	private void createDateModelPerCourse(int courseId) {
			 
			 
			 
			 
		 		dateModelForReservationPerMonthPerCourse = new LineChartModel();
		        LineChartSeries series1 = new LineChartSeries();
		        series1.setLabel("Reservation Per Month");
		 
		        
		        
		        dateModelForReservationPerMonthPerCourse.addSeries(initLineModelUsingID(courseId));
		    
		        
		        dateModelForReservationPerMonthPerCourse.setTitle("Reservation Per Month for a  course");
		        dateModelForReservationPerMonthPerCourse.setZoom(true);
		        dateModelForReservationPerMonthPerCourse.setLegendPosition("e");
		        dateModelForReservationPerMonthPerCourse.setLegendPlacement(LegendPlacement.OUTSIDEGRID);
		        dateModelForReservationPerMonthPerCourse.getAxis(AxisType.Y).setLabel("Reservation");
		        DateAxis axis = new DateAxis("Dates");
		        
		        axis.setTickFormat("%b");
		         
		        dateModelForReservationPerMonthPerCourse.getAxes().put(AxisType.X, axis);
		    }
		 
		 public LineChartSeries initLineModelUsingID(int courseId)
		 {
			 LineChartSeries series1 = new LineChartSeries();
			 
			 	int JanuaryOcc = courseServiceLocal.numberReservationByMonthperCourse(1, courseId);
			 	int FebruaryOcc = courseServiceLocal.numberReservationByMonthperCourse(2, courseId);
			 	int MarsOcc = courseServiceLocal.numberReservationByMonthperCourse(3, courseId);
			 	int AvrilOcc = courseServiceLocal.numberReservationByMonthperCourse(4, courseId);
			 	int MaiOcc = courseServiceLocal.numberReservationByMonthperCourse(5, courseId);
			 	int JuinOcc = courseServiceLocal.numberReservationByMonthperCourse(6, courseId);
			 	int juilletOcc  = courseServiceLocal.numberReservationByMonthperCourse(7, courseId);
			 	int AoutOcc  = courseServiceLocal.numberReservationByMonthperCourse(8, courseId);
			 	int SeptembreOcc = courseServiceLocal.numberReservationByMonthperCourse(9, courseId);
			 	int OctobreOcc = courseServiceLocal.numberReservationByMonthperCourse(10, courseId);
			 	int NovembreOcc = courseServiceLocal.numberReservationByMonthperCourse(11, courseId);	
			 	int DecembreOcc = courseServiceLocal.numberReservationByMonthperCourse(12, courseId);
			 	
			 	 series1.setLabel("course");
				 
			        series1.set("01",JanuaryOcc );
			        series1.set("02",FebruaryOcc );
			        series1.set("03",MarsOcc );
			        series1.set("04",AvrilOcc );
			        series1.set("05",MaiOcc );
			        series1.set("06",JuinOcc );
			        series1.set("07",juilletOcc);
			        series1.set("08",AoutOcc );
			        series1.set("09",SeptembreOcc );
			        series1.set("10",OctobreOcc);
			        series1.set("11",NovembreOcc);
			        series1.set("12",DecembreOcc );
			 
			 
			 return series1;
		 }
	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	public List<Course> getListCourse() {
		return listCourse;
	}

	public void setListCourse(List<Course> listCourse) {
		this.listCourse = listCourse;
	}

	public boolean isForm() {
		return form;
	}

	public void setForm(boolean form) {
		this.form = form;
	}

	
	public int getOccurence() {
		return occurence;
	}

	public void setOccurence(int occurence) {
		this.occurence = occurence;
	}

	
	public BarChartModel getAnimatedModeForCoursePerType() {
		return animatedModeForCoursePerType;
	}

	public void setAnimatedModeForCoursePerType(BarChartModel animatedModeForCoursePerType) {
		this.animatedModeForCoursePerType = animatedModeForCoursePerType;
	}

	public LineChartModel getDateModelForReservationPerMonth() {
		return dateModelForReservationPerMonth;
	}

	public void setDateModelForReservationPerMonth(LineChartModel dateModelForReservationPerMonth) {
		this.dateModelForReservationPerMonth = dateModelForReservationPerMonth;
	}

	public boolean isListform() {
		return listform;
	}

	public void setListform(boolean listform) {
		this.listform = listform;
	}

	public List<Reservation> getListReservationForCourse() {
		return listReservationForCourse;
	}

	public void setListReservationForCourse(List<Reservation> listReservationForCourse) {
		this.listReservationForCourse = listReservationForCourse;
	}

	public boolean isListformreservation() {
		return listformreservation;
	}

	public void setListformreservation(boolean listformreservation) {
		this.listformreservation = listformreservation;
	}

	public LineChartModel getDateModelForReservationPerMonthPerCourse() {
		return dateModelForReservationPerMonthPerCourse;
	}

	public void setDateModelForReservationPerMonthPerCourse(LineChartModel dateModelForReservationPerMonthPerCourse) {
		this.dateModelForReservationPerMonthPerCourse = dateModelForReservationPerMonthPerCourse;
	}

	public String getValuesearch() {
		return valuesearch;
	}

	public void setValuesearch(String valuesearch) {
		this.valuesearch = valuesearch;
	}

	public String getTitleforstat() {
		return Titleforstat;
	}

	public void setTitleforstat(String titleforstat) {
		Titleforstat = titleforstat;
	}
	
	
	////////////////////*******************************reservationBean***********////////////////
	private List<Reservation> listReservation =  new ArrayList<Reservation>();
	private List<Reservation> listReservationTest =  new ArrayList<Reservation>();
	private List<Course> listReservationTest1 =  new ArrayList<Course>();
	
	private List<Course>  listCourseNonReserved = new ArrayList<Course>();	
	private List<Course>  listCourseNonreserved = new ArrayList<Course>();
	
	private List<Course>  listCourseRing = new ArrayList<Course>();
	
    private Reservation reserv = new Reservation();
    
    private boolean listforreservation;
	
	
	private Member membre = new Member();
	
	private int minprice;
	private int maxprice;
	
	

	
	
	
	
	
	
	public String doReservation(){
		
		
		reservationServiceLocal.reserve(membre.getId(), course.getCourseID());
		listReservation=reservationServiceLocal.findListCourseReserveByUser(membre.getId());
		listCourseNonReserved = reservationServiceLocal.findListCourseNonReservedByUser(membre.getId());
		
	
		
		
		return  "";
	}
	public String ShowListCourseforReserved()
	{
		listReservation=reservationServiceLocal.findListCourseReserveByUser(membre.getId());
		listCourseNonReserved = reservationServiceLocal.findListCourseNonReservedByUser(membre.getId());
		
		
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

	public boolean isListforreservation() {
		return listforreservation;
	}

	public void setListforreservation(boolean listforreservation) {
		this.listforreservation = listforreservation;
	}

	

	




	

}
