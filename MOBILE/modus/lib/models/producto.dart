class Producto {
  List<ListElement> list;
  PageInfo pageInfo;

  Producto({
    required this.list,
    required this.pageInfo,
  });

  factory Producto.fromJson(Map<String, dynamic> json) => Producto(
    list: List<ListElement>.from(json["list"].map((x) => ListElement.fromJson(x))),
    pageInfo: PageInfo.fromJson(json["pageInfo"]),
  );
}

class ListElement {
  int id;
  String nombre;
  String descripcion;
  String precio;
  String? imagen;
  List<InventarioList> inventarioList;

  ListElement({
    required this.id,
    required this.nombre,
    required this.descripcion,
    required this.precio,
    this.imagen,
    required this.inventarioList,
  });

  factory ListElement.fromJson(Map<String, dynamic> json) => ListElement(
    id: json["Id"],
    nombre: json["Nombre"],
    descripcion: json["Descripcion"],
    precio: json["Precio"],
    imagen: json["Imagen"],
    inventarioList: List<InventarioList>.from(json["Inventario List"].map((x) => InventarioList.fromJson(x))),
  );
}

class InventarioList {
  int id;
  int idProducto;

  InventarioList({
    required this.id,
    required this.idProducto,
  });

  factory InventarioList.fromJson(Map<String, dynamic> json) => InventarioList(
    id: json["Id"],
    idProducto: json["IdProducto"],
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