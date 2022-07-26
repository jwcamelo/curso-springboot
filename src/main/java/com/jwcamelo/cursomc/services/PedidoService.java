package com.jwcamelo.cursomc.services;

import java.util.Date;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jwcamelo.cursomc.domain.ItemPedido;
import com.jwcamelo.cursomc.domain.PagamentoComBoleto;
import com.jwcamelo.cursomc.domain.Pedido;
import com.jwcamelo.cursomc.domain.enums.EstadoPagamento;
import com.jwcamelo.cursomc.repositories.ItemPedidoRepository;
import com.jwcamelo.cursomc.repositories.PagamentoRepository;
import com.jwcamelo.cursomc.repositories.PedidoRepository;
import com.jwcamelo.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class PedidoService {

    private final PedidoRepository repo;
    private final BoletoService boletoService;
    private final PagamentoRepository pagamentoRepository;
    private final ProdutoService produtoService;
    private final ItemPedidoRepository ipRepository;

    public PedidoService(PedidoRepository repo, BoletoService boletoService, 
    		PagamentoRepository pagamentoRepository,ProdutoService produtoService,ItemPedidoRepository ipRepository) {
        this.repo = repo;
        this.boletoService = boletoService;
        this.pagamentoRepository = pagamentoRepository;
        this.produtoService = produtoService;
        this.ipRepository = ipRepository;
    }

    public Optional<Pedido> find(Integer id){
    	Optional<Pedido> pedido = repo.findById(id);
    	if(!pedido.isPresent())
    		throw new ObjectNotFoundException("Objeto não encontrado: Id: "+id+" , Tipo: "+Pedido.class);
    	return pedido;
    }

    @Transactional
	public Pedido insert( Pedido obj) {
		obj.setId(null);
		obj.setInstante(new Date());
		obj.getPagamento().setEstado(EstadoPagamento.PENDENTE);
		obj.getPagamento().setPedido(obj);
		if(obj.getPagamento() instanceof PagamentoComBoleto) {
			PagamentoComBoleto pgto = (PagamentoComBoleto) obj.getPagamento();
			boletoService.preencherPagamentoComBoleto(pgto, obj.getInstante());
		}
		obj = repo.save(obj);
		pagamentoRepository.save(obj.getPagamento());
		for(ItemPedido ip: obj.getItens()) {
			ip.setDesconto(0.0);
			ip.setPreco(produtoService.find(ip.getProduto().getId()).get().getPreco());
			ip.setPedido(obj);
			
		}
		ipRepository.saveAll(obj.getItens());
		return obj;
	}

}
