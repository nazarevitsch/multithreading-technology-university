package FirstLab.printer;


public class PrinterThread1 extends Thread {

    private Printer printer;

    public PrinterThread1(Printer printer) {
        this.printer = printer;
    }

    @Override
    public void run() {
        try {
            for (int i = 0; i < 100; i++) {
                printer.print1();
//                printer.print("-");
                Thread.sleep(10);
            }
        } catch (InterruptedException ex) {
        }
    }
}
