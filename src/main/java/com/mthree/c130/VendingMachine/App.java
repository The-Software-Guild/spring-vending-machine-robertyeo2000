package com.mthree.c130.VendingMachine;

import com.mthree.c130.VendingMachine.controller.VendingMachineController;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class App {

    public static void main(String[] args) {

        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.scan("com.mthree.c130.VendingMachine");
        applicationContext.refresh();

        VendingMachineController controller =
                applicationContext.getBean("vendingMachineController", VendingMachineController.class);
        controller.run();
    }
}
