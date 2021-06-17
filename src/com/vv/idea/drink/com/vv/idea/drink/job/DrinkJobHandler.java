package com.vv.idea.drink.com.vv.idea.drink.job;

import com.vv.idea.drink.com.vv.idea.drink.constant.MyCache;
import com.vv.idea.drink.com.vv.idea.drink.handler.PropertiesHandler;
import com.vv.idea.drink.com.vv.idea.drink.quartz.JobHandler;
import com.vv.idea.drink.com.vv.idea.drink.quartz.QuartzJob;
import org.apache.commons.lang3.StringUtils;

import java.util.Date;

import static com.vv.idea.drink.com.vv.idea.drink.constant.Constants.DRINK_INTERVAL_SECONDS;
import static com.vv.idea.drink.com.vv.idea.drink.constant.Constants.LAST_EXECUTE_TIME;

/**
 * @description:
 * @creator zhangwei73
 * @date 2021/6/17 15:30
 */
public class DrinkJobHandler {

    /**
     * 根据上次提醒时间来修改任务
     *
     * @param newInterval
     */
    public static void safetyUpdateJob(Integer newInterval) {
        new JobHandler(MyCache.scheduler).updateJob(buildSafetyDrinkJob(newInterval));
    }

    /**
     * 根据上次提醒时间来创建任务对象
     *
     * @param newInterval
     * @return
     */
    public static QuartzJob buildSafetyDrinkJob(Integer newInterval) {
        QuartzJob quartzJob = DrinkJobBuilder.getDefault();
        //获取上次执行时间
        String lastExecuteTime = PropertiesHandler.getAppProp().getValue(LAST_EXECUTE_TIME);
        Long last;
        if (StringUtils.isBlank(lastExecuteTime)) {
            last = 0l;
        } else {
            last = Long.valueOf(lastExecuteTime);
        }
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
        return quartzJob;
    }

    /**
     * 根据上次提醒时间来创建任务对象
     *
     * @return
     */
    public static QuartzJob buildSafetyDrinkJob() {
        Integer value = PropertiesHandler.getAppProp().getInt(DRINK_INTERVAL_SECONDS, 30 * 60);
        return buildSafetyDrinkJob(value);
    }
}
