package com.nmptt.ticketapi.controller;

import com.nmptt.ticketapi.dto.request.GheRequest;
import com.nmptt.ticketapi.dto.response.GheResponse;
import com.nmptt.ticketapi.service.GheService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ghe")
@AllArgsConstructor
public class GheController {
    private final GheService gheService;

    @GetMapping
    public ResponseEntity<List<GheResponse>> getAllGhe() {
        return ResponseEntity.ok(gheService.getAllGhe());
    }

    @GetMapping("/{id}")
    public ResponseEntity<GheResponse> getGheById(@PathVariable Integer id) {
        return ResponseEntity.ok(gheService.getGheById(id));
    }

    @PostMapping
    public ResponseEntity<GheResponse> createGhe(@RequestBody GheRequest gheRequest) {
        return ResponseEntity.ok(gheService.createGhe(gheRequest));
    }

    @PutMapping
    public ResponseEntity<GheResponse> updateGhe(@RequestBody GheRequest gheRequest) {
        return ResponseEntity.ok(gheService.updateGhe(gheRequest));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteGhe(@PathVariable Integer id) {
        gheService.deleteGhe(id);
        return ResponseEntity.ok("Xóa ghế thành công!");
    }

    @GetMapping("/search")
    public ResponseEntity<List<GheResponse>> searchGhe(
            @RequestParam(required = false) String soGhe,
            @RequestParam(required = false) Integer idToaTau) {
        return ResponseEntity.ok(gheService.searchGhe(soGhe, idToaTau));
    }
}