package com.vv.idea.drink.com.vv.idea.drink.job;

import com.vv.idea.drink.com.vv.idea.drink.handler.PropertiesHandler;
import com.vv.idea.drink.com.vv.idea.drink.quartz.JobHandler;
import com.vv.idea.drink.com.vv.idea.drink.quartz.QuartzJob;

import static com.vv.idea.drink.com.vv.idea.drink.constant.Constants.DRINK_INTERVAL_SECONDS;
import static com.vv.idea.drink.com.vv.idea.drink.constant.Constants.DRINK_JOB_DESCRIPTION;
import static com.vv.idea.drink.com.vv.idea.drink.constant.Constants.DRINK_JOB_GROUP;
import static com.vv.idea.drink.com.vv.idea.drink.constant.Constants.DRINK_JOB_NAME;

/**
 * @description:
 * @creator zhangwei73
 * @date 2021/6/17 13:13
 */
public class DrinkJobBuilder {

    public static QuartzJob getDefault() {
        Integer value = PropertiesHandler.getAppProp().getInt(DRINK_INTERVAL_SECONDS, 60 * 30);
        QuartzJob quartzJob = new QuartzJob();
        quartzJob.setMode(JobHandler.Mode.INTERVAL.type);
        quartzJob.setJobName(DRINK_JOB_NAME);
        quartzJob.setJobGroup(DRINK_JOB_GROUP);
        quartzJob.setDescription(DRINK_JOB_DESCRIPTION);
        quartzJob.setInterval(value);
        quartzJob.setJobClassName("com.vv.idea.drink.com.vv.idea.drink.job.DrinkJob");
        return quartzJob;
    }
}
