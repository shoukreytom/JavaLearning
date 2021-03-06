package com.houarizegai.customertracker.dao;

import com.houarizegai.customertracker.entity.Customer;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CustomerDAOImpl implements CustomerDAO {

    // need to inject the session factory
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<Customer> getCustomers() {
        // get the current hibernate session
        Session currentSession = sessionFactory.getCurrentSession();

        // create a query
        Query<Customer> query = currentSession.createQuery("from Customer order by lastName", Customer.class);

        // execute query and get result list
        List<Customer> customers = query.getResultList();

        return customers;
    }

    @Override
    public Customer getCustomer(int customerId) {
        // get the current sessiob
        Session currentSession = sessionFactory.getCurrentSession();
        Customer customer = currentSession.get(Customer.class, customerId);

        return customer;
    }

    @Override
    public void saveCustomer(Customer customer) {
        // get the current hibernate session
        Session currentSession = sessionFactory.getCurrentSession();

        // insert the customer to the database
        currentSession.saveOrUpdate(customer);
    }

    @Override
    public void deleteCustomer(int customerId) {
        // get the current hibernate session
        Session currentSession = sessionFactory.getCurrentSession();

        // delete the customer from the database
        Query query = currentSession.createQuery("delete from Customer where id=:customerId");
        query.setParameter("customerId", customerId);
        query.executeUpdate();
    }
}
