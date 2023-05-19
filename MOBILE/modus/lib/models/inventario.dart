class Inventario {
  int id;
  int cantidad;
  DateTime ultimaActualizacion;
  Productos productos;
  Tiendas tiendas;

  Inventario({
    required this.id,
    required this.cantidad,
    required this.ultimaActualizacion,
    required this.productos,
    required this.tiendas,
  });

  factory Inventario.fromJson(Map<String, dynamic> json) => Inventario(
    id: json["Id"],
    cantidad: json["Cantidad"],
    ultimaActualizacion: DateTime.parse(json["UltimaActualizacion"]),
    productos: Productos.fromJson(json["Productos"]),
    tiendas: Tiendas.fromJson(json["Tiendas"]),
  );
}

class Productos {
  int id;
  String nombre;

  Productos({
    required this.id,
    required this.nombre,
  });

  factory Productos.fromJson(Map<String, dynamic> json) => Productos(
    id: json["Id"],
    nombre: json["Nombre"],
  );
}

class Tiendas {
  int id;
  String nombre;

  Tiendas({
    required this.id,
    required this.nombre,
  });

  factory Tiendas.fromJson(Map<String, dynamic> json) => Tiendas(
    id: json["Id"],
    nombre: json["Nombre"],
  );
}