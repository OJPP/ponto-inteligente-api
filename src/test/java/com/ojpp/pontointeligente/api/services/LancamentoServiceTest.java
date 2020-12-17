package com.ojpp.pontointeligente.api.services;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.ojpp.pontointeligente.api.entities.Lancamento;
import com.ojpp.pontointeligente.api.repositories.LancamentoRepository;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@ActiveProfiles("test")
public class LancamentoServiceTest {

	@MockBean
	private LancamentoRepository lancamentoRepository;

	@Autowired
	private LancamentoService lancamentoService;

	@BeforeEach
	public void setUp() throws Exception {
		BDDMockito
				.given(this.lancamentoRepository.findByFuncionarioId(Mockito.anyLong(), Mockito.any(PageRequest.class)))
				.willReturn(new PageImpl<Lancamento>(new ArrayList<Lancamento>()));
		BDDMockito
				.given(this.lancamentoRepository.findById(Mockito.anyLong()))
				.willReturn(Optional.ofNullable(new Lancamento()));
		BDDMockito
				.given(this.lancamentoRepository.save(Mockito.any(Lancamento.class)))
				.willReturn(new Lancamento());
	}

	@Test
	@DisplayName("Buscar Lancamento Por Funcionario Id.")
	public void testBuscarLancamentoPorFuncionarioId() {

		PageRequest page = PageRequest.of(0, 10, Sort.by("id"));
		Page<Lancamento> lancamento = this.lancamentoService.buscarPorFuncionarioId(1L, page);

		assertNotNull(lancamento);
	}

	@Test
	@DisplayName("Buscar Lancamento Por Id.")
	public void testBuscarLancamentoPorId() {

		Optional<Lancamento> lancamento = this.lancamentoService.buscarPorId(1L);

		assertTrue(lancamento.isPresent());
	}

	@Test
	@DisplayName("Persistir Lancamento.")
	public void testPersistirLancamento() {

		Lancamento lancamento = this.lancamentoService.persistir(new Lancamento());

		assertNotNull(lancamento);
	}

}
