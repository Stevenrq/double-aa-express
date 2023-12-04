package org.doubleaaexpress.controllers;

import org.doubleaaexpress.controllers.forms.AdministratorFormController;
import org.doubleaaexpress.controllers.forms.CustomerFormController;
import org.doubleaaexpress.controllers.forms.MotorcycleFormController;
import org.doubleaaexpress.models.Administrator;
import org.doubleaaexpress.models.Buyer;
import org.doubleaaexpress.models.Customer;
import org.doubleaaexpress.models.Mechanic;
import org.doubleaaexpress.models.Motorcycle;
import org.doubleaaexpress.models.Seller;
import org.doubleaaexpress.views.AdministratorMainView;
import org.doubleaaexpress.views.MainView;
import org.doubleaaexpress.views.RegisteredUsersView;
import org.doubleaaexpress.views.forms.AdministratorFormView;
import org.doubleaaexpress.views.forms.BuyerFormView;
import org.doubleaaexpress.views.forms.CustomerFormView;
import org.doubleaaexpress.views.forms.SignInView;
import org.doubleaaexpress.views.tables.RegisteredAdministratorsTableView;
import org.doubleaaexpress.views.forms.MechanicFormView;
import org.doubleaaexpress.views.forms.MotorcycleFormView;
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
    private Buyer buyer;
    private Customer customer;
    private Mechanic mechanic;
    private Seller seller;

    private MainView mainView;
    private SignInView signInView;
    private AdministratorFormView administratorFormView;
    private BuyerFormView buyerFormView;
    private CustomerFormView customerFormView;
    private MechanicFormView mechanicFormView;
    private SellerFormView sellerFormView;
    private MotorcycleFormView motorcycleFormView;
    private RegisteredAdministratorsTableView registeredAdministratorsTableView;
    private AdministratorMainView administratorMainView;
    private RegisteredUsersView registeredUsersView;

    private AdministratorFormController administratorFormController;
    private CustomerFormController customerFormController;
    private SignInController signInController;
    private AdministratorMainController administratorMainController;
    private MotorcycleFormController motorcycleFormController;

    public MainController(Administrator administrator, Buyer buyer, Customer customer, Mechanic mechanic,
            Seller seller, MainView mainView, SignInView signInView, AdministratorFormView administratorFormView,
            BuyerFormView buyerFormView, CustomerFormView customerFormView, MechanicFormView mechanicFormView,
            SellerFormView sellerFormView, MotorcycleFormView motorcycleFormView,
            RegisteredAdministratorsTableView registeredAdministratorsTableView,
            AdministratorMainView administratorMainView, RegisteredUsersView registeredUsersView,
            AdministratorFormController administratorFormController,
            CustomerFormController customerFormController, SignInController signInController,
            AdministratorMainController administratorMainController,
            MotorcycleFormController motorcycleFormController) {

        this.administrator = administrator;
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
        this.registeredAdministratorsTableView = registeredAdministratorsTableView;
        this.administratorFormController = administratorFormController;
        this.customerFormController = customerFormController;
        this.signInController = signInController;
        this.administratorMainView = administratorMainView;
        this.registeredUsersView = registeredUsersView;
        this.administratorMainController = administratorMainController;
        this.motorcycleFormController = motorcycleFormController;

        // main view
        this.mainView.getbSignIn().addActionListener(this);
        this.mainView.getmSignUp().addActionListener(this);
        this.mainView.getMiRegisterAdministrator().addActionListener(this);
        this.mainView.getMiRegisterBuyer().addActionListener(this);
        this.mainView.getMiRegisterCustomer().addActionListener(this);
        this.mainView.getMiRegisterMechanic().addActionListener(this);
        this.mainView.getMiRegisterSeller().addActionListener(this);

        // form views
        this.administratorFormView.getbSignUp().addActionListener(this);
        this.customerFormView.getbSignUp().addActionListener(this);
        this.motorcycleFormView.getbRegister().addActionListener(this);

        // sign in view
        this.signInView.getbSignIn().addActionListener(this);

        // administrator main view
        this.administratorMainView.getbShowRegisteredUsers().addActionListener(this);
        this.administratorMainView.getbRegisterMotorcycle().addActionListener(this);
        this.registeredUsersView.getCbRegisteredUsers().addActionListener(this);
        this.registeredUsersView.getbSeeRegisteredUsers().addActionListener(this);
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

    @Override
    public void actionPerformed(ActionEvent e) {

        // show form views
        if (e.getSource() == getMainView().getMiRegisterAdministrator()) {
            showAdministratorFormView();
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
            getAdministratorFormController().signUpAdministrator();
        }

        if (e.getSource() == getCustomerFormView().getbSignUp()) {
            getCustomerFormController().signUpCustomer();
        }

        if (e.getSource() == getMotorcycleFormView().getbRegister()) {
            getMotorcycleFormController().signUpMotorcycle();
        }

        // sign in
        if (e.getSource() == getSignInView().getbSignIn()) {
            getSignInController().administratorSignIn();
        }

        // show registered users view
        if (e.getSource() == getAdministratorMainView().getbShowRegisteredUsers()) {
            showRegisteredUsersView();
        }

        // show register tables
        if (e.getSource() == getRegisteredUsersView().getbSeeRegisteredUsers()) {
            getAdministratorMainController().showRegisteredUsersTables();
        }
    }
}
