package com.jabait.accounting

class AccountHead {
    String title;
    String description;
    Boolean isActive;

    static constraints = {
        title(nullable: false);
        description(nullable: true);

    }

    public static void initialize(){
        def insertSql = [
                ['Salary & Allowance','Salary and allowance related transaction ']
        ]
        if (AccountHead.count() < 1 && insertSql) {
            insertSql.each {
                new AccountHead(title: it[0], description:it[1],isActive: true).save();
            }
        }


    }
}
