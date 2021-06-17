package com.vv.idea.drink.com.vv.idea.drink.enums;

/**
 * @description:
 * @creator vv
 * @date 2021/6/17 14:27
 */
public class Enums {

    public enum YES_NO_DIALOG {
        YES(0),
        NO(1),
        ;
        public Integer type;

        YES_NO_DIALOG(Integer type) {
            this.type = type;
        }
    }

    public enum JOB_STATUS {
        PAUSE("1"),
        RUNNING("2"),
        ;
        public String type;

        JOB_STATUS(String type) {
            this.type = type;
        }
    }
}
