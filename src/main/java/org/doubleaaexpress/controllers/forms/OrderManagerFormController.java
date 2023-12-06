package org.doubleaaexpress.controllers.forms;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import javax.swing.JOptionPane;

import org.doubleaaexpress.models.OrderManager;
import org.doubleaaexpress.models.dao.abstractfactory.AbstractFactoryDAO;
import org.doubleaaexpress.models.dao.abstractfactory.ConcreteAbstractFactoryDAO;
import org.doubleaaexpress.models.dao.abstractfactory.GenericUserDAO;
import org.doubleaaexpress.views.MainView;
import org.doubleaaexpress.views.forms.OrderManagerFormView;
import org.doubleaaexpress.views.tables.RegisteredOrderManagersTableView;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class OrderManagerFormController {

    private OrderManager orderManager;

    private MainView mainView;
    private OrderManagerFormView orderManagerFormView;
    private RegisteredOrderManagersTableView registeredOrderManagersTableView;

    public OrderManagerFormController(OrderManager orderManager, MainView mainView,
            OrderManagerFormView orderManagerFormView,
            RegisteredOrderManagersTableView registeredOrderManagersTableView) {

        this.orderManager = orderManager;
        this.mainView = mainView;
        this.orderManagerFormView = orderManagerFormView;
        this.registeredOrderManagersTableView = registeredOrderManagersTableView;
    }

    public void signUpOrderManager() {
        long id = 0L;
        String firstName = "", lastName = "", phoneNumber = "", address = "", email = "", password = "";
        LocalDate birthDate = null, dateToLocalDate;
        boolean v;
        Date d = getOrderManagerFormView().getDcBirthDate().getDate();
        AbstractFactoryDAO abstractFactoryDAO = new ConcreteAbstractFactoryDAO();
        GenericUserDAO<OrderManager> orderManagerDAO = abstractFactoryDAO.getOrderManagerDAO();

        if (emptyFields()) {
            v = false;
            JOptionPane.showMessageDialog(null, "The fields cannot be empty");
        } else {
            try {
                id = Long.parseLong(getOrderManagerFormView().getTfId().getText());
                firstName = getOrderManagerFormView().getTfFirstName().getText();
                lastName = getOrderManagerFormView().getTfLastName().getText();
                phoneNumber = getOrderManagerFormView().getTfPhoneNumber().getText();
                address = getOrderManagerFormView().getTfAddress().getText();
                dateToLocalDate = d.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                birthDate = dateToLocalDate;
                email = getOrderManagerFormView().getTfEmail().getText();
                password = new String(getOrderManagerFormView().getPfPassword().getPassword());
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "The id must be just numbers");
                return;
            }
            v = true;
            getOrderManagerFormView().setVisible(false);
            cleanFields();
        }

        if (v) {
            OrderManager orderManager = new OrderManager(id, firstName, lastName, phoneNumber, address, birthDate,
                    email, password);
            orderManagerDAO.add(orderManager);
            orderManager
                    .populateAdministratorTable(getRegisteredOrderManagersTableView().gettRegisteredOrderManagers());
        }
    }

    public void cleanFields() {
        getOrderManagerFormView().getTfId().setText("");
        getOrderManagerFormView().getTfFirstName().setText("");
        getOrderManagerFormView().getTfLastName().setText("");
        getOrderManagerFormView().getTfPhoneNumber().setText("");
        getOrderManagerFormView().getTfAddress().setText("");
        getOrderManagerFormView().getDcBirthDate().setDate(null);
        getOrderManagerFormView().getTfEmail().setText("");
        getOrderManagerFormView().getPfPassword().setText("");
    }

    public boolean emptyFields() {
        return getOrderManagerFormView().getTfId().getText().isBlank() ||
                getOrderManagerFormView().getTfFirstName().getText().isBlank() ||
                getOrderManagerFormView().getTfLastName().getText().isBlank() ||
                getOrderManagerFormView().getTfPhoneNumber().getText().isBlank() ||
                getOrderManagerFormView().getTfAddress().getText().isBlank() ||
                getOrderManagerFormView().getDcBirthDate().getDate() == null ||
                getOrderManagerFormView().getTfEmail().getText().isBlank() ||
                getOrderManagerFormView().getPfPassword().getPassword().length == 0;
    }
}
