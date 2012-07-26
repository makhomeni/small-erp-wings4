package com.jabait.hrm

import com.jabait.util.ExpertiseLevel;
import com.jabait.util.ExpertiseUsageYear;
import com.jabait.util.LastUsage;

class EmployeeSkill {

    String skillName;
    String skillDescription;
    ExpertiseLevel level;
    ExpertiseUsageYear year;
    LastUsage lastUsed;

    static hasMany = [skilledEmployees : Employee]

    static belongsTo = [Employee]

    static constraints = {
    }
}
