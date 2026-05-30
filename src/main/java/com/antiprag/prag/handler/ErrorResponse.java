package com.antiprag.prag.handler;

import java.util.Map;

public record ErrorResponse(
    int status,
    String message,
    Map<String, String> errors
) {}

