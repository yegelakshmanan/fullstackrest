package com.dao;

import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.model.User;



@Repository
public class UserDaoImpl implements UserDao{

	@Autowired
	private SessionFactory sessionFactory;
	@Override
	public User createUser(User user) {
		Session session=this.sessionFactory.openSession();
		//Transaction tx=session.beginTransaction();
		
		session.save(user);
		//tx.commit();
		return user;
	}

	@Override
	public List<User> readUser() {
		Session session=this.sessionFactory.openSession();
		//HQL
		String readUser="from User";
		List<User> users=session.createQuery(readUser).list();
		return users;
	}

	@Override
	public User readUserById(int userId) {
		//jdbc
		Session session=this.sessionFactory.openSession();
		String hqlRead="from User alias where alias.userId=2";
		List<User> users=session.createQuery(hqlRead).list();
		return users.get(0);
	}

	@Override
	public User readUserByName(String userName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User updateUser(User user) {
		Session session=this.sessionFactory.openSession();
		session.saveOrUpdate(user);
	    
		return user;
	}

	@Override
	public User deleteUserById(int userId) {
		Session session=this.sessionFactory.openSession();
		User obj=session.load(User.class,new Integer(userId));
		session.delete(obj);
		/*String hql="delete from User where userId=:ID";
		Query query =session.createQuery(hql);
		query.setParameter("ID", userId);
		int res=query.executeUpdate();*/
		return obj;
	}

	

}
