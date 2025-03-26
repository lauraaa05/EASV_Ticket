
import bll.BarCodeGenerator;

public class Main {

        public static void main(String[] args) {
            String barcodePath = BarCodeGenerator.generateBarcode("123456789");
            if (barcodePath != null) {
                System.out.println("Código de barras generator en: " + barcodePath);
            } else {
                System.out.println("Error al generar el código de barras.");
            }
        }
    }

