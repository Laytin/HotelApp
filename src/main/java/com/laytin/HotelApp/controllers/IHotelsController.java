package com.laytin.HotelApp.controllers;

import com.laytin.HotelApp.dto.HotelDTO;
import com.laytin.HotelApp.dto.HotelSimpleDTO;
import com.laytin.HotelApp.dto.RegHotelDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequestMapping("/property-view/hotels")
public interface IHotelsController {
    @Operation(summary = "Get hotels list without any pagination")
    @ApiResponse(responseCode = "200", description = "Return list of hotels", content = {@Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = HotelSimpleDTO.class)))})
    @ResponseBody
    @GetMapping()
    List<HotelSimpleDTO> getHotelList();

    @Operation(summary = "Get existing hotel by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Exist hotel", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = HotelDTO.class))}),
            @ApiResponse(responseCode = "400", description = "Hotel with this id  not found")
    })
    @ResponseBody
    @GetMapping("/{id}")
    HotelDTO getHotelById(@Parameter(description = "id of hotel") @PathVariable("id") int id);

    @Operation(summary = "Get hotels list with searching")
    @ApiResponse(responseCode = "200", description = "Return list of hotels (or empty list)", content = {@Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = HotelSimpleDTO.class)))})
    @ResponseBody
    @GetMapping("/search")
    List<HotelSimpleDTO> searchHotelsBySmth(@Parameter(name = "name", description = "if search by name (optional)")
                                            @RequestParam(value = "name", required = false) Optional<String> name,
                                            @Parameter(name = "brand", description = "if search by brand(optional)")
                                            @RequestParam(value = "brand", required = false) Optional<String> brand,
                                            @Parameter(name = "city", description = "if search by city(optional)")
                                            @RequestParam(value = "city", required = false) Optional<String> city,
                                            @Parameter(name = "country", description = "if search by country(optional)")
                                            @RequestParam(value = "country", required = false) Optional<String> country,
                                            @Parameter(name = "amenities", description = "if search by amenities(optional)")
                                            @RequestParam(value = "amenities", required = false) Optional<String[]> amenities);

    @Operation(summary = "Create new hotel")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Create hotel. If ok -> return simple info about hotel", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = HotelSimpleDTO.class))}),
            @ApiResponse(responseCode = "400", description = "Smth going wrong, check error message in responce")
    })
    @ResponseBody
    @io.swagger.v3.oas.annotations.parameters.RequestBody
    @PostMapping()
    HotelSimpleDTO createNewHotel(@RequestBody @Valid RegHotelDTO hotelDTO, BindingResult result);
    @Operation(summary = "Setup amenities for exist hotel")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Init success, return hotel full info", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = HotelDTO.class))}),
            @ApiResponse(responseCode = "400", description = "Hotel with this id not found")
    })
    @ResponseBody
    @io.swagger.v3.oas.annotations.parameters.RequestBody
    @PostMapping("/{id}/amenities")
    HotelDTO changeAmenities(@Parameter(name = "id", description = "hotel id to edit") @PathVariable("id") int id, @RequestBody String[] amenities);
}
