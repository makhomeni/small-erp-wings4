package com.jabait.hrm

/**
 * Domain class for Job Title
 */
class JobTitle {

    String jobTitleCode;
    String jobTitleName;
    String jobTitleDescription;

    static constraints = {
    }

    public static initialize(){
        if(JobTitle.getCount() == 0){
            JobTitle jobTitle = new JobTitle();
            jobTitle.jobTitleCode = "01";
            jobTitle.jobTitleName = "Managing Director";
            jobTitle.jobTitleDescription = "Managing everything of the organization";

            jobTitle.save();

            JobTitle jobTitle2 = new JobTitle();
            jobTitle2.jobTitleCode = "01";
            jobTitle2.jobTitleName = "Finance Manager";
            jobTitle2.jobTitleDescription = "Managing financial part of the organization";
            jobTitle2.save(flush: true);
        }

    }
}
