package com.vv.idea.drink.com.vv.idea.drink.action;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.ui.Messages;
import com.vv.idea.drink.com.vv.idea.drink.constant.MyCache;
import com.vv.idea.drink.com.vv.idea.drink.handler.PropertiesHandler;
import com.vv.idea.drink.com.vv.idea.drink.job.JobBuilder;
import com.vv.idea.drink.com.vv.idea.drink.quartz.JobHandler;
import com.vv.idea.drink.com.vv.idea.drink.quartz.QuartzJob;

import java.util.Date;

import static com.vv.idea.drink.com.vv.idea.drink.constant.Constants.DRINK_INTERVAL_SECONDS;
import static com.vv.idea.drink.com.vv.idea.drink.constant.Constants.LAST_EXECUTE_TIME;

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
        QuartzJob quartzJob = JobBuilder.getDefault();
        //获取上次执行时间
        String lastExecuteTime = PropertiesHandler.getAppProp().getValue(LAST_EXECUTE_TIME);
        Long last = Long.valueOf(lastExecuteTime);
        Long now = System.currentTimeMillis();
        //距上次过了多少毫秒
        long l = now - last;
        //如果新的间隔小于间隔,立刻开始任务
        if (newInterval * 1000 <= l) {
            quartzJob.setStartDate(new Date(now));
        } else {
            //如果新的间隔大于间隔,延迟生效
            long delay = newInterval * 1000 - l;
            quartzJob.setStartDate(new Date(now + delay));
        }
        new JobHandler(MyCache.scheduler).updateJob(quartzJob);
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
