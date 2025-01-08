package com.lutfilahdzaky.hospitalapp;

import com.formdev.flatlaf.FlatClientProperties;
import com.formdev.flatlaf.FlatLaf;
import com.formdev.flatlaf.fonts.roboto.FlatRobotoFont;
import raven.modal.Drawer;

import com.lutfilahdzaky.hospitalapp.system.FormManager;
import com.lutfilahdzaky.hospitalapp.utils.AppPreferences;
import com.lutfilahdzaky.hospitalapp.view.menu.DrawerBuilder;

import javax.swing.*;
import java.awt.*;

/**
 *
 * @author Lutfilah Dzaky
 */
public class HospitalManagementApp extends JFrame {

    public HospitalManagementApp() {
        init();
    }

    private void init() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getRootPane().putClientProperty(FlatClientProperties.FULL_WINDOW_CONTENT, true);
        Drawer.installDrawer(this, new DrawerBuilder());
        FormManager.install(this);
        setSize(new Dimension(1366, 768));
        setLocationRelativeTo(null);
    }

    public static void main(String[] args) {
        AppPreferences.init();
        FlatRobotoFont.install();
        FlatLaf.registerCustomDefaultsSource("com.lutfilahdzaky.hospitalapp.themes");
        UIManager.put("defaultFont", new Font(FlatRobotoFont.FAMILY, Font.PLAIN, 13));
        AppPreferences.setupLaf();
        EventQueue.invokeLater(() -> new HospitalManagementApp().setVisible(true));
    }
}
