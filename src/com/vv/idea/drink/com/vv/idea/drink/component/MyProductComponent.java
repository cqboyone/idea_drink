package com.vv.idea.drink.com.vv.idea.drink.component;

import com.intellij.openapi.components.ProjectComponent;
import com.vv.idea.drink.com.vv.idea.drink.constant.MyCache;
import com.vv.idea.drink.com.vv.idea.drink.enums.Enums;
import com.vv.idea.drink.com.vv.idea.drink.handler.PropertiesHandler;
import com.vv.idea.drink.com.vv.idea.drink.job.DrinkJobHandler;
import com.vv.idea.drink.com.vv.idea.drink.quartz.JobHandler;
import org.apache.commons.lang3.StringUtils;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.impl.StdSchedulerFactory;

import static com.vv.idea.drink.com.vv.idea.drink.constant.Constants.DRINK_JOB_GROUP;
import static com.vv.idea.drink.com.vv.idea.drink.constant.Constants.DRINK_JOB_NAME;
import static com.vv.idea.drink.com.vv.idea.drink.constant.Constants.DRINK_JOB_STATUS;

/**
 * @description:
 * @creator zhangwei73
 * @date 2021/6/11 16:34
 */
public class MyProductComponent implements ProjectComponent {

    @Override
    public void projectOpened() {
        try {
            // 获取到一个StdScheduler, StdScheduler其实是QuartzScheduler的一个代理
            Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
            scheduler.start();
            MyCache.scheduler = scheduler;
            //开始处理任务
            JobHandler jobHandler = new JobHandler(scheduler);
            jobHandler.saveJob(DrinkJobHandler.buildSafetyDrinkJob());
            //获取idea存储的任务执行状态
            String status = PropertiesHandler.getAppProp().getValue(DRINK_JOB_STATUS);
            if (StringUtils.isNotBlank(status) && !Enums.JOB_STATUS.RUNNING.type.equals(status)) {
                jobHandler.pauseJob(DRINK_JOB_NAME, DRINK_JOB_GROUP);
                return;
            }
            PropertiesHandler.getAppProp().setValue(DRINK_JOB_STATUS, Enums.JOB_STATUS.RUNNING.type);
        } catch (SchedulerException se) {
            se.printStackTrace();
        }
    }

}
