class Inventario {
  List<ListInventario> list;
  PageInfo pageInfo;

  Inventario({
    required this.list,
    required this.pageInfo,
  });


  factory Inventario.fromJson(Map<String, dynamic> json) => Inventario(
    list: List<ListInventario>.from(json["list"].map((x) => ListInventario.fromJson(x))),
    pageInfo: PageInfo.fromJson(json["pageInfo"]),
  );
}

class ListInventario {
  int id;
  int cantidad;
  DateTime ultimaActualizacion;
  Productos productos;
  Tiendas tiendas;

  ListInventario({
    required this.id,
    required this.cantidad,
    required this.ultimaActualizacion,
    required this.productos,
    required this.tiendas,
  });

  factory ListInventario.fromJson(Map<String, dynamic> json) => ListInventario(
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

class PageInfo {
  int totalRows;
  int page;
  int pageSize;
  bool isFirstPage;
  bool isLastPage;

  PageInfo({
    required this.totalRows,
    required this.page,
    required this.pageSize,
    required this.isFirstPage,
    required this.isLastPage,
  });

  factory PageInfo.fromJson(Map<String, dynamic> json) => PageInfo(
    totalRows: json["totalRows"],
    page: json["page"],
    pageSize: json["pageSize"],
    isFirstPage: json["isFirstPage"],
    isLastPage: json["isLastPage"],
  );
}