package org.doubleaaexpress;

import org.doubleaaexpress.controllers.AdministratorMainController;
import org.doubleaaexpress.controllers.MainController;
import org.doubleaaexpress.controllers.SignInController;
import org.doubleaaexpress.controllers.forms.AdministratorFormController;
import org.doubleaaexpress.controllers.forms.CustomerFormController;
import org.doubleaaexpress.models.*;
import org.doubleaaexpress.views.AdministratorMainView;
import org.doubleaaexpress.views.MainView;
import org.doubleaaexpress.views.RegisteredUsersView;
import org.doubleaaexpress.views.forms.*;
import org.doubleaaexpress.views.tables.RegisteredAdministratorsTableView;

public class App {

    public static void main(String[] args) {
        // models
        Administrator administrator = new Administrator();
        Buyer buyer = new Buyer();
        Customer customer = new Customer();
        Mechanic mechanic = new Mechanic();
        Seller seller = new Seller();

        // views
        MainView mainView = new MainView();
        SignInView signInView = new SignInView();
        AdministratorFormView administratorFormView = new AdministratorFormView();
        BuyerFormView buyerFormView = new BuyerFormView();
        CustomerFormView customerFormView = new CustomerFormView();
        MechanicFormView mechanicFormView = new MechanicFormView();
        SellerFormView sellerFormView = new SellerFormView();
        RegisteredAdministratorsTableView registeredAdministratorsTableView = new RegisteredAdministratorsTableView();
        AdministratorMainView administratorMainView = new AdministratorMainView();
        RegisteredUsersView registeredUsersView = new RegisteredUsersView();

        // controllers
        AdministratorFormController administratorFormController = new AdministratorFormController(administrator,
                mainView, administratorFormView, registeredAdministratorsTableView);

        CustomerFormController customerFormController = new CustomerFormController(customer, mainView, customerFormView);

        SignInController signInController = new SignInController(administrator, customer, mainView, signInView, administratorMainView);

        AdministratorMainController administratorMainController = new AdministratorMainController(administrator,
                mainView, administratorMainView, registeredAdministratorsTableView, registeredUsersView);

        MainController mainController = new MainController(administrator, buyer, customer, mechanic, seller, mainView,
                signInView, administratorFormView, buyerFormView, customerFormView, mechanicFormView,
                sellerFormView, registeredAdministratorsTableView, administratorMainView, registeredUsersView,
                administratorFormController, customerFormController, signInController, administratorMainController);

        mainController.showMainView();
    }
}
