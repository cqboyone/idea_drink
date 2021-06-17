package com.vv.idea.drink.com.vv.idea.drink.handler;

import com.intellij.ide.util.PropertiesComponent;

/**
 * @description:
 * @creator vv
 * @date 2021/6/17 9:39
 */
public class PropertiesHandler {

    /**
     * @return 获取 application 级别的 PropertiesComponent
     */
    public static PropertiesComponent getAppProp() {
        return PropertiesComponent.getInstance();
    }

}
