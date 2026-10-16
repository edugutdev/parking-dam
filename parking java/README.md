# ACTIVIDAD 2 — Del diagrama al código: PARKING DAM-PRO en Java

**Módulo:** Programación · **Curso:** 1.º DAM  
**Repositorio:** el **mismo** `parking-dam` que creaste en la Actividad 1  
**Herramientas:** IntelliJ IDEA, Java 21, Maven, Git y GitHub  
**Calificación:** 10 puntos · **Entrega:** enlace al proyecto Java dentro del repositorio público en Moodle

> **No crees otro repositorio**. Esta actividad es independiente para evaluarla, pero **continúa el trabajo anterior**: `parking-dam` ya contiene tu README y los seis diagramas. Tu reto es convertir ese diseño en un programa Java, comprobarlo, documentarlo y publicarlo con un historial progresivo.

## 1. Objetivos y enunciado

Crearás un programa de consola llamado **PARKING DAM-PRO** que pida el nombre de usuario, muestre un menú hasta elegir salir y permita registrar vehículos, calcular tarifas y mostrar un resumen de la sesión. La tarifa es de **1,50 € por hora**.

Debes trabajar con **Java 21**, `Scanner`, `String`, `int`, `float`, `do-while`, `while`, `for`, `switch` (o `if-else`), contadores, acumuladores y **métodos definidos por ti**. No necesitas arrays, listas ni clases adicionales. Puedes suponer que las entradas numéricas tienen formato entero correcto: aquí se evalúa la **validación del rango**, no los errores por introducir letras.

**Opción 1:** preguntar cuántos vehículos registrar (entero positivo); para cada vehículo, pedir matrícula y horas positivas. Incrementar el total de vehículos y sumar las horas registradas. **Opción 2:** pedir horas positivas, calcular `horas × 1.50f`, incrementar el contador de cálculos y acumular el importe. No cambiar contadores ni horas de la opción 1. **Opción 3:** mostrar nombre, total de vehículos, total de horas registradas, cantidad de cálculos de tarifa e importe acumulado. **Opción 4:** mostrar despedida y salir. Una opción diferente mostrará un aviso y volverá a presentar el menú.

## 2. Paso 1 — Recuperar y sincronizar TU repositorio

Abre Git Bash o Bash y entra en tu repositorio de la Actividad 1:

```bash
cd ~/git/parking-dam
git status
git remote -v
git branch --show-current
git fetch origin
git log --oneline HEAD..origin/main
git pull --ff-only origin main
git status
```

Si es **otro ordenador** y todavía no tienes `parking-dam`, clónalo en `~/git/` mediante `git clone https://github.com/TU_USUARIO/parking-dam.git`. Comprueba que conservas `README.md`, `diagramas/parking.drawio` y tus seis imágenes. Si hay cambios locales pendientes o falla el `pull`, no borres nada: consulta al profesor.

**Explicación para tu documentación:** «Antes de programar comprobé [qué comandos], observé [estado real] y confirmé que el repositorio remoto era [URL de tu cuenta]».

## 3. Paso 2 — Crear un proyecto Maven dentro de `parking-dam`

1. Abre **IntelliJ IDEA** y selecciona **File → New → Project**.
2. Selecciona **Java**, **Maven**, **JDK 21**, nombre **`parking-java`**.
3. Sitúa el proyecto **dentro de la carpeta que ya clonaste**: su ruta final debe ser `~/git/parking-dam/parking-java` (no `parking-java/parking-java`). Si te solicita datos Maven, introduce `groupId: es.dam` y `artifactId: parking-java`.
4. Si aparece **Create Git repository**, **desactívalo**: el único Git es el de `parking-dam`.
5. Conserva el `pom.xml` generado. Crea `Main.java` en `parking java/src` o en un paquete (`es.dam`) si así lo has configurado; en ese caso, conserva `package es.dam;`.
6. Crea también `parking-java/README.md` (es el README técnico del programa) desde IntelliJ IDEA.

Estructura final esperada:

