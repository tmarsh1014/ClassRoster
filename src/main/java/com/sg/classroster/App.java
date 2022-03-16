package com.sg.classroster;

import com.sg.classroster.controller.ClassRosterController;
import com.sg.classroster.dao.*;
import com.sg.classroster.service.ClassRosterServiceLayer;
import com.sg.classroster.service.ClassRosterServiceLayerImpl;
import com.sg.classroster.ui.*;

public class App {
    public static void main(String[] args){
        UserIO myIo = new UserIOConsoleImpl();
        ClassRosterView myView = new ClassRosterView(myIo);
        ClassRosterDao myDao = new ClassRosterDaoFileImpl();
        ClassRosterAuditDao myAuditDao = new ClassRosterAuditDaoFileImpl();
        ClassRosterServiceLayer myServ = new ClassRosterServiceLayerImpl(myDao, myAuditDao);
        ClassRosterController controller = new ClassRosterController(myView, myServ);
        controller.run();
    }
}
