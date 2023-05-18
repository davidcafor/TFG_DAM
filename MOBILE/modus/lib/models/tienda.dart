import 'package:flutter/material.dart';

class Tienda {
  List<ListTiendas> list;
  PageInfo pageInfo;

  Tienda({
    required this.list,
    required this.pageInfo,
  });

  factory Tienda.fromJson(Map<String, dynamic> json) => Tienda(
        list: List<ListTiendas>.from(
            json["list"].map((x) => ListTiendas.fromJson(x))),
        pageInfo: PageInfo.fromJson(json["pageInfo"]),
      );
}

class ListTiendas {
  int id;
  String nombre;
  String direccion;
  String ciudad;
  String telefono;
  List<InventarioList> inventarioList;

  ListTiendas({
    required this.id,
    required this.nombre,
    required this.direccion,
    required this.ciudad,
    required this.telefono,
    required this.inventarioList,
  });

  factory ListTiendas.fromJson(Map<String, dynamic> json) => ListTiendas(
        id: json["Id"],
        nombre: json["Nombre"],
        direccion: json["Direccion"],
        ciudad: json["Ciudad"],
        telefono: json["Telefono"],
        inventarioList: List<InventarioList>.from(
            json["Inventario List"].map((x) => InventarioList.fromJson(x))),
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

  Map<String, dynamic> toJson() => {
        "Id": id,
        "IdProducto": idProducto,
      };
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
