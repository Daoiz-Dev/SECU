CREATE SCHEMA IF NOT EXISTS "PROYECTO";

SET search_path TO "PROYECTO";

CREATE TABLE Paises (
                        id_pais SERIAL PRIMARY KEY,
                        nombre_pais VARCHAR(50) UNIQUE NOT NULL
);
CREATE TABLE Nacionalidades (
                                id_nacionalidades SERIAL PRIMARY KEY,
                                nombre_nacionalidad VARCHAR(50) NOT NULL UNIQUE,
                                id_pais INT NOT NULL,
                                FOREIGN KEY (id_pais) REFERENCES Paises (id_pais)
);
CREATE TABLE Sedes (
                       id_sede SERIAL PRIMARY KEY,
                       nombre_sede VARCHAR(100) UNIQUE NOT NULL
);
CREATE TABLE Roles (
                       id_rol SERIAL PRIMARY KEY,
                       nombre_rol VARCHAR(50) UNIQUE NOT NULL
);
CREATE TABLE Carreras (
                          id_carrera SERIAL PRIMARY KEY,
                          nombre_carrera VARCHAR(50) NOT NULL UNIQUE
);
CREATE TABLE Instituciones (
                               id_institucion SERIAL PRIMARY KEY,
                               nombre_institucion VARCHAR(50) UNIQUE NOT NULL
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
CREATE TABLE Depto_Academico (
                                 id_depto_academico SERIAL PRIMARY KEY,
                                 nombre_dep VARCHAR(100) UNIQUE
);
CREATE TABLE Seccion_Rubrica (
                                 id_seccion SERIAL PRIMARY KEY,
                                 nombre_seccion VARCHAR(100) NOT NULL,
                                 peso DECIMAL NOT NULL CONSTRAINT chk_peso_seccion CHECK (peso >= 0 AND peso <= 100)
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
CREATE TABLE Tipo_Beca (
                           Id_Tipo_Beca SERIAL PRIMARY KEY,
                           tipo_beca VARCHAR(100) UNIQUE NOT NULL
);
CREATE TABLE Tipo_Convocatoria (
                                   Id_Tipo_Convocatoria SERIAL PRIMARY KEY,
                                   tipo_convocatoria VARCHAR(100) UNIQUE NOT NULL
);
CREATE TABLE Estado_Convocatoria (
                                     id_estado_convocatoria SERIAL PRIMARY KEY NOT NULL,
                                     nombre_estado VARCHAR(50) NOT NULL UNIQUE
);
CREATE TABLE Convocatorias (
                               id_convocatoria SERIAL PRIMARY KEY,
                               nombre_convocatoria VARCHAR(100) UNIQUE NOT NULL,
                               id_tipo_beca INT NOT NULL,
                               id_tipo_convocatoria INT NOT NULL,
                               id_estado_convocatoria INT NOT NULL DEFAULT 1, -- Clave Foránea
                               anio_convocatoria INTEGER NOT NULL,
                               descripcion TEXT,
                               cantidad_becas INTEGER NOT NULL,
                               documento_requerido VARCHAR(100),
                               fecha_apertura DATE NOT NULL,
                               fecha_cierre DATE NOT NULL,
                               CONSTRAINT fk_convocatoria_tipo_beca FOREIGN KEY (id_tipo_beca) REFERENCES Tipo_Beca(id_tipo_beca),
                               CONSTRAINT fk_convocatoria_tipo_convocatoria FOREIGN KEY (id_tipo_convocatoria) REFERENCES Tipo_Convocatoria(id_tipo_convocatoria),
                               CONSTRAINT fk_convocatoria_estado FOREIGN KEY (id_estado_convocatoria) REFERENCES Estado_Convocatoria(id_estado_convocatoria)
);
CREATE TABLE Usuarios (
                          id_usuario SERIAL PRIMARY KEY,
                          correo VARCHAR(50) UNIQUE NOT NULL,
                          ci VARCHAR(20) UNIQUE NOT NULL,
                          nombre VARCHAR(50) NOT NULL,
                          apellido VARCHAR(50) NOT NULL,
                          id_domicilio INT NOT NULL,
                          contrasenia_hash VARCHAR(65),
                          estado_usuario BOOLEAN,
                          id_nacionalidad INT NOT NULL,
                          FOREIGN KEY (id_nacionalidad) REFERENCES Nacionalidades (id_nacionalidades),
                          FOREIGN KEY (id_domicilio) REFERENCES Domicilios (id_domicilio)
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
CREATE TABLE Postulantes (
                             id_postulante SERIAL PRIMARY KEY,
                             documento VARCHAR(20) UNIQUE NOT NULL,
                             correo VARCHAR(50) UNIQUE NOT NULL,
                             nombre VARCHAR(50) NOT NULL,
                             apellido VARCHAR(50) NOT NULL,
                             es_docente_utec BOOLEAN NOT NULL,
                             act_laboral_fuera BOOLEAN NOT NULL,
                             id_domicilio INTEGER NOT NULL,
                             id_institucion INT NOT NULL,
                             id_formacion INT,
                             FOREIGN KEY (id_domicilio) REFERENCES Domicilios (id_domicilio),
                             FOREIGN KEY (id_institucion) REFERENCES Instituciones(id_institucion),
                             FOREIGN KEY (id_formacion) REFERENCES Formaciones (id_formacion)
);
CREATE TABLE Postulaciones (
                               id_postulacion SERIAL PRIMARY KEY,
                               id_postulante INTEGER NOT NULL,
                               nombre_proyecto VARCHAR(100) NOT NULL,
                               linea_investigacion VARCHAR(50) NOT NULL,
                               fecha_solicitud DATE NOT NULL DEFAULT CURRENT_DATE,
                               cantidad_meses INTEGER NOT NULL CONSTRAINT chk_cantidad_meses CHECK (cantidad_meses > 0),
                               grupo_investigacion VARCHAR(50) NOT NULL,
                               otro_grupo_investigacion VARCHAR(50),
                               observacion_ces TEXT,
                               orden_pre_calculado INTEGER,
                               puntaje_consolidado NUMERIC,
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
CREATE TABLE Evaluaciones (
                              id_evaluacion SERIAL PRIMARY KEY,
                              id_postulacion INT NOT NULL,
                              id_evaluador INT NOT NULL,
                              puntaje_total NUMERIC,
                              comentario_evaluador TEXT,
                              estado VARCHAR(30) DEFAULT 'Pendiente' CONSTRAINT chk_estado_evaluacion      CHECK (estado IN ('Pendiente', 'En Proceso', 'Completada')),
                              CONSTRAINT fk_evaluaciones_postulaciones FOREIGN KEY (id_postulacion)       REFERENCES Postulaciones(id_postulacion), CONSTRAINT fk_evaluaciones_evaluadores  FOREIGN KEY (id_evaluador) REFERENCES Evaluadores_Externos(id_evaluador)
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
CREATE TABLE Usuario_Telefonos (
                                   id_usuario INT NOT NULL,
                                   num_telefono VARCHAR(20) NOT NULL,
                                   tipo_telefono VARCHAR(20) DEFAULT 'Celular', -- Ej: Celular, Trabajo, Casa
                                   PRIMARY KEY (id_usuario, num_telefono),
                                   FOREIGN KEY (id_usuario) REFERENCES Usuarios(id_usuario) ON DELETE CASCADE
);
CREATE TABLE Postulante_Celulares (
                                      id_postulante INT NOT NULL,
                                      num_celular VARCHAR(20) NOT NULL,
                                      PRIMARY KEY (id_postulante, num_celular),
                                      FOREIGN KEY (id_postulante) REFERENCES Postulantes(id_postulante) ON DELETE CASCADE
);
CREATE TABLE Auditoria_Cambios (
                                   id_auditoria SERIAL PRIMARY KEY,
                                   tabla_afectada VARCHAR(50) NOT NULL,
                                   operacion VARCHAR(10) NOT NULL CONSTRAINT chk_operacion_auditoria CHECK (operacion IN ('INSERT', 'UPDATE', 'DELETE')),
                                   usuario_db VARCHAR(50) NOT NULL DEFAULT CURRENT_USER,
                                   id_usuario_app INTEGER,
                                   fecha_hora TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP,
                                   datos_anteriores JSONB,
                                   datos_nuevos JSONB
);
INSERT INTO Estado_Convocatoria (nombre_estado)
VALUES ('Publicada'), ('En Evaluación'), ('Finalizada');
CREATE OR REPLACE FUNCTION fn_auditar_cambios()
RETURNS TRIGGER AS $$
DECLARE
v_usuario_app INTEGER;
BEGIN
    -- Lee el usuario que informó la aplicación (NULL si nadie lo informó)
    v_usuario_app := NULLIF(current_setting('secu.id_usuario', true), '')::INTEGER;
    IF TG_OP = 'INSERT' THEN
        INSERT INTO Auditoria_Cambios (tabla_afectada, operacion, id_usuario_app, datos_nuevos)
        VALUES (TG_TABLE_NAME, TG_OP, v_usuario_app, to_jsonb(NEW) - 'contrasenia_hash');
RETURN NEW;
ELSIF TG_OP = 'UPDATE' THEN
        INSERT INTO Auditoria_Cambios (tabla_afectada, operacion, id_usuario_app, datos_anteriores, datos_nuevos)
        VALUES (TG_TABLE_NAME, TG_OP, v_usuario_app,
                to_jsonb(OLD) - 'contrasenia_hash',
                to_jsonb(NEW) - 'contrasenia_hash');
RETURN NEW;
ELSIF TG_OP = 'DELETE' THEN
        INSERT INTO Auditoria_Cambios (tabla_afectada, operacion, id_usuario_app, datos_anteriores)
        VALUES (TG_TABLE_NAME, TG_OP, v_usuario_app, to_jsonb(OLD) - 'contrasenia_hash');
RETURN OLD;
END IF;
RETURN NULL;
END;
$$ LANGUAGE plpgsql;
-- Se crea un trigger por cada tabla a auditar:
CREATE TRIGGER trg_auditoria_convocatorias
    AFTER INSERT OR UPDATE OR DELETE ON Convocatorias
    FOR EACH ROW EXECUTE FUNCTION fn_auditar_cambios();
CREATE TRIGGER trg_auditoria_postulaciones
    AFTER INSERT OR UPDATE OR DELETE ON Postulaciones
    FOR EACH ROW EXECUTE FUNCTION fn_auditar_cambios();
CREATE TRIGGER trg_auditoria_evaluaciones
    AFTER INSERT OR UPDATE OR DELETE ON Evaluaciones
    FOR EACH ROW EXECUTE FUNCTION fn_auditar_cambios();
CREATE TRIGGER trg_auditoria_detalles_evaluaciones
    AFTER INSERT OR UPDATE OR DELETE ON Detalles_Evaluaciones
    FOR EACH ROW EXECUTE FUNCTION fn_auditar_cambios();
CREATE TRIGGER trg_auditoria_asignar_evaluadores
    AFTER INSERT OR UPDATE OR DELETE ON Asignar_Evaluadores
    FOR EACH ROW EXECUTE FUNCTION fn_auditar_cambios();
CREATE TRIGGER trg_auditoria_usuarios
    AFTER INSERT OR UPDATE OR DELETE ON Usuarios
    FOR EACH ROW EXECUTE FUNCTION fn_auditar_cambios();
CREATE TRIGGER trg_auditoria_administradores
    AFTER INSERT OR UPDATE OR DELETE ON Administradores
    FOR EACH ROW EXECUTE FUNCTION fn_auditar_cambios();
CREATE TRIGGER trg_auditoria_evaluadores_externos
    AFTER INSERT OR UPDATE OR DELETE ON Evaluadores_Externos
    FOR EACH ROW EXECUTE FUNCTION fn_auditar_cambios();
CREATE TRIGGER trg_auditoria_postulantes
    AFTER INSERT OR UPDATE OR DELETE ON Postulantes
    FOR EACH ROW EXECUTE FUNCTION fn_auditar_cambios();
CREATE TRIGGER trg_auditoria_doc_adjunto
    AFTER INSERT OR UPDATE OR DELETE ON Doc_Adjunto
    FOR EACH ROW EXECUTE FUNCTION fn_auditar_cambios();
CREATE TRIGGER trg_auditoria_tutor_postulacion
    AFTER INSERT OR UPDATE OR DELETE ON Tutor_Postulacion
    FOR EACH ROW EXECUTE FUNCTION fn_auditar_cambios();