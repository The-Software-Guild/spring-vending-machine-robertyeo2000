package com.mthree.c130.VendingMachine;

import com.mthree.c130.VendingMachine.controller.VendingMachineController;
import com.mthree.c130.VendingMachine.dao.*;
import com.mthree.c130.VendingMachine.service.VendingMachineMoney;
import com.mthree.c130.VendingMachine.service.VendingMachineServiceLayer;
import com.mthree.c130.VendingMachine.service.VendingMachineServiceLayerImpl;
import com.mthree.c130.VendingMachine.ui.UserIO;
import com.mthree.c130.VendingMachine.ui.UserIOImpl;
import com.mthree.c130.VendingMachine.ui.VendingMachineView;

public class App {

    public static void main(String[] args) {

        UserIO myIo = new UserIOImpl();
        VendingMachineView myView = new VendingMachineView(myIo);
        VendingMachineDao myDao = new VendingMachineDaoImpl();
        VendingMachineMoney myMoney = new VendingMachineMoney();
        VendingMachineAuditDao auditDao = new VendingMachineAuditDaoImpl();
        VendingMachineServiceLayer myService = new VendingMachineServiceLayerImpl(myDao, myMoney, auditDao);
        VendingMachineController myController = new VendingMachineController(myView, myService);
        myController.run();
    }
}
