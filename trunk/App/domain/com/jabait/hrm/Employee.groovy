package com.jabait.hrm

import com.jabait.security.User
import com.jabait.hrm.time.FingerPrintDetails
import com.jabait.hrm.time.LeaveRegister
import com.jabait.util.RelationShip
import com.jabait.hrm.payroll.PayGroup
import com.jabait.hrm.payroll.Allowance

class Employee extends User {

    static hasOne = [employeeProfile: EmployeeProfile];


    static hasMany = [leaveRegister: LeaveRegister, trainings:Training,
            relationShips : RelationShip, educations : Education, skillList : EmployeeSkill, payGroups : PayGroup,
            experiences : WorkExperience, allowances : Allowance]

    static mapping = {
        discriminator(value: "hrm_user")
    }

    static constraints = {
    }
}
