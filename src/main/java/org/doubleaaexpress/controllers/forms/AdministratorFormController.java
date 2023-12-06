package org.doubleaaexpress.controllers.forms;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import javax.swing.JOptionPane;

import org.doubleaaexpress.models.Administrator;
import org.doubleaaexpress.models.dao.abstractfactory.AbstractFactoryDAO;
import org.doubleaaexpress.models.dao.abstractfactory.ConcreteAbstractFactoryDAO;
import org.doubleaaexpress.models.dao.abstractfactory.GenericUserDAO;
import org.doubleaaexpress.views.MainView;
import org.doubleaaexpress.views.forms.AdministratorFormView;
import org.doubleaaexpress.views.tables.RegisteredAdministratorsTableView;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class AdministratorFormController {

    private Administrator administrator;

    private MainView mainView;
    private AdministratorFormView administratorFormView;
    private RegisteredAdministratorsTableView registeredAdministratorsTableView;

    public AdministratorFormController(Administrator administrator, MainView mainView,
            AdministratorFormView administratorFormView,
            RegisteredAdministratorsTableView registeredAdministratorsTableView) {
        this.administrator = administrator;

        this.mainView = mainView;
        this.administratorFormView = administratorFormView;
        this.registeredAdministratorsTableView = registeredAdministratorsTableView;
    }

    public void signUpAdministrator() {
        long id = 0L;
        String firstName = "", lastName = "", phoneNumber = "", address = "", email = "", password = "";
        LocalDate birthDate = null, dateToLocalDate;
        boolean v;
        Date d = getAdministratorFormView().getDcBirthDate().getDate();
        // create a factory DAOs
        AbstractFactoryDAO abstractFactoryDAO = new ConcreteAbstractFactoryDAO();
        // get a DAO for Administrator
        GenericUserDAO<Administrator> administratorDAO = abstractFactoryDAO.getAdministratorDAO();

        if (emptyFields()) {
            v = false;
            JOptionPane.showMessageDialog(null, "The fields cannot be empty");
        } else {
            try {
                id = Long.parseLong(getAdministratorFormView().getTfId().getText());
                firstName = getAdministratorFormView().getTfFirstName().getText();
                lastName = getAdministratorFormView().getTfLastName().getText();
                phoneNumber = getAdministratorFormView().getTfPhoneNumber().getText();
                address = getAdministratorFormView().getTfAddress().getText();
                dateToLocalDate = d.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                birthDate = dateToLocalDate;
                email = getAdministratorFormView().getTfEmail().getText();
                password = new String(getAdministratorFormView().getPfPassword().getPassword());
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "The id must be just numbers");
                return;
            }
            v = true;
            getAdministratorFormView().setVisible(false);
            cleanFields();
        }

        if (v) {
            Administrator administrator = new Administrator(id, firstName, lastName, phoneNumber, address, birthDate,
                    email, password);
            administratorDAO.add(administrator);
            administrator
                    .populateAdministratorTable(getRegisteredAdministratorsTableView().gettRegisteredAdministrators());
        }
    }

    public void cleanFields() {
        getAdministratorFormView().getTfId().setText("");
        getAdministratorFormView().getTfFirstName().setText("");
        getAdministratorFormView().getTfLastName().setText("");
        getAdministratorFormView().getTfPhoneNumber().setText("");
        getAdministratorFormView().getTfAddress().setText("");
        getAdministratorFormView().getDcBirthDate().setDate(null);
        getAdministratorFormView().getTfEmail().setText("");
        getAdministratorFormView().getPfPassword().setText("");
    }

    public boolean emptyFields() {
        return getAdministratorFormView().getTfId().getText().isBlank() ||
                getAdministratorFormView().getTfFirstName().getText().isBlank() ||
                getAdministratorFormView().getTfLastName().getText().isBlank() ||
                getAdministratorFormView().getTfPhoneNumber().getText().isBlank() ||
                getAdministratorFormView().getTfAddress().getText().isBlank() ||
                getAdministratorFormView().getDcBirthDate().getDate() == null ||
                getAdministratorFormView().getTfEmail().getText().isBlank() ||
                getAdministratorFormView().getPfPassword().getPassword().length == 0;
    }
}
