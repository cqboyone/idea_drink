package com.vv.idea.drink.com.vv.idea.drink.job;

import com.intellij.notification.Notification;
import com.intellij.notification.NotificationDisplayType;
import com.intellij.notification.NotificationGroup;
import com.intellij.notification.Notifications;
import com.intellij.openapi.ui.MessageType;
import com.vv.idea.drink.com.vv.idea.drink.handler.PropertiesHandler;
import org.apache.commons.lang3.StringUtils;
import org.quartz.Job;
import org.quartz.JobExecutionContext;

import static com.vv.idea.drink.com.vv.idea.drink.constant.Constants.DEFAULT_MESSAGE;
import static com.vv.idea.drink.com.vv.idea.drink.constant.Constants.LAST_EXECUTE_TIME;
import static com.vv.idea.drink.com.vv.idea.drink.constant.Constants.YOUR_NAME;

/**
 * @description:
 * @creator vv
 * @date 2021/6/11 14:57
 */
public class DrinkJob implements Job {

    @Override
    public void execute(JobExecutionContext jobExecutionContext) {
        String yourName = PropertiesHandler.getAppProp().getValue(YOUR_NAME);
        //构造器要在2021.3移除
        NotificationGroup notificationGroup =
                new NotificationGroup("drink_id", NotificationDisplayType.BALLOON, false);
        //通知
        String message = (StringUtils.isBlank(yourName) ? DEFAULT_MESSAGE[0] : yourName) + " , " + DEFAULT_MESSAGE[1];
        Notification notification = notificationGroup.createNotification(message, MessageType.INFO);
        Notifications.Bus.notify(notification);
        //记录执行任务的时间
        PropertiesHandler.getAppProp().setValue(LAST_EXECUTE_TIME, System.currentTimeMillis() + "");
    }

}


