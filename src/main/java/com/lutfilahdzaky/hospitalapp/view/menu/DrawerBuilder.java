package com.lutfilahdzaky.hospitalapp.view.menu;

import com.formdev.flatlaf.FlatClientProperties;
import com.lutfilahdzaky.hospitalapp.view.*;
import com.lutfilahdzaky.hospitalapp.system.AllForms;
import com.lutfilahdzaky.hospitalapp.system.Form;
import com.lutfilahdzaky.hospitalapp.system.FormManager;

import raven.modal.drawer.DrawerPanel;
import raven.modal.drawer.data.Item;
import raven.modal.drawer.data.MenuItem;
import raven.modal.drawer.menu.*;
import raven.modal.drawer.renderer.DrawerStraightDotLineStyle;
import raven.modal.drawer.simple.SimpleDrawerBuilder;
import raven.modal.drawer.simple.footer.LightDarkButtonFooter;
import raven.modal.drawer.simple.footer.SimpleFooterData;
import raven.modal.drawer.simple.header.SimpleHeaderData;
import raven.extras.AvatarIcon;
import raven.modal.option.Option;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;

public class DrawerBuilder extends SimpleDrawerBuilder {

    private final int SHADOW_SIZE = 12;

    public DrawerBuilder() {
        super(createSimpleMenuOption());
        LightDarkButtonFooter lightDarkButtonFooter = (LightDarkButtonFooter) footer;
        lightDarkButtonFooter.addModeChangeListener(isDarkMode -> {
            // event for light dark mode changed
        });
    }

    @Override
    public SimpleHeaderData getSimpleHeaderData() {
        AvatarIcon icon = new AvatarIcon(getClass().getResource("/com/lutfilahdzaky/hospitalapp/drawer/image/profile.png"), 50, 50, 3.5f);
        icon.setType(AvatarIcon.Type.MASK_SQUIRCLE);
        icon.setBorder(2, 2);

        changeAvatarIconBorderColor(icon);

        UIManager.addPropertyChangeListener(evt -> {
            if (evt.getPropertyName().equals("lookAndFeel")) {
                changeAvatarIconBorderColor(icon);
            }
        });

        return new SimpleHeaderData()
                .setIcon(icon)
                .setTitle("Lutfilah Dzaky")
                .setDescription("@lutfilahdzaky");
    }

    private void changeAvatarIconBorderColor(AvatarIcon icon) {
        icon.setBorderColor(new AvatarIcon.BorderColor(UIManager.getColor("Component.accentColor"), 0.7f));
    }

    @Override
    public SimpleFooterData getSimpleFooterData() {
        return new SimpleFooterData()
                .setTitle("Hospital Management Application");
    }


    public static MenuOption createSimpleMenuOption() {

        // create simple menu option
        MenuOption simpleMenuOption = new MenuOption();

        MenuItem items[] = new MenuItem[]{
                new Item.Label("MAIN"),
                new Item("Dashboard", "dashboard.svg", DashboardForm.class),
                new Item.Label("OTHER"),
                new Item("Setting", "setting.svg", SettingForm.class),
                new Item("About", "about.svg"),
                new Item("Logout", "logout.svg")
        };

        simpleMenuOption.setMenuStyle(new MenuStyle() {

            @Override
            public void styleMenu(JComponent component) {
                component.putClientProperty(FlatClientProperties.STYLE, getDrawerBackgroundStyle());
            }
        });

        simpleMenuOption.getMenuStyle().setDrawerLineStyleRenderer(new DrawerStraightDotLineStyle());
        simpleMenuOption.setMenuItemAutoSelectionMode(MenuOption.MenuItemAutoSelectionMode.SELECT_SUB_MENU_LEVEL);
        simpleMenuOption.addMenuEvent(new MenuEvent() {
            @Override
            public void selected(MenuAction action, int[] index) {
                System.out.println("Drawer menu selected " + Arrays.toString(index));
                Class<?> itemClass = action.getItem().getItemClass();
                String itemName = action.getItem().getName();

                switch (itemName) {
                    case "About":
                        action.consume();
                        FormManager.showAbout();
                        return;
                    case "Logout":
                        action.consume();
                        FormManager.logout();
                        return;
                    default:
                        if (itemClass == null || !Form.class.isAssignableFrom(itemClass)) {
                            action.consume();
                            return;
                        }
                        Class<? extends Form> formClass = (Class<? extends Form>) itemClass;
                        FormManager.showForm(AllForms.getForm(formClass));
                }
            }
        });

        simpleMenuOption.setMenus(items)
                .setBaseIconPath("com/lutfilahdzaky/hospitalapp/drawer/icon")
                .setIconScale(0.45f);

        return simpleMenuOption;
    }

    @Override
    public int getDrawerWidth() {
        return 270 + SHADOW_SIZE;
    }

    @Override
    public int getDrawerCompactWidth() {
        return 80 + SHADOW_SIZE;
    }

    @Override
    public int getOpenDrawerAt() {
        return 1000;
    }

    @Override
    public Option getOption() {
        Option option = super.getOption();
        option.setOpacity(0.3f);
        option.getBorderOption()
                .setShadowSize(new Insets(0, 0, 0, SHADOW_SIZE));
        return option;
    }

    @Override
    public boolean openDrawerAtScale() {
        return false;
    }

    @Override
    public void build(DrawerPanel drawerPanel) {
        drawerPanel.putClientProperty(FlatClientProperties.STYLE, getDrawerBackgroundStyle());
    }

    private static String getDrawerBackgroundStyle() {
        return "" +
                "[light]background:tint($Panel.background,20%);" +
                "[dark]background:tint($Panel.background,5%);";
    }
}
