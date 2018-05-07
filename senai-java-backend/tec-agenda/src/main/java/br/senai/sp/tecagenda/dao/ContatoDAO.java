package br.senai.sp.tecagenda.dao;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import br.senai.sp.tecagenda.model.Contato;

@Component
public class ContatoDAO {
	private static List<Contato> contatos;
	{

		contatos = new ArrayList<Contato>();

		contatos.add(new Contato(1L, "Nome A", "12345678", "Endereço A", "a@a.a", "http://www.facebook.com/a", null, 5));
		contatos.add(new Contato(2L, "Nome B", "12345678", "Endereço B", "b@b.b", "http://www.facebook.com/b", null, 3));
		contatos.add(new Contato(3L, "Nome C", "12345678", "Endereço C", "c@c.c", "http://www.facebook.com/c", null, 4));
		contatos.add(new Contato(4L, "Nome D", "12345678", "Endereço D", "d@d.d", "http://www.facebook.com/d", null, 2));
		
	}

	/**
	 * 
	 * @return lista de contatos
	 */
	public List<Contato> list() {
		return contatos;
	}

	public Contato find(Long id) {
		for (Contato curso : contatos) {
			if (curso.getIdContato() == id) {
				return curso;
			}
		}
		return null;
	}

	/**
	 * Salva o objeto Curso.
	 * 
	 * @param c
	 * @return Curso
	 */
	public Contato save(Contato c) {
		c.setIdContato((long) (contatos.size() + 1));
		contatos.add(c);
		return c;
	}

	/**
	 * Remove o objeto Contato.
	 * 
	 * @param id
	 * @return Contato
	 */
	public Contato remove(Long id) {
		for (Contato curso : contatos) {
			if (curso.getIdContato() == id) {
				contatos.remove(curso);
				return curso;
			}
		}
		return null;
	}

	/**
	 * Atualizar o objeto Contato.
	 * 
	 * @param id
	 * @return Contato
	 */
	public Contato merge(Long id, Contato c) {
		for (Contato curso : contatos) {
			if (curso.getIdContato() == id) {
				c.setIdContato(curso.getIdContato());
				contatos.remove(curso);
				contatos.add(c);
				return curso;
			}
		}
		return null;

	}
}
