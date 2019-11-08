package com.qf.pojo;

public class Role {

    private int roleid;
    private String roleName;
    private Users users;

    public int getRoleid() {
        return roleid;
    }

    public void setRoleid(int roleid) {
        this.roleid = roleid;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public Users getUsers() {
        return users;
    }

    public void setUsers(Users users) {
        this.users = users;
    }

    @Override
    public String toString() {
        return "Role{" +
                "roleid=" + roleid +
                ", roleName='" + roleName + '\'' +
                ", users=" + users +
                '}';
    }
}
