package esprit.tn.pidev.services;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import esprit.tn.pidev.persistence.Course;
import esprit.tn.pidev.persistence.Member;


/**
 * Session Bean implementation class MemberService
 */
@Stateless
public class MemberService implements MemberServiceRemote, MemberServiceLocal {

	@PersistenceContext
	private EntityManager em;
	
    /**
     * Default constructor. 
     */
    public MemberService() {
        // TODO Auto-generated constructor stub
    }

	@Override
	public void createMember(Member member) {
		em.persist(member);
		
	}

	@Override
	public void saveMember(Member member) {
		em.merge(member);
		
	}

	@Override
	public List<Member> findAllMember() {
		return em.createQuery("select m from Member m", Member.class)
				.getResultList();
	}

	@Override
	public Member authenticate(String login, String password) {
		Member found = null;
		String jpql = "select u from Member u where u.login=:login and u.password=:password";
		TypedQuery<Member> query = em.createQuery(jpql, Member.class);
		query.setParameter("login", login);
		query.setParameter("password", password);
		try {
			found = query.getSingleResult();
		} catch (Exception ex) {
			Logger.getLogger(MemberService.class.getName()).log(
					Level.WARNING,
					"authentication attempt failure with login=" + login
							+ " and password=" + password);
		}
		return found;
		
	}

	@Override
	public boolean loginExists(String login) {
		boolean exists = false;
		String jpql = "select case when (count(u) > 0)  then true else false end from Member u where u.login=:login";
		TypedQuery<Boolean> query = em.createQuery(jpql, Boolean.class);
		query.setParameter("login", login);
		try {
			exists = query.getSingleResult();
		} catch (NoResultException e) {
			Logger.getLogger(MemberService.class.getName()).log(Level.WARNING,
					"no user registred with login=" + login);
		}
		return exists;
	}

	@Override
	public Member findUserByLogin(String login) {
		Member found = null;
		String jpql = "select u from Member u where u.login=:login";
		TypedQuery<Member> query = em.createQuery(jpql, Member.class);
		query.setParameter("login", login);
		try {
			found = query.getSingleResult();
		} catch (Exception ex) {
			Logger.getLogger(MemberService.class.getName()).log(Level.WARNING,
					"no such login=" + login);
		}
		return found;
	}

	@Override
	public Member findUserByID(Integer id) {
		
		return em.find(Member.class, id); 
	}

}
