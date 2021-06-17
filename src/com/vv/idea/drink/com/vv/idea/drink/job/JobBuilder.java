package com.vv.idea.drink.com.vv.idea.drink.job;

import com.vv.idea.drink.com.vv.idea.drink.handler.PropertiesHandler;
import com.vv.idea.drink.com.vv.idea.drink.quartz.JobHandler;
import com.vv.idea.drink.com.vv.idea.drink.quartz.QuartzJob;

import static com.vv.idea.drink.com.vv.idea.drink.constant.Constants.DRINK_INTERVAL_SECONDS;

/**
 * @description:
 * @creator zhangwei73
 * @date 2021/6/17 13:13
 */
public class JobBuilder {

    public static QuartzJob getDefault() {
        Integer value = PropertiesHandler.getAppProp().getInt(DRINK_INTERVAL_SECONDS, 60 * 30);
        QuartzJob quartzJob = new QuartzJob();
        quartzJob.setMode(JobHandler.Mode.INTERVAL.type);
        quartzJob.setJobName("jobName");
        quartzJob.setJobGroup("group");
        quartzJob.setDescription("喝水任务");
        quartzJob.setInterval(value);
        quartzJob.setJobClassName("com.vv.idea.drink.com.vv.idea.drink.job.DrinkJob");
        return quartzJob;
    }
}
