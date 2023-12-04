package org.doubleaaexpress.controllers.forms;

import javax.swing.JOptionPane;

import org.doubleaaexpress.models.Administrator;
import org.doubleaaexpress.models.Motorcycle;
import org.doubleaaexpress.models.dao.MotorcycleDAO;
import org.doubleaaexpress.views.AdministratorMainView;
import org.doubleaaexpress.views.forms.MotorcycleFormView;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class MotorcycleFormController {

    private Administrator administrator;
    private Motorcycle motorcycle;

    private AdministratorMainView administratorMainView;
    private MotorcycleFormView motorcycleFormView;

    public MotorcycleFormController(Administrator administrator, Motorcycle motorcycle,
            AdministratorMainView administratorMainView, MotorcycleFormView motorcycleFormView) {

        this.administrator = administrator;
        this.motorcycle = motorcycle;
        this.administratorMainView = administratorMainView;
        this.motorcycleFormView = motorcycleFormView;
    }

    public void signUpMotorcycle() {
        long id = 0L;
        String name = "", model = "", plateNumber = "", status = "";
        double price = 0;
        short year = 0;
        boolean v = false;

        if (emptyFields()) {
            JOptionPane.showMessageDialog(null, "The fields cannot be empty");
        } else {
            try {
                id = Long.parseLong(getMotorcycleFormView().getTfId().getText());
                name = getMotorcycleFormView().getTfName().getText();
                price = Double.parseDouble(getMotorcycleFormView().getTfId().getText());
                model = getMotorcycleFormView().getTfModel().getText();
                year = Short.parseShort(getMotorcycleFormView().getTfYear().getText());
                plateNumber = getMotorcycleFormView().getTfPlateNumber().getText();
                status = getMotorcycleFormView().getTfStatus().getText();
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Incorrect number format");
                return;
            }
            v = true;
            getMotorcycleFormView().setVisible(false);
            cleanFields();
        }

        if (v) {
            Motorcycle motorcycle = new Motorcycle(id, name, price, model, year, plateNumber, status);
            MotorcycleDAO motorcycleDAO = new MotorcycleDAO();
            motorcycleDAO.addMotorcycle(motorcycle);
        }
    }

    public boolean emptyFields() {
        return getMotorcycleFormView().getTfId().getText().isBlank() ||
                getMotorcycleFormView().getTfName().getText().isBlank() ||
                getMotorcycleFormView().getTfPrice().getText().isBlank() ||
                getMotorcycleFormView().getTfModel().getText().isBlank() ||
                getMotorcycleFormView().getTfYear().getText().isBlank() ||
                getMotorcycleFormView().getTfPlateNumber().getText().isBlank() ||
                getMotorcycleFormView().getTfStatus().getText().isBlank();
    }

    public void cleanFields() {
        getMotorcycleFormView().getTfId().setText("");
        getMotorcycleFormView().getTfName().setText("");
        getMotorcycleFormView().getTfPrice().setText("");
        getMotorcycleFormView().getTfModel().setText("");
        getMotorcycleFormView().getTfYear().setText("");
        getMotorcycleFormView().getTfPlateNumber().setText("");
        getMotorcycleFormView().getTfStatus().setText("");
    }
}
