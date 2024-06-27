import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

public class ConversorMoneda {

    private static final String API_URL = "https://v6.exchangerate-api.com/v6/0249d1e1740f7fa41d1bcc2a/latest/USD";

    public static <InputMismatchException> double convertirMoneda(String from, String to, double cantidad) {
        try {
            URL url = new URL(API_URL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Content-Type", "application/json");

            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String inputLine;
            StringBuilder content = new StringBuilder();
            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine);
            }
            in.close();
            conn.disconnect();

            Gson gson = new Gson();
            JsonObject jsonObject = gson.fromJson(content.toString(), JsonObject.class);
            JsonObject conversionRates = jsonObject.getAsJsonObject("conversion_rates");

            double tasaCambioFrom = from.equals("USD") ? 1.0 : conversionRates.get(from).getAsDouble();
            double tasaCambioTo = conversionRates.get(to).getAsDouble();

            // Convertir la cantidad a USD si no es USD
            double cantidadEnUSD = from.equals("USD") ? cantidad : cantidad / tasaCambioFrom;
            // Convertir de USD a la moneda de destino
            return cantidadEnUSD * tasaCambioTo;
        }
        catch (Exception e) {
            System.out.println("Error al realizar la conversión: " + e.getMessage());
            return 0;
        }
    }
}