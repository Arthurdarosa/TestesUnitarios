package repository;

import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import enums.EnumSexo;
import models.Pessoa;

public class BancoDadosTest {
	private BancoDados bd = new BancoDados();
	
	@BeforeEach
	void init() {
		
		bd.clearData();
		Pessoa p1 = new Pessoa("Pessoa 1", 10, EnumSexo.MASCULINO);
		Pessoa p2 = new Pessoa("Pessoa 2", 15, EnumSexo.FEMININO);
		Pessoa p3 = new Pessoa("Pessoa 3", 20, EnumSexo.MASCULINO);
		Pessoa p4 = new Pessoa("Pessoa 4", 25, EnumSexo.FEMININO);
		bd.add(p1);
		bd.add(p2);
		bd.add(p3);
		bd.add(p4);
	}
	
	@Test
	@DisplayName("teste listar todos")
	void testListAll() {
		List<Pessoa> cads = bd.listAll();
		assertEquals(4, cads.size());
		assertEquals("Pessoa 1", cads.get(0).getNome());
		bd.add(new Pessoa("pessoa 5", 30, EnumSexo.MASCULINO));
		cads = bd.listAll();
		assertEquals(5, cads.size());
		assertEquals(5, cads.get(4).getId());
	}
	
	@Test
    @DisplayName("Teste encontrar por ID")
    void testFindId() {
        assertNull(bd.findById(0));
        assertEquals("Pessoa 1", bd.findById(1).getNome());
        assertEquals("Pessoa 2", bd.findById(2).getNome());
    }
	
	@Test
	@DisplayName("teste updade")
	void testUpdade() {
		List<Pessoa> cads = bd.listAll();
		
		Pessoa p5 = new Pessoa("Pessoa 5", 35, EnumSexo.MASCULINO); ;
		bd.update(3,p5);
		cads = bd.listAll();
		assertEquals("Pessoa 5", cads.get(3).getNome());
		assertEquals(35, cads.get(3).getIdade());
		assertEquals( EnumSexo.MASCULINO, cads.get(3).getSexo());
		
		bd.update(4,p5);
		cads = bd.listAll();
		assertEquals("Pessoa 5", cads.get(3).getNome());
		assertEquals(35, cads.get(3).getIdade());
		assertEquals( EnumSexo.MASCULINO, cads.get(3).getSexo());
	}
	
	@Test
    @DisplayName("Teste excluir")
    void testDelete() {
        bd.delete(2);
        List<Pessoa> cads = bd.listAll();
        assertEquals(3, cads.size());
        assertNull(bd.findById(2));

        bd.delete(5);
        cads = bd.listAll();
        assertEquals(3, cads.size());
    }
	


}
