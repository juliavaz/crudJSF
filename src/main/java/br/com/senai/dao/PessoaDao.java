package br.com.senai.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.senai.conexao.FabricaConexao;
import br.com.senai.modelo.Pessoa;

public class PessoaDao {
	
	public void salvar(Pessoa pessoa) {
		Connection conexao = FabricaConexao.getConnection();
		PreparedStatement ps;
		try {
			
			if(pessoa.getIdPessoa() == null) {
				ps = conexao.prepareStatement("INSERT INTO pessoa(nome, email, cpf, telefone, endereco, sexo)value(?, ?, ?, ?, ?, ?)");
			}else {
				ps = conexao.prepareStatement("update pessoa set nome=?, email=?, cpf=?, telefone=?, endereco=?, sexo=? where idPessoa=?");
				ps.setInt(7, pessoa.getIdPessoa());
			}
			ps.setString(1, pessoa.getNomePessoa());
			ps.setString(2, pessoa.getEmailPessoa());
			ps.setString(3, pessoa.getCpfPessoa());
			ps.setString(4, pessoa.getTelefone());
			ps.setString(5, pessoa.getEnderecoPessoa());
			ps.setString(6, pessoa.getSexoPessoa());
			
			ps.execute();
			ps.close();
			conexao.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public List<Pessoa> buscar(){
		Connection conexao = FabricaConexao.getConnection();
		try {
			PreparedStatement ps = conexao.prepareStatement("select * from pessoa");
			ResultSet resultSet = ps.executeQuery();
			List<Pessoa> pessoas = new ArrayList<>();
			while(resultSet.next()) {
				Pessoa pessoa = new Pessoa();
				pessoa.setIdPessoa(resultSet.getInt("idPessoa"));
				pessoa.setNomePessoa(resultSet.getString("nome"));
				pessoa.setEmailPessoa(resultSet.getString("email"));
				pessoa.setCpfPessoa(resultSet.getString("cpf"));
				pessoa.setTelefone(resultSet.getString("telefone"));
				pessoa.setEnderecoPessoa(resultSet.getString("endereco"));
				pessoa.setSexoPessoa(resultSet.getString("sexo"));
				pessoas.add(pessoa);
			}
			ps.close();
			conexao.close();
			return pessoas;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	public void deletar(Integer idPessoa) {
		
		try {
			Connection conexao = FabricaConexao.getConnection();
			PreparedStatement ps = conexao.prepareStatement("delete from pessoa where idPessoa= ?");
			ps.setInt(1, idPessoa);
			ps.execute();
			
			ps.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
