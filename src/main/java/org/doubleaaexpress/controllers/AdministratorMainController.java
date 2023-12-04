package org.doubleaaexpress.controllers;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.doubleaaexpress.models.Administrator;
import org.doubleaaexpress.views.AdministratorMainView;
import org.doubleaaexpress.views.MainView;
import org.doubleaaexpress.views.RegisteredUsersView;
import org.doubleaaexpress.views.tables.RegisteredAdministratorsTableView;

import javax.swing.*;
import java.util.Objects;

@NoArgsConstructor
@Getter
@Setter
public class AdministratorMainController {

    private Administrator administrator;

    private MainView mainView;
    private AdministratorMainView administratorMainView;
    private RegisteredAdministratorsTableView registeredAdministratorsTableView;
    private RegisteredUsersView registeredUsersView;

    public AdministratorMainController(Administrator administrator, MainView mainView,
                                       AdministratorMainView administratorMainView,
                                       RegisteredAdministratorsTableView registeredAdministratorsTableView,
                                       RegisteredUsersView registeredUsersView) {
        this.administrator = administrator;

        this.mainView = mainView;
        this.administratorMainView = administratorMainView;
        this.registeredAdministratorsTableView = registeredAdministratorsTableView;
        this.registeredUsersView = registeredUsersView;

        // populates the table when starting the application
        this.administrator.populateAdministratorTable(getRegisteredAdministratorsTableView().gettRegisteredAdministrators());
    }

    public void showRegisteredUsersTables() {
        if (Objects.equals(getRegisteredUsersView().getCbRegisteredUsers().getSelectedItem(), "Administrators")) {
            if (getRegisteredAdministratorsTableView().gettRegisteredAdministrators().getRowCount() == 0) {
                JOptionPane.showMessageDialog(null, "There are no registered administrators");
            }
            getRegisteredAdministratorsTableView().setVisible(true);
        } else if (Objects.equals(getRegisteredUsersView().getCbRegisteredUsers().getSelectedItem(), "Select")) {
            JOptionPane.showMessageDialog(null, "You must select an option");
        }
    }
}
