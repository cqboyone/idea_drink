package com.vv.idea.drink.com.vv.idea.drink.action;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.ui.Messages;
import com.vv.idea.drink.com.vv.idea.drink.constant.MyCache;
import com.vv.idea.drink.com.vv.idea.drink.enums.Enums;
import com.vv.idea.drink.com.vv.idea.drink.handler.PropertiesHandler;
import com.vv.idea.drink.com.vv.idea.drink.quartz.JobHandler;

import static com.vv.idea.drink.com.vv.idea.drink.constant.Constants.DRINK_JOB_GROUP;
import static com.vv.idea.drink.com.vv.idea.drink.constant.Constants.DRINK_JOB_NAME;
import static com.vv.idea.drink.com.vv.idea.drink.constant.Constants.DRINK_JOB_STATUS;

/**
 * @description:
 * @creator vv
 * @date 2021/6/17 14:54
 */
public class StartDrinkAction extends AnAction {

    @Override
    public void actionPerformed(AnActionEvent e) {
        int i = Messages.showYesNoDialog("Start drink?", "Drink Setting", null);
        if (Enums.YES_NO_DIALOG.YES.type.equals(i)) {
            PropertiesHandler.getAppProp().setValue(DRINK_JOB_STATUS, Enums.JOB_STATUS.RUNNING.type);
            new JobHandler(MyCache.scheduler).resumeJob(DRINK_JOB_NAME, DRINK_JOB_GROUP);
        }
    }
}
