package br.edu.fema.usuario.controller;

import br.edu.fema.usuario.domain.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.fema.generics.PageResponseDTO;
import br.edu.fema.generics.PaginationRequest;
import br.edu.fema.usuario.dto.UsuarioConsultaDTO;
import br.edu.fema.usuario.service.UsuarioService;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
	@Autowired
	private UsuarioService usuarioService;
	@GetMapping()
	public PageResponseDTO<UsuarioConsultaDTO> recuperarTodosPaginados(PaginationRequest request) {
		return UsuarioConsultaDTO.converterParaPageResponseDTO(this.usuarioService.recuperarTodosPaginados(request));
	}

	@GetMapping("/usuario-ativo")
	public UsuarioConsultaDTO recuperarUsuarioSession() {
		return new UsuarioConsultaDTO(usuarioService.getUsuarioDaSession());
	}

	@PreAuthorize("hasRole('ROLE_USER')")
	@GetMapping("/{id}")
	public UsuarioConsultaDTO recuperarPorId(@PathVariable Long id) {
		return new UsuarioConsultaDTO((usuarioService.findById(id)));
	}
	
}
