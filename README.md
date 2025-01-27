# Sistema de Reservas de Hotel

Este proyecto es una aplicación desarrollada en **Java** utilizando **JavaFX** para la gestión de reservas de hotel. Incluye una interfaz gráfica interactiva y atractiva, con un diseño estilizado a través de CSS.

## Características principales

- **Gestión de datos de clientes**: Permite registrar el nombre, DNI, dirección, ciudad y provincia del cliente.
- **Detalles de la reserva**: Incluye campos para fechas de llegada y salida, número de huéspedes, tipo de habitación, preferencias de fumador y régimen de alojamiento.
- **Resumen de reserva**: Muestra un resumen detallado de la reserva al confirmar.
- **Accesibilidad**: Uso de atajos de teclado y ToolTips para mejorar la experiencia del usuario.
- **Estilización**: Diseño moderno utilizando un archivo CSS personalizado.

## Estructura del proyecto

```
ReservaHotel/
├── src/                   # Código fuente
│   └── main/java          # Código Java del proyecto
├── estilo.css             # Archivo CSS para los estilos de la interfaz
├── pom.xml                # Archivo de configuración de Maven
└── README.md              # Documentación del proyecto
```

## Instalación y ejecución

1. **Clonar el repositorio**:
   ```bash
   git clone <URL_DEL_REPOSITORIO>
   ```

2. **Abrir el proyecto**:
   - Usa un IDE compatible como **IntelliJ IDEA** o **Eclipse**.

3. **Configurar dependencias**:
   - Asegúrate de tener **Java 8** o superior instalado.
   - Configura el archivo `pom.xml` para resolver las dependencias del proyecto (si aplica).

4. **Ejecutar el proyecto**:
   - Ejecuta la clase principal `ReservaHotel` desde el IDE.

## Funcionalidades principales

### Campos de entrada
- Nombre, DNI, dirección, ciudad y provincia.
- Selección de fechas de llegada y salida.
- Número de huéspedes (spinner configurable).
- Tipo de habitación (ComboBox con opciones como Doble, Suite, etc.).
- Preferencia de fumador.
- Régimen de alojamiento (ComboBox).

### Botones de acción
- **Confirmar Reserva**: Genera un resumen de la reserva.
- **Cancelar**: Cierra la aplicación.
- **Limpiar Campos**: Restaura los valores predeterminados de todos los campos.

### Resumen de reserva
- Se muestra en un área de texto no editable que resume todos los detalles ingresados por el usuario.

## Diseño de la interfaz

La interfaz está diseñada para ser intuitiva y visualmente atractiva, con los siguientes estilos definidos en el archivo `estilo.css`:

- Fondo en **verde claro**.
- Botones estilizados con colores vibrantes y efectos hover:
  - **Confirmar**: Verde.
  - **Cancelar**: Naranja.
  - **Limpiar**: Amarillo.
- Títulos destacados con un tamaño de fuente mayor y efectos de sombra.

### Ejemplo de estilos CSS utilizados

```css
/* Fondo de la interfaz */
.root {
    -fx-background-color: #98FB98;
    -fx-font-family: Calibri;
    -fx-font-size: 15;
    -fx-font-weight: bold;
}

/* Botones */
.boton-confirmar {
    -fx-background-color: #4CAF50;
    -fx-text-fill: white;
    -fx-padding: 10px 20px;
}
.boton-cancelar {
    -fx-background-color: #FF5722;
}
```

## Contribución

1. Realiza un fork del repositorio.
2. Crea una nueva rama para tu funcionalidad o corrección de errores.
3. Envía un Pull Request detallado explicando los cambios realizados.

## Licencia

Este proyecto está licenciado bajo la [MIT License](LICENSE).
