package api;

import java.util.List;

import models.Reporte;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface ReportApi {
    @POST("apireporte/reporte.php")
    Call<Reporte> enviarReporte( @Header("Authorization") String token, @Body Reporte reporte);

    @GET("apireporte/reporte.php") // 🔥 Ajusta la ruta según la API de tu profesor
    Call<List<Reporte>> obtenerReportes(@Header("Authorization") String token);


}




