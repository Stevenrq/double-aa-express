package org.doubleaaexpress;

import org.doubleaaexpress.controllers.AdministratorMainController;
import org.doubleaaexpress.controllers.CustomerMainController;
import org.doubleaaexpress.controllers.MainController;
import org.doubleaaexpress.controllers.OrderController;
import org.doubleaaexpress.controllers.forms.SignInFormController;
import org.doubleaaexpress.controllers.forms.AdministratorFormController;
import org.doubleaaexpress.controllers.forms.CustomerFormController;
import org.doubleaaexpress.controllers.forms.MotorcycleFormController;
import org.doubleaaexpress.controllers.forms.OrderManagerFormController;
import org.doubleaaexpress.models.*;
import org.doubleaaexpress.views.*;
import org.doubleaaexpress.views.forms.*;
import org.doubleaaexpress.views.tables.RegisteredAdministratorsTableView;
import org.doubleaaexpress.views.tables.RegisteredCustomersTableView;
import org.doubleaaexpress.views.tables.RegisteredMotorcyclesTableView;
import org.doubleaaexpress.views.tables.RegisteredOrderManagersTableView;
import org.doubleaaexpress.views.tables.RegisteredOrdersTableView;

public class App {

        public static void main(String[] args) {

                // models
                Administrator administrator = new Administrator();
                OrderManager orderManager = new OrderManager();
                Buyer buyer = new Buyer();
                Customer customer = new Customer();
                Mechanic mechanic = new Mechanic();
                Seller seller = new Seller();
                Motorcycle motorcycle = new Motorcycle();

                // views
                MainView mainView = new MainView();
                SignInFormView signInView = new SignInFormView();
                AdministratorMainView administratorMainView = new AdministratorMainView();
                OrderManagerMainView orderManagerMainView = new OrderManagerMainView();
                CustomerMainView customerMainView = new CustomerMainView();
                AdministratorFormView administratorFormView = new AdministratorFormView();
                RegisteredUsersView registeredUsersView = new RegisteredUsersView();
                BuyerFormView buyerFormView = new BuyerFormView();
                CustomerFormView customerFormView = new CustomerFormView();
                MechanicFormView mechanicFormView = new MechanicFormView();
                SellerFormView sellerFormView = new SellerFormView();
                MotorcycleFormView motorcycleFormView = new MotorcycleFormView();
                OrderManagerFormView orderManagerFormView = new OrderManagerFormView();
                RegisteredAdministratorsTableView registeredAdministratorsTableView = new RegisteredAdministratorsTableView();
                RegisteredOrderManagersTableView registeredOrderManagersTableView = new RegisteredOrderManagersTableView();
                RegisteredCustomersTableView registeredCustomersTableView = new RegisteredCustomersTableView();
                RegisteredMotorcyclesTableView registeredMotorcyclesTableView = new RegisteredMotorcyclesTableView();
                RegisteredOrdersTableView registeredOrdersTableView = new RegisteredOrdersTableView();

                // controllers
                AdministratorFormController administratorFormController = new AdministratorFormController(administrator,
                                mainView, administratorFormView, registeredAdministratorsTableView);

                OrderManagerFormController orderManagerFormController = new OrderManagerFormController(orderManager,
                                mainView, orderManagerFormView, registeredOrderManagersTableView);

                CustomerFormController customerFormController = new CustomerFormController(customer, mainView,
                                customerFormView, registeredCustomersTableView);

                SignInFormController signInController = new SignInFormController(administrator, orderManager, customer,
                                mainView, signInView, administratorMainView, orderManagerMainView, customerMainView,
                                orderManagerFormView);

                AdministratorMainController administratorMainController = new AdministratorMainController(administrator,
                                orderManager, customer, motorcycle, mainView, administratorMainView,
                                registeredUsersView,
                                registeredAdministratorsTableView, registeredOrderManagersTableView,
                                registeredCustomersTableView,
                                registeredMotorcyclesTableView);

                CustomerMainController customerMainController = new CustomerMainController(customer, motorcycle,
                                mainView,
                                customerMainView, registeredMotorcyclesTableView);

                MotorcycleFormController motorcycleFormController = new MotorcycleFormController(administrator,
                                motorcycle, administratorMainView, motorcycleFormView, registeredMotorcyclesTableView);

                OrderController orderController = new OrderController(customer, motorcycle,
                                registeredMotorcyclesTableView, registeredOrdersTableView);

                MainController mainController = new MainController(administrator, orderManager, buyer, customer,
                                mechanic, seller, mainView, signInView, administratorFormView, buyerFormView,
                                customerFormView, mechanicFormView,
                                sellerFormView, motorcycleFormView, orderManagerFormView,
                                registeredAdministratorsTableView,
                                administratorMainView, customerMainView, registeredUsersView,
                                registeredMotorcyclesTableView,
                                administratorFormController,
                                customerFormController, signInController,
                                administratorMainController, motorcycleFormController, orderManagerFormController,
                                customerMainController, orderController);

                mainController.showMainView();
        }
}
