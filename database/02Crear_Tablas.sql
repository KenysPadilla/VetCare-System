CREATE TABLE PROPIETARIO(
    id_propietario NUMBER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    cedula VARCHAR2(20) NOT NULL UNIQUE,
    nombre VARCHAR2(50) NOT NULL,
    apellido VARCHAR2(50) NOT NULL,
    telefono VARCHAR2(15),
    correo VARCHAR2(100),
    direccion VARCHAR2(200),
    fecha_registro DATE DEFAULT SYSDATE NOT NULL
);

CREATE TABLE VETERINARIO(
    id_veterinario NUMBER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    cedula VARCHAR2(20) NOT NULL UNIQUE,
    nombre VARCHAR2(50) NOT NULL,
    apellido VARCHAR2(50) NOT NULL,
    telefono VARCHAR2(15),
    correo VARCHAR2(100),
    especialidad VARCHAR2(100) NOT NULL,
    licencia VARCHAR2(50) NOT NULL UNIQUE
);

CREATE TABLE ESTILISTA(
    id_estilista NUMBER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    cedula VARCHAR2(20) NOT NULL UNIQUE,
    nombre VARCHAR2(50) NOT NULL,
    apellido VARCHAR2(50) NOT NULL,
    telefono VARCHAR2(15),
    correo VARCHAR2(100),
    especialidad_estetica VARCHAR2(100) NOT NULL
);

CREATE TABLE VACUNA(
    id_vacuna NUMBER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    nombre VARCHAR2(100) NOT NULL,
    laboratorio VARCHAR2(100) NOT NULL,
    lote VARCHAR2(50) NOT NULL,
    fecha_vencimiento DATE NOT NULL
);

CREATE TABLE MEDICAMENTO(
    id_medicamento NUMBER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    nombre_comercial VARCHAR2(100) NOT NULL,
    principio_activo VARCHAR2(100) NOT NULL,
    presentacion VARCHAR2(50) NOT NULL,
    dosis_estandar VARCHAR2(100),
    stock_actual NUMBER(6) DEFAULT 0 NOT NULL,
    precio_unitario NUMBER(10,2) NOT NULL,
    fecha_vencimiento DATE NOT NULL,
    CONSTRAINT chk_stock CHECK(stock_actual >= 0),
    CONSTRAINT chk_precio CHECK(precio_unitario >= 0)
);

CREATE TABLE PACIENTE(
    id_paciente NUMBER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    id_propietario NUMBER NOT NULL,
    nombre VARCHAR2(50) NOT NULL,
    especie VARCHAR2(50) NOT NULL,
    raza VARCHAR2(50),
    sexo CHAR(1) NOT NULL,
    peso_kg NUMBER(5,2) NOT NULL,
    fecha_nacimiento DATE,
    microchip VARCHAR2(50) UNIQUE,
    CONSTRAINT fk_paciente_propietario FOREIGN KEY(id_propietario)
        REFERENCES PROPIETARIO(id_propietario) ON DELETE CASCADE,
    CONSTRAINT chk_sexo CHECK(sexo IN ('M', 'F')),
    CONSTRAINT chk_peso CHECK(peso_kg > 0)
);

CREATE TABLE CITA(
    id_cita NUMBER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    id_paciente NUMBER NOT NULL,
    id_veterinario NUMBER NOT NULL,
    fecha_hora TIMESTAMP NOT NULL,
    tipo_cita VARCHAR2(30) NOT NULL,
    motivo VARCHAR2(300),
    estado VARCHAR2(20) DEFAULT 'PROGRAMADA' NOT NULL,
    notas VARCHAR2(500),
    CONSTRAINT fk_cita_paciente FOREIGN KEY(id_paciente)
        REFERENCES PACIENTE(id_paciente),
    CONSTRAINT fk_cita_veterinario FOREIGN KEY(id_veterinario)
        REFERENCES VETERINARIO(id_veterinario),
    CONSTRAINT chk_tipo_cita CHECK(
        tipo_cita IN ('CONSULTA_GENERAL','VACUNACION','CIRUGIA','INTERNACION','CONTROL')
    ),
    CONSTRAINT chk_estado_cita CHECK(
        estado IN ('PROGRAMADA','EN_CURSO','COMPLETADA','CANCELADA')
    )
);

CREATE TABLE SERVICIO_ESTETICO(
    id_servicio NUMBER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    id_paciente NUMBER NOT NULL,
    id_estilista NUMBER NOT NULL,
    tipo_servicio VARCHAR2(10) NOT NULL,
    fecha_hora TIMESTAMP NOT NULL,
    estado VARCHAR2(20) DEFAULT 'PROGRAMADO' NOT NULL,
    costo NUMBER(10,2) NOT NULL,
    observaciones VARCHAR2(500),
    CONSTRAINT fk_serv_paciente FOREIGN KEY(id_paciente)
        REFERENCES PACIENTE(id_paciente),
    CONSTRAINT fk_serv_estilista FOREIGN KEY(id_estilista)
        REFERENCES ESTILISTA(id_estilista),
    CONSTRAINT chk_tipo_servicio CHECK(tipo_servicio IN ('BANO','MOTILADA')),
    CONSTRAINT chk_estado_serv CHECK(
        estado IN ('PROGRAMADO','EN_PROCESO','COMPLETADO','CANCELADO')
    ),
    CONSTRAINT chk_costo_serv CHECK(costo >= 0)
);

