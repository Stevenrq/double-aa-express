package org.doubleaaexpress.controllers;

import org.doubleaaexpress.controllers.forms.SignInFormController;
import org.doubleaaexpress.controllers.forms.AdministratorFormController;
import org.doubleaaexpress.controllers.forms.BuyerFormController;
import org.doubleaaexpress.controllers.forms.CustomerFormController;
import org.doubleaaexpress.controllers.forms.MotorcycleFormController;
import org.doubleaaexpress.controllers.forms.OrderManagerFormController;
import org.doubleaaexpress.models.Administrator;
import org.doubleaaexpress.models.Buyer;
import org.doubleaaexpress.models.Customer;
import org.doubleaaexpress.models.Mechanic;
import org.doubleaaexpress.models.OrderManager;
import org.doubleaaexpress.models.Seller;
import org.doubleaaexpress.views.AdministratorMainView;
import org.doubleaaexpress.views.BuyerMainView;
import org.doubleaaexpress.views.MainView;
import org.doubleaaexpress.views.RegisteredUsersView;
import org.doubleaaexpress.views.forms.AdministratorFormView;
import org.doubleaaexpress.views.forms.BuyerFormView;
import org.doubleaaexpress.views.forms.CustomerFormView;
import org.doubleaaexpress.views.forms.SignInFormView;
import org.doubleaaexpress.views.tables.RegisteredAdministratorsTableView;
import org.doubleaaexpress.views.tables.RegisteredMotorcyclesTableView;
import org.doubleaaexpress.views.forms.MechanicFormView;
import org.doubleaaexpress.views.forms.MotorcycleFormView;
import org.doubleaaexpress.views.forms.OrderManagerFormView;
import org.doubleaaexpress.views.forms.SellerFormView;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@NoArgsConstructor
@Getter
@Setter
public class MainController implements ActionListener {

    private Administrator administrator;
    private OrderManager orderManager;
    private Buyer buyer;
    private Customer customer;
    private Mechanic mechanic;
    private Seller seller;

    private MainView mainView;
    private SignInFormView signInView;
    private AdministratorFormView administratorFormView;
    private BuyerFormView buyerFormView;
    private CustomerFormView customerFormView;
    private MechanicFormView mechanicFormView;
    private SellerFormView sellerFormView;
    private MotorcycleFormView motorcycleFormView;
    private OrderManagerFormView orderManagerFormView;
    private RegisteredAdministratorsTableView registeredAdministratorsTableView;
    private AdministratorMainView administratorMainView;
    private BuyerMainView buyerMainView;
    private RegisteredUsersView registeredUsersView;
    private RegisteredMotorcyclesTableView registeredMotorcyclesTableView;

    private AdministratorFormController administratorFormController;
    private CustomerFormController customerFormController;
    private SignInFormController signInController;
    private AdministratorMainController administratorMainController;
    private MotorcycleFormController motorcycleFormController;
    private OrderManagerFormController orderManagerFormController;
    private BuyerMainController buyerMainController;
    private OrderController orderController;
    private BuyerFormController buyerFormController;

    public MainController(Administrator administrator, OrderManager orderManager, Buyer buyer, Customer customer,
            Mechanic mechanic, Seller seller, MainView mainView, SignInFormView signInView,
            AdministratorFormView administratorFormView,
            BuyerFormView buyerFormView, CustomerFormView customerFormView, MechanicFormView mechanicFormView,
            SellerFormView sellerFormView, MotorcycleFormView motorcycleFormView,
            OrderManagerFormView orderManagerFormView,
            RegisteredAdministratorsTableView registeredAdministratorsTableView,
            AdministratorMainView administratorMainView, BuyerMainView buyerMainView,
            RegisteredUsersView registeredUsersView, RegisteredMotorcyclesTableView registeredMotorcyclesTableView,
            AdministratorFormController administratorFormController,
            CustomerFormController customerFormController, SignInFormController signInController,
            AdministratorMainController administratorMainController,
            MotorcycleFormController motorcycleFormController, OrderManagerFormController orderManagerFormController,
            BuyerMainController buyerMainController, OrderController orderController,
            BuyerFormController buyerFormController) {

        this.administrator = administrator;
        this.orderManager = orderManager;
        this.buyer = buyer;
        this.customer = customer;
        this.mechanic = mechanic;
        this.seller = seller;
        this.mainView = mainView;
        this.signInView = signInView;
        this.administratorFormView = administratorFormView;
        this.buyerFormView = buyerFormView;
        this.customerFormView = customerFormView;
        this.mechanicFormView = mechanicFormView;
        this.sellerFormView = sellerFormView;
        this.motorcycleFormView = motorcycleFormView;
        this.orderManagerFormView = orderManagerFormView;
        this.registeredAdministratorsTableView = registeredAdministratorsTableView;
        this.administratorFormController = administratorFormController;
        this.customerFormController = customerFormController;
        this.signInController = signInController;
        this.administratorMainView = administratorMainView;
        this.buyerMainView = buyerMainView;
        this.registeredUsersView = registeredUsersView;
        this.registeredMotorcyclesTableView = registeredMotorcyclesTableView;
        this.administratorMainController = administratorMainController;
        this.motorcycleFormController = motorcycleFormController;
        this.orderManagerFormController = orderManagerFormController;
        this.buyerMainController = buyerMainController;
        this.orderController = orderController;
        this.buyerFormController = buyerFormController;

        // main view
        this.mainView.getbSignIn().addActionListener(this);
        this.mainView.getmSignUp().addActionListener(this);
        this.mainView.getMiRegisterAdministrator().addActionListener(this);
        this.mainView.getMiRegisterOrderManager().addActionListener(this);
        this.mainView.getMiRegisterBuyer().addActionListener(this);
        this.mainView.getMiRegisterCustomer().addActionListener(this);
        this.mainView.getMiRegisterMechanic().addActionListener(this);
        this.mainView.getMiRegisterSeller().addActionListener(this);

        // form views
        this.administratorFormView.getbSignUp().addActionListener(this);
        this.customerFormView.getbSignUp().addActionListener(this);
        this.motorcycleFormView.getbRegister().addActionListener(this);
        this.orderManagerFormView.getbSignUp().addActionListener(this);
        this.buyerFormView.getbSignUp().addActionListener(this);

        // sign in view
        this.signInView.getbSignIn().addActionListener(this);

        // administrator main view
        this.administratorMainView.getbShowRegisteredUsers().addActionListener(this);
        this.administratorMainView.getbRegisterMotorcycle().addActionListener(this);
        this.registeredUsersView.getCbRegisteredUsers().addActionListener(this);
        this.registeredUsersView.getbSeeRegisteredUsers().addActionListener(this);

        // customer main view
        this.getBuyerMainView().getbSeeMotorcycles().addActionListener(this);

        this.registeredMotorcyclesTableView.getbBuyMotorcycle().addActionListener(this);
    }

