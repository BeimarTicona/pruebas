package com.velacuss.backend.service.impl;

import com.velacuss.backend.dao.FuncionarioDao;
import com.velacuss.backend.domain.Funcionario;
import com.velacuss.backend.domain.dto.FuncionarioDTO;
import com.velacuss.backend.exceptions.BadRequestException;
import com.velacuss.backend.exceptions.NotFoundException;
import com.velacuss.backend.mapper.FuncionarioMapper;
import com.velacuss.backend.service.FuncionarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;;

@Service
@Transactional
public class FuncionarioServiceImpl implements FuncionarioService {
    @Autowired
    private FuncionarioDao funcionarioDao;
    @Autowired
    private FuncionarioMapper funcionarioMapper;

    @Override
    public FuncionarioDTO guardar(FuncionarioDTO funcionarioDTO) {
        if(funcionarioDTO==null)
            throw new BadRequestException("La solicitud debe tener un funcionario");

        Funcionario funcionario = funcionarioMapper.toEntity(funcionarioDTO);
        funcionario.setLogtransId(1L);
        return funcionarioMapper.toDto(funcionarioDao.crudFuncionario(funcionario, 1));
    }

    @Override
    public FuncionarioDTO obtener(Long id) {
        Funcionario funcionario = funcionarioDao.obtenerFuncionarioPorId(id);
        if (funcionario == null)
            throw new NotFoundException("Funcionario no encontrado");
        return funcionarioMapper.toDto(funcionario);
    }

    @Override
    public FuncionarioDTO editar(FuncionarioDTO funcionarioDTO) {
        if(funcionarioDTO==null)
            throw new BadRequestException("La solicitud debe tener un funcionario");
        Funcionario funcionario = funcionarioDao.obtenerFuncionarioPorId(funcionarioDTO.getId());
        if (funcionario == null)
            throw new NotFoundException("Funcionario no encontrada");

        funcionarioMapper.updateToEntity(funcionarioDTO, funcionario);
        return funcionarioMapper.toDto(funcionarioDao.crudFuncionario(funcionario, 2));
    }

    @Override
    public void eliminar(Long id) {
        Funcionario funcionario = funcionarioDao.obtenerFuncionarioPorId(id);
        if (funcionario == null)
            throw new NotFoundException("Funcionario no encontrado");
        funcionarioDao.crudFuncionario(funcionario, 3);
    }

    @Override
    public List<FuncionarioDTO> listaFuncionarios() {
        return funcionarioMapper.toDto(funcionarioDao.listarFuncionario());
    }

    @Override
    public FuncionarioDTO obtenerFuncionarioPorLogin(String userName){
        return funcionarioMapper.toDto(funcionarioDao.obtenerFuncionarioPorUsuario(userName));
    }

    @Override
    public List<FuncionarioDTO> listaFuncionariosVendedores() {
        return funcionarioMapper.toDto(funcionarioDao.listarFuncionariosVendedores());
    }
}
