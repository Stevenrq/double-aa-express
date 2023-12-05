package org.doubleaaexpress.controllers;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.swing.JOptionPane;

import org.doubleaaexpress.models.Administrator;
import org.doubleaaexpress.models.Customer;
import org.doubleaaexpress.models.OrderManager;
import org.doubleaaexpress.models.dao.abstractfactory.AbstractFactoryDAO;
import org.doubleaaexpress.models.dao.abstractfactory.ConcreteAbstractFactoryDAO;
import org.doubleaaexpress.models.dao.abstractfactory.GenericUserDAO;
import org.doubleaaexpress.views.AdministratorMainView;
import org.doubleaaexpress.views.CustomerMainView;
import org.doubleaaexpress.views.MainView;
import org.doubleaaexpress.views.OrderManagerMainView;
import org.doubleaaexpress.views.forms.OrderManagerFormView;
import org.doubleaaexpress.views.forms.SignInView;

@NoArgsConstructor
@Getter
@Setter
public class SignInController {

    private Administrator administrator;
    private OrderManager orderManager;
    private Customer customer;

    private MainView mainView;
    private SignInView signInView;
    private AdministratorMainView administratorMainView;
    private OrderManagerMainView orderManagerMainView;
    private CustomerMainView customerMainView;
    private OrderManagerFormView orderManagerFormView;

    public SignInController(Administrator administrator, OrderManager orderManager, Customer customer,
            MainView mainView, SignInView signInView, AdministratorMainView administratorMainView,
            OrderManagerMainView orderManagerMainView, CustomerMainView customerMainView,
            OrderManagerFormView orderManagerFormView) {

        this.administrator = administrator;
        this.orderManager = orderManager;
        this.customer = customer;

        this.mainView = mainView;
        this.signInView = signInView;
        this.administratorMainView = administratorMainView;
        this.orderManagerMainView = orderManagerMainView;
        this.customerMainView = customerMainView;
        this.orderManagerFormView = orderManagerFormView;
    }

    public void signIn() {
        AbstractFactoryDAO abstractFactoryDAO = new ConcreteAbstractFactoryDAO();
        GenericUserDAO<Administrator> administratorDAO = abstractFactoryDAO.getAdministratorDAO();
        GenericUserDAO<OrderManager> orderManagerDAO = abstractFactoryDAO.getOrderManagerDAO();
        GenericUserDAO<Customer> customerDAO = abstractFactoryDAO.getCustomerDAO();

        String email = getSignInView().getTfEmail().getText();
        String password = new String(getSignInView().getTfPassword().getPassword());

        if (administratorDAO.get(email, password)) {
            getSignInView().setVisible(false);
            getSignInView().getTfEmail().setText("");
            getSignInView().getTfPassword().setText("");
            getSignInView().getTfEmail().requestFocus();
            getAdministratorMainView().setVisible(true);

        } else if (orderManagerDAO.get(email, password)) {
            getSignInView().setVisible(false);
            getSignInView().getTfEmail().setText("");
            getSignInView().getTfPassword().setText("");
            getSignInView().getTfEmail().requestFocus();
            getOrderManagerMainView().setVisible(true);

        } else if (customerDAO.get(email, password)) {
            getSignInView().setVisible(false);
            getSignInView().getTfEmail().setText("");
            getSignInView().getTfPassword().setText("");
            getSignInView().getTfEmail().requestFocus();
            getCustomerMainView().setVisible(true);
        } else if (email.isBlank() || password.isBlank()) {
            JOptionPane.showMessageDialog(null, "You must fill out all fields");
        } else {
            JOptionPane.showMessageDialog(null, "Incorrect email or password");
        }
    }
}
