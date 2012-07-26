package com.jabait.accounting

class Accounts {     
    
    String accountName;
    Integer accountNumber;
    AccountHead accountHead;
    Boolean isActive=true;

    static constraints = {
    }

    public static void initialize(){
        def insertSql = [
                ['Advance Salary',1000001,AccountHead.findByTitleAndIsActive("Salary & Allowance",true)]
        ]
        if (Accounts.count() < 1 && insertSql) {
            insertSql.each {
                new Accounts(accountName: it[0], accountNumber: it[1],accountHead: it[2], isActive: true).save();
            }
        }


    }
}
