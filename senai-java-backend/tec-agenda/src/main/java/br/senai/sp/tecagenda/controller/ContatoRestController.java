package br.senai.sp.tecagenda.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.senai.sp.tecagenda.dao.ContatoDAO;
import br.senai.sp.tecagenda.model.Contato;

@RestController
@RequestMapping("api/contatos")
@SuppressWarnings({ "unchecked", "rawtypes" })
public class ContatoRestController {

	@Autowired
	private ContatoDAO contatoDAO;

	@GetMapping
	public ResponseEntity<List<Contato>> list() {
		List<Contato> cursos = contatoDAO.list();
		if (cursos.isEmpty()) {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Contato>>(cursos, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Contato> find(@PathVariable("id") Long id) {
		Contato contato = contatoDAO.find(id);
		if (contato == null)
			return new ResponseEntity<Contato>(HttpStatus.NOT_FOUND);
		return new ResponseEntity<Contato>(contato, HttpStatus.OK);

	}

	@PostMapping
	public ResponseEntity<List<Contato>> save(@RequestBody Contato contato) {
		contatoDAO.save(contato);
		return new ResponseEntity<List<Contato>>(contatoDAO.list(), HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public void remove(@PathVariable("id") Long id) {
		contatoDAO.remove(id);
	}

	@PutMapping("/{id}")
	public ResponseEntity<?> merge(@PathVariable Long id, @RequestBody Contato contato) {
		contatoDAO.merge(id, contato);
		return new ResponseEntity<Contato>(HttpStatus.OK);
	}

}
