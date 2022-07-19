package com.jwcamelo.cursomc.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Produto implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nome;
    private Double preco;
    

    @JsonIgnore
    @OneToMany(mappedBy="id.produto")
    private Set<ItemPedido> itens = new HashSet<>();
    

    public Produto(){}

    public Produto(Integer id, String nome, Double preco){
        super();
        this.id=id;
        this.nome=nome;
        this.preco=preco;
    }
    
    @JsonIgnore
    public List<Pedido> getPedidos(){
    	List<Pedido> lista = new ArrayList<>();
    	for(ItemPedido x: itens)
    		lista.add(x.getPedido());
    	return lista;
    }

    @JsonIgnore
    @ManyToMany
    @JoinTable(name = "PRODUTO_CATEGORIA",
    joinColumns = @JoinColumn(name="produto_id"),
    inverseJoinColumns = @JoinColumn(name="categoria_id"))
    private List<Categoria> categorias = new ArrayList<>();

    
    @JsonIgnore
    public Set<ItemPedido> getItems() {
		return itens;
	}

    
	public void setItems(Set<ItemPedido> itens) {
		this.itens = itens;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    public List<Categoria> getCategorias() {
        return categorias;
    }

    public void setCategorias(List<Categoria> categorias) {
        this.categorias = categorias;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Produto produto = (Produto) o;
        return Objects.equals(id, produto.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
