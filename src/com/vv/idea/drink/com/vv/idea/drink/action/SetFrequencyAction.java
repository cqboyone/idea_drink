package com.vv.idea.drink.com.vv.idea.drink.action;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.ui.Messages;
import com.vv.idea.drink.com.vv.idea.drink.handler.PropertiesHandler;

import static com.vv.idea.drink.com.vv.idea.drink.constant.Constants.DRINK_INTERVAL_SECONDS;
import static com.vv.idea.drink.com.vv.idea.drink.job.DrinkJobHandler.safetyUpdateJob;

/**
 * @description:
 * @creator zhangwei73
 * @date 2021/6/17 11:43
 */
public class SetFrequencyAction extends AnAction {

    @Override
    public void actionPerformed(AnActionEvent e) {
        Integer newInterval = showInputDialog();
        if (newInterval == null) {
            return;
        }
        //转换成秒
        newInterval *= 60;
        //存储频率
        PropertiesHandler.getAppProp().setValue(DRINK_INTERVAL_SECONDS, newInterval, 0);
        safetyUpdateJob(newInterval);
    }

    public Integer showInputDialog() {
        try {
            Integer value = PropertiesHandler.getAppProp().getInt(DRINK_INTERVAL_SECONDS, 30 * 60);
            String frequency = Messages.showInputDialog("How many minutes to remind? \n Please input int 1 to 120! \n Setting will work after one minute!",
                    "Drink Setting", null, value / 60 + "", null);
            Integer integer = Integer.valueOf(frequency);
            if (integer >= 1 && integer <= 120) {
                return integer;
            }
            Messages.showErrorDialog("Must 1 to 120!", "Drink Setting Error");
        } catch (NumberFormatException exception) {
            Messages.showErrorDialog("Must be int!", "Drink Setting Error");
        }
        return null;
    }
}