```text
parking-dam/
├── README.md                  (presenta ambas actividades)
├── .gitignore
├── diagramas/
│   ├── parking.drawio
│   ├── 01_inicio.png
│   ├── 02_menu.png
│   ├── 03_registro.png
│   ├── 04_tarifa.png
│   ├── 05_resumen.png
│   └── 06_salida.png
└── parking-java/
    ├── README.md              (documenta la aplicación Java)
    ├── pom.xml
    └── src/
        └── main/
            └── java/
                └── Main.java   (o es/dam/Main.java si hay paquete)
```

### 3.1. Evitar archivos generados en Git

En la raíz de `parking-dam`, crea o completa `.gitignore` sin borrar reglas existentes:

```gitignore
**/target/
*.class
parking java/.idea/
*.iml
```

**No** publiques carpetas `target/`, ficheros `.class`, configuraciones personales ni credenciales.

### 3.2. Primer commit de la Actividad 2

Desde la **raíz de `parking-dam`**:

```bash
git status
git add .gitignore parking-java/pom.xml parking-java/README.md
git diff --cached --stat
git commit -m "build: crear proyecto Maven parking-java"
git push origin main
```

Si IntelliJ generó otros ficheros Maven necesarios, revísalos y añádelos de forma selectiva. No generes un commit vacío.

## 4. Paso 3 — Antes de programar, consultar los diagramas

Abre los **seis diagramas que construiste en la Actividad 1**. En el README de Java escribe un apartado `## 2. Relación entre el diagrama y el programa`: explica qué diagrama representa el menú, dónde aparece el `for`, dónde se usan validaciones repetitivas y cómo se sale del menú. **Corrige** en diagrams.net cualquier conexión incorrecta que detectes y publica la corrección en el **mismo repositorio**.

## 5. Paso 4 — Dividir el programa en métodos

`main` debe **coordinar el menú**; no debe contener todo el programa. Como mínimo, crea los métodos siguientes (sus tipos y parámetros pueden adaptarse si conservas el comportamiento):

| Método sugerido | Para qué sirve |
|---|---|
| `mostrarMenu()` | Mostrar las cuatro opciones de la aplicación |
| `leerEnteroPositivo(Scanner teclado, String mensaje)` | Pedir un entero y repetir con `while` mientras no sea positivo; devolverlo |
| `registrarVehiculos(...)` | Registrar una cantidad conocida de vehículos con `for`, sumar vehículos y horas |
| `calcularTarifa(int horas, float tarifaHora)` | Devolver `horas × tarifaHora` como `float` |
| `mostrarResumen(...)` | Mostrar nombre, contadores y acumuladores |

**Advertencia didáctica importante:** Java pasa los argumentos primitivos **por valor**. Si `registrarVehiculos` recibe un `int totalHoras` y lo incrementa dentro, **no cambia automáticamente** la variable de `main`. Resuelve esto de manera coherente con tus conocimientos: por ejemplo, haz que el método devuelva las horas de la operación y actualiza los acumuladores en `main`, o define métodos auxiliares con valores de retorno. No necesitas arrays, listas ni atributos globales para esta tarea.

### 5.1. Plantilla orientativa SIN solución completa

```java
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);
        float tarifaHora = 1.50f;
        int totalVehiculos = 0;
        int totalHorasRegistradas = 0;
        int totalCalculosTarifa = 0;
        float importeTotalCalculado = 0f;

        // 1. Leer el nombre del usuario.
        // 2. Repetir el menú con do-while.
        // 3. Resolver cada opción mediante switch.
        // 4. Llamar a tus métodos y actualizar acumuladores.
        // 5. Cerrar Scanner al salir.

        teclado.close();
    }

    // Escribe aquí tus métodos.
}
```

Conserva `package ...;` si has creado la clase en un paquete. Si alternas `nextInt()` y `nextLine()`, gestiona el salto de línea pendiente.

## 6. Paso 5 — Implementación por fases con commits

**Solo debes confirmar cada fase cuando hayas ejecutado y comprobado su funcionalidad.** Usa siempre `git status`, prepara selectivamente lo que has modificado y revisa `git diff --cached` antes del commit.

### Fase A — Menú que se repite

Pide el nombre, usa `mostrarMenu()` y `do-while` para mantener el menú, resuelve las opciones con `switch` (pueden mostrar mensajes provisionales) y termina al elegir 4. Una opción inválida debe mostrar un error.

```bash
git status
git add parking-java/src/
git diff --cached
git commit -m "feat: implementar menu principal con do-while"
git push origin main
```

