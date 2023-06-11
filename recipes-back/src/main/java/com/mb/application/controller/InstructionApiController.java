package com.mb.application.controller;

import com.mb.application.exception.ResourceNotFoundException;
import com.mb.application.service.InstructionService;
import com.mb.server.api.InstructionsApi;
import com.mb.server.model.Instruction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.NativeWebRequest;

import java.util.List;
import java.util.Optional;

@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2022-05-13T18:53:22.574116200+02:00[Europe/Prague]")
@Controller
@RequestMapping("${openapi.recipeManagement.base-path:/mb/v1}")
public class InstructionApiController implements InstructionsApi {

    @Autowired
    InstructionService is;

    public ResponseEntity<Instruction> createInstruction(@RequestBody Instruction instruction) {
        Instruction created = is.createInstruction(instruction);
        return new ResponseEntity<>(created, HttpStatus.CREATED);

    }

    public ResponseEntity<Void> deleteInstruction(String id) {
        is.deleteRecipe(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }

    public ResponseEntity<List<Instruction>> listInstructions(String fields, Integer offset, Integer limit, String name, Integer recipeId) {
        List<Instruction> instructions = is.listInstructions();
        return new ResponseEntity<List<Instruction>>(instructions, HttpStatus.OK);
    }

    public ResponseEntity<Instruction> retrieveInstruction(@PathVariable("id") String id) {
        Instruction instruction = is.getInstruction(id);
        if (instruction == null) {
            throw new ResourceNotFoundException("Instruction not found for instructionId: " + id);
        }
        return new ResponseEntity<>(instruction, HttpStatus.OK);

    }

    public ResponseEntity<Instruction> updateInstruction(String id, @RequestBody Instruction instruction) {
        int updatedId = is.updateInstruction(id, instruction);
        return retrieveInstruction(Integer.toString(updatedId));
    }
}
