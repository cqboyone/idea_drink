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

import static com.vv.idea.drink.com.vv.idea.drink.constant.Constants.YOUR_NAME;

/**
 * @description:
 * @creator zhangwei73
 * @date 2021/6/11 14:57
 */
public class DrinkJob implements Job {

    @Override
    public void execute(JobExecutionContext jobExecutionContext) {
        String yourName = PropertiesHandler.getAppProp().getValue(YOUR_NAME);
        //因为构造器要在2021.3移除，我在源码中发现了这个api，传递参数参考的源码的。
        NotificationGroup notificationGroup = NotificationGroup.create("drink_id", NotificationDisplayType.BALLOON,
                false, null, null, "24", null);
        //通知
        String message = (StringUtils.isBlank(yourName) ? "主人" : yourName) + "，该喝水啦";
        Notification notification = notificationGroup.createNotification(message, MessageType.INFO);
        Notifications.Bus.notify(notification);
    }

}


