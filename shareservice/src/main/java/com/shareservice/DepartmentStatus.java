package com.shareservice;

/**
 * Created by melon on 4/24/2017.
 */
public class DepartmentStatus {

    private Department department;
    private Boolean shared;

    public DepartmentStatus() {
    }

    public DepartmentStatus(Department department, Boolean shared) {
        this.setDepartment(department);
        this.setShared(shared);
    }


    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public Boolean getShared() {
        return shared;
    }

    public void setShared(Boolean shared) {
        this.shared = shared;
    }
}
