package org.doubleaaexpress.controllers;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.doubleaaexpress.models.Administrator;
import org.doubleaaexpress.models.Customer;
import org.doubleaaexpress.models.dao.abstractfactory.AbstractFactoryDAO;
import org.doubleaaexpress.models.dao.abstractfactory.ConcreteAbstractFactoryDAO;
import org.doubleaaexpress.models.dao.abstractfactory.GenericDAO;
import org.doubleaaexpress.views.AdministratorMainView;
import org.doubleaaexpress.views.MainView;
import org.doubleaaexpress.views.forms.SignInView;

@NoArgsConstructor
@Getter
@Setter
public class SignInController {

    private Administrator administrator;
    private Customer customer;

    private MainView mainView;
    private SignInView signInView;
    private AdministratorMainView administratorMainView;

    public SignInController(Administrator administrator, Customer customer, MainView mainView, SignInView signInView,
                            AdministratorMainView administratorMainView) {
        this.administrator = administrator;
        this.customer = customer;

        this.mainView = mainView;
        this.signInView = signInView;
        this.administratorMainView = administratorMainView;
    }

    public void administratorSignIn() {
        String email = getSignInView().getTfEmail().getText();
        String password = new String(getSignInView().getTfPassword().getPassword());
        AbstractFactoryDAO abstractFactoryDAO = new ConcreteAbstractFactoryDAO();
        GenericDAO<Administrator> administratorDAO = abstractFactoryDAO.getAdministratorDAO();

        if (administratorDAO.get(email, password)) {
            getSignInView().setVisible(false);
            getSignInView().getTfEmail().setText("");
            getSignInView().getTfPassword().setText("");
            getSignInView().getTfEmail().requestFocus();
            getAdministratorMainView().setVisible(true);
        }
    }
}
