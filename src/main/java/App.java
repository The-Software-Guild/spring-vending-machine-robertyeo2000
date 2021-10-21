import controller.VendingMachineController;
import dao.*;
import service.VendingMachineMoney;
import service.VendingMachineServiceLayer;
import service.VendingMachineServiceLayerImpl;
import ui.UserIO;
import ui.UserIOImpl;
import ui.VendingMachineView;

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
