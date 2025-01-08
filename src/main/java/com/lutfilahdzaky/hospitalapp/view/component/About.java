package com.lutfilahdzaky.hospitalapp.view.component;

import com.formdev.flatlaf.FlatClientProperties;
import com.formdev.flatlaf.util.LoggingFacade;
import net.miginfocom.swing.MigLayout;
import com.lutfilahdzaky.hospitalapp.HospitalManagementApp;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.event.HyperlinkEvent;
import javax.swing.text.DefaultCaret;
import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;

public class About extends JPanel {

    public About() {
        init();
    }

    private void init() {
        setLayout(new MigLayout("fillx,wrap,insets 5 30 5 30,width 400", "[fill,330::]", ""));

        JTextPane title = createText("Hospital Management Application");
        title.putClientProperty(FlatClientProperties.STYLE, "" +
                "font:bold +5");

        JTextPane description = createText("");
        description.setContentType("text/html");
        description.setText(getDescriptionText());
        description.addHyperlinkListener(e -> {
            if (e.getEventType() == HyperlinkEvent.EventType.ACTIVATED) {
                showUrl(e.getURL());
            }
        });

        add(title);
        add(description);
        add(createSystemInformation());
    }

    private JTextPane createText(String text) {
        JTextPane textPane = new JTextPane();
        textPane.setBorder(BorderFactory.createEmptyBorder());
        textPane.setText(text);
        textPane.setEditable(false);
        textPane.setCaret(new DefaultCaret() {
            @Override
            public void paint(Graphics g) {
            }
        });
        return textPane;
    }

    private String getDescriptionText() {
        String text = "Hospital Management App is a desktop application designed to manage various aspects of a hospital's operations. It includes functionalities for handling patient records, doctor appointments, and medication prescriptions.<br><br>" +
                "The app allows hospital staff to create, read, update, and delete (CRUD) data related to patients, doctors, and appointments through a user-friendly interface. The application follows the Model-View-Controller (MVC) design pattern, ensuring that the business logic, user interface, and data handling are separated into distinct components.<br><br>" +
                "This project serves as an educational tool to learn how to implement CRUD operations in a real-world scenario while applying the MVC pattern to maintain clean and scalable code.<br><br>" +
                "For source code, visit the <a href=\"https://github.com/lutfilahdzaky/hospital-management-app/\">GitHub Project</a>.";

        return text;
    }

    private String getSystemInformationText() {
        String text = "<b>Java: </b>%s<br/>" +
                "<b>System: </b>%s<br/>";

        return text;
    }

    private JComponent createSystemInformation() {
        JPanel panel = new JPanel(new MigLayout("wrap"));
        panel.setBorder(new TitledBorder("System Information"));
        JTextPane textPane = createText("");
        textPane.setContentType("text/html");
        String java = System.getProperty("java.vendor") + " - v" + System.getProperty("java.version");
        String system = System.getProperty("os.name") + " " + System.getProperty("os.arch") + " - v" + System.getProperty("os.version");
        String text = String.format(getSystemInformationText(), java, system);
        textPane.setText(text);
        panel.add(textPane);
        return panel;
    }

    private void showUrl(URL url) {
        if (Desktop.isDesktopSupported()) {
            Desktop desktop = Desktop.getDesktop();
            if (desktop.isSupported(Desktop.Action.BROWSE)) {
                try {
                    desktop.browse(url.toURI());
                } catch (IOException | URISyntaxException e) {
                    LoggingFacade.INSTANCE.logSevere("Error browse url", e);
                }
            }
        }
    }
}