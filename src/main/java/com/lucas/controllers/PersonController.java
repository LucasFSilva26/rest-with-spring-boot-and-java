package com.lucas.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lucas.data.vo.v1.PersonVO;
//import com.lucas.data.vo.v2.PersonVOV2;
import com.lucas.services.PersonServices;
import com.lucas.util.MediaType;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/person")
@Tag(name = "People", description = "Endpoints for Managing People")
public class PersonController {

	@Autowired
	private PersonServices service;

	@GetMapping(produces = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML,MediaType.APPLICATION_YML})
	@Operation(summary = "Finds all People", description = "Finds all People", 
		tags = {"People"},
		responses = {
				@ApiResponse(description = "Sucess", responseCode = "200",
						content = {
								@Content(
										mediaType = "application/json",
										array = @ArraySchema(schema = @Schema(implementation = PersonVO.class))
										)
						}),
				@ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
				@ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
				@ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
				@ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
				}
	)
	public List<PersonVO> findAll() {

		return service.findAll();
	}

		
	@GetMapping(value = "/{id}", produces = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML })
	@Operation(summary = "Finds one Person", description = "Finds one Person", 
	tags = {"People"},
	responses = {
			@ApiResponse(description = "Sucess", responseCode = "200",
					content = 
							@Content(schema = @Schema(implementation = PersonVO.class))
									),
			@ApiResponse(description = "No Content", responseCode = "204", content = @Content),
			@ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
			@ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
			@ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
			@ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
			}
)
	public PersonVO findById(@PathVariable(value = "id") Long id) {

		return service.findById(id);
	}

	@PostMapping(consumes = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML },
				produces = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML })
	@Operation(summary = "Adds one Person", description = "Adds one Person", 
	tags = {"People"},
	responses = {
			@ApiResponse(description = "Sucess", responseCode = "200",
					content = 
							@Content(schema = @Schema(implementation = PersonVO.class))
									),
			@ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
			@ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
			@ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
			@ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
			}
)
	public PersonVO create(@RequestBody PersonVO person) {

		return service.create(person);
	}

	@PostMapping(value = "/v2", consumes = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML }, 
								produces = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML })
	//public PersonVOV2 createV2(@RequestBody PersonVOV2 person) {

	//	return service.createV2(person);
	//}

	@PutMapping(consumes = { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML }, 
				produces = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML })
	@Operation(summary = "Updates one Person", description = "Updates one Person", 
	tags = {"People"},
	responses = {
			@ApiResponse(description = "Sucess", responseCode = "200",
					content = 
							@Content(schema = @Schema(implementation = PersonVO.class))
									),
			@ApiResponse(description = "No Content", responseCode = "204", content = @Content),
			@ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
			@ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
			@ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
			@ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
			}
)
	public PersonVO update(@RequestBody PersonVO person) {

		return service.update(person);
	}

	@DeleteMapping("/{id}")
	@Operation(summary = "Deletes one Person", description = "Deletes one Person", 
	tags = {"People"},
	responses = {
			@ApiResponse(description = "No Content", responseCode = "204",content = @Content),
			@ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
			@ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
			@ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
			@ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
			}
)
	public ResponseEntity<?> delete(@PathVariable(value = "id") Long id) {

		service.delete(id);

		return ResponseEntity.noContent().build();
	}

}
