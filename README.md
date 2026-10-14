# SECU - Logic Stack - ML
SECU (Sistema de Evaluación de Convocatorias UTEC)


## Descripción General
Este proyecto consiste en la implementación de clases en Java a partir de un conjunto de requerimientos funcionales y no funcionales. Se busca representar adecuadamente las entidades del sistema y sus relaciones. 

El proyecto tiene como objetivo cumplir con lo solicitado en la Etapa 3 del caso de estudio (Diseño Inicial), incluyendo el modelo de clases y el modelo Entidad-Relación. Además, la entrega incluye el archivo del diagrama de clases generado en PlantUML.

## Integrantes del Equipo

|       Nombre y Apellido       | Cédula de Identidad   | Rol en el Equipo                           |
| ----------------------------- | --------------------- |--------------------------------------------|
| Cristian Amaro                | 5.650.820-2           | Evaluador                                  |
| Camila Pacheco                | 5.062.361-8           | Secretaria y Coordinadora de Base de Datos |
| Juan Ignacio Coronel Pintos   | 5.126.941-3           | Responsanle de testeo                      |
| Mathías Daóiz Pérez Recuero   | 5.407.349-9           | Responsable de Repositorio                 |

## Tutores

|   Nombre y Apellido  | Área           |
| -------------------- | -------------- |
| Flavia Amaral        | Proyecto       |
| Mauricio Novo        | Base de Datos  |
| Juan Maccio          | Programacion   |

## Herramientas utilizadas
Java 25

Maven

IntelliJ IDEA

PostgreSQL 17

Docker / Docker Compose

Git / GitLab

## Pasos para instalacion del proyecto

1. Instalar:

Java JDK 25

Git

Docker Desktop

IntelliJ IDEA

2. Clonar el repositorio, para esto abrimos la terminal y vamos a donde queremos guardar el proyecto:

Por ejemplo: cd C:\Users\TuUsuario\Desktop

Clonamos el repositorio: git clone https://git.utec.edu.uy/cristian.amaro/proyectodesarrollogrupo1melo 

Entramos a la carpeta: cd ProyectoDesarrolloGrupo1Melo.

3. Abrir IntelliJ, seleccionamos file, luego open y seleccionamos la carpeta ProyectoDesarrolloGrupo1Melo.

4. Levantar Postgres, para esto desde la carpera ProyectoDesarollo... ejecutamos:

docker compose up -d

Y verificamos que el contenedor se este ejecuando con:

docker ps

Debaria mostrarnos:

proyecto-postgres

0.0.0.0:5433->5432/tcp

5. Luego de esto ya podriamso trabajar en el proyecto

## Estructura del proyecto

```text
src/
└── main/
    └── java/
        ├── Datos/
        │   ├── Postulacion.java
        │   └── Postulante.java
        │
        ├── DAO/
        │   ├── ExisteUsuario.java
        │   └── RolUsuario.java
        │
        ├── Persistencia/
        │   └── Conexion.java
        │
        ├── LogicaDeNegocio/
        │   └── IngresarUsuario.java
        │
        └── Presentacion/
            └── Main.java

database/
└── init.sql
```




# Anexo Bajar repositorio
Para copiara el proyecto seguiremos lso siguientes pasos:
1) Abrimos la terminal(cmd) y nos dirigimois a donde queremos copiar el proyecto. Para esto podemos abrir una carpeta, copiamos su direccion y luego en el cmd escribimos "cd direccion del archivo".
2) Luego que estamos en el directorio (en mi caso C:\Users\pablo\IdeaProjects) ejecutamos el comando "git clone https://git.utec.edu.uy/cristian.amaro/proyectodesarrollogrupo1melo".
3) Luego de clonar el repositorio entramos al mismo con "cd ProyectoDesarrolloGrupo1Melo"(recomiendo escribir "cd pr" y luego precionar tab para que el nombre se autocomplete).
4) Cuando estamos dentro de la carpeta ejecutamos "git checkout -b su-nombre"(por ejemplo: git checkout -b cristian).
5)Ya estaria listo para programar.
