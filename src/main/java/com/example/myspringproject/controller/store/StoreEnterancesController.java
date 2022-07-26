package com.example.myspringproject.controller.store;

import com.example.myspringproject.dto.store.StoreEnterancesDto;
import com.example.myspringproject.service.store.IStoreEnteranceService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/store-enterances")
@RequiredArgsConstructor
@Api(value = "Store Enterances API", tags = "3- Store Enterances API", description = "List all store enterance records.")
public class StoreEnterancesController {
    private final IStoreEnteranceService storeEnterancesService;

    @GetMapping
    @ApiOperation(value = "A method to get all store enterances.")
    public ResponseEntity<List<StoreEnterancesDto>> listAll(){
        return ResponseEntity.ok(storeEnterancesService.getAll());
    }
}
