package com.pheobe.application.menu;

/**
 *
 * @author pheobeo
 */
public class MenuAction {

    protected boolean isCancel() {
        return cancel;
    }

    public void cancel() {
        this.cancel = true;
    }

    private boolean cancel = false;
}
