package org.doubleaaexpress.controllers.forms;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import javax.swing.JOptionPane;

import org.doubleaaexpress.models.Buyer;
import org.doubleaaexpress.models.dao.abstractfactory.AbstractFactoryDAO;
import org.doubleaaexpress.models.dao.abstractfactory.ConcreteAbstractFactoryDAO;
import org.doubleaaexpress.models.dao.abstractfactory.GenericUserDAO;
import org.doubleaaexpress.views.MainView;
import org.doubleaaexpress.views.forms.BuyerFormView;
import org.doubleaaexpress.views.tables.RegisteredBuyersTableView;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class BuyerFormController {

    private Buyer buyer;

    private MainView mainView;
    private BuyerFormView buyerFormView;
    private RegisteredBuyersTableView registeredBuyersTableView;

    public BuyerFormController(Buyer buyer, MainView mainView, BuyerFormView buyerFormView,
            RegisteredBuyersTableView registeredBuyersTableView) {
        this.buyer = buyer;
        this.mainView = mainView;
        this.buyerFormView = buyerFormView;
        this.registeredBuyersTableView = registeredBuyersTableView;
    }

    public void signUpBuyer() {
        long id = 0L;
        String firstName = "", lastName = "", phoneNumber = "", address = "", email = "", password = "";
        LocalDate birthDate = null, dateToLocalDate;
        boolean v;
        Date d = getBuyerFormView().getDcBirthDate().getDate();

        // create a factory DAOs
        AbstractFactoryDAO abstractFactoryDAO = new ConcreteAbstractFactoryDAO();
        // get a DAO for Buyer
        GenericUserDAO<Buyer> buyerDAO = abstractFactoryDAO.getBuyerDAO();

        if (emptyFields()) {
            v = false;
            JOptionPane.showMessageDialog(null, "The fields cannot be empty");
        } else {
            try {
                id = Long.parseLong(getBuyerFormView().getTfId().getText());
                firstName = getBuyerFormView().getTfFirstName().getText();
                lastName = getBuyerFormView().getTfLastName().getText();
                phoneNumber = getBuyerFormView().getTfPhoneNumber().getText();
                address = getBuyerFormView().getTfAddress().getText();
                dateToLocalDate = d.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                birthDate = dateToLocalDate;
                email = getBuyerFormView().getTfEmail().getText();
                password = new String(getBuyerFormView().getPfPassword().getPassword());
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "The id must be just numbers");
                return;
            }
            v = true;
            getBuyerFormView().setVisible(false);
            cleanFields();
        }

        if (v) {
            Buyer buyer = new Buyer(id, firstName, lastName, phoneNumber, address, birthDate, email,
                    password);
            buyerDAO.add(buyer);
            buyer.populateBuyerTable(getRegisteredBuyersTableView().gettRegisteredBuyers());
        }
    }

    public void cleanFields() {
        getBuyerFormView().getTfId().setText("");
        getBuyerFormView().getTfFirstName().setText("");
        getBuyerFormView().getTfLastName().setText("");
        getBuyerFormView().getTfPhoneNumber().setText("");
        getBuyerFormView().getTfAddress().setText("");
        getBuyerFormView().getDcBirthDate().setDate(null);
        getBuyerFormView().getTfEmail().setText("");
        getBuyerFormView().getPfPassword().setText("");
    }

    public boolean emptyFields() {
        return getBuyerFormView().getTfId().getText().isBlank() ||
                getBuyerFormView().getTfFirstName().getText().isBlank() ||
                getBuyerFormView().getTfLastName().getText().isBlank() ||
                getBuyerFormView().getTfPhoneNumber().getText().isBlank() ||
                getBuyerFormView().getTfAddress().getText().isBlank() ||
                getBuyerFormView().getDcBirthDate().getDate() == null ||
                getBuyerFormView().getTfEmail().getText().isBlank() ||
                getBuyerFormView().getPfPassword().getPassword().length == 0;
    }
}
