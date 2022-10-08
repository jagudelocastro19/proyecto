package com.proyecto.proyecto;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
class ProyectoApplicationTests {

	@Autowired
	private RolRepository rolRepo;
	@Autowired
	private UsuarioRepository usrRepo;
	@Autowired
	private CategoriaRepository catRepo;
	@Autowired
	private ProductoRepository pdtRepo;

	@Test
	public void contextLoads() {
		Long numRoles = rolRepo.count();
		assertNotNull(numRoles);
		Long numUsrs = usrRepo.count();
		assertNotNull(numUsrs);
		Long numCats = catRepo.count();
		assertNotNull(numCats);
		Long numPdts = pdtRepo.count();
		assertNotNull(numPdts);

	}

}
