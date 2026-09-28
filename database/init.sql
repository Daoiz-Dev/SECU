CREATE SCHEMA IF NOT EXISTS "PROYECTO";

SET search_path TO "PROYECTO";

CREATE TABLE Paises (
    id_pais SERIAL PRIMARY KEY,
    nombre_pais VARCHAR(50) UNIQUE
);


CREATE TABLE Seccion_Rubrica (
    id_seccion SERIAL PRIMARY KEY,
    nombre_seccion VARCHAR(100) NOT NULL,
    peso DECIMAL NOT NULL
);

CREATE TABLE Criterios (
    id_criterio SERIAL PRIMARY KEY,
    nombre_criterio VARCHAR(80) NOT NULL,
    id_seccion INTEGER NOT NULL,
    FOREIGN KEY (id_seccion) REFERENCES Seccion_Rubrica (id_seccion)
);

CREATE TABLE Tutores (
    id_tutor SERIAL PRIMARY KEY,
    correo_utec VARCHAR(100) NOT NULL,
    nombre TEXT NOT NULL,
    apellido TEXT NOT NULL,
    departamento_area VARCHAR(100) NOT NULL
);

CREATE TABLE Telefonos (
    id_telefonos INT PRIMARY KEY,
    num_telefono VARCHAR(50) UNIQUE
);

CREATE TABLE Sedes (
    id_sede SERIAL PRIMARY KEY,
    nombre_sede VARCHAR(100) UNIQUE
);

CREATE TABLE Nacionalidades (
    id_nacionalidades SERIAL PRIMARY KEY,
    nombre_nacionalidad VARCHAR(50) NOT NULL UNIQUE,
    id_pais INT NOT NULL,
    FOREIGN KEY (id_pais) REFERENCES Paises (id_pais)
);

CREATE TABLE Convocatorias (
    id_convocatoria SERIAL PRIMARY KEY,
    nombre_convocatoria VARCHAR(100) UNIQUE NOT NULL,
    tipo_beca VARCHAR(100) NOT NULL,
    anio_convocatoria INTEGER NOT NULL,
    descripcion TEXT,
    cantidad_becas INTEGER,
    documento_requerido VARCHAR(100),
    estado VARCHAR(30),
    fecha_apertura DATE,
    fecha_cierre DATE,
    tipo_convocatoria VARCHAR(100)
);

CREATE TABLE Carreras (
    id_carrera SERIAL PRIMARY KEY,
    nombre_carrera VARCHAR(50) NOT NULL UNIQUE
);

CREATE TABLE Depto_Academico (
    id_depto_academico SERIAL PRIMARY KEY,
    nombre_dep VARCHAR(100) UNIQUE
);

CREATE TABLE Domicilios (
    id_domicilio SERIAL PRIMARY KEY,
    calle VARCHAR(50) NOT NULL,
    numero_puerta INTEGER NOT NULL,
    apartamento VARCHAR(10),
    bis_domicilio VARCHAR(10),
    localidad VARCHAR(50) NOT NULL,
    departamento VARCHAR(50) NOT NULL,
    id_pais INTEGER,
    FOREIGN KEY (id_pais) REFERENCES Paises (id_pais)
);

CREATE TABLE Instituciones (
    id_institucion SERIAL PRIMARY KEY,
    nombre_institucion VARCHAR(50) UNIQUE
);

CREATE TABLE Posgrados (
    id_posgrado SERIAL PRIMARY KEY,
    nombre_posgrado VARCHAR(50) NOT NULL UNIQUE,
    id_institucion INT NOT NULL,
    CONSTRAINT fk_posgrados_instituciones FOREIGN KEY (id_institucion) REFERENCES Instituciones(id_institucion)
);

CREATE TABLE Formaciones (
    id_formacion SERIAL PRIMARY KEY,
    tipo_formacion VARCHAR(100) NOT NULL,
    id_posgrado INTEGER,
    id_carrera INTEGER NOT NULL,
    FOREIGN KEY (id_posgrado) REFERENCES Posgrados(id_posgrado),
    FOREIGN KEY (id_carrera) REFERENCES Carreras(id_carrera)
);

CREATE TABLE Postulantes (
    id_postulante SERIAL PRIMARY KEY,
    documento VARCHAR(20) UNIQUE NOT NULL,
    correo VARCHAR(50) UNIQUE NOT NULL,
    nombre VARCHAR(50) NOT NULL,
    apellido VARCHAR(50) NOT NULL,
    es_docente_utec BOOLEAN NOT NULL,
    act_laboral_fuera BOOLEAN NOT NULL,
    id_domicilio INTEGER NOT NULL,
    id_telefono INT NOT NULL,
    id_institucion INT NOT NULL,
    id_formacion INT,
    FOREIGN KEY (id_telefono) REFERENCES Telefonos (id_telefonos),
    FOREIGN KEY (id_domicilio) REFERENCES Domicilios (id_domicilio),
    FOREIGN KEY (id_institucion) REFERENCES Instituciones(id_institucion),
    FOREIGN KEY (id_formacion) REFERENCES Formaciones (id_formacion)
);

