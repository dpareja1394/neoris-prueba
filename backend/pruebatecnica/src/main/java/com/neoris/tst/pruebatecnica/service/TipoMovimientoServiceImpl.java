package com.neoris.tst.pruebatecnica.service;

import com.neoris.tst.pruebatecnica.domain.TipoMovimiento;
import com.neoris.tst.pruebatecnica.exception.TipoMovimientoException;
import com.neoris.tst.pruebatecnica.repository.TipoMovimientoRepository;
import org.springframework.stereotype.Service;

@Service
public class TipoMovimientoServiceImpl implements TipoMovimientoService{
    private final TipoMovimientoRepository tipoMovimientoRepository;

    public final static String TIPO_MOVIMIENTO_NO_EXISTE_POR_DESCRIPCION_MENSAJE =
            "El Tipo de Movimiento %s con estado %s no existe en el sistema";

    public TipoMovimientoServiceImpl(TipoMovimientoRepository tipoMovimientoRepository) {
        this.tipoMovimientoRepository = tipoMovimientoRepository;
    }


    @Override
    public TipoMovimiento buscarPorDescripcionYEstado(String descripcion, Boolean estado) throws TipoMovimientoException {
        return tipoMovimientoRepository.findByDescripcionAndEstado(descripcion, estado).orElseThrow(
                () ->new TipoMovimientoException(
                        String.format(TIPO_MOVIMIENTO_NO_EXISTE_POR_DESCRIPCION_MENSAJE,
                                descripcion, estado))
        );
    }
}
