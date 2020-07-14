package br.com.senai.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.SessionScoped;

import javax.inject.Inject;
import javax.inject.Named;

import br.com.senai.dao.PessoaDao;
import br.com.senai.modelo.Pessoa;

@SessionScoped
@Named("pb")
public class PessoaBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private Pessoa pessoa = new Pessoa();
	private PessoaDao pdao = new PessoaDao();

	private List<Pessoa> pessoas = new ArrayList<>();

	public void adicionar() {
		pessoas.add(pessoa);
		pdao.salvar(pessoa);
		pessoa = new Pessoa();
	}

	public void listar() {
		pessoas = pdao.buscar();
	}

	public void editar(Pessoa p) {
		pessoa = p;
	}

	public void deletar(Pessoa p) {

		pdao.deletar(p.getIdPessoa());

	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	public List<Pessoa> getPessoas() {
		return pessoas;
	}

	public void setPessoas(List<Pessoa> pessoas) {
		this.pessoas = pessoas;
	}

}
