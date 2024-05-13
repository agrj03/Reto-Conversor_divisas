package modelos;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ConsultaDivisa {
    public Moneda conectar(String base, String target, double monto){
        URI direccion = URI.create("https://v6.exchangerate-api.com/v6/7bcb409996a9274514c30b7b/pair/"+base+"/"+target+"/"+monto);

        HttpClient client   = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder().uri(direccion).build();

        try {
            HttpResponse<String> response = null;
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
            return new Gson().fromJson(response.body(), Moneda.class);
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException("Ocurrio un problema inesperado: "+ e.getMessage());
        }
    }

    public JsonObject conexion(String divisa){
        URI direccion = URI.create("https://v6.exchangerate-api.com/v6/7bcb409996a9274514c30b7b/latest/"+divisa);

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder().uri(direccion).build();

        try {
            HttpResponse<String> response = null;
            response = client.send(request,HttpResponse.BodyHandlers.ofString());
            JsonElement elementoJson = JsonParser.parseString(response.body());

            return elementoJson.getAsJsonObject();
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException("Ocurrio un problema inesperado: "+ e.getMessage());
        }
    }
}
