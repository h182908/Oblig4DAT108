package database;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

@Stateless
public class UsersDAO {
	
	@PersistenceContext(name="usersDB")
	private EntityManager em;
	
	public synchronized List<Users> getUsers() {
		TypedQuery<Users> query = em.createQuery("select u from Users u", Users.class);
		return query.getResultList();
	}
	
	public synchronized Users getUser(String phone_number) {
		return em.find(Users.class, phone_number);
	}
	
	public synchronized Boolean saveNewUser(Users user) {
		if(em.find(Users.class, user.getPhone_number()) == null) {
			em.persist(user);
			return true;
		}
		return false;
	}
}