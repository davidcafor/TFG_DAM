import 'models.dart';

class Producto {
  int id;
  String nombre;
  String descripcion;
  String precio;
  String? imagen;
  List<InventarioList> inventarioList;

  Producto({
    required this.id,
    required this.nombre,
    required this.descripcion,
    required this.precio,
    this.imagen,
    required this.inventarioList,
  });

  factory Producto.fromJson(Map<String, dynamic> json) => Producto(
    id: json["Id"],
    nombre: json["Nombre"],
    descripcion: json["Descripcion"],
    precio: json["Precio"],
    imagen: json["Imagen"],
    inventarioList: List<InventarioList>.from(json["Inventario List"].map((x) => InventarioList.fromJson(x))),
  );
}
