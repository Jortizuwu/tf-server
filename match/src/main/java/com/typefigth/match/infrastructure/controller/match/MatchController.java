package com.typefigth.match.infrastructure.controller.match;

import com.typefigth.match.application.response.Response;
import com.typefigth.match.application.services.match.MatchService;
import com.typefigth.match.domain.models.Match;
import com.typefigth.match.infrastructure.exceptions.ResourceNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/match")
public class MatchController {

    private final MatchService matchServices;

    public MatchController(MatchService matchServices) {
        this.matchServices = matchServices;
    }

    @GetMapping()
    public ResponseEntity<Response> listMatches(HttpServletRequest request) {
        List<Match> user = this.matchServices.listMatch();
        Response response = Response.build(200, request.getContextPath(), true, user, false, getClientIp(request), "OK", HttpStatus.OK.toString());
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Response> getMatch(@PathVariable String id, HttpServletRequest request) {
        Match user = this.matchServices.findById(id).orElseThrow(() -> new ResourceNotFoundException(String.format("error match with id: %s not found", id)));
        Response response = Response.build(200, request.getContextPath(), true, user, false, getClientIp(request), "OK", HttpStatus.OK.toString());
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PostMapping
    public ResponseEntity<Response> createMatch(@RequestBody Match body, HttpServletRequest request) {
        Match newMatch = this.matchServices.createMatch(body);
        Response response = Response.build(200, request.getContextPath(), true, newMatch, false, getClientIp(request), "OK", HttpStatus.OK.toString());
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    private String getClientIp(HttpServletRequest request) {
        String remoteAddr = "";

        if (request != null) {
            remoteAddr = request.getHeader("X-FORWARDED-FOR");
            if (remoteAddr == null || remoteAddr.isEmpty()) {
                remoteAddr = request.getRemoteAddr();
            }
        }

        return remoteAddr;
    }
}
