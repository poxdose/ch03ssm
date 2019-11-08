package com.qf.pojo;

public class Permission {
    private int pid;
    private String permission;
    private Role role;

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "Permission{" +
                "pid=" + pid +
                ", permission='" + permission + '\'' +
                ", role=" + role +
                '}';
    }
}
