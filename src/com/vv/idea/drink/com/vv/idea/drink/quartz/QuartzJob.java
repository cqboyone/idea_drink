package com.vv.idea.drink.com.vv.idea.drink.quartz;

import java.io.Serializable;
import java.util.Date;


public class QuartzJob implements Serializable {
    private static final long serialVersionUID = 956948286382121256L;

    /**
     * 任务名称
     */
    private String jobName;

    /**
     * 任务分组
     */
    private String jobGroup;

    /**
     * 执行类，如：com.vv.quartz.job.SendMsgJob
     */
    private String jobClassName;

    /**
     * 任务启动时间，若为空，立即执行
     */
    private Date startDate;

    /**
     * cron表达式
     */
    private String cronExpression;

    /**
     * 任务状态
     */
    private String triggerState;

    /**
     * 任务描述
     */
    private String description;

    /**
     * 模式。1：间隔；2 cron。如果为1，cronExpression必填；如果为2，interval必填
     */
    private Integer mode;

    /**
     * 间隔(秒)
     */
    private Integer interval;

    /**
     * 重复次数
     */
    private Integer repeatCount;

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public String getJobGroup() {
        return jobGroup;
    }

    public void setJobGroup(String jobGroup) {
        this.jobGroup = jobGroup;
    }

    public String getJobClassName() {
        return jobClassName;
    }

    public void setJobClassName(String jobClassName) {
        this.jobClassName = jobClassName;
    }

    public String getCronExpression() {
        return cronExpression;
    }

    public void setCronExpression(String cronExpression) {
        this.cronExpression = cronExpression;
    }

    public String getTriggerState() {
        return triggerState;
    }

    public void setTriggerState(String triggerState) {
        this.triggerState = triggerState;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getMode() {
        return mode;
    }

    public void setMode(Integer mode) {
        this.mode = mode;
    }

    public Integer getInterval() {
        return interval;
    }

    public void setInterval(Integer interval) {
        this.interval = interval;
    }

    public Integer getRepeatCount() {
        return repeatCount;
    }

    public void setRepeatCount(Integer repeatCount) {
        this.repeatCount = repeatCount;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }
}