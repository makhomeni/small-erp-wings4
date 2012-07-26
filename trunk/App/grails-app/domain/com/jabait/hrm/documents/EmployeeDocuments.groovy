package com.jabait.hrm.documents

import com.jabait.util.Files

class EmployeeDocuments extends Files {

    static mapping = {
        discriminator(value: "employee_document")
    }

    static constraints = {
    }
}
