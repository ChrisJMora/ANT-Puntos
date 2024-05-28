package com.demo.consultas_ant;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.ResponseEntity;

@RestController
public class ANTController {
    private final String URL_TEMPLATE = "https://consultaweb.ant.gob.ec/PortalWEB/paginas/clientes/clp_grid_citaciones.jsp?ps_tipo_identificacion=CED&ps_identificacion={id}&ps_placa=";

    @GetMapping(value = "/consulta", produces = MediaType.APPLICATION_JSON_VALUE)
    public Response LookForCarPlate(@RequestParam String cedula) {
        RestTemplate restTemplate = new RestTemplate();
        String url = URL_TEMPLATE.replace("{id}", cedula);
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
        String licencePoints = SearchPhrase(response.getBody(), "Información adicional de Puntos", 2, 2);
        return new Response(licencePoints);
    }

    private String SearchPhrase(String htmlContent, String phrase, int offset, int targetWidth)
    {
        int index = htmlContent.indexOf(phrase) + phrase.length() + offset;
        String result;
        try {
            result = htmlContent.substring(index, index + targetWidth);
        }catch (Exception ex)
        {
            result = ex.getMessage();
        }
        return result;
    }
}