CREATE TABLE TipoDNI (
    tipoDNI varchar(255),
    idCandidato int
);
CREATE TABLE Consultor (
    idConsultor int NOT NULL AUTO_INCREMENT,
    contraseña int,
    usuario varchar(255)
);
CREATE TABLE Candidato (
    idConsultor int,
    idCandidato int NOT NULL AUTO_INCREMENT,
    clave int,
    nombre varchar(255),
    nacionalidad varchar(255),
    escolaridad varchar(255),
    dni int,
    fechaNacimiento date,
    email nvarchar(255),
    eliminado boolean
);
CREATE TABLE Estado (estado int, idCuestionario int);
CREATE TABLE Cuestionario (
    idCuestionario int NOT NULL AUTO_INCREMENT,
    idEvaluación int,
    idPuesto int,
    idCandidatoRealiza int,
    idCandidatoRealizo int,
    clave int,
    fechaInicio date,
    ultimoIngreso date,
    nroIntresos int,
    eliminado boolean
);
CREATE TABLE Bloque (
    idBloque int NOT NULL AUTO_INCREMENT,
    idCuestionario int,
    nroPreguntas int
);
CREATE TABLE PreguntaCuestionario (
    idPregunta int NOT NULL AUTO_INCREMENT,
    idFactor int,
    idBloque int,
    descripcion varchar(255)
);
CREATE TABLE OpciónCuestionario (
    idOpcion int NOT NULL AUTO_INCREMENT,
    idPreguntaElige int,
    idPreguntaEligen int,
    descripcion varchar(255),
    ponderación int
);
CREATE TABLE Cuestionario_Competencia (idComp int, idCuestionario int);
CREATE TABLE CompetenciaCuestionario (
    idComp int NOT NULL AUTO_INCREMENT,
    nombreComp varchar(255),
    ponderaciónNecesaria int
);
CREATE TABLE FactorCuestionario (
    idFactor int NOT NULL AUTO_INCREMENT,
    idComp int,
    nombreFactor varchar(255)
);
CREATE TABLE Evaluación (
    idEvaluación int NOT NULL AUTO_INCREMENT,
    idPuesto int,
    fecha date
);
CREATE TABLE Puesto (
    idPuesto int NOT NULL AUTO_INCREMENT,
    idEmpresa int,
    nombrePuesto varchar(255),
    descripción varchar(255),
    eliminado boolean
);
CREATE TABLE Empresa (
    idEmpresa int NOT NULL AUTO_INCREMENT,
    nombre varchar(255)
);
CREATE TABLE Puesto_Competencia (
    idPuesto int,
    idComp int,
    ponderaciónNecesaria int
);
CREATE TABLE Competencia (
    idComp int NOT NULL AUTO_INCREMENT,
    nombreComp varchar(255),
    descripción varchar(255),
    eliminado boolean
);
CREATE TABLE Factor (
    idFactor int NOT NULL AUTO_INCREMENT,
    idComp int,
    nombreFactor varchar(255),
    descripción varchar(255),
    eliminado boolean
);
CREATE TABLE Pregunta (
    idPregunta int NOT NULL AUTO_INCREMENT,
    idFactor int,
    idOpciónRt int,
    pregunta varchar(255),
    eliminado boolean
);
CREATE TABLE Pregunta_Opción (
    idPregunta int,
    idOpción int,
    ponderación int
);
CREATE TABLE Opcion (
    idOpción int NOT NULL AUTO_INCREMENT,
    idOpciónRt int,
    nombre varchar(255),
    descripción varchar(255),
    eliminado boolean
);
CREATE TABLE OpcionRespuesta (
    idOpcionRt int NOT NULL AUTO_INCREMENT,
    nombre varchar(255),
    descripción varchar(255),
    eliminado boolean
);
ALTER TABLE Bloque
ADD CONSTRAINT PK_Bloque PRIMARY KEY (idBloque);
ALTER TABLE Puesto_Competencia
ADD CONSTRAINT PK_Puesto_Competencia PRIMARY KEY (idPuesto, idComp);
ALTER TABLE OpciónCuestionario
ADD CONSTRAINT PK_OpciónCuestionario PRIMARY KEY (idOpcion);
ALTER TABLE Pregunta
ADD CONSTRAINT PK_Pregunta PRIMARY KEY (idPregunta);
ALTER TABLE Puesto
ADD CONSTRAINT PK_Puesto PRIMARY KEY (idPuesto);
ALTER TABLE Opcion
ADD CONSTRAINT PK_Opcion PRIMARY KEY (idOpción);
ALTER TABLE Pregunta_Opción
ADD CONSTRAINT PK_Pregunta_Opción PRIMARY KEY (idPregunta, idOpción);
ALTER TABLE Evaluación
ADD CONSTRAINT PK_Evaluación PRIMARY KEY (idEvaluación);
ALTER TABLE CompetenciaCuestionario
ADD CONSTRAINT PK_CompetenciaCuestionario PRIMARY KEY (idComp);
ALTER TABLE Competencia
ADD CONSTRAINT PK_Competencia PRIMARY KEY (idComp);
ALTER TABLE Cuestionario_Competencia
ADD CONSTRAINT PK_Cuestionario_Competencia PRIMARY KEY (idCuestionario, idComp);
ALTER TABLE OpcionRespuesta
ADD CONSTRAINT PK_OpcionRespuesta PRIMARY KEY (idOpcionRt);
ALTER TABLE Cuestionario
ADD CONSTRAINT PK_Cuestionario PRIMARY KEY (idCuestionario);
ALTER TABLE Empresa
ADD CONSTRAINT PK_Empresa PRIMARY KEY (idEmpresa);
ALTER TABLE PreguntaCuestionario
ADD CONSTRAINT PK_PreguntaCuestionario PRIMARY KEY (idPregunta);
ALTER TABLE Factor
ADD CONSTRAINT PK_Factor PRIMARY KEY (idFactor);
ALTER TABLE FactorCuestionario
ADD CONSTRAINT PK_idFactor PRIMARY KEY (idFactor);
ALTER TABLE TipoDNI
ADD CONSTRAINT PK_TipoDNI PRIMARY KEY (tipoDNI, idCandidato);
ALTER TABLE Consultor
ADD CONSTRAINT PK_Consultor PRIMARY KEY (idConsultor);
ALTER TABLE Candidato
ADD CONSTRAINT PK_Candidato PRIMARY KEY (idCandidato);
ALTER TABLE Estado
ADD CONSTRAINT PK_TipoDNI PRIMARY KEY (estado, idCuestionario);
ALTER TABLE Candidato
ADD FOREIGN KEY (idConsultor) REFERENCES Consultor(idConsultor);
ALTER TABLE Estado
ADD FOREIGN KEY (idCuestionario) REFERENCES Cuestionario(idCuestionario);
ALTER TABLE Cuestionario
ADD FOREIGN KEY (idEvaluación) REFERENCES Evaluación(idEvaluación);
ALTER TABLE Cuestionario
ADD FOREIGN KEY (idPuesto) REFERENCES Puesto(idPuesto);
ALTER TABLE Cuestionario
ADD FOREIGN KEY (idCandidatoRealiza) REFERENCES Candidato(idCandidato);
ALTER TABLE Cuestionario
ADD FOREIGN KEY (idCandidatoRealizo) REFERENCES Candidato(idCandidato);
ALTER TABLE Bloque
ADD FOREIGN KEY (idCuestionario) REFERENCES Cuestionario(idCuestionario);
ALTER TABLE PreguntaCuestionario
ADD FOREIGN KEY (idFactor) REFERENCES FactorCuestionario(idFactor);
ALTER TABLE PreguntaCuestionario
ADD FOREIGN KEY (idBloque) REFERENCES Bloque(idBloque);
ALTER TABLE OpciónCuestionario
ADD FOREIGN KEY (idPreguntaElige) REFERENCES PreguntaCuestionario(idPregunta);
ALTER TABLE OpciónCuestionario
ADD FOREIGN KEY (idPreguntaEligen) REFERENCES PreguntaCuestionario(idPregunta);
ALTER TABLE Cuestionario_Competencia
ADD FOREIGN KEY (idCuestionario) REFERENCES Cuestionario(idCuestionario);
ALTER TABLE Cuestionario_Competencia
ADD FOREIGN KEY (idComp) REFERENCES CompetenciaCuestionario(idComp);
ALTER TABLE FactorCuestionario
ADD FOREIGN KEY (idComp) REFERENCES CompetenciaCuestionario(idComp);
ALTER TABLE Evaluación
ADD FOREIGN KEY (idPuesto) REFERENCES Puesto(idPuesto);
ALTER TABLE Puesto
ADD FOREIGN KEY (idEmpresa) REFERENCES Empresa(idEmpresa);
ALTER TABLE Puesto_Competencia
ADD FOREIGN KEY (idPuesto) REFERENCES Puesto(idPuesto);
ALTER TABLE Puesto_Competencia
ADD FOREIGN KEY (idComp) REFERENCES Competencia(idComp);
ALTER TABLE Factor
ADD FOREIGN KEY (idComp) REFERENCES Competencia(idComp);
ALTER TABLE Pregunta
ADD FOREIGN KEY (idFactor) REFERENCES Factor(idFactor);
ALTER TABLE Pregunta
ADD FOREIGN KEY (idOpciónRt) REFERENCES OpcionRespuesta(idOpcionRt);
ALTER TABLE Pregunta_Opción
ADD FOREIGN KEY (idPregunta) REFERENCES Pregunta(idPregunta);
ALTER TABLE Pregunta_Opción
ADD FOREIGN KEY (idOpción) REFERENCES Opcion(idOpción);
ALTER TABLE TipoDNI
ADD FOREIGN KEY (idCandidato) REFERENCES Candidato(idCandidato);
ALTER TABLE Opcion
ADD FOREIGN KEY (idOpciónRt) REFERENCES OpcionRespuesta(idOpcionRt);