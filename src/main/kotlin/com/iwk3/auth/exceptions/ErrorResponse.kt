package com.iwk3.auth.exceptions

import lombok.AllArgsConstructor
import lombok.Data
import lombok.NoArgsConstructor
import org.springframework.http.ResponseEntity

@Data
@NoArgsConstructor
@AllArgsConstructor
class ErrorResponse(
    var status: Int,
    var message: String
) {
    fun res(): ResponseEntity<ErrorResponse> {
        return ResponseEntity.status(this.status).body(this)
    }
}
