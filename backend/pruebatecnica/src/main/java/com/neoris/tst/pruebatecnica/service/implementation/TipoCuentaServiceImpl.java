package com.neoris.tst.pruebatecnica.service.implementation;

import com.neoris.tst.pruebatecnica.domain.TipoCuenta;
import com.neoris.tst.pruebatecnica.exception.TipoCuentaException;
import com.neoris.tst.pruebatecnica.repository.TipoCuentaRepository;
import com.neoris.tst.pruebatecnica.service.TipoCuentaService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.neoris.tst.pruebatecnica.utility.MensajeExcepcionService.TIPO_CUENTA_NO_EXISTE_POR_DESCRIPCION_MENSAJE;

@Service
public class TipoCuentaServiceImpl implements TipoCuentaService {
    private final TipoCuentaRepository tipoCuentaRepository;


    public TipoCuentaServiceImpl(TipoCuentaRepository tipoCuentaRepository) {
        this.tipoCuentaRepository = tipoCuentaRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public TipoCuenta buscarTipoCuentaPorDescripcionYEstado(String descripcion, Boolean estado) throws TipoCuentaException {
        return tipoCuentaRepository.findByDescripcionAndEstado(descripcion, estado).orElseThrow(
                () ->new TipoCuentaException(
                        String.format(TIPO_CUENTA_NO_EXISTE_POR_DESCRIPCION_MENSAJE,
                                descripcion, estado))
        );
    }
}