CREATE TABLE Postulaciones (
    id_postulacion SERIAL PRIMARY KEY,
    id_postulante INTEGER NOT NULL,
    nombre_proyecto VARCHAR(50) NOT NULL,
    linea_investigacion VARCHAR(50) NOT NULL,
    fecha_solicitud DATE NOT NULL,
    cantidad_meses INTEGER NOT NULL,
    grupo_investigacion VARCHAR(50) NOT NULL,
    otro_grupo_investigacion VARCHAR(50),
    observacion_ces TEXT,
    orden_pre_calculado INTEGER,
    puntaje_consolidado INTEGER,
    id_sede INTEGER NOT NULL,
    id_convocatoria INTEGER NOT NULL,
    id_depto_academico INTEGER NOT NULL,
    juicio_global TEXT,
    FOREIGN KEY (id_postulante) REFERENCES Postulantes(id_postulante),
    FOREIGN KEY (id_sede) REFERENCES Sedes(id_sede),
    FOREIGN KEY (id_convocatoria) REFERENCES Convocatorias(id_convocatoria),
    FOREIGN KEY (id_depto_academico) REFERENCES Depto_Academico(id_depto_academico)
);

CREATE TABLE Doc_Adjunto (
    id_documento_adjunto SERIAL PRIMARY KEY,
    id_postulacion INTEGER NOT NULL,
    nombre_documento VARCHAR(50) NOT NULL,
    ruta_archivo VARCHAR(100) NOT NULL,
    FOREIGN KEY (id_postulacion) REFERENCES Postulaciones(id_postulacion)
);


CREATE TABLE Usuarios (
    id_usuario SERIAL PRIMARY KEY,
    correo VARCHAR(50) UNIQUE NOT NULL,
    ci VARCHAR(20) UNIQUE NOT NULL,
    nombre VARCHAR(50) NOT NULL,
    apellido VARCHAR(50) NOT NULL,
    calle_direccion VARCHAR(100) NOT NULL,
    num_direccion INT NOT NULL,
    contraseña_hash VARCHAR(65),
    estado_usuario BOOLEAN,
    id_nacionalidad INT NOT NULL,
    id_telefono INTEGER NOT NULL,
    FOREIGN KEY (id_nacionalidad) REFERENCES Nacionalidades (id_nacionalidades),
    FOREIGN KEY (id_telefono) REFERENCES Telefonos (id_telefonos)
);

CREATE TABLE Roles (
    id_rol SERIAL PRIMARY KEY,
    nombre_rol VARCHAR(50) UNIQUE NOT NULL
);

CREATE TABLE Administradores (
    id_administrador SERIAL PRIMARY KEY,
    id_rol INTEGER NOT NULL,
    id_usuario INTEGER NOT NULL,
    FOREIGN KEY (id_rol) REFERENCES Roles(id_rol),
    FOREIGN KEY (id_usuario) REFERENCES Usuarios(id_usuario)
);

CREATE TABLE Evaluadores_Externos (
    id_evaluador SERIAL PRIMARY KEY,
    id_usuario INT NOT NULL,
    id_rol INT NOT NULL,
    FOREIGN KEY (id_usuario) REFERENCES Usuarios (id_usuario),
    FOREIGN KEY (id_rol) REFERENCES Roles (id_rol)
);

CREATE TABLE Evaluaciones (
    id_evaluacion SERIAL PRIMARY KEY,
    id_postulacion INT NOT NULL,
    id_evaluador INT NOT NULL,
    puntaje_total NUMERIC,
    comentario_evaluador TEXT,
    estado VARCHAR(30),
    CONSTRAINT fk_evaluaciones_postulaciones FOREIGN KEY (id_postulacion) REFERENCES Postulaciones(id_postulacion),
   CONSTRAINT fk_evaluaciones_evaluadores FOREIGN KEY (id_evaluador)     REFERENCES Evaluadores_Externos(id_evaluador)
);

CREATE TABLE Asignar_Evaluadores (
    id_evaluador INT NOT NULL,
    id_postulacion INT NOT NULL,
    PRIMARY KEY (id_evaluador, id_postulacion),
    FOREIGN KEY (id_evaluador) REFERENCES Evaluadores_Externos(id_evaluador),
    FOREIGN KEY (id_postulacion) REFERENCES Postulaciones(id_postulacion)
);

CREATE TABLE Detalles_Evaluaciones (
    id_evaluacion INT NOT NULL,
    id_criterio INT NOT NULL,
    puntaje FLOAT NOT NULL,
    PRIMARY KEY (id_evaluacion, id_criterio),
    FOREIGN KEY (id_evaluacion) REFERENCES Evaluaciones (id_evaluacion),
    FOREIGN KEY (id_criterio) REFERENCES Criterios (id_criterio)
);

CREATE TABLE Tutor_Postulacion (
    id_postulacion INT NOT NULL,
    id_tutor INT NOT NULL,
    es_cotutor BOOLEAN NOT NULL,
    PRIMARY KEY (id_postulacion, id_tutor),
    FOREIGN KEY (id_postulacion) REFERENCES Postulaciones (id_postulacion),
    FOREIGN KEY (id_tutor) REFERENCES Tutores(id_tutor)
);

CREATE TABLE tipo_beca (
id_tipo_beca SERIAL PRIMARY KEY,
tipo_beca VARCHAR(100) UNIQUE NOT NULL);

CREATE TABLE tipo_convocatoria (
id_convocatoria SERIAL PRIMARY KEY,
tipo_convocatoria VARCHAR(100) UNIQUE NOT NULL);