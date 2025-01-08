package com.lutfilahdzaky.hospitalapp.view;

import net.miginfocom.swing.MigLayout;
import com.lutfilahdzaky.hospitalapp.system.Form;
import com.lutfilahdzaky.hospitalapp.utils.SystemForm;

import javax.swing.*;

@SystemForm(name = "Dashboard", description = "dashboard form display some details")
public class DashboardForm extends Form {

    public DashboardForm() {
        init();
    }

    private void init() {
        setLayout(new MigLayout("al center center"));
        JLabel text = new JLabel("Dashboard");
        add(text);
    }
}