CREATE TABLE BANO(
    id_servicio NUMBER PRIMARY KEY,
    tipo_bano VARCHAR2(20) NOT NULL,
    incluye_secado CHAR(1) DEFAULT 'N' NOT NULL,
    incluye_perfume CHAR(1) DEFAULT 'N' NOT NULL,
    CONSTRAINT fk_bano_servicio FOREIGN KEY(id_servicio)
        REFERENCES SERVICIO_ESTETICO(id_servicio) ON DELETE CASCADE,
    CONSTRAINT chk_tipo_bano CHECK(
        tipo_bano IN ('BASICO','MEDICADO','ANTIPULGAS','HIDRATANTE')
    ),
    CONSTRAINT chk_secado CHECK(incluye_secado IN ('S','N')),
    CONSTRAINT chk_perfume CHECK(incluye_perfume IN ('S','N'))
);

CREATE TABLE MOTILADA(
    id_servicio NUMBER PRIMARY KEY,
    estilo_corte VARCHAR2(50) NOT NULL,
    largo VARCHAR2(10) NOT NULL,
    incluye_unas CHAR(1) DEFAULT 'N' NOT NULL,
    incluye_limpieza CHAR(1) DEFAULT 'N' NOT NULL,
    CONSTRAINT fk_motilada_servicio FOREIGN KEY(id_servicio)
        REFERENCES SERVICIO_ESTETICO(id_servicio) ON DELETE CASCADE,
    CONSTRAINT chk_largo CHECK(largo IN ('CORTO','MEDIANO','LARGO')),
    CONSTRAINT chk_unas CHECK(incluye_unas IN ('S','N')),
    CONSTRAINT chk_limpieza CHECK(incluye_limpieza IN ('S','N'))
);

CREATE TABLE CONSULTA(
    id_consulta NUMBER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    id_cita NUMBER NOT NULL UNIQUE,
    fecha_hora TIMESTAMP DEFAULT SYSTIMESTAMP NOT NULL,
    sintomas VARCHAR2(500),
    diagnostico VARCHAR2(500) NOT NULL,
    tratamiento VARCHAR2(500),
    motivo VARCHAR2(300),
    CONSTRAINT fk_consulta_cita FOREIGN KEY(id_cita)
        REFERENCES CITA(id_cita)
);

CREATE TABLE PRESCRIPCION(
    id_prescripcion NUMBER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    id_consulta NUMBER NOT NULL,
    dosis VARCHAR2(100) NOT NULL,
    frecuencia VARCHAR2(100) NOT NULL,
    duracion_dias NUMBER(4) NOT NULL,
    instrucciones VARCHAR2(500),
    CONSTRAINT fk_presc_consulta FOREIGN KEY(id_consulta)
        REFERENCES CONSULTA(id_consulta) ON DELETE CASCADE,
    CONSTRAINT chk_duracion CHECK(duracion_dias > 0)
);

CREATE TABLE PRESCRIPCION_MEDICAMENTO(
    id_prescripcion NUMBER NOT NULL,
    id_medicamento NUMBER NOT NULL,
    cantidad NUMBER(4) DEFAULT 1 NOT NULL,
    CONSTRAINT pk_presc_med PRIMARY KEY(id_prescripcion, id_medicamento),
    CONSTRAINT fk_pm_prescripcion FOREIGN KEY(id_prescripcion)
        REFERENCES PRESCRIPCION(id_prescripcion) ON DELETE CASCADE,
    CONSTRAINT fk_pm_medicamento FOREIGN KEY(id_medicamento)
        REFERENCES MEDICAMENTO(id_medicamento),
    CONSTRAINT chk_cantidad CHECK(cantidad > 0)
);

CREATE TABLE VACUNACION(
    id_vacunacion NUMBER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    id_paciente NUMBER NOT NULL,
    id_vacuna NUMBER NOT NULL,
    id_veterinario NUMBER NOT NULL,
    fecha_aplicacion DATE NOT NULL,
    fecha_proxima DATE,
    observaciones VARCHAR2(300),
    CONSTRAINT fk_vacunacion_paciente FOREIGN KEY(id_paciente)
        REFERENCES PACIENTE(id_paciente),
    CONSTRAINT fk_vacunacion_vacuna FOREIGN KEY(id_vacuna)
        REFERENCES VACUNA(id_vacuna),
    CONSTRAINT fk_vacunacion_veterinario FOREIGN KEY(id_veterinario)
        REFERENCES VETERINARIO(id_veterinario)
);

