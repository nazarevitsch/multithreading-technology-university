package FirstLab.printer;

public class PrinterThread2 extends Thread {

    private Printer printer;

    public PrinterThread2(Printer printer) {
        this.printer = printer;
    }

    @Override
    public void run() {
        try {
            for (int i = 0; i < 100; i++) {
//                printer.print2();
                printer.print("|");
                Thread.sleep(10);
            }
        } catch (InterruptedException ex) {
        }
    }
}