    // show views
    public void showMainView() {
        getMainView().setVisible(true);
    }

    public void showSignInView() {
        getSignInView().setVisible(true);
    }

    public void showAdministratorFormView() {
        getAdministratorFormView().setVisible(true);
    }

    public void showOrderManagerFormView() {
        getOrderManagerFormView().setVisible(true);
    }

    public void showBuyerFormView() {
        getBuyerFormView().setVisible(true);
    }

    public void showCustomerFormView() {
        getCustomerFormView().setVisible(true);
    }

    public void showMechanicFormView() {
        getMechanicFormView().setVisible(true);
    }

    public void showSellerFormView() {
        getSellerFormView().setVisible(true);
    }

    public void showMotorcycleFormView() {
        getMotorcycleFormView().setVisible(true);
    }

    public void showRegisteredUsersView() {
        getRegisteredUsersView().setVisible(true);
    }

    public void showRegisteredUsersTables() {
        getAdministratorMainController().showRegisteredUsersTables();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        // show form views
        if (e.getSource() == getMainView().getMiRegisterAdministrator()) {
            showAdministratorFormView();
        } else if (e.getSource() == getMainView().getMiRegisterOrderManager()) {
            showOrderManagerFormView();
        } else if (e.getSource() == getMainView().getMiRegisterBuyer()) {
            showBuyerFormView();
        } else if (e.getSource() == getMainView().getMiRegisterCustomer()) {
            showCustomerFormView();
        } else if (e.getSource() == getMainView().getMiRegisterMechanic()) {
            showMechanicFormView();
        } else if (e.getSource() == getMainView().getMiRegisterSeller()) {
            showSellerFormView();
        }

        if (e.getSource() == getAdministratorMainView().getbRegisterMotorcycle()) {
            showMotorcycleFormView();
        }

        if (e.getSource() == getMainView().getbSignIn()) {
            showSignInView();
        }

        // sign up
        if (e.getSource() == getAdministratorFormView().getbSignUp()) {
            administratorFormController.signUpAdministrator();
        }

        if (e.getSource() == getOrderManagerFormView().getbSignUp()) {
            orderManagerFormController.signUpOrderManager();
        }

        if (e.getSource() == getCustomerFormView().getbSignUp()) {
            customerFormController.signUpCustomer();
        }

        if (e.getSource() == getBuyerFormView().getbSignUp()) {
            buyerFormController.signUpBuyer();
        }

        if (e.getSource() == getMotorcycleFormView().getbRegister()) {
            motorcycleFormController.signUpMotorcycle();
        }

        // sign in
        if (e.getSource() == getSignInView().getbSignIn()) {
            signInController.signIn();
        }

        // show registered users view
        if (e.getSource() == getAdministratorMainView().getbShowRegisteredUsers()) {
            showRegisteredUsersView();
        }

        // show register tables
        if (e.getSource() == getRegisteredUsersView().getbSeeRegisteredUsers()) {
            showRegisteredUsersTables();
        }

        if (e.getSource() == getBuyerMainView().getbSeeMotorcycles()) {
            buyerMainController.showRegisteredMotorcyclesTable();
        }

        // register the order
        if (e.getSource() == getRegisteredMotorcyclesTableView().getbBuyMotorcycle()) {
            orderController.registerOrder();
        }
    }
}
