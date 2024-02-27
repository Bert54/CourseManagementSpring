package com.test.coursemanagementspring.core.services.classs.adapters.options;

public class MembershipOptions {
    private boolean withLog;

    public MembershipOptions() {
        // enable logs by default
        this.withLog = true;
    }

    public boolean isWithLog() {
        return withLog;
    }

    public void setWithLog(boolean withLog) {
        this.withLog = withLog;
    }

    public static void withoutLog(MembershipOptions options) {
        options.withLog = false;
    }
}
