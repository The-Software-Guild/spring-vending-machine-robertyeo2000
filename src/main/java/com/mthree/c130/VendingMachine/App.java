package com.mthree.c130.VendingMachine;

import com.mthree.c130.VendingMachine.controller.VendingMachineController;
import com.mthree.c130.VendingMachine.dao.*;
import com.mthree.c130.VendingMachine.service.VendingMachineMoneyImpl;
import com.mthree.c130.VendingMachine.service.VendingMachineServiceLayer;
import com.mthree.c130.VendingMachine.service.VendingMachineServiceLayerImpl;
import com.mthree.c130.VendingMachine.ui.UserIO;
import com.mthree.c130.VendingMachine.ui.UserIOImpl;
import com.mthree.c130.VendingMachine.ui.VendingMachineView;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {

    public static void main(String[] args) {

        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        VendingMachineController controller = ctx.getBean("controller", VendingMachineController.class);
        controller.run();
    }
}
