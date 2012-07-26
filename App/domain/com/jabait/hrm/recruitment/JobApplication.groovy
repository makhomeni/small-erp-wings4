package com.jabait.hrm.recruitment

import com.jabait.security.Name
import com.jabait.security.Address

/**
 * Application status for candidates
 */
class JobApplication {

    Name name;
    Address address;
    String qualifications;
    Date appliedDateTime;
    String resumeName;
    String photo;

    static constraints = {
    }
}
