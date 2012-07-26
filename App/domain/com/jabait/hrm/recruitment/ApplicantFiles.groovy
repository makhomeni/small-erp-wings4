package com.jabait.hrm.recruitment

import com.jabait.util.Files

class ApplicantFiles extends Files {

    static mapping = {
        discriminator(value: "applicant_files")
    }

    static constraints = {
    }
}
