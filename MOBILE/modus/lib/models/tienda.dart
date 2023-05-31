import 'models.dart';

class Tienda {
  int id;
  String nombre;
  String direccion;
  String ciudad;
  String telefono;
  List<InventarioList> inventarioList;

  Tienda({
    required this.id,
    required this.nombre,
    required this.direccion,
    required this.ciudad,
    required this.telefono,
    required this.inventarioList,
  });

  factory Tienda.fromJson(Map<String, dynamic> json) => Tienda(
        id: json["Id"],
        nombre: json["Nombre"],
        direccion: json["Direccion"],
        ciudad: json["Ciudad"],
        telefono: json["Telefono"],
        inventarioList: List<InventarioList>.from(
            json["Inventario List"].map((x) => InventarioList.fromJson(x))),
      );
}
