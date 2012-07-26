import com.jabait.security.User
import com.jabait.hrm.Organization
import com.jabait.hrm.Department
import com.jabait.hrm.calendar.CalendarType
import com.jabait.hrm.JobTitle
import com.jabait.hrm.calendar.CalendarSetting
import com.jabait.hrm.calendar.CalendarShare
import com.jabait.security.Feature
import com.jabait.accounting.AccountHead
import com.jabait.accounting.Accounts

class BootStrap {

    def init = { servletContext ->

        User.initialize();
        Feature.initialize();
        Organization.initialize();
        Department.initialize();
        CalendarType.initialize();
        JobTitle.initialize();
        CalendarSetting.initialize();
        CalendarShare.initialize();
        AccountHead.initialize();
        Accounts.initialize();

    }
    def destroy = {
    }
}
