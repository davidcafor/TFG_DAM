class InventarioList {
  int id;
  int idProducto;

  InventarioList({
    required this.id,
    required this.idProducto,
  });

  factory InventarioList.fromJson(Map<String, dynamic> json) =>
      InventarioList(
        id: json["Id"],
        idProducto: json["IdProducto"],
      );
}