CREATE TABLE CIRUGIA(
    id_cirugia NUMBER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    id_paciente NUMBER NOT NULL,
    id_veterinario NUMBER NOT NULL,
    tipo_cirugia VARCHAR2(100) NOT NULL,
    fecha_hora TIMESTAMP NOT NULL,
    anestesia VARCHAR2(100),
    resultado VARCHAR2(200),
    costo NUMBER(10,2) NOT NULL,
    descripcion VARCHAR2(1000),
    CONSTRAINT fk_cirugia_paciente FOREIGN KEY(id_paciente)
        REFERENCES PACIENTE(id_paciente),
    CONSTRAINT fk_cirugia_veterinario FOREIGN KEY(id_veterinario)
        REFERENCES VETERINARIO(id_veterinario),
    CONSTRAINT chk_costo_cirugia CHECK(costo >= 0)
);

CREATE TABLE INTERNACION(
    id_internacion NUMBER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    id_paciente NUMBER NOT NULL,
    id_veterinario NUMBER NOT NULL,
    id_cita NUMBER,
    fecha_ingreso DATE DEFAULT SYSDATE NOT NULL,
    fecha_egreso DATE,
    estado VARCHAR2(20) DEFAULT 'ACTIVO' NOT NULL,
    costo_dia NUMBER(10,2) NOT NULL,
    CONSTRAINT fk_intern_paciente FOREIGN KEY(id_paciente)
        REFERENCES PACIENTE(id_paciente),
    CONSTRAINT fk_intern_veterinario FOREIGN KEY(id_veterinario)
        REFERENCES VETERINARIO(id_veterinario),
    CONSTRAINT fk_intern_cita FOREIGN KEY(id_cita)
        REFERENCES CITA(id_cita),
    CONSTRAINT chk_estado_intern CHECK(estado IN ('ACTIVO','EGRESADO')),
    CONSTRAINT chk_costo_dia CHECK(costo_dia >= 0),
    CONSTRAINT chk_fechas_intern CHECK(
        fecha_egreso IS NULL OR fecha_egreso >= fecha_ingreso
    )
);

CREATE TABLE EXAMEN_LAB(
    id_examen NUMBER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    id_consulta NUMBER NOT NULL,
    tipo_examen VARCHAR2(100) NOT NULL,
    fecha_solicitud DATE DEFAULT SYSDATE NOT NULL,
    fecha_resultado DATE,
    resultados VARCHAR2(2000),
    costo NUMBER(10,2) NOT NULL,
    CONSTRAINT fk_examen_consulta FOREIGN KEY(id_consulta)
        REFERENCES CONSULTA(id_consulta),
    CONSTRAINT chk_costo_examen CHECK(costo >= 0)
);

CREATE TABLE FACTURA(
    id_factura NUMBER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    id_propietario NUMBER NOT NULL,
    fecha DATE DEFAULT SYSDATE NOT NULL,
    subtotal NUMBER(12,2) DEFAULT 0 NOT NULL,
    impuestos NUMBER(12,2) DEFAULT 0 NOT NULL,
    total NUMBER(12,2) DEFAULT 0 NOT NULL,
    estado_pago VARCHAR2(20) DEFAULT 'PENDIENTE' NOT NULL,
    metodo_pago VARCHAR2(30),
    CONSTRAINT fk_factura_propietario FOREIGN KEY(id_propietario)
        REFERENCES PROPIETARIO(id_propietario),
    CONSTRAINT chk_estado_pago CHECK(
        estado_pago IN ('PENDIENTE','PAGADA','ANULADA')
    ),
    CONSTRAINT chk_metodo_pago CHECK(
        metodo_pago IN ('EFECTIVO','TARJETA','TRANSFERENCIA') OR metodo_pago IS NULL
    ),
    CONSTRAINT chk_subtotal CHECK(subtotal >= 0),
    CONSTRAINT chk_impuestos CHECK(impuestos >= 0),
    CONSTRAINT chk_total CHECK(total >= 0)
);

CREATE TABLE DETALLE_FACTURA(
    id_detalle NUMBER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    id_factura NUMBER NOT NULL,
    concepto VARCHAR2(200) NOT NULL,
    cantidad NUMBER(4) DEFAULT 1 NOT NULL,
    precio_unitario NUMBER(10,2) NOT NULL,
    subtotal NUMBER(12,2) NOT NULL,
    CONSTRAINT fk_detalle_factura FOREIGN KEY(id_factura)
        REFERENCES FACTURA(id_factura) ON DELETE CASCADE,
    CONSTRAINT chk_cantidad_det CHECK(cantidad > 0),
    CONSTRAINT chk_precio_det CHECK(precio_unitario >= 0),
    CONSTRAINT chk_subtotal_det CHECK(subtotal >= 0)
);