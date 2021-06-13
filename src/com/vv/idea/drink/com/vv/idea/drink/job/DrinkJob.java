package com.vv.idea.drink.com.vv.idea.drink.job;

import com.intellij.notification.Notification;
import com.intellij.notification.NotificationDisplayType;
import com.intellij.notification.NotificationGroup;
import com.intellij.notification.Notifications;
import com.intellij.openapi.ui.MessageType;
import org.quartz.Job;
import org.quartz.JobExecutionContext;

/**
 * @description:
 * @creator zhangwei73
 * @date 2021/6/11 14:57
 */
public class DrinkJob implements Job {

    @Override
    public void execute(JobExecutionContext jobExecutionContext) {
        NotificationGroup notificationGroup = new NotificationGroup("drink_id", NotificationDisplayType.BALLOON, false);
        //通知
        Notification notification = notificationGroup.createNotification("小哥哥，该喝水啦", MessageType.INFO);
        Notifications.Bus.notify(notification);
    }

}


