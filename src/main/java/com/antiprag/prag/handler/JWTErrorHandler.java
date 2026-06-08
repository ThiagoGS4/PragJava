package com.antiprag.prag.handler;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.http.HttpServletResponse;

public class JWTErrorHandler {
public static void handleValidation(HttpServletResponse response, String mensagem, String code, String detalhe) throws IOException {
    response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
    response.setContentType("application/json");
    response.setCharacterEncoding("UTF-8");

    String json = String.format(
        "{\"status\": 401, \"mensagem\": \"%s\", \"code\": \"%s\", \"erros\": {\"Erro de token\": \"%s\"}}",
        mensagem, code, detalhe
    );

    PrintWriter writer = response.getWriter();
    response.getWriter().write(json);
    writer.flush();
    writer.close();
}
}