**Comprobación:** elegir 9 muestra un aviso y vuelve al menú; elegir 4 sale sin volver a mostrarlo.

### Fase B — Registro de vehículos con `for`

Pregunta cantidad positiva, solicita matrícula y horas por cada vehículo y utiliza un `for` para registrar la cantidad solicitada. Implementa o reutiliza `leerEnteroPositivo`. Al volver al menú, los acumuladores se conservan. Documenta cómo pasan los datos entre los métodos y `main`.

```bash
git status
git add parking-java/src/
git diff --cached
git commit -m "feat: registrar vehiculos con for y validar datos"
git push origin main
```

**Comprobación:** registrar dos vehículos de 2 y 3 horas debe sumar **2 vehículos y 5 horas**.

### Fase C — Calcular tarifa con un método

Implementa `calcularTarifa(int horas, float tarifaHora)` y llama al método desde la opción 2. Usa un `while` para asegurar horas positivas. Actualiza **solo** `totalCalculosTarifa` e `importeTotalCalculado`.

```bash
git status
git add parking-java/src/
git diff --cached
git commit -m "feat: calcular tarifas y acumular importes"
git push origin main
```

**Comprobación:** 2 horas = **3,00 €**; 5 horas = **7,50 €**. Los vehículos y las horas registradas no cambian al usar la opción 2.

### Fase D — Resumen y revisión de métodos

Implementa `mostrarResumen(...)`. Verifica que los datos acumulados sobreviven a varias vueltas del menú y que `main` coordina los métodos en lugar de duplicar lógica.

```bash
git status
git add parking-java/src/
git diff --cached
git commit -m "feat: mostrar resumen mediante metodos"
git push origin main
```

## 7. Paso 6 — Pruebas obligatorias del programa

Ejecuta `Main.main()` desde IntelliJ IDEA. Si Maven está disponible, puedes compilar desde `parking-java/` con `mvn compile` (o mediante la acción Maven de IntelliJ). Realiza las **12 pruebas** y escribe el resultado real en tu README:

| Nº | Qué tienes que probar | Resultado esperado |
|---|---|---|
| 1 | Escribir un nombre | El menú saluda o identifica correctamente al usuario |
| 2 | Elegir 9 | Error y regreso al menú |
| 3 | Introducir 0 como cantidad de vehículos, después 2 | Rechaza 0 y acepta 2 |
| 4 | Registrar 2 vehículos de 2 y 3 horas | Vehículos = 2; horas registradas = 5 |
| 5 | En registro, introducir horas -2, después 4 | Repite la pregunta hasta un valor positivo |
| 6 | Calcular tarifa de 2 horas | 3,00 € |
| 7 | Calcular tarifa de 5 horas | 7,50 € |
| 8 | En tarifa, introducir horas 0 y luego 3 | Rechaza 0; importe 4,50 € |
| 9 | Calcular dos tarifas positivas | Contador de cálculos = 2 |
| 10 | Registrar vehículos y luego calcular una tarifa | La opción 2 no altera vehículos ni horas registradas |
| 11 | Elegir opción 3 después de varias operaciones | Muestra todos los acumulados correctos |
| 12 | Elegir opción 4 | Despedida y fin sin repetir menú |

**Atención:** las pruebas se realizan en sesiones independientes **cuando sea necesario**, o debes indicar en qué orden las has ejecutado. No inventes resultados. El formato de decimales puede variar según cómo se muestren los `float`.

## 8. Paso 7 — Documentar con Markdown el programa

En `parking-java/README.md`, crea **un título** con `#`, secciones con `##` y subsecciones con `###`. Usa listas, tabla de pruebas, fragmentos de código con acentos graves y un enlace al README principal.

### 8.1. Plantilla orientativa para el README técnico

````markdown
# PARKING DAM-PRO — Implementación Java

## 1. Objetivo

He desarrollado un programa de consola que [explica qué hace].
El proyecto continúa el diseño de la Actividad 1, publicado en
[README principal](../README.md).

## 2. Relación entre diagramas y código

### 2.1. Menú

El diagrama del menú representa [explica]. En Java lo he implementado
mediante [estructura utilizada] porque [motivo].

### 2.2. Registro de vehículos

He utilizado un `for` para [explica qué se repite y cuántas veces].

