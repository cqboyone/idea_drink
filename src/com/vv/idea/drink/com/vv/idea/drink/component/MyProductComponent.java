package com.vv.idea.drink.com.vv.idea.drink.component;

import com.intellij.openapi.components.ProjectComponent;
import com.vv.idea.drink.com.vv.idea.drink.constant.MyCache;
import com.vv.idea.drink.com.vv.idea.drink.quartz.JobHandler;
import com.vv.idea.drink.com.vv.idea.drink.quartz.QuartzJob;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.impl.StdSchedulerFactory;

/**
 * @description:
 * @creator zhangwei73
 * @date 2021/6/11 16:34
 */
public class MyProductComponent implements ProjectComponent {

    @Override
    public void projectOpened() {
        System.out.println("工程打开了");
        try {
            // 获取到一个StdScheduler, StdScheduler其实是QuartzScheduler的一个代理
            Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
            MyCache.scheduler = scheduler;
            scheduler.start();
            JobHandler jobHandler = new JobHandler(scheduler);
            QuartzJob quartzJob = new QuartzJob();
            quartzJob.setMode(JobHandler.Mode.INTERVAL.type);
            quartzJob.setJobName("jobName");
            quartzJob.setJobGroup("group");
            quartzJob.setDescription("喝水任务");
            quartzJob.setInterval(60 * 30);
            quartzJob.setJobClassName("com.vv.idea.drink.com.vv.idea.drink.job.DrinkJob");
            System.out.println(jobHandler.saveJob(quartzJob));
        } catch (SchedulerException se) {
            se.printStackTrace();
        }
    }

}
