package com.jabait.hrm

class Department {

    String departmentName;
    Department parentDepartment;
    Organization organization;

    static belongsTo = [Organization]

    static constraints = {
        parentDepartment(nullable: true)
    }

    public static initialize(){
        if(Department.getCount() == 0){
            Department department = new Department();    
            department.organization = Organization.get(1)
            department.departmentName = "Finance";
            department.parentDepartment = null;
            department.save(flush: true);
        }
    }
}
