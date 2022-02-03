package com.velacuss.backend;

import com.velacuss.backend.dao.UsuarioDao;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BackendApplicationTests {

    @Autowired
    private UsuarioDao usuarioDao;

    @Test
    void contextLoads() {
    }

}
