package Controller;

import ExceptionAr.ExceptionWindow;
import View.labels.LabelProcess;
import sample.Main;
import java.io.*;
import java.util.concurrent.TimeUnit;

public class ConverterClass {

    private static String clientName;
    private static String ClientsScanOne;
    public static String ClientScanTwo;
    public static String body;
    public static String[] bodyTwo;
    public static String tempString;

    public static int orderCounter = 0;
    public static String createBody(String clientName) {
        body = "%%" + clientName + ";   *25.02.2013:  #3258" + orderCounter + "  (11.08.2020.11.29.37) P";
        if (orderCounter != 9)
        orderCounter ++;
        else orderCounter = 0;
        return body;
    }
    public static String createTempString(String leftScan, String rightScan, String clientName) {
        tempString =
                "ordr-vrsnr=1.3" +
                        " syst-vrson=1.99.2" +
                        " ordr-msyst=PressurePed/emed" +
                        " ordr-remit=Arvitum1" +
                        " wibu-boxnr=12-12231954" +
                        " ordr-place=Odessa 2" +
                        " ordr-xkdnr=-" +
                        " ordr-xprfx=" +
                        " ordr-mdate=" +
                        " ordr-xdate=11.08.2020" +
                        " ordr-numbe=32588" +
                        " ordr-xpres=Klinika" +
                        " flag-xpres=Normal" +
                        " cust-sname=" + clientName +
                        " cust-fname=" + clientName +
                        " cust-birth=25.02.2013" +
                        " cust-sexmf=M" +
                        " cust-strnr=" +
                        " cust-pcode=" +
                        " cust-ptown=" +
                        " cust-ctry=" +
                        " cust-phone=" +
                        " cust-email=" +
                        " cust-insur=Самооплата" +
                        " cust-weigh=" +
                        " cust-bodyh=" +
                        " cust-sizeL=40" +
                        " cust-sizeR=40" +
                        " inso-pairs=1" +
                        " inso-matL1=" +
                        " inso-matR1=" +
                        " inso-covrL=Kira" +
                        " inso-covrR=Kira" +
                        " inso-TypeL=Comfine full EVA" +
                        " inso-TypeR=Comfine full EVA" +
                        " inso-TypPL=" +
                        " inso-TypPR=" +
                        " INSO-DOWNS=1" +
                        " inso-cshiL=" +
                        " inso-cshiR=" +
                        " inso-intaL=" +
                        " inso-intaR=" +
                        " shoe-brand=" +
                        " shoe-type=Comfine full EVA" +
                        " shoe-size=40" +
                        " shoe-width=" +
                        " Pelt-Heigh=" +
                        " Pelt-Posit=" +
                        " inso-pitch=" +
                        " diag-mainL=ploska? stopa" +
                        " diag-mainR=ploska? stopa" +
                        " diag-detnl=" +
                        " diag-detnr=" +
                        " Cust-Pain-L=" +
                        " Cust-Pain-R=" +
                        " name-ins-L=BUKSS40L.VEC" +
                        " name-ins-R=BUKSS40R.VEC" +
                        " file-pdm-L=" + clientName + leftScan +
                        " file-pdm-R=" + clientName + rightScan +
                        " file-jpg-L=" + clientName + leftScan +
                        " file-jpg-R=" + clientName + rightScan +
                        " file-bmp-L=" + clientName + leftScan +
                        " file-bmp-R=" + clientName + rightScan +
                        " alig-bmp-l=" +
                        " alig-bmp-r=" +
                        " alig-pdm-l=63.18,0.00,0.00;0.00,0.00,0.00;0.00;20L" +
                        " alig-pdm-r=63.18,0.00,0.00;0.00,0.00,0.00;0.00;20L" +
                        " alin-defxl=0;209.6#0;31;56;64;71;83;100/16" +
                        " alin-defxr=0;209.6#0;31;56;64;71;83;100/16" +
                        " alin-selxl=0;209.6#0;31;56;64;71;83;100/16" +
                        " alin-selxr=0;209.6#0;31;56;64;71;83;100/16" +
                        " info-L-001=infoEnd" +
                        " info-R-001=infoEnd" +
                        " info-B-001=infoEnd";
        return tempString;
    }

    public static void createBoduTwo(String left, String right) {
        bodyTwo = createTempString(left, right, Main.firstNameForScans).split(" ");
    }

    public static String getClientName() {
        return clientName;
    }

    public static void setClientName(String name) throws Exception {
        clientName = name;
    }

    public static String getClientsScanOne() {
        return ClientsScanOne;
    }

    public static void setClientsScanOne(String clientsScanOne) {
        ClientsScanOne = clientsScanOne;
    }

    public static String getClientScanTwo() {
        return ClientScanTwo;
    }

    public static void setClientScanTwo(String clientScanTwo) {
        ClientScanTwo = clientScanTwo;
    }

    public static File whereToSave = null;

    public static void updateOrderlst() throws IOException {
        FileWriter fw = new FileWriter(Main.dir + "\\orderlst.txt", true);
        BufferedWriter bw = new BufferedWriter(fw);
        PrintWriter out = new PrintWriter(bw);
        createBoduTwo(Main.leftScan, Main.rightScan);
            try {
                out.println(createBody(Main.headNameForOrderlst));
                for (int e = 0; e <bodyTwo.length; e ++) {
                    out.println(bodyTwo [e]);
                }
                out.println();
                out.close();

            } catch (Exception e) {
                ExceptionWindow.problem.setText("The file wasnt renued");
                ExceptionWindow.window("Error!");
                //e.printStackTrace();
            }
        }
    }




