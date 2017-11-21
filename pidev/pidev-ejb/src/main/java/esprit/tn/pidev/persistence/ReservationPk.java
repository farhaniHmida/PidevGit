package esprit.tn.pidev.persistence;

import java.io.Serializable;


import javax.persistence.*;

/**
 * Entity implementation class for Entity: ReservationPk
 *
 */
@Embeddable
public class ReservationPk implements Serializable {

	
	private static final long serialVersionUID = 1L;
	
	private Integer courseID;
	private Integer userId;
	
	
	
	public ReservationPk(Integer courseID, Integer userId) {
		super();
		this.courseID = courseID;
		this.userId = userId;
	}


	public Integer getCourseID() {
		return courseID;
	}


	public void setCourseID(Integer courseID) {
		this.courseID = courseID;
	}

	
	public Integer getUserId() {
		return userId;
	}


	public void setUserId(Integer userId) {
		this.userId = userId;
	}


	public ReservationPk() {
		super();
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((courseID == null) ? 0 : courseID.hashCode());
		result = prime * result + ((userId == null) ? 0 : userId.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ReservationPk other = (ReservationPk) obj;
		if (courseID == null) {
			if (other.courseID != null)
				return false;
		} else if (!courseID.equals(other.courseID))
			return false;
		if (userId == null) {
			if (other.userId != null)
				return false;
		} else if (!userId.equals(other.userId))
			return false;
		return true;
	}


	
   
	
}
