package com.lojaTarget.service;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.lojaTarget.entity.Estoque;
import com.lojaTarget.entity.MovimentacaoEstoque;
import com.lojaTarget.enums.TipoDeMovimentacaoEstoque;
import com.lojaTarget.repository.EstoqueRepository;
import com.lojaTarget.repository.MovimentacaoEstoqueRepository;

@Service
public class EstoqueService {

	@Autowired
	private EstoqueRepository estoqueRepository;
	
	@Autowired
	private MovimentacaoEstoqueRepository movimentacaoEstoqueRepository;

	public Estoque movimentarEstoque(Long codigoProduto, Integer quantidade, TipoDeMovimentacaoEstoque descricao) {
		
		//verificar se o produto informado existe no estoque
        Estoque estoque = estoqueRepository.findById(codigoProduto).orElseThrow(() -> new RuntimeException("Produto não encontrado"));
        
        //movimentar entrada no estoque
        if(descricao == TipoDeMovimentacaoEstoque.ENTRADA) {
            estoque.setEstoque(estoque.getEstoque() + quantidade);
        } else { //movimentar saída do estoque
            if(estoque.getEstoque() < quantidade) {
                throw new RuntimeException("Estoque insuficiente, quantidade atual: " + estoque.getEstoque());
            }else
            	estoque.setEstoque(estoque.getEstoque() - quantidade);
        }

        estoqueRepository.save(estoque);
		
		//Criar a movimentação, vincular ao produto e salva no banco
		MovimentacaoEstoque movimentacao = new MovimentacaoEstoque();
		movimentacao.setQuantidade(quantidade);
		movimentacao.setDescricao(descricao);
		movimentacao.setEstoque(estoque);
		this.movimentacaoEstoqueRepository.save(movimentacao);
		
		//retorna o produto do estoque com a movimentacão realizada
		return estoque;
	}
	
	public String salvarEstoque(List<Estoque> estoque) {
		this.estoqueRepository.saveAll(estoque);
		return "Estoque salvo com sucesso.";
	}
	
}
