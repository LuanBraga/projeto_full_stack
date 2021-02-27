package com.luanbraga.projetofullstack;

import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.luanbraga.projetofullstack.domain.Categoria;
import com.luanbraga.projetofullstack.domain.Cidade;
import com.luanbraga.projetofullstack.domain.Cliente;
import com.luanbraga.projetofullstack.domain.Endereco;
import com.luanbraga.projetofullstack.domain.Estado;
import com.luanbraga.projetofullstack.domain.Pagamento;
import com.luanbraga.projetofullstack.domain.PagamentoComBoleto;
import com.luanbraga.projetofullstack.domain.PagamentoComCartão;
import com.luanbraga.projetofullstack.domain.Pedido;
import com.luanbraga.projetofullstack.domain.Produto;
import com.luanbraga.projetofullstack.domain.enums.EstadoPagamento;
import com.luanbraga.projetofullstack.domain.enums.TipoCliente;
import com.luanbraga.projetofullstack.repositories.CategoriaRepository;
import com.luanbraga.projetofullstack.repositories.CidadeRepository;
import com.luanbraga.projetofullstack.repositories.ClienteRepository;
import com.luanbraga.projetofullstack.repositories.EnderecoRepository;
import com.luanbraga.projetofullstack.repositories.EstadoRepository;
import com.luanbraga.projetofullstack.repositories.PagamentoRepository;
import com.luanbraga.projetofullstack.repositories.PedidoRepository;
import com.luanbraga.projetofullstack.repositories.ProdutoRepository;

@SpringBootApplication
public class ProjetofullstackApplication implements CommandLineRunner{
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired
	private EstadoRepository estadoRepository;
	
	@Autowired
	private CidadeRepository cidadeRepository;
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private EnderecoRepository enderecoRepository;
	
	@Autowired
	private PagamentoRepository pagamentoRepository;
	
	@Autowired
	private PedidoRepository pedidoRepository;

	public static void main(String[] args) {
		SpringApplication.run(ProjetofullstackApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		Categoria cat1 = new Categoria(null, "informática");
		Categoria cat2 = new Categoria(null, "escritório");
		
		Produto p1 = new Produto(null, "Computador", 2000.00);
		Produto p2 = new Produto(null, "Impressora", 800.00);
		Produto p3 = new Produto(null, "Mouse", 80.00);
		
		cat1.getProdutos().addAll(Arrays.asList(p1, p2, p3));
		cat2.getProdutos().addAll(Arrays.asList(p2));
		
		p1.getCategorias().addAll(Arrays.asList(cat1));
		p2.getCategorias().addAll(Arrays.asList(cat1, cat2));
		p3.getCategorias().addAll(Arrays.asList(cat1));
		
		categoriaRepository.saveAll(Arrays.asList(cat1, cat2));
		produtoRepository.saveAll(Arrays.asList(p1, p2, p3));
		
		Estado est1 = new Estado(null, "Minas Gerais");
		Estado est2 = new Estado(null, "Rio de Janeiro");
		
		Cidade c1 = new Cidade(null, "Alto Caparaó", est1);
		Cidade c2 = new Cidade(null,"Nova Iguaçu", est2);
		Cidade c3 = new Cidade(null, "Queimados", est2);
		
		est1.getCidades().addAll(Arrays.asList(c1));
		est2.getCidades().addAll(Arrays.asList(c2, c3));
		
		estadoRepository.saveAll(Arrays.asList(est1, est2));
		cidadeRepository.saveAll(Arrays.asList(c1, c2, c3));
		
		Cliente cli1 = new Cliente(null, "Letício", "leticio@gmail.com", "36378912377", TipoCliente.PESSOAFISICA);
		
		Endereco e1 = new Endereco(null, "Rua sem saída", "666", "casa 02", "Cabuçu", "38220834", cli1, c2);
		Endereco e2 = new Endereco(null, "Rua do queijo", "171", "apto 420", "Centro", "36777012", cli1, c1);
		
		cli1.getEnderecos().addAll(Arrays.asList(e1, e2));
		
		clienteRepository.saveAll(Arrays.asList(cli1));
		enderecoRepository.saveAll(Arrays.asList(e1, e2));
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		
		Pedido ped1 = new Pedido(null, sdf.parse("26/02/2021 10:32"), cli1, e1);
		Pedido ped2 = new Pedido(null, sdf.parse("22/02/2021 08:50"), cli1, e2);
		
		Pagamento pagto1 = new PagamentoComCartão(null, EstadoPagamento.QUITADO, ped1, 6);
		ped1.setPagamento(pagto1);
		
		Pagamento pagto2 = new PagamentoComBoleto(null, EstadoPagamento.PENDENTE, ped2, sdf.parse("20/10/2021 00:00"), null);
		ped2.setPagamento(pagto2);
		
		cli1.setPedidos(Arrays.asList(ped1, ped2));
		
		pedidoRepository.saveAll(Arrays.asList(ped1, ped2));
		pagamentoRepository.saveAll(Arrays.asList(pagto1, pagto2));
	}
}
