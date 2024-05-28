package com.demo.consultas_ant;

import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class ANTService {

    private final RestTemplate _restTemplate = new RestTemplate();

    public Response LicencePoints(String id)
    {
        ResponseEntity<String> response = _restTemplate.getForEntity(GetURL(id), String.class);
        String htmlContent = response.getBody();
        if(htmlContent == null) return null;
        String licencePoints = SearchPhrase(htmlContent, "Información adicional de Puntos", 2, 2);
        return new Response(licencePoints);
    }

    private String GetURL(String id)
    {
        String urlTemplate = "https://consultaweb.ant.gob.ec/PortalWEB/paginas/clientes/clp_grid_citaciones.jsp?ps_tipo_identificacion=CED&ps_identificacion={id}&ps_placa=";
        return urlTemplate.replace("{id}", id);
    }

    private String SearchPhrase(String htmlContent, String phrase, int offset, int targetWidth)
    {
        int index = htmlContent.indexOf(phrase) + phrase.length() + offset;
        String result;
        try {
            result = htmlContent.substring(index, index + targetWidth);
        } catch (Exception ex) {
            result = ex.getMessage();
        }
        return result;
    }
}
