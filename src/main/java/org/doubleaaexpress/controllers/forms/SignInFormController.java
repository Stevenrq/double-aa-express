package org.doubleaaexpress.controllers.forms;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.swing.JOptionPane;

import org.doubleaaexpress.models.Administrator;
import org.doubleaaexpress.models.Buyer;
import org.doubleaaexpress.models.Customer;
import org.doubleaaexpress.models.OrderManager;
import org.doubleaaexpress.models.dao.abstractfactory.AbstractFactoryDAO;
import org.doubleaaexpress.models.dao.abstractfactory.ConcreteAbstractFactoryDAO;
import org.doubleaaexpress.models.dao.abstractfactory.GenericUserDAO;
import org.doubleaaexpress.views.AdministratorMainView;
import org.doubleaaexpress.views.BuyerMainView;
import org.doubleaaexpress.views.MainView;
import org.doubleaaexpress.views.OrderManagerMainView;
import org.doubleaaexpress.views.forms.OrderManagerFormView;
import org.doubleaaexpress.views.forms.SignInFormView;

@NoArgsConstructor
@Getter
@Setter
public class SignInFormController {

    private Administrator administrator;
    private OrderManager orderManager;
    private Customer customer;
    private Buyer buyer;

    private MainView mainView;
    private SignInFormView sigInFormView;
    private AdministratorMainView administratorMainView;
    private OrderManagerMainView orderManagerMainView;
    private BuyerMainView buyerMainView;
    private OrderManagerFormView orderManagerFormView;

    public SignInFormController(Administrator administrator, OrderManager orderManager, Customer customer,
            Buyer buyer,
            MainView mainView, SignInFormView sigInFormView, AdministratorMainView administratorMainView,
            OrderManagerMainView orderManagerMainView, BuyerMainView buyerMainView,
            OrderManagerFormView orderManagerFormView) {

        this.administrator = administrator;
        this.orderManager = orderManager;
        this.customer = customer;
        this.buyer = buyer;

        this.mainView = mainView;
        this.sigInFormView = sigInFormView;
        this.administratorMainView = administratorMainView;
        this.orderManagerMainView = orderManagerMainView;
        this.buyerMainView = buyerMainView;
        this.orderManagerFormView = orderManagerFormView;
    }

    public void signIn() {
        AbstractFactoryDAO abstractFactoryDAO = new ConcreteAbstractFactoryDAO();
        GenericUserDAO<Administrator> administratorDAO = abstractFactoryDAO.getAdministratorDAO();
        GenericUserDAO<OrderManager> orderManagerDAO = abstractFactoryDAO.getOrderManagerDAO();
        GenericUserDAO<Customer> customerDAO = abstractFactoryDAO.getCustomerDAO();
        GenericUserDAO<Buyer> buyerDAO = abstractFactoryDAO.getBuyerDAO();

        String email = getSigInFormView().getTfEmail().getText();
        String password = new String(getSigInFormView().getTfPassword().getPassword());

        if (administratorDAO.get(email, password)) {
            getSigInFormView().setVisible(false);
            getSigInFormView().getTfEmail().setText("");
            getSigInFormView().getTfPassword().setText("");
            getSigInFormView().getTfEmail().requestFocus();
            getAdministratorMainView().setVisible(true);

        } else if (orderManagerDAO.get(email, password)) {
            getSigInFormView().setVisible(false);
            getSigInFormView().getTfEmail().setText("");
            getSigInFormView().getTfPassword().setText("");
            getSigInFormView().getTfEmail().requestFocus();
            getOrderManagerMainView().setVisible(true);

        } else if (buyerDAO.get(email, password)) {
            // the logged in user is assigned
            buyer = buyerDAO.getUser(email, password);
            
            getSigInFormView().setVisible(false);
            getSigInFormView().getTfEmail().setText("");
            getSigInFormView().getTfPassword().setText("");
            getSigInFormView().getTfEmail().requestFocus();
            getBuyerMainView().setVisible(true);
        } else if (customerDAO.get(email, password)) {
            getSigInFormView().setVisible(false);
            getSigInFormView().getTfEmail().setText("");
            getSigInFormView().getTfPassword().setText("");
            getSigInFormView().getTfEmail().requestFocus();
        } else if (email.isBlank() || password.isBlank()) {
            JOptionPane.showMessageDialog(null, "You must fill out all fields");
        } else {
            JOptionPane.showMessageDialog(null, "Incorrect email or password", "Error", JOptionPane.ERROR_MESSAGE);
            getSigInFormView().getTfPassword().setText("");
            getSigInFormView().getTfPassword().requestFocus();
        }
    }
}
