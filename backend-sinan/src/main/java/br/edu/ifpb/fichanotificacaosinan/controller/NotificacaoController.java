package br.edu.ifpb.fichanotificacaosinan.controller;

import br.edu.ifpb.fichanotificacaosinan.model.Notificacao;
import br.edu.ifpb.fichanotificacaosinan.service.FiltroNotificacao;
import br.edu.ifpb.fichanotificacaosinan.service.NotificacaoService;
import jakarta.validation.Valid;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/notificacao")
public class NotificacaoController {

    private final NotificacaoService service;

    public NotificacaoController(NotificacaoService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Notificacao> criar(@Valid @RequestBody Notificacao notificacao) {
        Notificacao criada = service.criar(notificacao);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(criada.getId())
                .toUri();

        return ResponseEntity.created(location).body(criada);
    }
    @GetMapping
    public ResponseEntity<List<Notificacao>> listar(
            @RequestParam(required = false) String numeroNotificacao,
            @RequestParam(required = false) String agravo,
            @RequestParam(required = false) String nomePaciente,
            @RequestParam(required = false) String ufResidencia,
            @RequestParam(required = false) String municipioResidencia,
            @RequestParam(required = false) Integer classificacaoFinal,
            @RequestParam(required = false)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataNotificacaoInicio,
            @RequestParam(required = false)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataNotificacaoFim) {

        FiltroNotificacao filtro = new FiltroNotificacao(
                numeroNotificacao, agravo, nomePaciente, ufResidencia, municipioResidencia,
                classificacaoFinal, dataNotificacaoInicio, dataNotificacaoFim);
        return ResponseEntity.ok(service.listar(filtro));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Notificacao> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(service.buscarPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Notificacao> atualizar(@PathVariable Long id,
                                                 @Valid @RequestBody Notificacao notificacao) {
        return ResponseEntity.ok(service.atualizar(id, notificacao));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remover(@PathVariable Long id) {
        service.remover(id);
        return ResponseEntity.noContent().build();
    }

}