### 2.3. Validaciones y cálculo de tarifas

El `while` se utiliza para [explica qué dato se valida].
La tarifa se calcula [indica la fórmula y un ejemplo real].

## 3. Métodos implementados

### 3.1. mostrarMenu()

Este método [qué hace, qué recibe y qué devuelve].

### 3.2. leerEnteroPositivo(...)

Este método [parámetros, validación y resultado].

### 3.3. registrarVehiculos(...)

Este método [qué hace el for y cómo actualizo los acumuladores].

### 3.4. calcularTarifa(...)

Este método [parámetros, valor devuelto y ejemplo].

### 3.5. mostrarResumen(...)

Este método [explica qué información muestra].

## 4. Pruebas realizadas

| Prueba | Datos introducidos | Resultado esperado | Resultado observado |
|---|---|---|---|
| 1 | [datos reales] | [lo previsto] | [lo que ocurrió] |

[Completa las 12 pruebas. Explica cualquier fallo y cómo lo corregiste.]

## 5. Historial de desarrollo con Git

[Indica al menos tres commits relevantes y qué modificaste en cada uno.]

## 6. Dificultades y soluciones

[Describe una dificultad auténtica y los pasos que seguiste para resolverla.]

## 7. Conclusión

[Explica qué aprendiste sobre métodos, bucles y Git.]
````

**No copies los corchetes literalmente**: sustituye cada orientación por tus propios resultados. En el README principal añade un apartado `## Implementación Java` con un enlace relativo: `[Ver el programa Java](parking-java/)` y explica brevemente qué ha cambiado desde la Actividad 1.

## 9. Paso 8 — Commit final y publicación

Cuando termines las pruebas y el README, desde la raíz de `parking-dam`:

```bash
git status
git add README.md parking-java/README.md parking-java/src/ parking-java/pom.xml
git diff --cached --stat
git commit -m "docs: documentar metodos y pruebas del parking"
git push origin main
git status
git log --oneline -8
```

Incluye cambios en `diagramas/` o `.gitignore` **solo si los has modificado**, tras revisarlos. En GitHub, comprueba que aparecen las dos actividades en el **mismo historial** de commits y que todos los enlaces Markdown funcionan.

## 10. Si termina el tiempo de clase

Publica los avances aunque el programa no esté terminado: desde la raíz, revisa el estado, añade selectivamente los archivos creados y ejecuta un commit como `wip: guardar avance de implementacion Java`, seguido de `git push origin main`. No hagas un commit si no hay cambios. Al retomar desde casa, empieza con `git status`, `git fetch origin` y `git pull --ff-only origin main`. **Nunca uses `push --force` ni borres trabajo para solucionar un error de sincronización.**

## 11. Entrega en Moodle

Publica el enlace **directo a la carpeta** del proyecto Java en tu propio repositorio:

`https://github.com/TU_USUARIO/parking-dam/tree/main/parking-java`

El profesor revisará el proyecto Maven (`pom.xml`), el código Java, las pruebas documentadas, el README técnico, la referencia a los diagramas de la Actividad 1 y los commits progresivos. **No** es necesario crear un segundo repositorio.

## 12. Rúbrica de la Actividad 2 (10 puntos)

| Criterio | Puntos |
|---|---:|
| Reutilizar repositorio y estructura Maven Java 21 correctos | 1,0 |
| Menú, `do-while`, opciones válidas e inválidas | 1,0 |
| Registro de vehículos con `for`, contador y acumulador de horas | 1,5 |
| Validación de cantidades y horas mediante `while` | 1,0 |
| Tarifa correcta y acumuladores independientes | 1,0 |
| Métodos: reparto de responsabilidades, parámetros y valores devueltos | 2,0 |
| Resumen correcto y estado conservado en toda la sesión | 0,5 |
| Doce pruebas con resultados reales y README explicativo | 1,5 |
| Commits progresivos, publicación y enlaces entre ambas actividades | 0,5 |
| **TOTAL** | **10,0** |

**Ampliación voluntaria, sin sustituir los requisitos anteriores:** diferencia tarifa de coche (1,50 €/h) y de moto (0,75 €/h). Documenta la ampliación y publícala en un commit propio: `feat: aplicar tarifas por tipo de vehiculo`.
