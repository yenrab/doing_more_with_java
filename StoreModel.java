package com.doing.more.java.example;

import com.doing.more.java.example.User;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.lang.Exception;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created by lee on 8/27/15.
 */
public class StoreModel {
    private HibernateConfig theHibernateConfiguration;
    public StoreModel(){
       this.theHibernateConfiguration = new HibernateConfig();
    }
    public void addUser(User aUser){
        Session theSession = this.theHibernateConfiguration.getCurrentSession();
        Transaction transaction = theSession.beginTransaction();
        theSession.save(aUser);
        transaction.commit();
    }
    public void updateUser(User aUser){
        Session theSession = this.theHibernateConfiguration.getCurrentSession();
        Transaction transaction = theSession.beginTransaction();
        theSession.merge(aUser);
        transaction.commit();
    }

    public ArrayList getAllUsers(){
        return this.getAll("");
    }
    public ArrayList getAllCustomers(){
        return this.getAll(" where u.manager_level == 0");
    }
    public ArrayList getAllManagers(){
        return this.getAll(" where u.manager_level > 0");
    }
    public ArrayList getAllManagersByLevel(int aLevel){
        return this.getAll(" where u.manager_level == "+aLevel);
    }
    private ArrayList getAll(String whereClause){
        Session theSession = this.theHibernateConfiguration.getCurrentSession();
        Transaction transaction = theSession.beginTransaction();
        Query allUsersQuery = theSession.createQuery("select u from User as u order by u.id" + whereClause);
        List userList = allUsersQuery.list();
        return new ArrayList(userList);
    }

    public User getUser(String aName, String aPassword){
        Session theSession = this.theHibernateConfiguration.getCurrentSession();
        Transaction transaction = theSession.beginTransaction();
        Query singleUserQuery = theSession.createQuery("select u from User as u where u.uname="+aName+ "and u.pword = "+aPassword);
        User theUser = (User)singleUserQuery.uniqueResult();
        return theUser;
    }
    public User getUserBySessionID(String aSessionID){
        Session theSession = this.theHibernateConfiguration.getCurrentSession();
        Transaction transaction = theSession.beginTransaction();
        Query singleUserQuery = theSession.createQuery("select u from User as u where u.session="+aSessionID);
        User theUser = (User)singleUserQuery.uniqueResult();
        return theUser;
    }
}
