package com.vv.idea.drink.com.vv.idea.drink.action;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.ui.Messages;
import com.vv.idea.drink.com.vv.idea.drink.handler.PropertiesHandler;

import static com.vv.idea.drink.com.vv.idea.drink.constant.Constants.YOUR_NAME;

/**
 * @description:
 * @creator vv
 * @date 2021/6/11 13:55
 */
public class SetNameAction extends AnAction {

    @Override
    public void actionPerformed(AnActionEvent e) {
        String beforeName = PropertiesHandler.getAppProp().getValue(YOUR_NAME);
        String showInputDialog = Messages.showInputDialog("Input your name,please!",
                "Drink Setting", null, beforeName, null);
        PropertiesHandler.getAppProp().setValue(YOUR_NAME, showInputDialog);
    }
}
