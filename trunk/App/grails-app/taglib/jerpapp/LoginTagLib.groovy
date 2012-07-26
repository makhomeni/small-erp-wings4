package jerpapp

class LoginTagLib {

    def loginControl(){
        if(session.user){
            out << "Welcome ${session.user.userCode}";
            out << """[${link(action:"logout", controller:"user"){"Logout"}}]""";
        } else {
            out << """[${link(action:"login", controller:"user"){"Login"}}]""";
        }
    }

}
