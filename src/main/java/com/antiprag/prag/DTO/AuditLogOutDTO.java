package com.antiprag.prag.DTO;

import java.time.Instant;

public record AuditLogOutDTO (
Integer id,
String operation,
String method,
String created_by,
Instant created_at,
Integer status
){}
