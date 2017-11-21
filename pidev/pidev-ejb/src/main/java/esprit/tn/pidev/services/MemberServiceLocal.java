package esprit.tn.pidev.services;

import java.util.List;

import javax.ejb.Local;

import esprit.tn.pidev.persistence.Member;



@Local
public interface MemberServiceLocal {
	void createMember(Member member);
	void saveMember(Member member);
	List<Member> findAllMember();
	Member findUserByID(Integer id);
	Member authenticate(String login, String password);
	boolean loginExists(String login);
	Member findUserByLogin(String login);

}
