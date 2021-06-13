package com.vv.idea.drink.com.vv.idea.drink.action;

import com.intellij.ide.util.PropertiesComponent;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;

/**
 * @description:
 * @creator zhangwei73
 * @date 2021/6/11 13:55
 */
public class DrinkAction extends AnAction {

    public static final String DRINK_INTERVAL = "drink_interval";

    @Override
    public void actionPerformed(AnActionEvent e) {


        //获取 application 级别的 PropertiesComponent
        PropertiesComponent propertiesComponent = PropertiesComponent.getInstance();
        propertiesComponent.setValue(DRINK_INTERVAL, "10");
        System.out.println(propertiesComponent.getValue(DRINK_INTERVAL));

    }
}
