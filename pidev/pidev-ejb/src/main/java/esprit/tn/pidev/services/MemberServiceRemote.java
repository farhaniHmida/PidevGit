package esprit.tn.pidev.services;

import java.util.List;

import javax.ejb.Remote;

import esprit.tn.pidev.persistence.Member;

@Remote
public interface MemberServiceRemote {
	
	void createMember(Member member);
	void saveMember(Member member);
	List<Member> findAllMember();
	Member findUserByID(Integer id);
	Member authenticate(String login, String password);
	boolean loginExists(String login);
	Member findUserByLogin(String login);
}
