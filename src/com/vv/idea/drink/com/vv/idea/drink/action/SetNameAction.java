package com.vv.idea.drink.com.vv.idea.drink.action;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.ui.Messages;
import com.vv.idea.drink.com.vv.idea.drink.handler.PropertiesHandler;

import static com.vv.idea.drink.com.vv.idea.drink.constant.Constants.YOUR_NAME;

/**
 * @description:
 * @creator zhangwei73
 * @date 2021/6/11 13:55
 */
public class SetNameAction extends AnAction {

    @Override
    public void actionPerformed(AnActionEvent e) {
        String showInputDialog = Messages.showInputDialog("请输入您的昵称", "Drink Setting", null);
        PropertiesHandler.getAppProp().setValue(YOUR_NAME, showInputDialog);
    }
}
