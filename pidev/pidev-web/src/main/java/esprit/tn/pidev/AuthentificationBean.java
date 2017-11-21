package esprit.tn.pidev;

import javax.annotation.PostConstruct;

import javax.ejb.EJB;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import esprit.tn.pidev.persistence.Member;
import esprit.tn.pidev.services.MemberServiceLocal;



@ManagedBean
@SessionScoped
public class AuthentificationBean {

	@EJB
	private MemberServiceLocal memberServiceLocal;
	
	private String userType ="";

	private Member user = new Member();
	
	private Member user1 = new Member();
	
	private boolean logged = false;
	
	
	@PostConstruct
	public void init()
	{
		user1 = memberServiceLocal.findUserByID(2);
		
	}

	public boolean isLogged() {
		return logged;
	}

	public void setLogged(boolean logged) {
		this.logged = logged;
	}

	public String doLogin() {
		String navto = "";
		Member found = memberServiceLocal.authenticate(user.getLogin(), user.getPassword());
		if (found != null) {
			if (found.getLogin().equalsIgnoreCase("admin")) {
				userType = "Admin";
				logged = true;
				navto = "list?faces-redirect=true";
				user = found;
			}else
			{
				logged = true;
				user = found;
				navto = "reservation/listreserve?faces-redirect=true";	
							
			}
			
		}
		return navto;
	}
	
	public String doLogout(){
		logged = false;
		user = new Member();
		
		userType="";
		return "login?faces-redirect=true";
	}

	public Member getUser() {
		return user;
	}

	public void setUser(Member user) {
		this.user = user;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public Member getUser1() {
		return user1;
	}

	public void setUser1(Member user1) {
		this.user1 = user1;
	}

}
