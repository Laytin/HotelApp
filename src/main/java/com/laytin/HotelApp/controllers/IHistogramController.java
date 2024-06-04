package com.laytin.HotelApp.controllers;

import com.laytin.HotelApp.dto.HotelSimpleDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.Map;
@RequestMapping("/property-view/histogram")
public interface IHistogramController {
    @Operation(summary = "Get hotels histogram")
    @ApiResponse(responseCode = "200", description = "Return map with type and count for each category", content = {@Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = HashMap.class)))})
    @GetMapping("/{type}")
    Map<String, Long> getHistogram(@Parameter(name = "type", description = "by which param we should group") @PathVariable("type") String type);
}
