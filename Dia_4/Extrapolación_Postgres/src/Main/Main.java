/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package Main;

import Conexion_BD.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Main {

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("Seleccione una operación:");
            System.out.println("1. Mostrar datos de las tablas");
            System.out.println("2. Insertar datos en las tablas");
            System.out.println("3. Salir");
            System.out.print("Ingrese el número de la opción: ");
            
            int choice = scanner.nextInt();
            scanner.nextLine(); 
            
            switch (choice) {
                case 1 -> showTables();
                case 2 -> insertIntoTables();
                case 3 -> {
                    System.out.println("Saliendo...");
                    return;
                }
                default -> System.out.println("Opción inválida. Intente de nuevo.");
            }
        }
    }

    private static void showTables() {
        System.out.println("Seleccione la tabla para mostrar:");
        System.out.println("1. Cliente");
        System.out.println("2. Vehículo");
        System.out.println("3. Historial de Transacciones");
        System.out.println("4. Empleado");
        System.out.println("5. Info Departamento");
        System.out.println("6. Cliente Potencial");
        System.out.println("7. Historial de Servicio");
        System.out.println("8. Proveedor");
        System.out.println("9. Pieza");
        System.out.println("10. Servicio Pieza");
        System.out.print("Ingrese el número de la opción: ");
        
        int tableChoice = scanner.nextInt();
        scanner.nextLine(); 

        String sql = "";
        switch (tableChoice) {
            case 1:
                sql = "SELECT * FROM Clientes_ventas";
                break;
            case 2:
                sql = "SELECT * FROM Vehiculo";
                break;
            case 3:
                sql = "SELECT * FROM historial_transaccion";
                break;
            case 4:
                sql = "SELECT * FROM Empleado";
                break;
            case 5:
                sql = "SELECT * FROM info_departamento";
                break;
            case 6:
                sql = "SELECT * FROM Clientes_potenciales";
                break;
            case 7:
                sql = "SELECT * FROM Historial_servicios";
                break;
            case 8:
                sql = "SELECT * FROM Proveedor_piezas";
                break;
            case 9:
                sql = "SELECT * FROM pieza";
                break;
            case 10:
                sql = "SELECT * FROM servicio_pieza";
                break;
            default:
                System.out.println("Opción inválida.");
                return;
        }

        try (Connection connection = Conexion.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {

            while (resultSet.next()) {
                System.out.println(resultSet.getString(1) + "\t" +
                                   resultSet.getString(2) + "\t" +
                                   resultSet.getString(3) + "\t" +
                                   resultSet.getString(4));
            }
        } catch (SQLException e) {
            System.out.println("Error al ejecutar la consulta: " + e.getMessage());
        }
    }

    private static void insertIntoTables() {
        System.out.println("Seleccione la tabla para insertar datos:");
        System.out.println("1. Cliente");
        System.out.println("2. Vehículo");
        System.out.println("3. Historial de Transacciones");
        System.out.println("4. Empleado");
        System.out.println("5. Info Departamento");
        System.out.println("6. Cliente Potencial");
        System.out.println("7. Historial de Servicio");
        System.out.println("8. Proveedor");
        System.out.println("9. Pieza");
        System.out.println("10. Servicio Pieza");
        System.out.print("Ingrese el número de la opción: ");
        
        int tableChoice = scanner.nextInt();
        scanner.nextLine(); 
        
        switch (tableChoice) {
            case 1:
                insertCliente();
                break;
            case 2:
                insertVehiculo();
                break;
            case 3:
                insertHistorialTransaccion();
                break;
            case 4:
                insertEmpleado();
                break;
            case 5:
                insertInfoDepartamento();
                break;
            case 6:
                insertClientePotencial();
                break;
            case 7:
                insertHistorialServicio();
                break;
            case 8:
                insertProveedor();
                break;
            case 9:
                insertPieza();
                break;
            case 10:
                insertServicioPieza();
                break;
            default:
                System.out.println("Opción inválida.");
        }
    }

    private static void insertCliente() {
        System.out.print("Ingrese nombre: ");
        String nombre = scanner.nextLine();
        System.out.print("Ingrese identificación: ");
        int identificacion = scanner.nextInt();
        System.out.print("Ingrese número: ");
        int numero = scanner.nextInt();
        scanner.nextLine(); // Limpiar el buffer del scanner

        String sql = "INSERT INTO cliente (nombre, identificacion, numero) VALUES (?, ?, ?)";

        try (Connection connection = Conexion.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, nombre);
            pstmt.setInt(2, identificacion);
            pstmt.setInt(3, numero);
            pstmt.executeUpdate();
            System.out.println("Cliente insertado correctamente.");
        } catch (SQLException e) {
            System.out.println("Error al insertar cliente: " + e.getMessage());
        }
    }

    private static void insertVehiculo() {
        System.out.print("Ingrese marca: ");
        String marca = scanner.nextLine();
        System.out.print("Ingrese modelo: ");
        String modelo = scanner.nextLine();
        System.out.print("Ingrese año: ");
        String anho = scanner.nextLine();
        System.out.print("Ingrese precio: ");
        double precio = scanner.nextDouble();
        scanner.nextLine(); // Limpiar el buffer del scanner
        System.out.print("Ingrese estado (Nuevo/Usado): ");
        String estado = scanner.nextLine();

        String sql = "INSERT INTO vehiculo (marca, modelo, anho, precio, estado) VALUES (?, ?, ?, ?, ?)";

        try (Connection connection = Conexion.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, marca);
            pstmt.setString(2, modelo);
            pstmt.setString(3, anho);
            pstmt.setDouble(4, precio);
            pstmt.setString(5, estado);
            pstmt.executeUpdate();
            System.out.println("Vehículo insertado correctamente.");
        } catch (SQLException e) {
            System.out.println("Error al insertar vehículo: " + e.getMessage());
        }
    }

    private static void insertHistorialTransaccion() {
        System.out.print("Ingrese ID de cliente: ");
        int cliente = scanner.nextInt();
        System.out.print("Ingrese código de vehículo: ");
        int vehiculo = scanner.nextInt();
        scanner.nextLine(); // Limpiar el buffer del scanner
        System.out.print("Ingrese descripción: ");
        String descripcion = scanner.nextLine();

        String sql = "INSERT INTO historial_transaccion (cliente, vehiculo, descripcion) VALUES (?, ?, ?)";

        try (Connection connection = Conexion.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, cliente);
            pstmt.setInt(2, vehiculo);
            pstmt.setString(3, descripcion);
            pstmt.executeUpdate();
            System.out.println("Historial de transacción insertado correctamente.");
        } catch (SQLException e) {
            System.out.println("Error al insertar historial de transacción: " + e.getMessage());
        }
    }

    private static void insertEmpleado() {
        System.out.print("Ingrese nombre: ");
        String nombre = scanner.nextLine();
        System.out.print("Ingrese identificación: ");
        int identificacion = scanner.nextInt();
        System.out.print("Ingrese número: ");
        int numero = scanner.nextInt();
        scanner.nextLine(); // Limpiar el buffer del scanner
        System.out.print("Ingrese rol: ");
        String rol = scanner.nextLine();
        System.out.print("Ingrese fecha de contratación (YYYY-MM-DD): ");
        String fechaContratacion = scanner.nextLine();

        String sql = "INSERT INTO empleado (nombre, identificacion, numero, rol, fecha_contratacion) VALUES (?, ?, ?, ?, ?)";

        try (Connection connection = Conexion.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, nombre);
            pstmt.setInt(2, identificacion);
            pstmt.setInt(3, numero);
            pstmt.setString(4, rol);
            pstmt.setString(5, fechaContratacion);
            pstmt.executeUpdate();
            System.out.println("Empleado insertado correctamente.");
        } catch (SQLException e) {
            System.out.println("Error al insertar empleado: " + e.getMessage());
        }
    }

    private static void insertInfoDepartamento() {
        System.out.print("Ingrese rendimiento personal: ");
        String rendimientoPersonal = scanner.nextLine();
        System.out.print("Ingrese comisiones generadas: ");
        String comisionesGeneradas = scanner.nextLine();
        System.out.print("Ingrese ventas realizadas: ");
        int ventasRealizadas = scanner.nextInt();
        scanner.nextLine(); // Limpiar el buffer del scanner

        String sql = "INSERT INTO info_departamento (rendimiento_personal, comisiones_generadas, ventas_realizadas) VALUES (?, ?, ?)";

        try (Connection connection = Conexion.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, rendimientoPersonal);
            pstmt.setString(2, comisionesGeneradas);
            pstmt.setInt(3, ventasRealizadas);
            pstmt.executeUpdate();
            System.out.println("Información de departamento insertada correctamente.");
        } catch (SQLException e) {
            System.out.println("Error al insertar información de departamento: " + e.getMessage());
        }
    }

    private static void insertClientePotencial() {
        System.out.print("Ingrese ID de cliente: ");
        int cliente = scanner.nextInt();
        System.out.print("Ingrese código de vehículo: ");
        int vehiculo = scanner.nextInt();
        scanner.nextLine(); // Limpiar el buffer del scanner
        System.out.print("Ingrese descripción: ");
        String descripcion = scanner.nextLine();

        String sql = "INSERT INTO cliente_potencial (cliente, vehiculo, descripcion) VALUES (?, ?, ?)";

        try (Connection connection = Conexion.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, cliente);
            pstmt.setInt(2, vehiculo);
            pstmt.setString(3, descripcion);
            pstmt.executeUpdate();
            System.out.println("Cliente potencial insertado correctamente.");
        } catch (SQLException e) {
            System.out.println("Error al insertar cliente potencial: " + e.getMessage());
        }
    }

    private static void insertHistorialServicio() {
        System.out.print("Ingrese código de vehículo: ");
        int vehiculo = scanner.nextInt();
        System.out.print("Ingrese ID de empleado: ");
        int empleado = scanner.nextInt();
        scanner.nextLine(); // Limpiar el buffer del scanner
        System.out.print("Ingrese descripción: ");
        String descripcion = scanner.nextLine();
        System.out.print("Ingrese fecha (YYYY-MM-DD): ");
        String fecha = scanner.nextLine();

        String sql = "INSERT INTO historial_servicio (vehiculo, descripcion, empleado, fecha) VALUES (?, ?, ?, ?)";

        try (Connection connection = Conexion.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, vehiculo);
            pstmt.setString(2, descripcion);
            pstmt.setInt(3, empleado);
            pstmt.setString(4, fecha);
            pstmt.executeUpdate();
            System.out.println("Historial de servicio insertado correctamente.");
        } catch (SQLException e) {
            System.out.println("Error al insertar historial de servicio: " + e.getMessage());
        }
    }

    private static void insertProveedor() {
        System.out.print("Ingrese nombre: ");
        String nombre = scanner.nextLine();
        System.out.print("Ingrese identificación: ");
        int identificacion = scanner.nextInt();
        System.out.print("Ingrese número: ");
        int numero = scanner.nextInt();
        scanner.nextLine(); // Limpiar el buffer del scanner

        String sql = "INSERT INTO proveedor (nombre, identificacion, numero) VALUES (?, ?, ?)";

        try (Connection connection = Conexion.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, nombre);
            pstmt.setInt(2, identificacion);
            pstmt.setInt(3, numero);
            pstmt.executeUpdate();
            System.out.println("Proveedor insertado correctamente.");
        } catch (SQLException e) {
            System.out.println("Error al insertar proveedor: " + e.getMessage());
        }
    }

    private static void insertPieza() {
        System.out.print("Ingrese nombre de la pieza: ");
        String nombre = scanner.nextLine();
        System.out.print("Ingrese ID de proveedor: ");
        int proveedor = scanner.nextInt();
        scanner.nextLine(); // Limpiar el buffer del scanner

        String sql = "INSERT INTO pieza (nombre, proveedor) VALUES (?, ?)";

        try (Connection connection = Conexion.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, nombre);
            pstmt.setInt(2, proveedor);
            pstmt.executeUpdate();
            System.out.println("Pieza insertada correctamente.");
        } catch (SQLException e) {
            System.out.println("Error al insertar pieza: " + e.getMessage());
        }
    }

    private static void insertServicioPieza() {
        System.out.print("Ingrese código de servicio: ");
        int servicio = scanner.nextInt();
        System.out.print("Ingrese ID de pieza: ");
        int pieza = scanner.nextInt();
        scanner.nextLine(); // Limpiar el buffer del scanner

        String sql = "INSERT INTO servicio_pieza (servicio, pieza) VALUES (?, ?)";

        try (Connection connection = Conexion.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, servicio);
            pstmt.setInt(2, pieza);
            pstmt.executeUpdate();
            System.out.println("Servicio pieza insertado correctamente.");
        } catch (SQLException e) {
            System.out.println("Error al insertar servicio pieza: " + e.getMessage());
        }
    }
}

    
