package com.neoris.tst.pruebatecnica.service;

import com.neoris.tst.pruebatecnica.domain.TipoCuenta;
import com.neoris.tst.pruebatecnica.exception.PersonaNoExistePorNombre;
import com.neoris.tst.pruebatecnica.exception.TipoCuentaNoExistePorDescripcion;
import com.neoris.tst.pruebatecnica.repository.TipoCuentaRepository;
import org.springframework.stereotype.Service;

import static com.neoris.tst.pruebatecnica.utility.MensajeExcepcionService.TIPO_CUENTA_NO_EXISTE_POR_DESCRIPCION_MENSAJE;

@Service
public class TipoCuentaServiceImpl implements TipoCuentaService{
    private final TipoCuentaRepository tipoCuentaRepository;


    public TipoCuentaServiceImpl(TipoCuentaRepository tipoCuentaRepository) {
        this.tipoCuentaRepository = tipoCuentaRepository;
    }

    @Override
    public TipoCuenta buscarTipoCuentaPorDescripcionYEstado(String descripcion, Boolean estado) throws TipoCuentaNoExistePorDescripcion {
        return tipoCuentaRepository.findByDescripcionAndEstado(descripcion, estado).orElseThrow(
                () ->new TipoCuentaNoExistePorDescripcion(
                        String.format(TIPO_CUENTA_NO_EXISTE_POR_DESCRIPCION_MENSAJE,
                                descripcion, estado))
        );
    }
}
