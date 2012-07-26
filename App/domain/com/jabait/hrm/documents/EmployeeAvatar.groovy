package com.jabait.hrm.documents

import com.jabait.util.Files

class EmployeeAvatar extends Files {
    static mapping = {
        discriminator(value: "employee_avatar")
    }
    static constraints = {
    }
}
