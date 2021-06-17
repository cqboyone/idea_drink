package com.vv.idea.drink.com.vv.idea.drink.quartz;

import org.quartz.CronScheduleBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.ScheduleBuilder;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.TriggerKey;

import java.util.Date;

public class JobHandler {

    private static final String TRIGGER_IDENTITY = "trigger";

    private Scheduler scheduler;

    public JobHandler(Scheduler scheduler) {
        this.scheduler = scheduler;
    }

    /**
     * @Description: 新增job，若模式为2，默认无限重复
     */
    public boolean saveJob(QuartzJob quartzJob) {
        try {
            schedulerJob(quartzJob);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    /**
     * @Description: 触发job(执行一次)
     */
    public boolean triggerJob(String jobName, String jobGroup) {
        JobKey key = new JobKey(jobName, jobGroup);
        try {
            scheduler.triggerJob(key);
        } catch (SchedulerException e) {
            return false;
        }
        return true;
    }

    /**
     * @Description: 暂停job
     */
    public boolean pauseJob(String jobName, String jobGroup) {
        JobKey key = new JobKey(jobName, jobGroup);
        try {
            scheduler.pauseJob(key);
        } catch (SchedulerException e) {
            return false;
        }
        return true;
    }

    /**
     * @Description: 恢复job
     */
    public boolean resumeJob(String jobName, String jobGroup) {
        JobKey key = new JobKey(jobName, jobGroup);
        try {
            scheduler.resumeJob(key);
        } catch (SchedulerException e) {
            return false;
        }
        return true;
    }

    /**
     * @Description: 移除job。
     */
    public boolean removeJob(String jobName, String jobGroup) {

        try {
            TriggerKey triggerKey = TriggerKey.triggerKey(TRIGGER_IDENTITY + jobName, jobGroup);
            scheduler.pauseTrigger(triggerKey);                                 // 停止触发器
            scheduler.unscheduleJob(triggerKey);                                // 移除触发器
            scheduler.deleteJob(JobKey.jobKey(jobName, jobGroup));              // 删除任务
        } catch (Exception e) {
        }
        return false;
    }

    /**
     * @Description: 更新任务。
     */
    public boolean updateJob(QuartzJob quartz) {
        try {
            scheduler.deleteJob(new JobKey(quartz.getJobName(), quartz.getJobGroup()));
            schedulerJob(quartz);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public void schedulerJob(QuartzJob job) throws Exception {
        //任务启动时间
        if (job.getStartDate() == null) {
            job.setStartDate(new Date());
        }
        //构建job信息
        Class cls = Class.forName(job.getJobClassName());
        // cls.newInstance(); // 检验类是否存在
        JobDetail jobDetail = JobBuilder.newJob(cls)
                .withIdentity(job.getJobName(), job.getJobGroup())
                .withDescription(job.getDescription())
                .build();

        // 触发频率
        ScheduleBuilder scheduleBuilder = null;
        if (Mode.CRON.type.equals(job.getMode())) {
            scheduleBuilder = CronScheduleBuilder.cronSchedule(job.getCronExpression().trim());
        }
        if (Mode.INTERVAL.type.equals(job.getMode())) {
            if (job.getRepeatCount() != null) {
                scheduleBuilder = SimpleScheduleBuilder.simpleSchedule()
                        .withIntervalInSeconds(job.getInterval())
                        .withRepeatCount(job.getRepeatCount());
            } else {
                scheduleBuilder = SimpleScheduleBuilder.simpleSchedule()
                        .withIntervalInSeconds(job.getInterval())
                        .repeatForever();
            }
        }
        Trigger trigger = TriggerBuilder.newTrigger()
                .withIdentity(TRIGGER_IDENTITY + job.getJobName(), job.getJobGroup())
                .startAt(job.getStartDate())
                .withSchedule(scheduleBuilder)
                .build();
        //交由Scheduler安排触发
        scheduler.scheduleJob(jobDetail, trigger);
    }

    public enum Mode {
        INTERVAL(1),
        CRON(2),
        ;
        public Integer type;

        Mode(Integer type) {
            this.type = type;
        }
    }
}
